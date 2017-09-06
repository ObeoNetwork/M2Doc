package org.obeonetwork.m2doc.sirius.ui.handlers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.sirius.ui.M2DocSiriusUIPlugin;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CDOGenerateHandler extends AbstractHandler {
    /**
     * The constructor.
     */
    public CDOGenerateHandler() {
    }

    /**
     * Returns the first local resource contained in the associated resource
     * set.
     * 
     * @param resources
     *            a resource contained in the target resource set.
     * @return a local resource;
     */
    Resource getPlatformeResource(Set<Resource> resources) {
        for (Resource res : resources) {
            URI uri = res.getURI();
            if (uri.isPlatform()) {
                return res;
            }
        }
        return null;
    }

    /**
     * the command has been executed, so extract extract the needed information
     * from the application context.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        Shell shell = HandlerUtil.getActiveShell(event);
        if (selection instanceof IStructuredSelection) {
            Object selected = ((IStructuredSelection) selection).getFirstElement();
            Generation generation = null;
            if (selected instanceof Generation) {
                generation = (Generation) selected;
            } else {
                MessageDialog.openError(shell, "Bad selection",
                        "Document generation action can only be triggered on Generation object.");
                return null;
            }
            try {
                List<URI> generatedfiles = GenconfUtils.generate(generation, M2DocPlugin.getClassProvider(),
                        new BasicMonitor());
                if (generatedfiles.size() == 1) {
                    MessageDialog.openInformation(shell, "M2Doc generation",
                            "The document '" + generatedfiles.get(0).toString() + "' is generated.");
                } else if (generatedfiles.size() == 2) {
                    MessageDialog.openInformation(shell, "M2Doc generation",
                            "The document '" + generatedfiles.get(0).toString()
                                + "' is generated. \n\n The template file contains validation errors, please read '"
                                + generatedfiles.get(1).toString() + "'.");
                }
            } catch (IOException e) {
                M2DocSiriusUIPlugin.INSTANCE
                        .log(new Status(Status.ERROR, M2DocSiriusUIPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
                MessageDialog.openError(shell, "I/O problem, see the error log for details", e.getMessage());
            } catch (DocumentParserException e) {
                M2DocSiriusUIPlugin.INSTANCE
                        .log(new Status(Status.ERROR, M2DocSiriusUIPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
                MessageDialog.openError(shell, "Template parsing problem. See the error log for details",
                        e.getMessage());
            } catch (DocumentGenerationException e) {
                M2DocSiriusUIPlugin.INSTANCE
                        .log(new Status(Status.ERROR, M2DocSiriusUIPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
                MessageDialog.openError(shell, "Generation problem. See the error log for details", e.getMessage());
                // CHECKSTYLE:OFF
            } catch (RuntimeException e) { // do not let exception leak out.
                // CHECKSTYLE:ON
                String msg = e.getMessage();
                M2DocSiriusUIPlugin.INSTANCE.log(new Status(Status.ERROR, M2DocSiriusUIPlugin.PLUGIN_ID, Status.ERROR,
                        "M2Doc : technical error" + (msg == null ? "." : " : " + msg), e));
                MessageDialog.openError(shell, "Generation problem. See the error log for details", e.getMessage());
            }
        } else {
            MessageDialog.openError(shell, "Bad selection",
                    "Document generation action can only be triggered on Generation object.");
        }

        return null;
    }
}
