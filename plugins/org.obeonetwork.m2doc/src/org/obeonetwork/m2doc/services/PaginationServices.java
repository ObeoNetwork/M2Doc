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
package org.obeonetwork.m2doc.services;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.m2doc.MPagination;

/**
 * Pagination services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
//@formatter:off
@ServiceProvider(
value = "Services available for pagination"
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class PaginationServices {

    // @formatter:off
    @Documentation(
        value = "Converts a String to an hyperlink",
        params = {
            @Param(name = "paginationElement", value = "The name of the pagination element: 'newTableOfContent', 'newPage', 'newParagraph', 'newColumn', 'newTextWrapping'"),
        },
        result = "Insert the element.",
        examples = {
            @Example(expression = "'newPage'.asPagination()", result = "insert a page break"),
        }
    )
    // @formatter:on
    public MPagination asPagination(String paginationElement) {
        return MPagination.valueOf(paginationElement);
    }

}
