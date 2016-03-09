package org.obeonetwork.wgen.ui.popup.actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.obeonetwork.wgen.generator.DocumentGenerationException;
import org.obeonetwork.wgen.generator.DocumentGenerator;
import org.obeonetwork.wgen.parser.DocumentParser;
import org.obeonetwork.wgen.parser.DocumentParserException;
import org.obeonetwork.wgen.template.DocumentTemplate;
import org.obeonetwork.wgen.ui.Activator;
import org.obeonetwork.wgen.ui.genconf.Definition;
import org.obeonetwork.wgen.ui.genconf.Generation;
import org.obeonetwork.wgen.ui.genconf.ModelDefinition;
import org.obeonetwork.wgen.ui.genconf.StringDefinition;

public class GenerateDocumentation implements IObjectActionDelegate {

	private Shell shell;
	private ISelection selection;

	/**
	 * Constructor for Action1.
	 */
	public GenerateDocumentation() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			if (selected instanceof Generation) {
				Map<String, Object> definitions = createDefinitions(((Generation) selected));
				try {
					generate((Generation) selected, definitions);
				} catch (IOException e) {
					Activator.getDefault().getLog()
							.log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
				} catch (DocumentParserException e) {
					Activator.getDefault().getLog()
							.log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
				} catch (DocumentGenerationException e) {
					Activator.getDefault().getLog()
							.log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
				}
			} else {
				MessageDialog.openError(shell, "Bad selection",
						"Document generation action can only be triggered on Generation object.");
			}
		}
	}

	protected IFile getFile(Resource resource) {
		URI uri = resource.getURI();
		String fileString = URI.decode(uri.path());
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(fileString));
		return file;
	}

	private void generate(Generation generation, Map<String, Object> definitions)
			throws IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		for (String nsURI : generation.getPackagesNSURI()) {
			EPackage p = EPackage.Registry.INSTANCE.getEPackage(nsURI);
			if (p == null) {
				Activator.getDefault().getLog().log(
						new Status(Status.WARNING, Activator.PLUGIN_ID, "Couldn't find package with nsURI " + nsURI));
			} else {
				queryEnvironment.registerEPackage(p);
			}
		}
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.getRoot().getLocation();
		IContainer container = workspace.getRoot().findMember(generation.eResource().getURI().toPlatformString(true))
				.getParent();
		IFile templateFile = container.getFile(new Path(generation.getTemplateFileName()));
		IFile generatedFile = container.getFile(new Path(generation.getResultFileName()));
		if (!templateFile.exists()) {
			MessageDialog.openError(shell, "File not found", "Couldn't find file " + templateFile);
			return;
		}
		FileInputStream is = new FileInputStream(templateFile.getLocation().toFile());
		OPCPackage oPackage;
		try {
			oPackage = OPCPackage.open(is);
		} catch (InvalidFormatException e) {
			throw new IllegalArgumentException("Couldn't open template", e);
		}
		XWPFDocument document = new XWPFDocument(oPackage);
		DocumentParser parser = new DocumentParser(document, queryEnvironment);
		DocumentTemplate template = parser.parseDocument();
		DocumentGenerator generator = new DocumentGenerator(templateFile.getLocation().toFile().getAbsolutePath(),
				generatedFile.getLocation().toFile().getAbsolutePath(), template, definitions, queryEnvironment);
		generator.generate();
		MessageDialog.openConfirm(shell, "WGen generation",
				"document " + generatedFile.getLocation().toString() + " generated");
	}

	/**
	 * Creates a definition map from an
	 * 
	 * @param generation
	 * @return the created map.
	 */
	private Map<String, Object> createDefinitions(Generation generation) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (Definition def : generation.getDefinitions()) {
			if (def instanceof ModelDefinition) {
				result.put(((ModelDefinition) def).getKey(), ((ModelDefinition) def).getValue());
			} else if (def instanceof StringDefinition) {
				result.put(((StringDefinition) def).getKey(), ((StringDefinition) def).getValue());
			} else {
				throw new UnsupportedOperationException();
			}
		}
		return result;
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
