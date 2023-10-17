/*******************************************************************************
 *  Copyright (c) 2019, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

/**
 * Generates a sample template.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class SampleTemplateGenerator {

    /**
     * The variable name tag.
     */
    private static final String VARIABLE_NAME_TAG = "VARIABLE_NAME";

    /**
     * The feature name tag.
     */
    private static final String FEATURE_NAME_TAG = "FEATURE_NAME";

    /**
     * The buffer size.
     */
    private static final int BUFFER_SIZE = 1024 * 8;

    /**
     * Creates the sample template {@link XWPFDocument}. The returned {@link XWPFDocument} should be {@link XWPFDocument#close() closed} by
     * the
     * caller.
     * 
     * @param variableName
     *            the variable name
     * @param eCls
     *            the variable {@link EClass}
     * @return the created sample template {@link XWPFDocument}
     * @throws IOException
     *             if the sample template can't be read
     * @throws InvalidFormatException
     *             if the sample template can't be read
     */
    @SuppressWarnings("resource")
    public XWPFDocument generate(String variableName, EClass eCls) throws InvalidFormatException, IOException {
        final InputStream is = SampleTemplateGenerator.class.getResourceAsStream("/resources/sampleTemplate.docx");
        final OPCPackage pkg = OPCPackage.open(is);

        String featureName;
        if (!eCls.getEAllAttributes().isEmpty()) {
            featureName = eCls.getEAllAttributes().get(0).getName();
            for (EAttribute attribute : eCls.getEAllAttributes()) {
                if (attribute.getEType() == EcorePackage.eINSTANCE.getEString()) {
                    featureName = attribute.getName();
                    break;
                }
            }
        } else {
            featureName = null;
        }

        final StringBuilder builder = new StringBuilder();
        final byte[] buffer = new byte[BUFFER_SIZE];
        final PackagePart part = pkg.getPart(PackagingURIHelper.createPartName("/word/document.xml"));
        try (InputStream partIS = part.getInputStream()) {
            int nbBytes = partIS.read(buffer);
            while (nbBytes != -1) {
                builder.append(new String(buffer, 0, nbBytes));
                nbBytes = partIS.read(buffer);
            }
        }
        String xml;
        if (featureName != null) {
            xml = builder.toString().replace(VARIABLE_NAME_TAG, variableName);
            xml = xml.replace(FEATURE_NAME_TAG, featureName);
        } else {
            xml = builder.toString().replace(VARIABLE_NAME_TAG, variableName + " EClass");
            xml = xml.replace(FEATURE_NAME_TAG, "eClass().name");
        }

        try (OutputStream partOS = part.getOutputStream()) {
            partOS.write(xml.getBytes("UTF-8"));
        }

        final XWPFDocument res = new XWPFDocument(pkg);

        final TemplateCustomProperties customProperties = new TemplateCustomProperties(res);
        customProperties.setM2DocVersion(M2DocUtils.VERSION);
        customProperties.getVariables().put(variableName, eCls.getEPackage().getName() + "::" + eCls.getName());
        final Set<String> packages = new LinkedHashSet<>();
        packages.add(eCls.getEPackage().getNsURI());
        for (EClass superCls : eCls.getEAllSuperTypes()) {
            packages.add(superCls.getEPackage().getNsURI());
        }
        customProperties.getPackagesURIs().addAll(packages);
        customProperties.save();

        return res;
    }

}
