package org.obeonetwork.m2doc.sirius.tests;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.sirius.providers.SiriusDiagramByTitleProvider;

public abstract class AbstractTemplatesTestSuite extends org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite {

    private static final SiriusDiagramByTitleProvider PROVIDER = new SiriusDiagramByTitleProvider();

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

}
