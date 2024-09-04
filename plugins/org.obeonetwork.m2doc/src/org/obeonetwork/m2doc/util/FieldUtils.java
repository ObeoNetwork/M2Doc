/*******************************************************************************
 *  Copyright (c) 2016, 2024 Obeo. 
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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.obeonetwork.m2doc.parser.TokenProvider;
import org.obeonetwork.m2doc.util.SequenceField.Type;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

/**
 * DocX Field tools.
 * Used to extract field value.
 * 
 * @author ohaegi
 */
public class FieldUtils {

    /**
     * The SEQ {@link Pattern}.
     */
    // CHECKSTYLE:OFF
    private static final Pattern SEQ_PATTERN = Pattern.compile("\\s+SEQ\\s+([A-Za-z0-9_-]+)\\s+\\\\\\*\\s+("
        + Type.ALPHABETIC.getType() + "|" + Type.ALPHABETIC_CAP.getType() + "|" + Type.ARABIC.getType() + "|"
        + Type.ROMAN.getType() + "|" + Type.ROMAN_CAP.getType() + ")\\s+");
    // CHECKSTYLE:ON

    /**
     * The sequence name index in {@link #SEQ_PATTERN}.
     */
    private static final int NAME_INDEX = 1;

    /**
     * The sequence type index in {@link #SEQ_PATTERN}.
     */
    private static final int TYPE_INDEX = 2;

    /**
     * read up a tag looking ahead the runs so that a prediction can be made
     * over the nature of a field.
     * <p>
     * Using such a method is mandatory because for some reasons fields like
     * {m:if ...} can be broken up in an unexpected number of runs thus
     * precluding the tag nature prediction based on the first run only.
     * </p>
     * 
     * @param runIterator
     *            run iterator
     * @return the complete text of the current field.
     */
    public String lookAheadTag(final TokenProvider runIterator) {
        int i = 1;
        // first run must begin a field.
        XWPFRun run = runIterator.lookAhead(i).getRun();
        if (run == null) {
            throw new IllegalStateException("lookAheadTag shouldn't be called on a table.");
        }
        if (isFieldBegin(run)) {
            StringBuilder builder = new StringBuilder();
            i++;
            run = runIterator.lookAhead(i).getRun();
            // run is null when EOF is reached or a table is encountered.
            while (run != null && !isFieldEnd(run)) {
                builder.append(readUpInstrText(run));
                run = runIterator.lookAhead(++i).getRun();
            }
            return builder.toString().trim();
        } else {
            return "";
        }
    }

    /**
     * Returns <code>true</code> when the specified run is a field begin run and <code>false</code> otherwise.
     * 
     * @param run
     *            the run
     * @return <code>true</code> for field begin.
     */
    public boolean isFieldBegin(XWPFRun run) {
        if (run.getCTR().getFldCharList().size() > 0) {
            CTFldChar fldChar = run.getCTR().getFldCharList().get(0);
            return STFldCharType.BEGIN.equals(fldChar.getFldCharType());
        } else {
            return false;
        }
    }

    /**
     * Returns <code>true</code> when the specified {@link CTR} is a field begin run and <code>false</code> otherwise.
     * 
     * @param ctr
     *            the {@link CTR}
     * @return <code>true</code> for field begin.
     */
    public boolean isFieldBegin(CTR ctr) {
        if (ctr.getFldCharList().size() > 0) {
            CTFldChar fldChar = ctr.getFldCharList().get(0);
            return STFldCharType.BEGIN.equals(fldChar.getFldCharType());
        } else {
            return false;
        }
    }

    /**
     * Returns <code>true</code> when the specified {@link CTR} is a field separate run and <code>false</code> otherwise.
     * 
     * @param ctr
     *            the {@link CTR}
     * @return <code>true</code> for field separate.
     */
    public boolean isFieldSeparate(CTR ctr) {
        if (ctr.getFldCharList().size() > 0) {
            CTFldChar fldChar = ctr.getFldCharList().get(0);
            return STFldCharType.SEPARATE.equals(fldChar.getFldCharType());
        } else {
            return false;
        }
    }

    /**
     * Returns <code>true</code> when the specified {@link CTR} is a field end run and <code>false</code> otherwise.
     * 
     * @param ctr
     *            the {@link CTR}
     * @return <code>true</code> for field end.
     */
    public boolean isFieldEnd(CTR ctr) {
        if (ctr.getFldCharList().size() > 0) {
            CTFldChar fldChar = ctr.getFldCharList().get(0);
            return STFldCharType.END.equals(fldChar.getFldCharType());
        } else {
            return false;
        }
    }

    /**
     * Gets the {@link SequenceField} for the given {@link CTR}.
     * 
     * @param run
     *            the {@link CTR}
     * @return the {@link SequenceField} for the given {@link CTR}
     */
    public SequenceField getSequenceField(CTR run) {
        final SequenceField res;

        if (isFieldBegin(run)) {
            try (XmlCursor cursor = run.newCursor()) {
                if (cursor.toNextSibling() && cursor.getObject() instanceof CTR
                    && ((CTR) cursor.getObject()).getInstrTextArray().length == 1) {
                    final CTText instr = ((CTR) cursor.getObject()).getInstrTextArray()[0];
                    if (instr.getStringValue() != null) {
                        res = getSequenceFieldFromStart(cursor, instr.getStringValue());
                    } else {
                        res = null;
                    }
                } else {
                    res = null;
                }
            }
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the {@link SequenceField} from the start of the field.
     * 
     * @param cursor
     *            the {@link XmlCursor}
     * @param instr
     *            the instr text
     * @return the {@link SequenceField} from the start of the field if any, <code>null</code> otherwise
     */
    private SequenceField getSequenceFieldFromStart(XmlCursor cursor, String instr) {
        final SequenceField res;
        final Matcher matcher = SEQ_PATTERN.matcher(instr);
        if (matcher.matches()) {
            final String name = matcher.group(NAME_INDEX);
            final Type type = Type.getType(matcher.group(TYPE_INDEX));
            if (cursor.toNextSibling() && cursor.getObject() instanceof CTR
                && isFieldSeparate((CTR) cursor.getObject())) {
                if (cursor.toNextSibling() && cursor.getObject() instanceof CTR
                    && ((CTR) cursor.getObject()).getTArray().length == 1) {
                    final CTText indexText = ((CTR) cursor.getObject()).getTArray()[0];
                    res = new SequenceField(name, type, indexText);
                } else {
                    res = null;
                }
            } else {
                res = null;
            }
        } else {
            res = null;
        }
        return res;
    }

    /**
     * Returns <code>true</code> when the specified run is a field end run and <code>false</code> otherwise.
     * 
     * @param run
     *            the concerned run
     * @return <code>true</code> for field end.
     */

    public boolean isFieldEnd(XWPFRun run) {
        return isFieldEnd(run.getCTR());
    }

    /**
     * reads up the instruction of a field's run.
     * 
     * @param run
     *            the run to read.
     * @return the aggregated instruction text of the run
     */
    public static StringBuilder readUpInstrText(XWPFRun run) {
        List<CTText> texts = run.getCTR().getInstrTextList();
        StringBuilder runBuilder = new StringBuilder();
        for (CTText text : texts) {
            runBuilder.append(text.getStringValue());
        }
        return runBuilder;
    }
}
