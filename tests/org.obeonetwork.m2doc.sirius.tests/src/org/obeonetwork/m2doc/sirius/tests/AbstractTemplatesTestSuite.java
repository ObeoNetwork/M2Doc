package org.obeonetwork.m2doc.sirius.tests;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.sirius.providers.SiriusDiagramByTitleProvider;

public abstract class AbstractTemplatesTestSuite extends org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite {

    private final static SiriusDiagramByTitleProvider PROVIDER = new SiriusDiagramByTitleProvider();

    private Session session = null;

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder path
     * @throws IOException
     *             if the tested template can't be read
     * @throws DocumentParserException
     *             if the tested template can't be parsed
     */
    public AbstractTemplatesTestSuite(String testFolder) throws IOException, DocumentParserException {
        super(testFolder);
    }

    /**
     * Register
     */
    @BeforeClass
    public static void beforeClass() {
        org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite.beforeClass();
        ProviderRegistry.INSTANCE.registerProvider(PROVIDER);
    }

    /**
     * 
     */
    @AfterClass
    public static void afterClass() throws IOException {
        org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite.afterClass();
        ProviderRegistry.INSTANCE.removeProvider(PROVIDER);
    }

    @Override
    protected void setTemplateFileName(final Generation gen, final String templateFileName) {
        final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gen);
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                AbstractTemplatesTestSuite.super.setTemplateFileName(gen, templateFileName);
            }
        });

    }

    private Session initSession() {
        final Session res;

        final URI sessionURI = getSessionURI(new File(getTestFolderPath()));
        if (URIConverter.INSTANCE.exists(sessionURI, Collections.emptyMap())) {
            final Session s = SessionManager.INSTANCE.getSession(sessionURI, new NullProgressMonitor());
            s.open(new NullProgressMonitor());
            res = s;
        } else {
            res = null;
        }

        return res;
    }

    @Override
    protected ResourceSet getResourceSet() {
        final ResourceSet res;

        session = initSession();
        if (session != null) {
            res = session.getSessionResource().getResourceSet();
        } else {
            res = super.getResourceSet();
        }

        return res;
    }

    @Override
    protected Generation getGeneration(final URI genconfURI, ResourceSet rs) {
        final Generation res;

        if (session != null) {
            session.getTransactionalEditingDomain().getCommandStack()
                    .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

                        @Override
                        protected void doExecute() {
                            session.addSemanticResource(genconfURI, new NullProgressMonitor());
                        }
                    });
            Generation foundGeneration = null;
            for (Resource r : session.getSemanticResources()) {
                if (r.getURI().toString().endsWith(".genconf")) {
                    foundGeneration = (Generation) r.getContents().get(0);
                }
            }
            if (foundGeneration != null) {
                res = foundGeneration;
            } else {
                res = super.getGeneration(genconfURI, rs);
            }
        } else {
            res = super.getGeneration(genconfURI, rs);
        }

        return res;
    }

    /**
     * Gets the session {@link URI} from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the session {@link URI} from the test folder path
     */
    protected URI getSessionURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + ".aird");
    }

}
