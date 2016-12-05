package org.obeonetwork.m2doc.sirius.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.parser.DocumentParserException;

public abstract class AbstractTemplatesTestSuite extends org.obeonetwork.m2doc.test.AbstractTemplatesTestSuite {

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

    private Session initSession() {
        final Session res;

        final File sessionFile = getSessionFile(new File(getTestFolderPath()));
        if (sessionFile.exists()) {
            final Session s = SessionManager.INSTANCE.getSession(URI.createFileURI(sessionFile.getAbsolutePath()),
                    new NullProgressMonitor());
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
    protected Generation getGeneration(File genconfFile, ResourceSet rs) {
        final Generation res;

        if (session != null) {
            session.addSemanticResource(
                    URI.createFileURI(getGenconfFile(new File(getTestFolderPath())).getAbsolutePath()),
                    new NullProgressMonitor());
            Generation foundGeneration = null;
            for (Resource r : session.getSemanticResources()) {
                if (r.getURI().toString().endsWith(".genconf")) {
                    foundGeneration = (Generation) r.getContents().get(0);
                }
            }
            if (foundGeneration != null) {
                res = foundGeneration;
            } else {
                res = super.getGeneration(genconfFile, rs);
            }
        } else {
            res = super.getGeneration(genconfFile, rs);
        }

        return res;
    }

    /**
     * Gets the session file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the session file from the test folder path
     */
    protected File getSessionFile(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + ".aird");
    }

}
