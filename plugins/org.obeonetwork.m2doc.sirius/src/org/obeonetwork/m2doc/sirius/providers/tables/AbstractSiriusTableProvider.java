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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.obeonetwork.m2doc.provider.AbstractTableProvider;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;

/**
 * Abstract super-implementation of all Sirius table providers.
 * 
 * @author ldelaigue
 */
public abstract class AbstractSiriusTableProvider extends AbstractTableProvider {

    /**
     * Extract the given tables as {@link MTable}s.
     * 
     * @param tables
     *            The sirius tables to extract
     * @return A list, never <code>null</code> but possibly empty, of tables in the M2Doc format.
     */
    protected List<MTable> extractTables(List<DTable> tables) {
        List<MTable> result = new ArrayList<MTable>();
        for (DTable table : tables) {
            result.add(new DMTable(table));
        }
        return result;
    }

    /**
     * This default implementation does nothing.
     * 
     * @return an empty list, never <code>null</code> but always empty.
     */
    @Override
    public List<ProviderValidationMessage> validate(Map<String, Object> options) {
        return Collections.emptyList();
    }

}
