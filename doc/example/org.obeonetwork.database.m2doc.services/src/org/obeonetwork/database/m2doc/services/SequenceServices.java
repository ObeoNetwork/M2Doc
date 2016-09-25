/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.database.m2doc.services;

import java.util.List;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.Column;
import org.obeonetwork.dsl.database.Sequence;

//@formatter:off
@ServiceProvider(
value = "Services available for Sequences"
)
//@formatter:on
public class SequenceServices {

    // @formatter:off
    @Documentation(
        value = "Gets the start of the Sequence.",
        params = {
            @Param(name = "sequence", value = "The Sequence"),
        },
        result = "the start of the Sequence",
        examples = {
            @Example(expression = "sequence.start()", result = "10")
        }
    )
    // @formatter:on
    public Integer start(Sequence sequence) {
        return sequence.getStart();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the increment of the Sequence.",
        params = {
            @Param(name = "sequence", value = "The Sequence"),
        },
        result = "the increment of the Sequence",
        examples = {
            @Example(expression = "sequence.increment()", result = "10")
        }
    )
    // @formatter:on
    public Integer increment(Sequence sequence) {
        return sequence.getIncrement();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the minimum value of the Sequence.",
        params = {
            @Param(name = "sequence", value = "The Sequence"),
        },
        result = "the minimum value of the Sequence",
        examples = {
            @Example(expression = "sequence.minValue()", result = "10")
        }
    )
    // @formatter:on
    public Integer minValue(Sequence sequence) {
        return sequence.getMinValue();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the maximum value of the Sequence.",
        params = {
            @Param(name = "sequence", value = "The Sequence"),
        },
        result = "the maximum value of the Sequence",
        examples = {
            @Example(expression = "sequence.maxValue()", result = "10")
        }
    )
    // @formatter:on
    public Integer maxValue(Sequence sequence) {
        return sequence.getMaxValue();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the cache size of the Sequence.",
        params = {
            @Param(name = "sequence", value = "The Sequence"),
        },
        result = "the cache size of the Sequence",
        examples = {
            @Example(expression = "sequence.cacheSize()", result = "10")
        }
    )
    // @formatter:on
    public Integer cacheSize(Sequence sequence) {
        return sequence.getCacheSize();
    }

    // @formatter:off
    @Documentation(
        value = "Tells if the Sequence is cyclic.",
        params = {
            @Param(name = "sequence", value = "The Sequence"),
        },
        result = "true if the Sequence is cyclic, false otherwise",
        examples = {
            @Example(expression = "sequence.cacheSize()", result = "10")
        }
    )
    // @formatter:on
    public boolean isCyclic(Sequence sequence) {
        return sequence.isCycle();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of Column of the Sequence.",
        params = {
            @Param(name = "sequence", value = "The Sequence"),
        },
        result = "the List of Column of the Sequence",
        examples = {
            @Example(expression = "sequence.columns()", result = "{column1, column2}")
        }
    )
    // @formatter:on
    public List<Column> columns(Sequence sequence) {
        return sequence.getColumns();
    }

}
