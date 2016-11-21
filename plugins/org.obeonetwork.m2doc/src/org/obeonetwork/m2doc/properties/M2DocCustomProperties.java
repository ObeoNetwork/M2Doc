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
package org.obeonetwork.m2doc.properties;

/**
 * Definition of custom properties prefixes. Properties are used to define variables and service tokens.
 * 
 * @author Romain Guider
 */
public interface M2DocCustomProperties {
    /**
     * Separator used to provide several service tokens through the m:service custom property.
     */
    String SERVICETOKEN_SEPARATOR = ",";
    /**
     * id of the integer type to use in variable declaration properties (not supported yet).
     */
    String INT_TYPE = "int";
    /**
     * id of the real type to use in variable declaration properties (not supported yet).
     */
    String REAL_TYPE = "real";
    /**
     * id of the String type to use in variable declaration properties.
     */
    String STRING_TYPE = "string";
    /**
     * id of the boolean type to use in variable declaration properties (not supported yet).
     */
    String BOOLEAN_TYPE = "boolean";
    /**
     * id of the date type to use in variable declaration properties (not supported yet).
     */
    String DATE_TYPE = "date";
    /**
     * id of the object type to use in variable declaration properties (not supported yet).
     */
    String OBJECT_TYPE = "object";
    /**
     * Prefix of the variable declaration custom properties.
     */
    String VAR_PROPERTY_PREFIX = "m:var";
    /**
     * Prefix of the uri declaration custom properties.
     */
    String URI_PROPERTY_PREFIX = "m:uri";
    /**
     * Prefix of the service declaration custom properties.
     */
    String SERVICE_PROPERTY_PREFIX = "m:service";
}
