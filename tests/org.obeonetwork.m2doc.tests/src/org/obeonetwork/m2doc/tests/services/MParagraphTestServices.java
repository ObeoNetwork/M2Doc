/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests.services;

import org.obeonetwork.m2doc.element.MElementContainer.HAlignment;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.MParagraph;
import org.obeonetwork.m2doc.element.impl.MListImpl;
import org.obeonetwork.m2doc.element.impl.MParagraphImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;

/**
 * Test {@link MParagraph} insertion.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MParagraphTestServices {

    /**
     * Generates a {@link MList} of {@link MParagraph} with all {@link HAlignment}.
     * 
     * @param text
     *            the text to set for each {@link MParagraph}
     * @return a {@link MList} of {@link MParagraph} with all {@link HAlignment}
     */
    public MList getAlignments(String text) {
        final MList result = new MListImpl();

        for (HAlignment alignment : HAlignment.values()) {
            final MParagraph paragraph = new MParagraphImpl(new MTextImpl(text, null), null);
            paragraph.setHAlignment(alignment);
            result.add(paragraph);
        }

        return result;
    }

}
