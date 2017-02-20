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
package org.obeonetwork.m2doc.parser;

import java.util.HashMap;
import java.util.Map;

import org.obeonetwork.m2doc.template.IConstruct;

import static org.obeonetwork.m2doc.util.M2DocUtils.validationError;

/**
 * This class handle parsing of generic tag options used by providers but not by the M2Doc core engine.
 * The syntax is the following:
 * 
 * <pre>
 * m:xxx key1:"Value for key1" key2:"Value for key2"
 * </pre>
 * 
 * The parsing will return a map <code>{"key1":"Value for key1", "key2":"Value for key2"}</code>
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class OptionParser {

    /**
     * The M2Doc escape character.
     */
    public static final char M2DOC_ESCAPE_CHARACTER = '\\';
    /**
     * The character delimiting a value. Value is enclosed by this character.
     */
    public static final char VALUE_DELIMITER_CHARACTER = '"';
    /**
     * The character used to separate the key from the value.
     */
    public static final char KEY_VALUE_SEPARATOR = ':';
    /**
     * Parsing index of the options string.
     */
    private int index;
    /**
     * True when parsing has read the last character of the options string.
     */
    private boolean finished;
    /**
     * True if the value has been parsed completely. I.e the final delimiter "'" has been reached.
     */
    private boolean valueParsedCompletely;
    /**
     * True if we have started to read a key.
     */
    private boolean keyDetected;
    /**
     * True if a key has been completely parsed.
     */
    private boolean keyParsed;

    /**
     * Parses all options of the given tag.
     * 
     * @param tag
     *            the tag to parse
     * @param tokenType
     *            the token type triggering the option retrieval.
     * @param optionGroupRank
     *            the rank of the option in the tag.
     * @param optionValGroupRank
     *            the rank of the option's value in the tag.
     * @param construct
     *            the result image where to put error messages.
     * @return the option map created.
     * @throws DocumentParserException
     *             if tag is syntactically invalid.
     */
    public Map<String, String> parseOptions(String tag, TokenType tokenType, int optionGroupRank,
            int optionValGroupRank, IConstruct construct) throws DocumentParserException {
        Map<String, String> result = new HashMap<>();
        // first match the tag:
        if (tag.trim().startsWith(tokenType.getValue())) {
            // remove the token type from the type to handle generically all options from the same string.
            int tokenLength = tokenType.getValue().length();
            String optionsString = tag.trim().substring(tokenLength).trim();
            index = 0;
            int maxIndex = optionsString.length();
            finished = false;
            String key = "";
            String value = "";
            // We stop reading options when we have parsed all the line.
            while (!finished) {
                key = parseKey(construct, optionsString, maxIndex);
                if (!finished) {
                    consumeOptionUntilValueDelimiter(construct, optionsString, maxIndex, key);
                    if (!finished) {
                        value = parseValue(construct, optionsString, maxIndex);
                        result.put(key, value);
                        // CHECKSTYLE:OFF
                        if (!valueParsedCompletely) {
                            validationError(construct,
                                    "The end delimiter for the option's value has not been reached. The option '" + key
                                        + "' may be invalid.");
                            // CHECKSTYLE:ON
                        }
                        key = "";
                        value = "";
                    } else {
                        result.put(key, value);
                    }
                } else if (!keyParsed && keyDetected) {
                    validationError(construct,
                            String.format(
                                    "The start of an option's key has been read but the end of it and the value were missing : '%s'.",
                                    key));
                    result.put(key.trim(), value);
                }
            }
        } else {
            throw new UnsupportedOperationException(String.format(
                    "the given tag '%s' should contains the given token type : '%s'", tag, tokenType.getValue()));
        }
        return result;
    }

    /**
     * Consume characters until we find the start value delimiter '"'. This method can adds parsing error message to the given construct if
     * some forbidden characters are present.
     * 
     * @param construct
     *            the template element were to put error parsing messages.
     * @param optionsString
     *            the string containing all options.
     * @param maxIndex
     *            the index of the last character of the options string.
     * @param key
     *            the previous parsed key.
     */
    private void consumeOptionUntilValueDelimiter(IConstruct construct, String optionsString, int maxIndex,
            String key) {
        boolean valueEnclosingQuoteReached = false;
        String forbiddenCharacters = "";
        while (!finished && !valueEnclosingQuoteReached) {
            char charAt = optionsString.charAt(index);
            if (!valueEnclosingQuoteReached && VALUE_DELIMITER_CHARACTER == charAt) {
                // We have encountered the first quote after the key/value separator.
                valueEnclosingQuoteReached = true;
            } else if (' ' != charAt) {
                // we have a forbidden character that is not a space between the key/value separator and the value
                // delimiter.
                forbiddenCharacters += charAt;
            }
            index++;
            finished = index >= maxIndex;
            if (!(!finished && !valueEnclosingQuoteReached) && !forbiddenCharacters.isEmpty()) {
                // We have finished the consuming but forbidden characters were present so we log an error message
                validationError(construct,
                        String.format(
                                "Forbidden characters (\"%s\") are present after the key value separator of the key : '%s'. Expected character is \"'\"",
                                forbiddenCharacters, key));
            }
        }
    }

    /**
     * Parse and returns the next option's value.
     * 
     * @param construct
     *            the template element were to put error parsing messages.
     * @param optionsString
     *            the string containing all options.
     * @param maxIndex
     *            the index of the last character of the options string.
     * @return the next option's value.
     */
    protected String parseValue(IConstruct construct, String optionsString, int maxIndex) {
        String value = "";
        valueParsedCompletely = false;
        boolean nextCharEscaped = false;
        while (!valueParsedCompletely && !finished) {
            char charAt = optionsString.charAt(index);
            // we are parsing the next available value.
            if (VALUE_DELIMITER_CHARACTER == charAt && !nextCharEscaped) {
                // The value delimiter quote has been reached.
                valueParsedCompletely = true;
            } else {
                if (nextCharEscaped || M2DOC_ESCAPE_CHARACTER != charAt) {
                    value += charAt;
                    nextCharEscaped = false;
                } else {
                    nextCharEscaped = true;
                }
            }
            index++;
            finished = index >= maxIndex;
        }
        return value;
    }

    /**
     * Parse and returns the next option's key.
     * 
     * @param construct
     *            the template element were to put error parsing messages.
     * @param optionsString
     *            the string containing all options.
     * @param maxIndex
     *            the index of the last character of the options string.
     * @return the next option's key.
     */
    protected String parseKey(IConstruct construct, String optionsString, int maxIndex) {
        String key = "";
        keyParsed = false;
        keyDetected = false;
        while (!finished && !keyParsed) {
            char charAt = optionsString.charAt(index);
            // we are parsing the next available key.
            if (KEY_VALUE_SEPARATOR == charAt) {
                // we reach the character telling the key has been parsed.
                // So we cache the key.
                keyParsed = true;
                key = key.trim();
                int firstIndexOfSpace = key.indexOf(' ');
                // CHECKSTYLE:OFF
                if (firstIndexOfSpace < key.length() && -1 != firstIndexOfSpace) {
                    // CHECKSTYLE:ON
                    // we have a space between two key characters. So we log an error message.
                    validationError(construct,
                            String.format(
                                    "A forbidden space character is present at the index %d of the key definition '%s'.",
                                    firstIndexOfSpace, key));
                }
            } else {
                // we keep the spaces that we remove when the end key character is detected.
                key += charAt;
                if (!keyDetected && ' ' != charAt) {
                    keyDetected = true;
                }
            }
            index++;
            finished = index >= maxIndex;
        }
        return key;
    }
}
