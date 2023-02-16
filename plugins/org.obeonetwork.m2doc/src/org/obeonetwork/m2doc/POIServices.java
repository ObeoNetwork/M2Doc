/*******************************************************************************
 *  Copyright (c) 2016, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

/**
 * POI services.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public final class POIServices {

    /**
     * Instance.
     */
    private static final POIServices INSTANCE = new POIServices();

    /**
     * Constructor.
     */
    private POIServices() {
        super();
    }

    /**
     * return the instance.
     * 
     * @return the instance
     */
    public static POIServices getInstance() {
        return INSTANCE;
    }

    /**
     * Get XWPFDocument from template file.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            the template {@link URI}
     * @return XWPFDocument
     * @throws IOException
     *             IOException
     */
    @SuppressWarnings("resource")
    public XWPFDocument getXWPFDocument(URIConverter uriConverter, URI templateURI) throws IOException {
        OPCPackage oPackage = getOPCPackage(uriConverter, templateURI);
        XWPFDocument document = new XWPFDocument(oPackage);
        return document;
    }

    /**
     * Get OPCPackage from template file.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            the template {@link URI}
     * @return OPCPackage
     * @throws IOException
     *             IOException
     */
    private OPCPackage getOPCPackage(URIConverter uriConverter, URI templateURI) throws IOException {
        OPCPackage oPackage;
        try (InputStream is = uriConverter.createInputStream(templateURI)) {
            try {
                oPackage = OPCPackage.open(is);

            } catch (InvalidFormatException e) {
                throw new IllegalArgumentException("Couldn't open template file", e);
            }
        }
        return oPackage;
    }

    /**
     * Get template informations.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            the template {@link URI}
     * @return TemplateInfo
     * @throws IOException
     *             IOException
     */
    public TemplateCustomProperties getTemplateCustomProperties(URIConverter uriConverter, URI templateURI)
            throws IOException {
        final TemplateCustomProperties res;

        try (XWPFDocument document = getXWPFDocument(uriConverter, templateURI);) {
            res = new TemplateCustomProperties(document);
        }
        return res;
    }

    /**
     * Save the document into the file pointing at the given path.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param document
     *            the validated document to save.
     * @param theDestinationURI
     *            the {@link URI} were to save the content of the validated document.
     * @throws IOException
     *             throws if the writing of the {@link URI} fails.
     */
    public void saveFile(URIConverter uriConverter, XWPFDocument document, URI theDestinationURI) throws IOException {
        try (OutputStream os = uriConverter.createOutputStream(theDestinationURI)) {
            document.write(os);
        }
    }

    /**
     * Marks the {@link CTSimpleField} of the given {@link IBody} as
     * {@link CTSimpleField#setDirty(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum) dirty}.
     * 
     * @param body
     *            the {@link IBody}
     */
    public void markFieldsAsDirty(IBody body) {
        if (body instanceof XWPFDocument) {
            final XWPFDocument document = (XWPFDocument) body;
            for (XWPFHeader header : document.getHeaderList()) {
                markFieldsAsDirty(header);
            }
            for (XWPFFooter footer : document.getFooterList()) {
                markFieldsAsDirty(footer);
            }
        }
        for (IBodyElement element : body.getBodyElements()) {
            markFieldsAsDirty(element);
        }
    }

    /**
     * Marks the {@link CTSimpleField} of the given {@link IBodyElement} as
     * {@link CTSimpleField#setDirty(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum) dirty}.
     * 
     * @param element
     *            the {@link IBodyElement}
     */
    public void markFieldsAsDirty(IBodyElement element) {
        if (element instanceof XWPFParagraph) {
            final XWPFParagraph paragraph = (XWPFParagraph) element;
            final CTP ctp = paragraph.getCTP();
            if (ctp != null) {
                for (CTSimpleField field : ctp.getFldSimpleList()) {
                    final org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff onOff = org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff.Factory
                            .newInstance();
                    onOff.setStringValue("true");
                    field.setDirty(onOff);
                }
            }
            for (XWPFRun run : paragraph.getRuns()) {
                final CTR ctr = run.getCTR();
                if (ctr != null) {
                    for (CTFldChar field : ctr.getFldCharList()) {
                        final org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff onOff = org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff.Factory
                                .newInstance();
                        onOff.setStringValue("true");
                        field.setDirty(onOff);
                    }
                    if (run instanceof XWPFHyperlinkRun) {
                        final CTHyperlink ctHyperlink = ((XWPFHyperlinkRun) run).getCTHyperlink();
                        for (CTSimpleField field : ctHyperlink.getFldSimpleList()) {
                            final org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff onOff = org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff.Factory
                                    .newInstance();
                            onOff.setStringValue("true");
                            field.setDirty(onOff);
                        }
                        for (CTR ctrHyperlink : ctHyperlink.getRList()) {
                            for (CTFldChar field : ctrHyperlink.getFldCharList()) {
                                final org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff onOff = org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff.Factory
                                        .newInstance();
                                onOff.setStringValue("true");
                                field.setDirty(onOff);
                            }
                        }
                    }
                }
            }
        } else if (element instanceof XWPFTable) {
            final XWPFTable table = (XWPFTable) element;
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    markFieldsAsDirty(cell);
                }
            }
        }
    }

    /**
     * Creates a {@link XWPFTable} in the given {@link IBody}.
     * 
     * @param body
     *            the {@link IBody} to insert to
     * @return the created {@link XWPFTable}
     */
    public XWPFTable createTable(IBody body) {
        final XWPFTable res;

        if (body instanceof XWPFDocument) {
            final XWPFDocument document = (XWPFDocument) body;
            final CTTbl cttbl = document.getDocument().getBody().addNewTbl();
            res = new XWPFTable(cttbl, document);
            if (res.getRows().size() > 0) {
                res.removeRow(0);
            }
            document.insertTable(body.getBodyElements().size(), res);
        } else if (body instanceof XWPFHeaderFooter) {
            final XWPFHeaderFooter headerFooter = (XWPFHeaderFooter) body;
            final CTTbl cttbl = headerFooter._getHdrFtr().addNewTbl();
            res = new XWPFTable(cttbl, headerFooter);
            if (res.getRows().size() > 0) {
                res.removeRow(0);
            }
            headerFooter.insertTable(body.getBodyElements().size(), res);
        } else if (body instanceof XWPFTableCell) {
            final XWPFTableCell tCell = (XWPFTableCell) body;
            final CTTbl tbl = tCell.getCTTc().addNewTbl();
            res = new XWPFTable(tbl, tCell);
            if (res.getRows().size() > 0) {
                res.removeRow(0);
            }
            tCell.insertTable(body.getBodyElements().size(), res);
        } else {
            throw new UnsupportedOperationException("unknown type of IBody : " + body.getClass());
        }

        return res;
    }

}
