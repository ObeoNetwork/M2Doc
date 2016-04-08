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
    public static final String SERVICETOKEN_SEPARATOR = ",";
    /**
     * id of the integer type to use in variable declaration properties.
     */
    public static final String INT_TYPE = "int";
    /**
     * id of the real type to use in variable declaration properties.
     */
    public static final String REAL_TYPE = "real";
    /**
     * id of the String type to use in variable declaration properties.
     */
    public static final String STRING_TYPE = "string";
    /**
     * id of the object type to use in variable declaration properties.
     */
    public static final String OBJECT_TYPE = "object";
    /**
     * Prefix of the variable declaration custom properties.
     */
    public static final String VAR_PROPERTY_PREFIX = "m:var";
    /**
     * Prefix of the service declaration custom properties.
     */
    public static final String SERVICE_PROPERTY_PREFIX = "m:service";
}
