/*******************************************************************************
 *  Copyright (c) 2024, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.jdt.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.m2doc.ide.ui.util.IClassPropertyUpdater;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.osgi.framework.Bundle;

/**
 * Updates imported classes for the given {@link TemplateCustomProperties} using the JDT.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ClassPropertyUpdater implements IClassPropertyUpdater {

    @Override
    public boolean updatePropertyClasses(TemplateCustomProperties customProperties, URI templateURI) {
        final boolean res;

        // TODO replace with resolver.getAvailableQualifiedNames()
        // and a filtered dialog see M2DocTypeSelectionDialog for instance

        final IFile templateFile = ResourcesPlugin.getWorkspace().getRoot()
                .getFile(new Path(templateURI.toPlatformString(true)));
        final IJavaElement[] javaElements;
        if (templateFile != null) {
            final IProject project = templateFile.getProject();
            if (project != null) {
                final IJavaProject javaProject = JavaCore.create(project);
                if (javaProject != null) {
                    javaElements = new IJavaElement[] {javaProject};
                } else {
                    javaElements = new IJavaElement[] {};
                }
            } else {
                javaElements = new IJavaElement[] {};
            }
        } else {
            javaElements = new IJavaElement[] {};
        }

        final IJavaSearchScope scope = SearchEngine.createJavaSearchScope(javaElements, true);
        final FilteredTypesSelectionDialog dialog = new FilteredTypesSelectionDialog(
                Display.getCurrent().getActiveShell(), true, PlatformUI.getWorkbench().getProgressService(), scope,
                IJavaSearchConstants.CLASS);
        if (dialog.open() == Dialog.OK && dialog.getResult() != null && dialog.getResult().length != 0) {
            for (Object object : dialog.getResult()) {
                customProperties.getImports().add(((IType) object).getFullyQualifiedName());
            }
            res = true;
        } else {
            res = false;
        }

        return res;
    }

    /**
     * Gets the bundle name of the given {@link IType}.
     * 
     * @param type
     *            the {@link IType}
     * @return the bundle name of the given {@link IType} if any, <code>null</code> otherwise
     */
    private String getBundleName(IType type) {
        final String packageName = type.getParent().getParent().getElementName();
        final List<String> segments = new ArrayList<>(Arrays.asList(packageName.split("\\.")));
        while (!segments.isEmpty()) {
            String bundleName = String.join(".", segments);
            final Bundle bundle = Platform.getBundle(bundleName);
            if (bundle != null) {
                return bundle.getSymbolicName();
            }
            segments.remove(segments.size() - 1);
        }

        return null;
    }
}
