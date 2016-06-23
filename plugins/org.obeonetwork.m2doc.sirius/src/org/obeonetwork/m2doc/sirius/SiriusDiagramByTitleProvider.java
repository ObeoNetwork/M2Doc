/*******************************************************************************
 * Copyright (c) 2016 Obeo. 
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    which accompanies this distribution, and is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *     
 *     Contributors:
 *         Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.m2doc.provider.DiagramProvider;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;

/**
 * {@link SiriusDiagramByTitleProvider} are used to get sirius diagrams from an
 * eObject and a name.
 * 
 * @author Romain Guider
 */
public class SiriusDiagramByTitleProvider extends DiagramProvider {
    /**
     * The key used in the map passed to {@link IProvider} to define the Sirius
     * representation title from which image will be computed.
     */
    private static final String KEY_REPRESENTATION_TITLE = "title";

    /**
     * Creates a new {@link SiriusDiagramByTitleProvider} given an EObject which
     * is the root of the seek diagram and the name of the diagram.
     */
    public SiriusDiagramByTitleProvider() {
    }

    /**
     * Replace forbidden characters with "_" in a filename.
     * 
     * @param name
     *            the string to sanitized.
     * @return sanitized string.
     */
    public static String sanitizeFilename(String name) {
        return name.replaceAll("[:\\\\/*?|<>]", "_");
    }

    /**
     * Get the full path (starting from the root folder) of the generated image
     * for a diagram.
     * 
     * @param diagram
     *            the diagram from which we want to create an image.
     * @param rootPath
     *            the project root path from which document generation has been launched.
     * @return Full path starting from root folder
     */
    private String getDiagramImageFilename(DSemanticDiagram diagram, String rootPath) {
        return rootPath + "/generated/images/representations/diagram_"
            + sanitizeFilename(diagram.eResource().getURIFragment(diagram)) + "." + ImageFileFormat.JPEG.getName();
    }

    /**
     * Retrieve all representations whose target is the specified EObject.
     * 
     * @param representationName
     *            the name of the representation from which we want to create an image.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @return the corresponding representation.
     */
    private DRepresentation getAssociatedRepresentationByName(String representationName, Session session) {
        if (representationName != null) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);

            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentation representation : representations) {
                if (representationName.equals(representation.getName())
                    && representation.eContainer() instanceof DView) {
                    DView dView = (DView) representation.eContainer();
                    Viewpoint vp = dView.getViewpoint();
                    if (selectedViewpoints.contains(vp)) {
                        return representation;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Get the GmF Diagram instance corresponding to a viewpoint
     * DSemanticDiagram.
     * 
     * @param semanticDiagram
     *            the diagram object from which we want to create an image.
     * @return the GmF Diagram instance corresponding to a viewpoint
     *         DSemanticDiagram.
     */
    private Diagram getGmfDiagram(DSemanticDiagram semanticDiagram) {
        for (final AnnotationEntry annotation : new DDiagramQuery(semanticDiagram)
                .getAnnotation(CustomDataConstants.GMF_DIAGRAMS)) {
            EObject eObject = annotation.getData();
            if (eObject instanceof Diagram) {
                final Diagram diagramInResource = (Diagram) eObject;
                final EObject semanticElement = ViewUtil.resolveSemanticElement(diagramInResource);
                if (semanticElement.equals(semanticDiagram)) {
                    return diagramInResource;
                }
            }
        }
        return null;
    }

    @Override
    public List<String> getRepresentationImagePath(Map<String, Object> parameters) throws ProviderException {

        EObject rootObject = (EObject) parameters.get(ProviderConstants.KEY_CONF_ROOT_OBJECT);
        String rootPath = (String) parameters.get(ProviderConstants.KEY_PROJECT_ROOT_PATH);
        Session session = SessionManager.INSTANCE.getSession(rootObject);
        Object representationName = parameters.get(KEY_REPRESENTATION_TITLE);
        if (!(representationName instanceof String)) {
            throw new ProviderException(
                    "Image cannot be computed because no representation title has been provided to the provider \""
                        + this.getClass().getName() + "\"");
        } else {
            DRepresentation representation = getAssociatedRepresentationByName((String) representationName, session);
            if (representation instanceof DSemanticDiagram) {
                DSemanticDiagram dsd = (DSemanticDiagram) representation;
                String filePath = getDiagramImageFilename(dsd, rootPath);
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                final IPath path = new Path(filePath);
                final Diagram gmfDiagram = getGmfDiagram(dsd);

                // Use the DiagramEditPartService to use the figure validation
                // infinite loop safe ViewpointDiagramGraphicalViewer.
                final CopyToImageUtil imageUtility = new DiagramEditPartService();

                final EditingDomain editingDomain = session.getTransactionalEditingDomain();
                final Diagram realOne = (Diagram) editingDomain.getResourceSet()
                        .getEObject(EcoreUtil.getURI(gmfDiagram), true);
                try {
                    imageUtility.copyToImage(realOne, path, ImageFileFormat.JPEG, new NullProgressMonitor(),
                            PreferencesHint.USE_DEFAULTS);
                    List<String> resultList = new ArrayList<String>(1);
                    resultList.add(filePath);
                    return resultList;
                } catch (CoreException e) {
                    throw new ProviderException("Image creation from diagram '" + representationName + "' to the file '"
                        + filePath + "' failed.", e);
                }
            } else {
                throw new ProviderException("Representation with title '" + representationName + "' not found");
            }
        }
    }

    @Override
    public Map<String, OptionType> getOptionTypes() {
        Map<String, OptionType> options = new HashMap<String, OptionType>();
        options.put(KEY_REPRESENTATION_TITLE, OptionType.AQL_EXPRESSION);
        return options;
    }

}
