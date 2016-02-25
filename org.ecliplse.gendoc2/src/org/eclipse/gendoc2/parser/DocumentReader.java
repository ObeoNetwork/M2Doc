package org.eclipse.gendoc2.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

public class DocumentReader {

	private OPCPackage oPackageSrc;
	private OPCPackage oPackageDst;

	private XWPFDocument documentSrc;
	private XWPFDocument documentDst;
	private String documentFileName;

	public DocumentReader(String documentFileName) throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream(documentFileName);
		oPackageSrc = OPCPackage.open(is);
		documentSrc = new XWPFDocument(oPackageSrc);
		is.close();
		is = new FileInputStream(documentFileName);
		oPackageDst = OPCPackage.open(is);
		documentDst = new XWPFDocument(oPackageDst);
		int size = documentDst.getBodyElements().size();
		for (int i = 0; i < size; i++) {
			documentDst.removeBodyElement(i);
		}
		this.documentFileName = documentFileName;
	}

	public void printRuns() {
		int i = 0;
		// on double tous les paragraphes
		List<XWPFParagraph> newPara = new ArrayList<XWPFParagraph>();
		for (XWPFParagraph p : documentSrc.getParagraphs()) {
			for (XWPFRun run : p.getRuns()) {
				System.out.println("run " + i++);
				if (run.getCTR().getFldCharList().size() > 0) {
					// The next line of code allows to get to the field char
					// type and detect the begining and the end of a field :
					// run.getCTR().getFldCharList().get(0).getFldCharType()
					System.out.println("field char type : " + run.getCTR().getFldCharList().get(0).getFldCharType());
				} else if (run.getCTR().getInstrTextList().size() > 0) {
					// The next line of code allows to get to the parameters of
					// a field (white space separated) :
					// run.getCTR().getInstrTextList().get(0).stringValue()
					// in a field like '{gd:for e | e.eReferences}' the
					// parameter returned will be ['gd:for' 'e' '|'
					// 'e.eReferences'].
					System.out.println(run.getCTR().getInstrTextList().get(0).stringValue());
				} else {
					System.out.println(run.getText(run.getTextPosition()));
				}
			}
		}
	}

	public void copyParagraphs1() throws FileNotFoundException, IOException {
		int i = 0;
		// we just copy the first paragraph
		XWPFParagraph pargraph = documentSrc.getParagraphs().get(1);
		XWPFParagraph outsidecopy = new XWPFParagraph((CTP) pargraph.getCTP().copy(), pargraph.getBody());
		outsidecopy.createRun().setText("NEW PARAGRAPH");
		XWPFParagraph insidecopy = documentSrc.insertNewParagraph(pargraph.getCTP().newCursor());
		documentSrc.setParagraph(outsidecopy, 1);
		documentSrc.write(new FileOutputStream(this.documentFileName));
	}

	public void copyParagraphs() throws FileNotFoundException, IOException {
		int i = 0;
		// we just copy the first paragraph
		XWPFParagraph paragraph = documentSrc.getParagraphs().get(1);
		XWPFParagraph copy = new XWPFParagraph((CTP) paragraph.getCTP().copy(), documentDst);
		copy.createRun().setText("NEW PARAGRAPH");
		documentDst.createParagraph();
		documentDst.setParagraph(copy, 0);
		documentDst.write(new FileOutputStream("templates/result.docx"));
	}

	public static void main(String args[]) throws InvalidFormatException, IOException {
		new DocumentReader("templates/testCopy.docx").copyParagraphs();
		System.out.println("done.");
	}
}
