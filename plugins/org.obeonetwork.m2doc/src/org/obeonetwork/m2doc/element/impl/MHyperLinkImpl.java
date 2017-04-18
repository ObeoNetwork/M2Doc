/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.element.impl;

import org.obeonetwork.m2doc.element.MHyperLink;

/**
 * An hyper link that can be returned by services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MHyperLinkImpl implements MHyperLink {

    /**
     * The text.
     */
    private final String text;

    /**
     * The url.
     */
    private final String url;

    /**
     * Constructor.
     * 
     * @param text
     *            the text
     * @param url
     *            the url
     */
    public MHyperLinkImpl(String text, String url) {
        this.text = text;
        this.url = url;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getUrl() {
        return url;
    }

}
