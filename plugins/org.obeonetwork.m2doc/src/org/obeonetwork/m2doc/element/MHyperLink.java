/*******************************************************************************
 *  Copyright (c) 2017, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.element;

/**
 * An hyper link that can be returned by services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MHyperLink extends MText {

    /**
     * Gets the url.
     * 
     * @return the url
     */
    String getUrl();

    /**
     * Sets the url.
     * 
     * @param url
     *            the new url
     */
    void setUrl(String url);

    /**
     * Gets the tool tip.
     * 
     * @return the tool tip
     */
    String getToolTip();

    /**
     * Sets the tool tip.
     * 
     * @param toolTip
     *            the new tool tip
     */
    void setToolTip(String toolTip);

}
