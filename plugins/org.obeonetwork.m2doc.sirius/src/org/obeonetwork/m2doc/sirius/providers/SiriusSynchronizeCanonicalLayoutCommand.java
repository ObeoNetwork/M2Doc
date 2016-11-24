package org.obeonetwork.m2doc.sirius.providers;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.AbstractCommand.NonDirtying;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * This class is a copy of Sirius code modified to support synchronous arrange.
 * A feature request was created to support synchronous arrange aspect : 
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=507026
 * When feature was implemented we could remove this class.
 * 
 * FIXME : Remove this class and used modified SiriusCanonicalLayoutHandler class in AbstractSiriusDiagramImagesProvider when 
 *          feature request will be resolved.
 *          
 * Specific Command to do layout for newly created views in pre-commit without
 * being in the IOperationHistory by being a {@link NonDirtying} . </br>
 * <b>NOTE
 * : the use of {@link NonDirtying} is a workaround to not have layout of
 * created views (in pre-commit) in the undo stack, but this doesn't seems break
 * the undo/redo model because here we only changes Bounds </b>.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SiriusSynchronizeCanonicalLayoutCommand extends RecordingCommand implements NonDirtying {
    // CHECKSTYLE:OFF
    private IGraphicalEditPart diagramEditPart;

    private List<IAdaptable> childViewsAdapters;

    private List<IAdaptable> childViewsAdaptersForCenterLayout;

    /**
     * Constructor used to do a layout on all created views child (directly or
     * indirectly) of Diagram. </br>
     * NOTE : to use at diagram representation
     * opening.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which executes this
     *            command
     * @param diagramEditPart
     *            the {@link DiagramEditPart} on which do the layout
     */
    public SiriusSynchronizeCanonicalLayoutCommand(TransactionalEditingDomain domain, DiagramEditPart diagramEditPart) {
        this(domain, diagramEditPart, null, null);
    }

    /**
     * Constructor to do layout on all created views child directly of a View.
     * </br>
     * NOTE : to use when external changes occurs.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which executes this
     *            command
     * @param parentEditPart
     *            the {@link IGraphicalEditPart} on which do the layout
     * @param childViewsAdapters
     *            list of {@link IAdaptable} for created Views to layout
     * @param childViewsAdaptersForCenterLayout
     *            list of {@link IAdaptable} for created Views to center layout
     */
    public SiriusSynchronizeCanonicalLayoutCommand(TransactionalEditingDomain domain, IGraphicalEditPart parentEditPart,
            List<IAdaptable> childViewsAdapters, List<IAdaptable> childViewsAdaptersForCenterLayout) {
        super(domain, Messages.SiriusCanonicalLayoutCommand_label);
        this.diagramEditPart = parentEditPart;
        this.childViewsAdapters = childViewsAdapters;
        this.childViewsAdaptersForCenterLayout = childViewsAdaptersForCenterLayout;
    }

    /**
     * Overridden to execute a DeferredLayoutCommand.
     */
    @Override
    protected void doExecute() {
        if (childViewsAdapters == null && childViewsAdaptersForCenterLayout == null) {
            executeLayoutOnDiagramOpening();
        } else {
            executeLayoutDueToExternalChanges();
        }
    }

    private void executeLayoutOnDiagramOpening() {
        org.eclipse.gef.commands.Command arrangeCmd = SiriusLayoutDataManager.INSTANCE
                .getArrangeCreatedViewsOnOpeningCommand(diagramEditPart);
        if (arrangeCmd != null && arrangeCmd.canExecute()) {
            arrangeCmd.execute();
        }
    }

    private void executeLayoutDueToExternalChanges() {
        org.eclipse.gef.commands.Command arrangeCmd = SiriusLayoutDataManager.INSTANCE
                .getArrangeCreatedViewsCommand(childViewsAdapters, childViewsAdaptersForCenterLayout, diagramEditPart);
        if (arrangeCmd != null && arrangeCmd.canExecute()) {
            arrangeCmd.execute();
        }
    }
    // CHECKSTYLE:ON
}
