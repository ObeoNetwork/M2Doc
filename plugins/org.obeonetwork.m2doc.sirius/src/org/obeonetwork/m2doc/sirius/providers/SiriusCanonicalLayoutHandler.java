package org.obeonetwork.m2doc.sirius.providers;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
//import org.obeonetwork.m2doc.sirius.providers.AbstractSiriusDiagramImagesProvider.SiriusSynchroneCanonicalLayoutCommand;

/**
 * 
 * This class is a copy of Sirius code modified to support synchronous arrange.
 * A feature request was created to support synchronous arrange aspect :
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=507026
 * When feature was implemented we could remove this class.
 * 
 * FIXME : Remove this class and used modified SiriusCanonicalLayoutHandler class in AbstractSiriusDiagramImagesProvider when
 * feature request will be resolved.
 * 
 * Helper to execute a ArrangeRequest's {@link Command} for created views (in
 * the DDiagramCanonicalSynchronizer ) to arrange.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
// CHECKSTYLE:OFF
@SuppressWarnings("restriction")
public final class SiriusCanonicalLayoutHandler {

    private SiriusCanonicalLayoutHandler() {
        // Helper to not instantiate
    }

    /**
     * Execute ArrangeRequest's {@link Command} for created views (in the
     * DDiagramCanonicalSynchronizer) to arrange.
     * 
     * @param diagramEditPart
     *            The {@link DiagramEditPart} used to get parent
     *            {@link IGraphicalEditPart} of created {@link View}s to layout.
     */
    public static void launchSynchroneArrangeCommand(DiagramEditPart diagramEditPart) {
        TransactionalEditingDomain editingDomain = diagramEditPart.getEditingDomain();
        Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap = getCreatedViewsToLayoutMap(diagramEditPart);
        Map<IGraphicalEditPart, List<IAdaptable>> createdViewsWithSpecialLayoutMap = getCreatedViewsWithSpecialLayoutMap(
                diagramEditPart);
        Command layoutCommand = getSynchroneLayoutCommand(createdViewsToLayoutMap, createdViewsWithSpecialLayoutMap,
                editingDomain);
        if (layoutCommand.canExecute()) {
            editingDomain.getCommandStack().execute(layoutCommand);
        }
    }

    private static Map<IGraphicalEditPart, List<IAdaptable>> getCreatedViewsToLayoutMap(
            DiagramEditPart diagramEditPart) {
        // For a more predictable result (and constant), the hashMap must be
        // sorted from the highest level container to the lowest level
        // container. The viewAdapters seems to be already sorted so we must
        // just keep this order by using a linked Hashmap.
        Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
        Map<Diagram, Set<View>> createdViewsToLayout = SiriusLayoutDataManager.INSTANCE.getCreatedViewsToLayout();

        getCreatedViewToLayoutMap(diagramEditPart, createdViewsToLayoutMap, createdViewsToLayout);
        return createdViewsToLayoutMap;
    }

    private static Map<IGraphicalEditPart, List<IAdaptable>> getCreatedViewsWithSpecialLayoutMap(
            DiagramEditPart diagramEditPart) {
        // For a more predictable result (and constant), the hashMap must be
        // sorted from the highest level container to the lowest level
        // container. The viewAdapters seems to be already sorted so we must
        // just keep this order by using a linked Hashmap.
        Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
        Map<Diagram, Set<View>> createdViewsToLayout = SiriusLayoutDataManager.INSTANCE
                .getCreatedViewWithCenterLayout();

        getCreatedViewToLayoutMap(diagramEditPart, createdViewsToLayoutMap, createdViewsToLayout);
        return createdViewsToLayoutMap;
    }

    private static void getCreatedViewToLayoutMap(DiagramEditPart diagramEditPart,
            Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap,
            Map<Diagram, Set<View>> createdViewsToLayout) {
        if (!createdViewsToLayout.isEmpty()) {

            Diagram diagramOfOpenedEditor = diagramEditPart.getDiagramView();
            if (diagramOfOpenedEditor != null && createdViewsToLayout.containsKey(diagramOfOpenedEditor)) {

                if (diagramEditPart != null) {
                    Map<?, ?> editPartRegistry = diagramEditPart.getViewer().getEditPartRegistry();
                    List<IAdaptable> viewAdapters = getAdapters(createdViewsToLayout.get(diagramOfOpenedEditor));
                    Map<View, List<IAdaptable>> splitedViewAdapters = splitViewAdaptersAccordingToParent(viewAdapters);
                    for (Entry<View, List<IAdaptable>> viewAdaptersWithSameParent : splitedViewAdapters.entrySet()) {
                        View parentView = viewAdaptersWithSameParent.getKey();
                        List<IAdaptable> childViewsAdapters = viewAdaptersWithSameParent.getValue();
                        IGraphicalEditPart parentEditPart = (IGraphicalEditPart) editPartRegistry.get(parentView);
                        // CHECKSTYLE:OFF
                        if (parentEditPart != null) {
                            createdViewsToLayoutMap.put(parentEditPart, childViewsAdapters);
                        }
                        // CHECKSTYLE:ON
                    }
                }
                createdViewsToLayout.remove(diagramOfOpenedEditor);
            }
        }
    }

    private static Map<View, List<IAdaptable>> splitViewAdaptersAccordingToParent(List<IAdaptable> viewAdapters) {
        // For a more predictable result (and constant), the hashMap must be
        // sorted from the highest level container to the lowest level
        // container. The viewAdapters seems to be already sorted so we must
        // just keep this order by using a linked Hashmap.
        Map<View, List<IAdaptable>> splitedViewAdaptersAccordingToParent = new LinkedHashMap<View, List<IAdaptable>>();
        for (IAdaptable viewAdapter : viewAdapters) {
            View createdViewToLayout = (View) viewAdapter.getAdapter(View.class);
            EObject eContainer = createdViewToLayout.eContainer();
            if (eContainer instanceof View) {
                View parentView = (View) eContainer;
                List<IAdaptable> viewAdaptersWithSameParent = splitedViewAdaptersAccordingToParent.get(parentView);
                if (viewAdaptersWithSameParent == null) {
                    viewAdaptersWithSameParent = new ArrayList<IAdaptable>();
                    splitedViewAdaptersAccordingToParent.put(parentView, viewAdaptersWithSameParent);
                }
                viewAdaptersWithSameParent.add(viewAdapter);
            }
        }

        return splitedViewAdaptersAccordingToParent;
    }

    private static List<IAdaptable> getAdapters(Set<View> createdViewsToLayout) {
        List<IAdaptable> viewAdapters = new ArrayList<IAdaptable>();
        for (View createdViewToLayout : createdViewsToLayout) {
            viewAdapters.add(new EObjectAdapter(createdViewToLayout));
        }
        return viewAdapters;
    }

    private static Command getSynchroneLayoutCommand(Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap,
            Map<IGraphicalEditPart, List<IAdaptable>> createdViewsWithSpecialLayoutMap,
            TransactionalEditingDomain editingDomain) {
        final CompoundCommand compoundCommand = new CompoundCommand();

        // Filter type of element to layout to avoid having elements layout
        // computed multiple times
        Predicate<Entry<IGraphicalEditPart, List<IAdaptable>>> typeOfElementToLayout = new Predicate<Map.Entry<IGraphicalEditPart, List<IAdaptable>>>() {

            @Override
            public boolean apply(Entry<IGraphicalEditPart, List<IAdaptable>> input) {
                return input.getKey() instanceof DDiagramEditPart
                    || input.getKey() instanceof DNodeContainerViewNodeContainerCompartmentEditPart
                    || (input.getKey() instanceof DNodeContainerViewNodeContainerCompartment2EditPart
                        && !(input.getKey().getParent()
                                .getParent() instanceof DNodeContainerViewNodeContainerCompartment2EditPart));
            }
        };

        for (Entry<IGraphicalEditPart, List<IAdaptable>> entry : Iterables.filter(createdViewsToLayoutMap.entrySet(),
                typeOfElementToLayout)) {
            IGraphicalEditPart parentEditPart = entry.getKey();
            List<IAdaptable> childViewsAdapters = entry.getValue();
            Command viewpointLayoutCanonicalSynchronizerCommand = new SiriusSynchronizeCanonicalLayoutCommand(
                    editingDomain, parentEditPart, childViewsAdapters, null);
            compoundCommand.append(viewpointLayoutCanonicalSynchronizerCommand);
        }

        for (Entry<IGraphicalEditPart, List<IAdaptable>> entry : Iterables
                .filter(createdViewsWithSpecialLayoutMap.entrySet(), typeOfElementToLayout)) {
            IGraphicalEditPart parentEditPart = entry.getKey();
            List<IAdaptable> childViewsAdapters = entry.getValue();
            Command viewpointLayoutCanonicalSynchronizerCommand = new SiriusSynchronizeCanonicalLayoutCommand(
                    editingDomain, parentEditPart, null, childViewsAdapters);
            compoundCommand.append(viewpointLayoutCanonicalSynchronizerCommand);
        }
        return compoundCommand;
    }
    // CHECKSTYLE:ON
}
