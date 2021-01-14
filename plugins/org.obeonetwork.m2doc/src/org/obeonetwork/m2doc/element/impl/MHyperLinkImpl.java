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
package org.obeonetwork.m2doc.element.impl;

import org.obeonetwork.m2doc.element.MHyperLink;
import org.obeonetwork.m2doc.element.MStyle;

/**
 * An hyper link that can be returned by services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MHyperLinkImpl extends MTextImpl implements MHyperLink {

    /**
     * The url.
     */
    private String url;

    /**
     * Constructor.
     * 
     * @param text
     *            the text
     * @param style
     *            the {@link MStyle}
     * @param url
     *            the url
     */
    public MHyperLinkImpl(String text, MStyle style, String url) {
        super(text, style);
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }
}
