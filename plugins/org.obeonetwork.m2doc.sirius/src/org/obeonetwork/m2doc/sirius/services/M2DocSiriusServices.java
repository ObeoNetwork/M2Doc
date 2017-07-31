package org.obeonetwork.m2doc.sirius.services;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
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
import org.obeonetwork.m2doc.sirius.util.DTable2MTableConverter;

//@formatter:off
@ServiceProvider(
value = "Services available for Sirius"
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class M2DocSiriusServices {

    /**
     * Registry of cleaning jobs.
     * 
     * @author Romain Guider
     */
    private class CleaningJobRegistry {

        /**
         * The {@link Set} of jobs.
         */

        private Set<Runnable> jobs = new LinkedHashSet<Runnable>();

        /**
         * Registers a job on a key.
         * 
         * @param job
         *            the job
         */
        public void registerJob(Runnable job) {
            jobs.add(job);
        }

        /**
         * Clears all the jobs associated to the given key.
         * 
         * @param key
         *            the key
         */
        public void clean(Object key) {
            for (Runnable job : jobs) {
                try {
                    job.run();
                } finally {
                    jobs.clear();
                }
            }
        }

    }

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
     * The {@link CleaningJobRegistry}.
     */
    private final CleaningJobRegistry registry = new CleaningJobRegistry();

    /**
     * The {@link Set} of temporary {@link File}.
     */
    private final Set<File> tmpFiles = new LinkedHashSet<>();

    /**
     * Tells if we should {@link Session#close(org.eclipse.core.runtime.IProgressMonitor) close} the {@link #session}.
     */
    private boolean shouldCloseSession;

    /**
     * Constructor.
     * 
     * @param session
     *            the Sirius {@link Session}
     */
    public M2DocSiriusServices(Session session) {
        this.session = session;
        if (!session.isOpen()) {
            session.open(new NullProgressMonitor());
            shouldCloseSession = true;
        }
    }

    // @formatter:off
    @Documentation(
        value = "Returns <code>true</code> if the arguments are not null, the eObject is in a session and there's a representation description with the specified name in it that is associated to the specified eObject. Returns <code>false</code> otherwise.",
        params = {
            @Param(name = "eObject", value = "Any eObject that is in the session where to search"),
            @Param(name = "representationDescriptionName", value = "the name of the searched representation description"),
        },
        result = "<code>true</code> if there's a representation description with the specified name in the eObject's associated session",
        examples = {
            @Example(expression = "ePackage.isRepresentationDescriptionName('class diagram')", result = "True"),
        }
    )
    // @formatter:on
    public boolean isRepresentationDescriptionName(EObject eObject, String representationDescriptionName) {
        final boolean result;

        if (representationDescriptionName != null) {
            result = getAssociatedRepresentationByDescriptionAndName(eObject, representationDescriptionName).size() > 0;
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Retrieve all representations whose target is the specified EObject and description the given one.
     * 
     * @param targetRootObject
     *            Object which is the target of the representations.
     * @param representationId
     *            the diagram description from which we want to retrieve representations.
     * @return all representations whose target is the specified EObject
     */
    protected List<DRepresentationDescriptor> getAssociatedRepresentationByDescriptionAndName(EObject targetRootObject,
            String representationId) {
        return getAssociatedRepresentationByDescriptionAndName(targetRootObject, representationId, false);
    }

    /**
     * Retrieve all representations whose target is the specified EObject and description the given one. If no representation is
     * found and createIfAbsent is <code>true</code> the create the representation.
     * 
     * @param generation
     *            the generation configuration object this generation has been launched on.
     * @param targetRootObject
     *            Object which is the target of the representations.
     * @param representationId
     *            the description from which we want to retrieve representations.
     * @param createIfAbsent
     *            if <code>true</code> and the generation object is present, the representations are created on the fly and cleaned after
     *            doc generation.
     * @return all representations whose target is the specified EObject
     */
    protected List<DRepresentationDescriptor> getAssociatedRepresentationByDescriptionAndName(EObject targetRootObject,
            String representationId, boolean createIfAbsent) {
        List<DRepresentationDescriptor> result = new ArrayList<>();
        if (representationId != null && targetRootObject != null && session != null) {
            Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE
                    .getRepresentationDescriptors(targetRootObject, session);
            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentationDescriptor representation : representations) {
                boolean isDangling = new DRepresentationDescriptorQuery(representation).isDangling();
                if (!isDangling && representationId.equals(representation.getDescription().getName())
                    && representation.getDescription().eContainer() instanceof Viewpoint) {
                    Viewpoint vp = (Viewpoint) representation.getDescription().eContainer();
                    if (selectedViewpoints.contains(vp)) {
                        result.add(representation);
                    }
                }
            }
        }
        if (result.isEmpty() && createIfAbsent) {
            RepresentationDescription description = SiriusRepresentationUtils.findDescription(session,
                    representationId);
            session.getTransactionalEditingDomain().getCommandStack().execute(new CreateRepresentationCommand(session,
                    description, targetRootObject, representationId, new NullProgressMonitor()));
            for (DRepresentationDescriptor representation : DialectManager.INSTANCE
                    .getRepresentationDescriptors(targetRootObject, session)) {
                if (representation != null && representation.getDescription() == description) {
                    registry.registerJob(new CleaningAIRDJob(targetRootObject, session, representation));
                    result = Lists.newArrayList(representation);
                }
            }

        }
        return result;
    }

    // @formatter:off
    @Documentation(
        value = "Returns <code>true</code> if the arguments are not null, there's a representation with the specified name in the Sirius session. Returns <code>false</code> otherwise.",
        params = {
            @Param(name = "representationName", value = "the name of the searched representation"),
        },
        result = "<code>true</code> if there's a representation with the specified name in the Sirius session",
        examples = {
            @Example(expression = "'MyEPackage class diagram'.isRepresentationName()", result = "True"),
        }
    )
    // @formatter:on
    public boolean isRepresentationName(String representationName) {
        final boolean result;

        if (representationName != null) {
            result = SiriusRepresentationUtils.getAssociatedRepresentationByName(representationName, session) != null;
        } else {
            result = false;
        }

        return result;
    }

    // @formatter:off
    @Documentation(
        value = "Insert the image of the given representation if it's a diagram.",
        params = {
            @Param(name = "representation", value = "the DRepresentation"),
        },
        result = "insert the image of the given representation if it's a diagram.",
        examples = {
            @Example(expression = "dRepresentation.asImage()", result = "insert the image of the given representation if it's a diagram"),
        }
    )
    // @formatter:on
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

    // @formatter:off
    @Documentation(
        value = "Insert the table of the given representation table.",
        params = {
            @Param(name = "representation", value = "the DTable"),
        },
        result = "insert the table of the given representation table.",
        examples = {
            @Example(expression = "dTable.asTable()", result = "insert the tablee of the given representation table"),
        }
    )
    // @formatter:on
    public MTable asTable(DTable table) {
        return DTable2MTableConverter.convert(table);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the Sequence of images for the diagrams associated to the given EObject with the given description name.",
        params = {
            @Param(name = "eObject", value = "Any eObject that is in the session where to search"),
            @Param(name = "representationDescriptionName", value = "the name of the searched representation description"),
        },
        result = "the Sequence of images for the diagrams associated to the given EObject with the given description name.",
        examples = {
            @Example(expression = "ePackage.asImageByRepresentationDescriptionName('class diagram')", result = "Sequence{image1, image2}"),
        }
    )
    // @formatter:on
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

    // @formatter:off
    @Documentation(
        value = "Gets the Sequence of tables for the tables associated to the given EObject with the given description name.",
        params = {
            @Param(name = "eObject", value = "Any eObject that is in the session where to search"),
            @Param(name = "representationDescriptionName", value = "the name of the searched representation description"),
        },
        result = "the Sequence of tables for the tables associated to the given EObject with the given description name.",
        examples = {
            @Example(expression = "ePackage.asTableByRepresentationDescriptionName('dependency table')", result = "Sequence{table1, table2}"),
        }
    )
    // @formatter:on
    public List<MTable> asTableByRepresentationDescriptionName(EObject eObj, String descriptionName) {
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

    // @formatter:off
    @Documentation(
        value = "Insert the image of the given representation name.",
        params = {
            @Param(name = "representationName", value = "the name of the searched representation"),
        },
        result = "Insert the image of the given representation name",
        examples = {
            @Example(expression = "'MyEPackage class diagram'.asImageByRepresentationName()", result = "insert the image"),
        }
    )
    // @formatter:on
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

    // @formatter:off
    @Documentation(
        value = "Insert the table of the given representation name.",
        params = {
            @Param(name = "representationName", value = "the name of the searched representation"),
        },
        result = "Insert the table of the given representation name",
        examples = {
            @Example(expression = "'MyEPackage class diagram'.asTableByRepresentationName()", result = "insert the table"),
        }
    )
    // @formatter:on
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
        registry.clean(this);
        if (shouldCloseSession) {
            session.close(new NullProgressMonitor());
        }
        tmpFiles.clear();
    }

}
