/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.generator.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.generator.TemplateProcessor;

/**
 * Test the {@link TemplateProcessor} class.
 * 
 * @author ohaegi
 */
public class TemplateProcessorUserDocRemplaceTest {
    /**
     * Root object of the genconf example model.
     */
    private EObject rootObject;

    /**
     * Query environment.
     */
    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

    /**
     * Create Destination Document.
     * 
     * @param inputDocumentFileName
     *            inputDocumentFileName
     * @return XWPFDocument
     * @throws IOException
     *             IOException
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
    private XWPFDocument createDestinationDocument(String inputDocumentFileName)
            throws IOException, InvalidFormatException {
        FileInputStream is = new FileInputStream(inputDocumentFileName);
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        int size = document.getBodyElements().size();
        for (int i = 0; i < size; i++) {
            document.removeBodyElement(0);
        }
        return document;
    }

    /**
     * Setup.
     */
    @Before
    public void setup() {
        ResourceSet rs = new ResourceSetImpl();
        rs.getPackageRegistry().put(GenconfPackage.eNS_URI, GenconfPackage.eINSTANCE);

        Registry r = rs.getResourceFactoryRegistry();
        Map<String, Object> m = r.getExtensionToFactoryMap();
        m.put("genconf", new XMIResourceFactoryImpl());

        URI uri = URI.createFileURI(new File("resources/semantic.genconf").getAbsolutePath());
        Resource resource = rs.getResource(uri, true);
        rootObject = resource.getContents().get(0);
    }

    /**
     * Load doc from path.
     * 
     * @param docPath
     *            resultPath
     * @return document
     * @throws FileNotFoundException
     *             FileNotFoundException
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     */
    private XWPFDocument loadDoc(String docPath) throws FileNotFoundException, InvalidFormatException, IOException {
        FileInputStream is = new FileInputStream(docPath);
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        return document;
    }
}
