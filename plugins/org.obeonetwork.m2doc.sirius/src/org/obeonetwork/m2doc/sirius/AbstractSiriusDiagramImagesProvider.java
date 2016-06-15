/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;
import org.obeonetwork.m2doc.provider.ProviderException;

/**
 * All providers of this kind provide Sirius diagram images.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public abstract class AbstractSiriusDiagramImagesProvider extends AbstractDiagramProvider {
    /**
     * Replace forbidden characters with "_" in a filename.
     * 
     * @param name
     *            the string to sanitized.
     * @return sanitized string.
     */
    protected static String sanitizeFilename(String name) {
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
    protected String getDiagramImageFilename(DSemanticDiagram diagram, String rootPath) {
        return rootPath + "/generated/images/representations/diagram_"
            + sanitizeFilename(diagram.eResource().getURIFragment(diagram)) + "." + ImageFileFormat.JPEG.getName();
    }

    /**
     * Get the GMF Diagram instance corresponding to a viewpoint
     * DSemanticDiagram.
     * 
     * @param semanticDiagram
     *            the diagram object from which we want to create an image.
     * @return the GMF Diagram instance corresponding to a viewpoint
     *         DSemanticDiagram.
     */
    protected Diagram getGmfDiagram(DSemanticDiagram semanticDiagram) {
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

    /**
     * Generates images corresponding to the given representation and returns their paths into a list.
     * 
     * @param rootPath
     *            the path of the project were to generate images.
     * @param session
     *            the Sirius session containing the representations from which we generate images.
     * @param representations
     *            all the representations from which to generate the corresponding images.
     * @return all images paths corresponding to the given representations.
     * @throws ProviderException
     *             if the image generation fails.
     */
    protected List<String> generateAndReturnDiagramImages(String rootPath, Session session,
            List<DRepresentation> representations) throws ProviderException {
        List<String> resultList = new ArrayList<String>();
        for (DRepresentation dRepresentation : representations) {
            if (dRepresentation instanceof DSemanticDiagram) {
                DSemanticDiagram dsd = (DSemanticDiagram) dRepresentation;
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
                    resultList.add(filePath);
                } catch (CoreException e) {
                    throw new ProviderException("Image creation from diagram '" + dRepresentation.getName()
                        + "' to the file '" + filePath + "' failed.", e);
                }
            }
        }
        return resultList;
    }
}
