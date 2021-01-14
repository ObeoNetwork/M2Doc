/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.IBody;
import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.template.UserDoc;

/**
 * The
 * {@link org.obeonetwork.m2doc.util.M2DocUtils#generate(org.obeonetwork.m2doc.template.DocumentTemplate, org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment, java.util.Map, org.eclipse.emf.common.util.URI)
 * generation} result.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenerationResult {

    /**
     * The {@link ValidationMessageLevel} for the generation.
     */
    private ValidationMessageLevel level = ValidationMessageLevel.OK;

    /**
     * Mapping of lost fragments from {@link UserDoc#getId() user doc ID} to fragment {@link URI}.
     */
    private final Map<String, URI> lostUserContents = new HashMap<>();

    /**
     * The {@link List} of duplicated {@link UserContent#getId() user content ID}.
     */
    private final List<String> duplicatedUserContentIDs = new ArrayList<>();

    /**
     * The {@link List} of {@link TemplateValidationMessage} produced at generation time.
     */
    private final List<TemplateValidationMessage> messages = new ArrayList<>();

    /**
     * The result {@link IBody}.
     */
    private final IBody body;

    /**
     * Constructor.
     * 
     * @param body
     *            the resulting {@link IBody}
     */
    public GenerationResult(IBody body) {
        this.body = body;
    }

    /**
     * Updates the current {@link #getLevel() level} with the given {@link ValidationMessageLevel}.
     * 
     * @param levels
     *            the {@link ValidationMessageLevel}
     * @return the updated {@link #getLevel() level}
     */
    public ValidationMessageLevel updateLevel(ValidationMessageLevel... levels) {
        level = ValidationMessageLevel.updateLevel(level, levels);

        return level;
    }

    /**
     * Gets the mapping of lost fragments from {@link UserDoc#getId() user doc ID} to fragment {@link URI}.
     * 
     * @return the mapping of lost fragments from {@link UserDoc#getId() user doc ID} to fragment {@link URI}
     */
    public Map<String, URI> getLostUserContents() {
        return lostUserContents;
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
     * Gets the {@link List} of duplicated {@link UserContent#getId() user content ID}.
     * 
     * @return the {@link List} of duplicated {@link UserContent#getId() user content ID}
     */
    public List<String> getDuplicatedUserContentIDs() {
        return duplicatedUserContentIDs;
    }

    /**
     * Gets the {@link List} of {@link TemplateValidationMessage} produced at generation time.
     * 
     * @return the {@link List} of {@link TemplateValidationMessage} produced at generation time
     */
    public List<TemplateValidationMessage> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    /**
     * Adds the given {@link TemplateValidationMessage} to {@link #getMessages() messages} and update the {@link #getLevel() level}.
     * 
     * @param message
     *            the {@link TemplateValidationMessage} to add
     */
    public void addMessage(TemplateValidationMessage message) {
        messages.add(message);
        updateLevel(message.getLevel());
    }

    /**
     * Gets the resulting {@link IBody}.
     * 
     * @return the resulting {@link IBody}
     */
    public IBody getBody() {
        return body;
    }

}
