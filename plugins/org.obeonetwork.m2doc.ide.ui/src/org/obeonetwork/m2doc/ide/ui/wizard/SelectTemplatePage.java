/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.obeonetwork.m2doc.ide.ui.wizard.SelectRegisteredTemplatePage.TemplateURISettable;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * A container selection wizard.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class SelectTemplatePage extends WizardPage {

    /**
     * The selected template {@link URI}.
     */
    private URI templateURI;

    /**
     * The {@link WizardNewFileCreationPage} providing the base {@link URI}.
     */
    private final WizardNewFileCreationPage page;

    /**
     * Constructor.
     * 
     * @param page
     *            the {@link WizardNewFileCreationPage}.
     */
    public SelectTemplatePage(WizardNewFileCreationPage page) {
        super("Select output folder");
        setTitle("Select the target container.");
        this.page = page;
    }

    @Override
    public void createControl(Composite parent) {
        final Composite container = new Composite(parent, SWT.NULL);
        setControl(container);
        container.setLayout(new GridLayout(1, false));

        final Text templateURIText = createTemplateURIComposite(container);

        final TreeViewer containerTreeViewer = new TreeViewer(container, SWT.BORDER);
        Tree tree = containerTreeViewer.getTree();
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        containerTreeViewer.setContentProvider(new WorkbenchContentProvider() {
            @Override
            public Object[] getChildren(Object element) {
                final List<Object> res = new ArrayList<>();
                for (Object obj : super.getChildren(element)) {
                    if (obj instanceof IContainer || (obj instanceof IFile
                        && M2DocUtils.DOCX_EXTENSION_FILE.equals(((IFile) obj).getFileExtension()))) {
                        res.add(obj);
                    }
                }
                return res.toArray();
            }
        });
        containerTreeViewer.setLabelProvider(new WorkbenchLabelProvider());
        containerTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final Object selected = ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (selected instanceof IFile) {
                    final IFile file = (IFile) selected;
                    setURI(templateURIText, URI.createPlatformResourceURI(file.getFullPath().toString(), true));
                    setTemplateURI(URI.createURI(templateURIText.getText()));
                }
            }
        });
        containerTreeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
        if (templateURI != null && templateURI.isPlatformResource()) {
            final Path filePath = new Path(templateURI.toPlatformString(true));
            final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(filePath);
            containerTreeViewer.setSelection(new StructuredSelection(file));
        }
        setPageComplete(templateURI != null);
    }

    /**
     * Creates the template {@link URI} {@link Composite}.
     * 
     * @param container
     *            the pasent {@link Composite}
     * @return the template {@link URI} {@link Text}
     */
    protected Text createTemplateURIComposite(final Composite container) {
        final Composite templateURIComposite = new Composite(container, SWT.NONE);
        templateURIComposite.setLayout(new GridLayout(2, false));
        templateURIComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        final Label templateURILabel = new Label(templateURIComposite, SWT.NONE);
        templateURILabel.setText("Template URI: ");

        final Text templateURIText = new Text(templateURIComposite, SWT.NONE);
        templateURIText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        new Label(templateURIComposite, SWT.NONE);
        if (templateURI != null) {
            setURI(templateURIText, templateURI);
        }
        templateURIText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                setTemplateURI(URI.createURI(templateURIText.getText(), false));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });

        final Button templateURIButton = new Button(templateURIComposite, SWT.NONE);
        templateURIButton.setText("Browse registry");
        templateURIButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                openDialog();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                openDialog();
            }

            private void openDialog() {
                SelectRegistredTemplateDialog dialog = new SelectRegistredTemplateDialog(getShell());

                final int dialogResult = dialog.open();
                if ((dialogResult == IDialogConstants.OK_ID) && dialog.getTemplateURI() != null) {
                    setURI(templateURIText, dialog.getTemplateURI());
                }
            }
        });

        return templateURIText;
    }

    /**
     * Sets the given {@link URI} to the given {@link Text}.
     * 
     * @param text
     *            the {@link Text}
     * @param uri
     *            the {@link URI}
     */
    private void setURI(final Text text, URI uri) {
        final String relativeTemplatePath = URI.decode(uri.deresolve(getBaseURI(page)).toString());
        text.setText(relativeTemplatePath);
    }

    /**
     * Sets the template {@link URI}.
     * 
     * @param templateURI
     *            the template {@link URI} to set
     */
    public void setTemplateURI(URI templateURI) {
        this.templateURI = templateURI.deresolve(getBaseURI(page));
        for (IWizardPage currentPage : getWizard().getPages()) {
            if (currentPage instanceof TemplateURISettable) {
                ((TemplateURISettable) getNextPage()).setTemplateURI(templateURI);
            }
        }
        setPageComplete(templateURI != null);
    }

    /**
     * Gets the template {@link URI}.
     * 
     * @return the template {@link URI}
     */
    public URI getTemplateURI() {
        return templateURI;
    }

    /**
     * Gets the {@link URI} from the given {@link WizardNewFileCreationPage}.
     * 
     * @param p
     *            the {@link WizardNewFileCreationPage}
     * @return the {@link URI} from the given {@link WizardNewFileCreationPage} if any, <code>null</code> otherwise
     */
    public URI getBaseURI(WizardNewFileCreationPage p) {
        final URI res;

        final IPath path = p.getContainerFullPath();
        if (path != null) {
            final String fileName = p.getFileName();
            if (fileName != null && !fileName.isEmpty()) {
                res = URI.createPlatformResourceURI(path.toString() + "/" + fileName, true);
            } else {
                res = null;
            }
        } else {
            res = null;
        }

        return res;
    }

}
