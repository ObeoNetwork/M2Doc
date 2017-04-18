/*******************************************************************************
 * Copyright (c) 2016 Obeo. 
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    which accompanies this distribution, and is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *     
 *     Contributors:
 *         Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius.providers.tables;

import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable.MColumn;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Implementation of {@link MColumn} based on a {@link DColumn}.
 * 
 * @author ldelaigue
 */
public class DMColumn implements MColumn {
    /** The wrapped sirius column. */
    private final DColumn column;
    /**
     * The column's style, possibly <code>null</code>. If it's not <code>null</code>, it will apply to cells of this column that don't
     * have any other style applied (either line stye or ce style)
     */
    private MStyle style;

    /**
     * Constructor.
     * 
     * @param column
     *            The sirius DColumn, cannot be <code>null</code>
     */
    public DMColumn(DColumn column) {
        this.column = checkNotNull(column);
        DTableElementStyle currentStyle = column.getCurrentStyle();
        if (currentStyle != null) {
            style = new DMStyle(currentStyle);
        }
    }

    @Override
    public MStyle getStyle() {
        return style;
    }

    @Override
    public String getLabel() {
        return column.getLabel();
    }

    @Override
    public void setStyle(MStyle style) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLabel(String label) {
        throw new UnsupportedOperationException();
    }
}
