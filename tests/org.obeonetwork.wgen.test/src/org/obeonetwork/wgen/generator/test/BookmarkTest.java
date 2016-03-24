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
package org.obeonetwork.wgen.generator.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.obeonetwork.wgen.generator.DocumentGenerationException;
import org.obeonetwork.wgen.parser.DocumentParserException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;

public class BookmarkTest {

	public BookmarkTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testStaticFragmentWithFieldProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		FileInputStream is = new FileInputStream("templates/test2.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		// insert some static content.
		document.createParagraph().createRun().setText("static part that will not contain any link");
		CTBookmark bookmark = document.getDocument().getBody().addNewBookmarkStart();
		bookmark.setName("bookmark1");
		bookmark.setId(new BigInteger("66"));
		document.createParagraph().createRun().setText("bookmarked part");
		CTMarkupRange range = document.getDocument().getBody().addNewBookmarkEnd();
		range.setId(new BigInteger("66"));
		document.createParagraph().createRun().setText("another static part that will not contain any link");
		// save the document in another file
		FileOutputStream fos = new FileOutputStream("results/bookmarkTest.docx");
		document.write(fos);
		document.close();
		fos.close();
	}

}
