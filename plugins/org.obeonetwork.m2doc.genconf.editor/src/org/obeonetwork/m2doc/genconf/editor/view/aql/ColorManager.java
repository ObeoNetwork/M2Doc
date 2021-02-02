/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.view.aql;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * {@link Color} manager.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ColorManager {

    /**
     * The {@link RGB} to {@link Color} mapping.
     */
    protected Map<RGB, Color> colors = new HashMap<>(10);

    /**
     * Disposes all {@link Color}.
     */
    public void dispose() {
        for (Color color : colors.values()) {
            color.dispose();
        }
    }

    /**
     * Gets the {@link Color} corresponding to the given {@link RGB}.
     * 
     * @param rgb
     *            the {@link RGB}
     * @return the {@link Color} corresponding to the given {@link RGB}
     */
    public Color getColor(RGB rgb) {
        Color res = colors.get(rgb);

        if (res == null) {
            res = new Color(Display.getCurrent(), rgb);
            colors.put(rgb, res);
        }

        return res;
    }
}
