package org.obeonetwork.m2doc.sirius.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.impl.MImageImpl;
import org.obeonetwork.m2doc.sirius.providers.tables.DMTable;

/**
 * AQL Services for Sirius representations.
 * 
 * @author Romain Guider
 */
public class M2DocSiriusServices {

    /**
     * The {@link ExportFormat}.
     */
    private static final ExportFormat FORMAT = new ExportFormat(ExportFormat.ExportDocumentFormat.NONE,
            ImageFileFormat.JPG);

    /**
     * The Sirius {@link Session}.
     */
    private final Session session;

    /**
     * The {@link Set} of temporary {@link File}.
     */
    private final Set<File> tmpFiles = new LinkedHashSet<>();

    /**
     * Constructor.
     * 
     * @param session
     *            the Sirius {@link Session}
     */
    public M2DocSiriusServices(Session session) {
        this.session = session;
    }

    /**
     * Returns <code>true</code> if the arguments are not null, the eObject is in a session and there's a representation with the specified
     * name in it that is associated to the specified eObject. Returns <code>false</code> otherwise.
     * 
     * @param eObject
     *            any eObject that is in the session where to search
     * @param representationDescriptionName
     *            the name of the searched representation
     * @return <code>true</code> if there's a representation with the specified name in the eObject's associated session.
     */
    public boolean isRepresentationDescriptionName(EObject eObject, String representationDescriptionName) {
        final boolean result;

        if (representationDescriptionName != null) {
            result = SiriusRepresentationUtils
                    .getAssociatedRepresentationByDescriptionAndName(eObject, representationDescriptionName, session)
                    .size() > 0;
        } else {
            result = false;
        }

        return result;
    }

    /**
     * * Returns <code>true</code> if the arguments are not null, the eObject is in a session and there's a representation with the
     * specified
     * title in it. Returns <code>false</code> otherwise.
     * 
     * @param representationName
     *            the name of the searched representation.
     * @return <code>true</code> if there's a representation with the specified name in the session associated to the specified eObject.
     */
    public boolean isRepresentationName(String representationName) {
        final boolean result;

        if (representationName != null) {
            result = SiriusRepresentationUtils.getAssociatedRepresentationByName(representationName, session) != null;
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Gets the {@link MImage} from the given {@link DRepresentation}.
     * 
     * @param representation
     *            the {@link DRepresentation}
     * @return the {@link MImage} from the given {@link DRepresentation}
     * @throws SizeTooLargeException
     *             if the image is too large in memory
     * @throws IOException
     *             if the image can't be serialized
     */
    public MImage asImage(final DRepresentation representation) throws SizeTooLargeException, IOException {
        final MImage res;

        final File tmpFile = File.createTempFile(representation.getName(), ".jpg");
        tmpFiles.add(tmpFile);

        // Make sure to run the Sirius image export in the UI thread.
        Runnable exportDiagUnitOfWork = new Runnable() {
            @Override
            public void run() {
                try {
                    DialectUIManager.INSTANCE.export(representation, session, new Path(tmpFile.getAbsolutePath()),
                            FORMAT, new NullProgressMonitor());
                } catch (SizeTooLargeException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        if (Display.getDefault() != null) {
            Display.getDefault().syncExec(exportDiagUnitOfWork);
        } else {
            exportDiagUnitOfWork.run();
        }

        res = new MImageImpl(URIConverter.INSTANCE, URI.createFileURI(tmpFile.getAbsolutePath()));

        return res;
    }

    /**
     * Gets the {@link MTable} from the given {@link DTable}.
     * 
     * @param table
     *            the {@link DTable}
     * @return the {@link MTable} from the given {@link DTable}
     */
    public MTable asTable(DTable table) {
        return new DMTable(table);
    }

    /**
     * Gets the {@link List} of {@link MImage} for the given {@link EObject} and {@link RepresentationDescription#getName()
     * representation
     * description name}
     * .
     * 
     * @param eObj
     *            the {@link EObject}
     * @param descriptionName
     *            the {@link RepresentationDescription#getName() description name}
     * @return the {@link List} of {@link MImage} for the given {@link EObject} and {@link RepresentationDescription#getName()
     *         representation
     *         description
     *         name}
     * @throws SizeTooLargeException
     *             if the image is too large in memory
     * @throws IOException
     *             if the image can't be serialized
     */
    public List<MImage> asImageByRepresentationDescriptionName(EObject eObj, String descriptionName)
            throws SizeTooLargeException, IOException {
        final List<MImage> res = new ArrayList<>();

        final Collection<DRepresentationDescriptor> repDescs = DialectManager.INSTANCE
                .getRepresentationDescriptors(eObj, session);
        // Filter representations to keep only those in a selected viewpoint
        final Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

        for (DRepresentationDescriptor repDesc : repDescs) {
            boolean isDangling = new DRepresentationDescriptorQuery(repDesc).isDangling();
            if (!isDangling && repDesc.getDescription() instanceof DiagramDescription
                && descriptionName.equals(repDesc.getDescription().getName())
                && repDesc.getDescription().eContainer() instanceof Viewpoint) {
                Viewpoint vp = (Viewpoint) repDesc.getDescription().eContainer();
                if (selectedViewpoints.contains(vp)) {
                    res.add(asImage(repDesc.getRepresentation()));
                }
            }
        }

        return res;
    }

    /**
     * Gets the {@link List} of {@link MTable} for the given {@link EObject} and {@link RepresentationDescription#getName()
     * representation
     * description name}
     * .
     * 
     * @param eObj
     *            the {@link EObject}
     * @param descriptionName
     *            the {@link RepresentationDescription#getName() description name}
     * @return the {@link List} of {@link MImage} for the given {@link EObject} and {@link RepresentationDescription#getName()
     *         representation
     *         description
     *         name}
     * @throws SizeTooLargeException
     *             if the image is too large in memory
     * @throws IOException
     *             if the image can't be serialized
     */
    public List<MTable> asTableByRepresentationDescriptionName(EObject eObj, String descriptionName)
            throws SizeTooLargeException, IOException {
        final List<MTable> res = new ArrayList<>();

        final Collection<DRepresentationDescriptor> repDescs = DialectManager.INSTANCE
                .getRepresentationDescriptors(eObj, session);
        // Filter representations to keep only those in a selected viewpoint
        final Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

        for (DRepresentationDescriptor repDesc : repDescs) {
            boolean isDangling = new DRepresentationDescriptorQuery(repDesc).isDangling();
            if (!isDangling && repDesc.getDescription() instanceof TableDescription
                && descriptionName.equals(repDesc.getDescription().getName())
                && repDesc.getDescription().eContainer() instanceof Viewpoint) {
                Viewpoint vp = (Viewpoint) repDesc.getDescription().eContainer();
                if (selectedViewpoints.contains(vp)) {
                    res.add(asTable((DTable) repDesc.getRepresentation()));
                }
            }
        }

        return res;
    }

    /**
     * Gets the {@link MImage} for the given {@link DRepresentation#getName() representation name}.
     * 
     * @param representationName
     *            the {@link DRepresentation#getName() representation name}
     * @return the {@link MImage} for the given {@link EObject} and {@link DRepresentation#getName() representation name}
     * @throws SizeTooLargeException
     *             if the image is too large in memory
     * @throws IOException
     *             if the image can't be serialized
     */
    public MImage asImageByRepresentationName(String representationName) throws SizeTooLargeException, IOException {
        final MImage res;

        final DRepresentationDescriptor description = SiriusRepresentationUtils
                .getAssociatedRepresentationByName(representationName, session);
        if (description != null) {
            res = asImage(description.getRepresentation());
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the {@link MTable} for the given {@link DRepresentation#getName() representation name}.
     * 
     * @param representationName
     *            the {@link DRepresentation#getName() representation name}
     * @return the {@link MTable} for the given {@link EObject} and {@link DRepresentation#getName() representation name}
     */
    public MTable asTableByRepresentationName(String representationName) {
        final MTable res;

        final DRepresentationDescriptor description = SiriusRepresentationUtils
                .getAssociatedRepresentationByName(representationName, session);
        if (description != null && description.getRepresentation() instanceof DTable) {
            res = asTable((DTable) description.getRepresentation());
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Cleans generated files.
     */
    public void clean() {
        for (File tmpFile : tmpFiles) {
            tmpFile.delete();
        }
        tmpFiles.clear();
    }

}
