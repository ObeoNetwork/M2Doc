/*******************************************************************************
 *  Copyright (c) 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.m2doc.generator.GenerationResult;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.util.HtmlSerializer;

//@formatter:off
@ServiceProvider(
value = "Services available for GenerationResult after a template construct call."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class GenerationResultServices {

    /**
     * The {@link HtmlSerializer}.
     */
    private static final HtmlSerializer HTML_SERIALIZER = new HtmlSerializer();

    // @formatter:off
    @Documentation(
        value = "Insert the text representation of the given GenerationResult.",
        params = {
            @Param(name = "result", value = "The generation result"),
        },
        result = "insert the text representation of the given GenerationResult",
        examples = {
            @Example(expression = "self.myTemplate().asText()", result = "the text representation of the given GenerationResult"),
        }
    )
    // @formatter:on
    public String asText(GenerationResult result) throws IOException {
        final String res;

        if (result.getBody() instanceof XWPFDocument) {
            // we do the serialization to synchronize the XML content of the document with the POI API.
            final byte[] serialized = serializeDocument((XWPFDocument) result.getBody());
            try (XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(serialized));
                    XWPFWordExtractor extractor = new XWPFWordExtractor(document);) {

                final String text = extractor.getText();
                if (text.endsWith("\n")) {
                    res = text.substring(0, text.length() - 1);
                } else {
                    res = text;
                }
            }
        } else {
            res = null;
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Insert the HTML representation of the given GenerationResult.",
        params = {
            @Param(name = "result", value = "The generation result"),
        },
        result = "insert the HTML representation of the given GenerationResult",
        examples = {
            @Example(expression = "self.myTemplate().asHTML()", result = "the HTML representation of the given GenerationResult"),
        }
    )
    // @formatter:on
    public String asHTML(GenerationResult result) throws IOException {
        final String res;

        if (result.getBody() instanceof XWPFDocument) {
            // we do the serialization to synchronize the XML content of the document with the POI API.
            final byte[] serialized = serializeDocument((XWPFDocument) result.getBody());
            try (XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(serialized));
                    XWPFWordExtractor extractor = new XWPFWordExtractor(document);) {
                res = HTML_SERIALIZER.serialize(document);
            }
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Serializes the given {@link DocumentTemplate}.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate} to serialize
     * @return the byte array of the serialized {@link DocumentTemplate}
     */
    protected byte[] serializeDocument(XWPFDocument document) {
        byte[] serializedDocument;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            document.write(output);
            serializedDocument = output.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            serializedDocument = null;
        }
        return serializedDocument;
    }

}
