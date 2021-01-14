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
package org.obeonetwork.m2doc.tests.services;

import java.awt.Color;

import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MText;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;

/**
 * Test {@link MText} insertion.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MTextTestServices {

    public MText emptyText(Object object) {
        return new MTextImpl(null, null);
    }

    public MText sampleText(String text, Integer fontSize) {
        final MStyle style = new MStyleImpl("French Script MT", fontSize, Color.ORANGE, Color.BLUE, MStyle.FONT_BOLD);

        return new MTextImpl(text, style);
    }

    public MText noStyleText(String text) {
        return new MTextImpl(text, null);
    }

}
