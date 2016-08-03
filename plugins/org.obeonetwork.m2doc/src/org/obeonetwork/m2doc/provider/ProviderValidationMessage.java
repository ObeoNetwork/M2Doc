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
package org.obeonetwork.m2doc.provider;

import org.obeonetwork.m2doc.parser.ValidationMessageLevel;

/**
 * {@link IProvider} validation message.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ProviderValidationMessage {

    /**
     * The option name.
     */
    private final String optionName;

    /**
     * The {@link ValidationMessageLevel}.
     */
    private final ValidationMessageLevel level;

    /**
     * The message.
     */
    private final String message;

    /**
     * Constructor.
     * 
     * @param optionName
     *            the option name
     * @param level
     *            the {@link ValidationMessageLevel}
     * @param message
     *            the message
     */
    public ProviderValidationMessage(String optionName, ValidationMessageLevel level, String message) {
        this.optionName = optionName;
        this.level = level;
        this.message = message;
    }

    /**
     * Gets the option name.
     * 
     * @return the option name
     */
    public String getOptionName() {
        return optionName;
    }

    /**
     * Gets the {@link ValidationMessageLevel}.
     * 
     * @return the {@link ValidationMessageLevel}
     */
    public ValidationMessageLevel getLevel() {
        return level;
    }

    /**
     * Gets the message.
     * 
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
