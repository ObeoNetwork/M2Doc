package org.obeonetwork.m2doc.sirius.providers;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator;
import org.eclipse.gmf.runtime.diagram.ui.render.util.DiagramImageUtils;
import org.eclipse.gmf.runtime.diagram.ui.util.DiagramEditorUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;

/**
 * Use the DiagramEditPartService to use the figure validation
 * infinite loop safe ViewpointDiagramGraphicalViewer.
 * redefine sirius DiagramEditPartService to get image size.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public final class InfinitLoopSafeService extends DiagramEditPartService {

    /**
     * Last height.
     */
    private int height;

    /**
     * Last width.
     */
    private int width;

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> copyToImage(Diagram diagram, IPath destination, ImageFileFormat format,
            IProgressMonitor monitor, PreferencesHint preferencesHint) throws CoreException {

        List<Object> partInfo = Collections.EMPTY_LIST;

        DiagramEditor openedDiagramEditor = DiagramEditorUtil
                .findOpenedDiagramEditorForID(ViewUtil.getIdStr(diagram));
        if (openedDiagramEditor != null) {
            DiagramGenerator generator = copyToImage(openedDiagramEditor.getDiagramEditPart(), destination, format,
                    monitor);
            partInfo = generator.getDiagramPartInfo(openedDiagramEditor.getDiagramEditPart());
            // begin added code
            Rectangle rectangle = generator
                    .calculateImageRectangle(openedDiagramEditor.getDiagramEditPart().getPrimaryEditParts());
            height = rectangle.height;
            width = rectangle.width;
            // end added code
        } else {

            Shell shell = new Shell();
            try {
                DiagramEditPart diagramEditPart = createDiagramEditPart(diagram, shell, preferencesHint);
                SiriusCanonicalLayoutHandler.launchSynchroneArrangeCommand(diagramEditPart);
                diagramEditPart.getViewer().flush();
                Assert.isNotNull(diagramEditPart);
                DiagramGenerator generator = copyToImage(diagramEditPart, destination, format, monitor);
                partInfo = generator.getDiagramPartInfo(diagramEditPart);

                Dimension size = DiagramImageUtils.calculateImageRectangle(diagramEditPart.getPrimaryEditParts(),
                        0.0, new Dimension(100, 100)).getSize();
                // begin added code
                height = size.height;
                width = size.width;
                // end added code
            } finally {
                shell.dispose();
            }
        }

        return partInfo;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}