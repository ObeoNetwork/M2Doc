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

import org.obeonetwork.m2doc.element.MHyperLink;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.impl.MHyperLinkImpl;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;

/**
 * Test {@link MHyperLink} insertion.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MHyperLinkTestServices {

    public MHyperLink sampleLink(String text, String url, Integer fontSize) {
        final MStyle style = new MStyleImpl("French Script MT", fontSize, Color.ORANGE, Color.BLUE, MStyle.FONT_BOLD);

        return new MHyperLinkImpl(text, style, url);
    }

    public MHyperLink noStyleLink(String text, String url) {
        return new MHyperLinkImpl(text, null, url);
    }

}
