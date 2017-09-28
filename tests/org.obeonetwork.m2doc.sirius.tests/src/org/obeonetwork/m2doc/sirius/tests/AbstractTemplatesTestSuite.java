package org.obeonetwork.m2doc.sirius.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.sirius.M2DocSiriusUtils;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * Run a folder with templates as a test suite JUnit with Sirius support.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractTemplatesTestSuite extends org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite {

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
        // make sure m2doc.ide is activated
        M2DocPlugin.getPlugin();
    }

    /**
     * Registers the {@link SiriusDiagramByTitleProvider}.
     */
    @BeforeClass
    public static void beforeClass() {
        org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite.beforeClass();
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
    }

    @Override
    protected void setTemplateFileName(Generation gen, String templateFileName) {
        super.setTemplateFileName(gen, templateFileName);
        final Option option = GenconfUtils.getOrCreateOption(gen, M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        option.setValue(getSessionURI(new File(getTestFolderPath())).deresolve(gen.eResource().getURI()).toString());
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
    }

}
