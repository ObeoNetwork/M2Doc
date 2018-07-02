package org.obeonetwork.m2doc.ide.ui.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.ide.ui.wizard.TemplateCustomPropertiesWizard;

/**
 * Edite template custom properties.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EditTemplatePropertiesHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final Shell shell = HandlerUtil.getActiveShell(event);

        WizardDialog dialog = new WizardDialog(shell, new TemplateCustomPropertiesWizard()) {

            @Override
            public void create() {
                super.create();
                getShell().setText("Template properties");
            }

            @Override
            public void showPage(IWizardPage page) {
                super.showPage(page);
                getShell().setText("Template properties");
            }
        };
        dialog.open();

        return null;
    }

}
