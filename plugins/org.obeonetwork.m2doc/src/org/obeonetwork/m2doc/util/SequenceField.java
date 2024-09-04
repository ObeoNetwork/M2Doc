/*******************************************************************************
 *  Copyright (c) 2024 Obeo. 
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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

/**
 * Representation of a <code>SEQ</code> field.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class SequenceField {

    /**
     * The alphabetic base.
     */
    private static final int ALPHABETIC_BASE = 26;

    /**
     * The mapping of an integer to its Roman notation.
     */
    private static final Map<Integer, String> ROMAN_MAP = initializeRomanMap();

    /**
     * Numbering type.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    enum Type {

        /** Roman. */
        ROMAN("roman"),
        /** Roman capital. */
        ROMAN_CAP("ROMAN"),
        /** Arabic. */
        ARABIC("ARABIC"),
        /** Alphabetic. */
        ALPHABETIC("alphabetic"),
        /** Alphabetic capital. */
        ALPHABETIC_CAP("ALPHABETIC");

        /**
         * The mapping from {@link String} representation of the {@link Type} to the {@link Type} itself.
         */
        private static final Map<String, Type> MAP = initializeStringTypes();

        /**
         * The {@link String} representation type.
         */
        private final String type;

        /**
         * Constructor.
         * 
         * @param type
         *            the {@link String} representation of the {@link Type}.
         */
        Type(String type) {
            this.type = type;
        }

        /**
         * Initializes the mapping from {@link String} representation of the {@link Type} to the {@link Type} itself.
         * 
         * @return the mapping from {@link String} representation of the {@link Type} to the {@link Type} itself
         */
        private static Map<String, Type> initializeStringTypes() {
            final Map<String, Type> res = new HashMap<>();

            res.put(ROMAN.type, ROMAN);
            res.put(ROMAN_CAP.type, ROMAN_CAP);
            res.put(ARABIC.type, ARABIC);
            res.put(ALPHABETIC.type, ALPHABETIC);
            res.put(ALPHABETIC_CAP.type, ALPHABETIC_CAP);

            return res;
        }

        /**
         * Gets the {@link Type} corresponding to the given {@link String} representation of the {@link Type}.
         * 
         * @param type
         *            the {@link String} representation of the {@link Type}
         * @return the {@link Type} corresponding to the given {@link String} representation of the {@link Type} if any, <code>null</code>
         *         otherwise
         */
        public static Type getType(String type) {
            return MAP.get(type);
        }

        /**
         * Gets the {@link String} representation for the {@link Type}.
         * 
         * @return the {@link String} representation for the {@link Type}
         */
        public String getType() {
            return type;
        }

    }

    /**
     * The sequence name.
     */
    private final String name;

    /**
     * The sequence numbering {@link Type}.
     */
    private final Type type;

    /**
     * The index {@link CTText}.
     */
    private final CTText indexText;

    /**
     * Constructor.
     * 
     * @param name
     *            the sequence name
     * @param type
     *            the {@link Type}
     * @param indexText
     *            the sequence index {@link CTText}
     */
    public SequenceField(String name, SequenceField.Type type, CTText indexText) {
        this.name = name;
        this.type = type;
        this.indexText = indexText;
    }

    /**
     * Initializes the mapping from integer to Roman notation.
     * 
     * @return the mapping from integer to Roman notation
     */
    private static Map<Integer, String> initializeRomanMap() {
        final Map<Integer, String> res = new LinkedHashMap<>();

        // CHECKSTYLE:OFF
        res.put(1000, "M");
        res.put(900, "CM");
        res.put(500, "D");
        res.put(400, "CD");
        res.put(100, "C");
        res.put(90, "XC");
        res.put(50, "L");
        res.put(40, "XL");
        res.put(10, "X");
        res.put(9, "IX");
        res.put(5, "V");
        res.put(4, "IV");
        res.put(1, "I");
        // CHECKSTYLE:ON

        return res;
    }

    /**
     * Gets the sequence name.
     * 
     * @return the sequence name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the sequence to the given index.
     * 
     * @param index
     *            the index
     */
    public void setIndex(int index) {
        switch (type) {
            case ROMAN:
                indexText.setStringValue(getRoman(index));
                break;

            case ROMAN_CAP:
                indexText.setStringValue(getRoman(index).toUpperCase());
                break;

            case ARABIC:
                indexText.setStringValue(String.valueOf(index));
                break;

            case ALPHABETIC:
                indexText.setStringValue(getAlphabetic(index));
                break;

            case ALPHABETIC_CAP:
                indexText.setStringValue(getAlphabetic(index).toUpperCase());
                break;

            default:
                break;
        }
    }

    /**
     * Gets the Roman representation of the given index.
     * 
     * @param index
     *            the index
     * @return the Roman representation of the given index
     */
    private String getRoman(int index) {
        final StringBuilder res = new StringBuilder();

        int localIndex = index;
        for (Entry<Integer, String> entry : ROMAN_MAP.entrySet()) {
            while (localIndex >= entry.getKey()) {
                localIndex -= entry.getKey();
                res.append(entry.getValue());
            }
        }

        return res.toString();
    }

    /**
     * Gets the alphabetic representation of the given index.
     * 
     * @param index
     *            the index
     * @return the alphabetic representation of the given index
     */
    private String getAlphabetic(int index) {
        final StringBuilder res = new StringBuilder();

        int localIndex = index;
        while (--localIndex >= 0) {
            res.insert(0, Character.valueOf((char) ('a' + localIndex % ALPHABETIC_BASE)));
            localIndex /= ALPHABETIC_BASE;
        }
        return res.toString();
    }

}
