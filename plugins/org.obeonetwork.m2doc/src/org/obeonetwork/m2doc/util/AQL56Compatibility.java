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
package org.obeonetwork.m2doc.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.acceleo.query.parser.AstBuilderListener;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;

/**
 * Utility class for AQL 5.x and 6.x compatibility.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public final class AQL56Compatibility {

    /**
     * {@link QueryBuilderEngine} {@link Constructor}.
     */
    private static final Constructor<QueryBuilderEngine> QUERY_BUILDER_ENGINE_CONSTRUCTOR = initQueryBuilderEngineConstructor();

    /**
     * {@link AstBuilderListener} {@link Constructor}.
     */
    private static final Constructor<AstBuilderListener> AST_BUILDER_LISTENER_CONSTRUCTOR = initAstBuilderListenerConstructor();

    /**
     * Constructor.
     */
    private AQL56Compatibility() {
        // nothing to do here
    }

    /**
     * Initializes {@link #QUERY_BUILDER_ENGINE_CONSTRUCTOR}.
     * 
     * @return the {@link Constructor}
     */
    @SuppressWarnings("unchecked")
    private static Constructor<QueryBuilderEngine> initQueryBuilderEngineConstructor() {
        return (Constructor<QueryBuilderEngine>) QueryBuilderEngine.class.getConstructors()[0];
    }

    /**
     * Initializes {@link #AST_BUILDER_LISTENER_CONSTRUCTOR}.
     * 
     * @return the {@link Constructor}
     */
    @SuppressWarnings("unchecked")
    private static Constructor<AstBuilderListener> initAstBuilderListenerConstructor() {
        return (Constructor<AstBuilderListener>) AstBuilderListener.class.getConstructors()[0];
    }

    /**
     * Creates a {@link QueryBuilderEngine}.
     * 
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}.
     * @return the {@link QueryBuilderEngine}
     */
    public static QueryBuilderEngine createQueryBuilderEngine(IQueryEnvironment queryEnvironment) {
        QueryBuilderEngine res = null;

        try {
            res = QUERY_BUILDER_ENGINE_CONSTRUCTOR.newInstance(queryEnvironment);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Creates a {@link AstBuilderListener}.
     * 
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}.
     * @return the {@link AstBuilderListener}
     */
    public static AstBuilderListener createAstBuilderListener(IQueryEnvironment queryEnvironment) {
        AstBuilderListener res = null;

        try {
            return AST_BUILDER_LISTENER_CONSTRUCTOR.newInstance(queryEnvironment);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return res;
    }

}
