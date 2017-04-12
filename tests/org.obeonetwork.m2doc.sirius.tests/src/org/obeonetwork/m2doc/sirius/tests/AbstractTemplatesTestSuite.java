package org.obeonetwork.m2doc.sirius.tests;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.sirius.M2DocSiriusUtils;
import org.obeonetwork.m2doc.sirius.providers.SiriusDiagramByTitleProvider;
import org.obeonetwork.m2doc.sirius.services.M2DocSiriusServices;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * Run a folder with templates as a test suite JUnit with Sirius support.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractTemplatesTestSuite extends org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite {

    /**
     * The instance of {@link SiriusDiagramByTitleProvider}.
     */
    private static final SiriusDiagramByTitleProvider PROVIDER = new SiriusDiagramByTitleProvider();

    /**
     * The instance of M2DocSiriusServices.
     */
    private M2DocSiriusServices m2DocSiriusServices;

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
     * Registers the {@link SiriusDiagramByTitleProvider}.
     */
    @BeforeClass
    public static void beforeClass() {
        org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite.beforeClass();
        ProviderRegistry.INSTANCE.registerProvider(PROVIDER);
    }

    /**
     * Unregisters the {@link SiriusDiagramByTitleProvider}.
     * 
     * @throws IOException
     *             if the {@link DocumentTemplate} can't be closed
     */
    @AfterClass
    public static void afterClass() throws IOException {
        org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite.afterClass();
        ProviderRegistry.INSTANCE.removeProvider(PROVIDER);
    }

    @Override
    protected void setTemplateFileName(Generation gen, String templateFileName) {
        super.setTemplateFileName(gen, templateFileName);
        gen.setRepresentationsFileName(getSessionURI(new File(getTestFolderPath())).lastSegment());
    }

    @Override
    protected IQueryEnvironment createEnvironment(URI templateURI) {
        final IQueryEnvironment res = super.createEnvironment(templateURI);

        m2DocSiriusServices = M2DocSiriusUtils.prepareEnvironmentServices(res, initSession());

        return res;
    }

    /**
     * Initializes the Sirius {@link Session}.
     * 
     * @return the Sirius {@link Session}
     */
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

    @Override
    public void generation() throws Exception {
        super.generation();
        m2DocSiriusServices.clean();
    }

}
