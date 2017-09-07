/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation and/or initial documentation
 *    ...
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.editor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.obeonetwork.m2doc.ide.ui.Activator;

/**
 * A tree that filter and has check boxes.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class FilteredCheckboxTree extends FilteredTree {

    /**
     * Constructor.
     * 
     * @param parent
     *            the parent <code>Composite</code>
     * @param treeStyle
     *            the style bits for the <code>Tree</code>
     * @param filter
     *            the filter to be used
     * @param useNewLook
     *            <code>true</code> if the new 3.5 look should be used
     */
    public FilteredCheckboxTree(Composite parent, int treeStyle, PatternFilter filter, boolean useNewLook) {
        super(parent, treeStyle, filter, useNewLook);
    }

    /**
     * Custom tree viewer subclass that clears the caches in patternFilter on any change to the tree. See bug
     * 187200.
     * 
     * @since 3.3
     */
    class NotifyingTreeViewer extends CheckboxTreeViewer {

        /**
         * Clear cache {@link Method}.
         */
        private final Method clearCaches;

        /**
         * A notifying tree with check boxes.
         * 
         * @param parent
         *            the parent control
         * @param style
         *            the SWT style bits
         */
        NotifyingTreeViewer(Composite parent, int style) {
            super(parent, style);
            Method method = null;
            try {
                method = PatternFilter.class.getDeclaredMethod("clearCaches");
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
            } catch (SecurityException e) {
                Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
            }
            clearCaches = method;
        }

        /**
         * Clear the cache.
         */
        private void clearCache() {
            try {
                clearCaches.invoke(getPatternFilter());
            } catch (IllegalAccessException e) {
                Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
            } catch (IllegalArgumentException e) {
                Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
            } catch (InvocationTargetException e) {
                Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
            }
        }

        @Override
        public void add(Object parentElementOrTreePath, Object childElement) {
            clearCache();
            super.add(parentElementOrTreePath, childElement);
        }

        @Override
        public void add(Object parentElementOrTreePath, Object[] childElements) {
            clearCache();
            super.add(parentElementOrTreePath, childElements);
        }

        @Override
        protected void inputChanged(Object input, Object oldInput) {
            clearCache();
            super.inputChanged(input, oldInput);
        }

        @Override
        public void insert(Object parentElementOrTreePath, Object element, int position) {
            clearCache();
            super.insert(parentElementOrTreePath, element, position);
        }

        @Override
        public void refresh() {
            clearCache();
            super.refresh();
        }

        @Override
        public void refresh(boolean updateLabels) {
            clearCache();
            super.refresh(updateLabels);
        }

        @Override
        public void refresh(Object element) {
            clearCache();
            super.refresh(element);
        }

        @Override
        public void refresh(Object element, boolean updateLabels) {
            clearCache();
            super.refresh(element, updateLabels);
        }

        @Override
        public void remove(Object elementsOrTreePaths) {
            clearCache();
            super.remove(elementsOrTreePaths);
        }

        @Override
        public void remove(Object parent, Object[] elements) {
            clearCache();
            super.remove(parent, elements);
        }

        @Override
        public void remove(Object[] elementsOrTreePaths) {
            clearCache();
            super.remove(elementsOrTreePaths);
        }

        @Override
        public void replace(Object parentElementOrTreePath, int index, Object element) {
            clearCache();
            super.replace(parentElementOrTreePath, index, element);
        }

        @Override
        public void setChildCount(Object elementOrTreePath, int count) {
            clearCache();
            super.setChildCount(elementOrTreePath, count);
        }

        @Override
        public void setContentProvider(IContentProvider provider) {
            clearCache();
            super.setContentProvider(provider);
        }

        @Override
        public void setHasChildren(Object elementOrTreePath, boolean hasChildren) {
            clearCache();
            super.setHasChildren(elementOrTreePath, hasChildren);
        }

    }

    @Override
    protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
        return new NotifyingTreeViewer(parent, style);
    }

    @Override
    public CheckboxTreeViewer getViewer() {
        return (CheckboxTreeViewer) super.getViewer();
    }

}
