/*******************************************************************************
 *  Copyright (c) 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.services;

import org.apache.poi.ooxml.POIXMLProperties.CoreProperties;
import org.apache.poi.ooxml.POIXMLProperties.CustomProperties;
import org.apache.poi.ooxml.POIXMLProperties.ExtendedProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

//@formatter:off
@ServiceProvider(
value = "Services available to manipulate the document ifself: title, version, ...."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class DocumentServices {

    /**
     * The format {@link String} for a non existing property.
     */
    private static final String PROPERTY_DOSNT_EXIST_FORMAT = "The property %s doesn't exist.";

    /**
     * The format {@link String} for a property that isn't a number.
     */
    private static final String PROPERTY_ISNT_A_NUMBER_FORMAT = "The property %s isn't a number.";

    /**
     * The document {@link CustomProperties}.
     */
    private CustomProperties customProperties;

    /**
     * The document {@link CoreProperties}.
     */
    private CoreProperties coreProperties;

    /**
     * The document {@link ExtendedProperties}.
     */
    private ExtendedProperties extendedProperties;

    /**
     * Sets the destination {@link XWPFDocument}.
     * 
     * @param destinationDocument
     *            the destination {@link XWPFDocument}
     */
    public void setDestinationDocument(XWPFDocument destinationDocument) {
        this.customProperties = destinationDocument.getProperties().getCustomProperties();
        this.coreProperties = destinationDocument.getProperties().getCoreProperties();
        this.extendedProperties = destinationDocument.getProperties().getExtendedProperties();
    }

    // @formatter:off
    @Documentation(
        value = "Adds a custom property with the given name and boolean value.",
        params = {
            @Param(name = "value", value = "The boolean value."),
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "true.addDocumentProperty('myBoolean')",
                result = "Adds a custom propery named myBoolean with the value true."
            )
        }
    )
    // @formatter:on
    public void addDocumentProperty(boolean value, String propertyName) {
        customProperties.addProperty(propertyName, value);
    }

    // @formatter:off
    @Documentation(
        value = "Adds a custom property with the given name and double value.",
        params = {
            @Param(name = "value", value = "The double value."),
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "myDouble.addDocumentProperty('myDouble')",
                result = "Adds a custom propery named myDouble with the reveiver value."
            )
        }
    )
    // @formatter:on
    public void addDocumentProperty(double value, String propertyName) {
        customProperties.addProperty(propertyName, value);
    }

    // @formatter:off
    @Documentation(
        value = "Adds a custom property with the given name and float value.",
        params = {
            @Param(name = "value", value = "The float value."),
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "myFloat.addDocumentProperty('myFloat')",
                result = "Adds a custom propery named myFloat with the reveiver value."
            )
        }
    )
    // @formatter:on
    public void addDocumentProperty(float value, String propertyName) {
        customProperties.addProperty(propertyName, value);
    }

    // @formatter:off
    @Documentation(
        value = "Adds a custom property with the given name and integer value.",
        params = {
            @Param(name = "value", value = "The integer value."),
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "myInteger.addDocumentProperty('myInteger')",
                result = "Adds a custom propery named myInteger with the reveiver value."
            )
        }
    )
    // @formatter:on
    public void addDocumentProperty(int value, String propertyName) {
        customProperties.addProperty(propertyName, value);
    }

    // @formatter:off
    @Documentation(
        value = "Adds a custom property with the given name and long value.",
        params = {
            @Param(name = "value", value = "The long value."),
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "myLong.addDocumentProperty('myLong')",
                result = "Adds a custom propery named myLong with the reveiver value."
            )
        }
    )
    // @formatter:on
    public void addDocumentProperty(long value, String propertyName) {
        customProperties.addProperty(propertyName, value);
    }

    // @formatter:off
    @Documentation(
        value = "Adds a custom property with the given name and string value.",
        params = {
            @Param(name = "value", value = "The string value."),
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'Some Value'.addDocumentProperty('myString')",
                result = "Adds a custom propery named myString with the reveiver value."
            )
        }
    )
    // @formatter:on
    public void addDocumentProperty(String value, String propertyName) {
        customProperties.addProperty(propertyName, value);
    }

    // @formatter:off
    @Documentation(
        value = "Removes a custom property with the given name.",
        params = {
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'MyProperty'.removeDocumentCustomProperty()",
                result = "Removes a custom propery named MyProperty."
            )
        }
    )
    // @formatter:on
    public void removeDocumentProperty(String propertyName) {
        final CTProperties underlyingCustomProperties = customProperties.getUnderlyingProperties();
        int index = -1;
        for (CTProperty property : underlyingCustomProperties.getPropertyList()) {
            if (propertyName.equals(property.getName())) {
                break;
            }
            index++;
        }
        if (index != -1) {
            underlyingCustomProperties.removeProperty(index + 1);
        }
    }

    // @formatter:off
    @Documentation(
        value = "Gets the value of the custom property with the given name as boolean.",
        params = {
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "The property value as boolean.",
        examples = {
            @Example(
                expression = "'MyProperty'.getDocumentPropertyAsBoolean()",
                result = "The property value as boolean."
            )
        }
    )
    // @formatter:on
    public boolean getDocumentPropertyAsBoolean(String propertyName) {
        final boolean res;

        final CTProperty property = customProperties.getProperty(propertyName);
        if (property != null) {
            if (property.isSetBool()) {
                res = property.getBool();
            } else if (property.isSetLpstr()) {
                res = Boolean.valueOf(property.getLpstr());
            } else if (property.isSetLpwstr()) {
                res = Boolean.valueOf(property.getLpwstr());
            } else if (documentPropertyIsNumber(propertyName)) {
                res = getNumberValue(property).doubleValue() != 0.0d;
            } else {
                res = false;
            }
        } else {
            throw new IllegalArgumentException(String.format(PROPERTY_DOSNT_EXIST_FORMAT, propertyName));
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the value of the custom property with the given name as double.",
        params = {
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "The property value as double.",
        examples = {
            @Example(
                expression = "'MyProperty'.getDocumentPropertyAsDouble()",
                result = "The property value as double."
            )
        }
    )
    // @formatter:on
    public double getDocumentPropertyAsDouble(String propertyName) {
        final double res;

        final CTProperty property = customProperties.getProperty(propertyName);
        if (property != null) {
            res = getNumberValue(property).doubleValue();
        } else {
            throw new IllegalArgumentException(String.format(PROPERTY_DOSNT_EXIST_FORMAT, propertyName));
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the value of the custom property with the given name as float.",
        params = {
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "The property value as float.",
        examples = {
            @Example(
                expression = "'MyProperty'.getDocumentPropertyAsFloat()",
                result = "The property value as float."
            )
        }
    )
    // @formatter:on
    public float getDocumentPropertyAsFloat(String propertyName) {
        final float res;

        final CTProperty property = customProperties.getProperty(propertyName);
        if (property != null) {
            res = getNumberValue(property).floatValue();
        } else {
            throw new IllegalArgumentException(String.format(PROPERTY_DOSNT_EXIST_FORMAT, propertyName));
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the value of the custom property with the given name as integer.",
        params = {
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "The property value as integer.",
        examples = {
            @Example(
                expression = "'MyProperty'.getDocumentPropertyAsInteger()",
                result = "The property value as integer."
            )
        }
    )
    // @formatter:on
    public int getDocumentPropertyAsInteger(String propertyName) {
        final int res;

        final CTProperty property = customProperties.getProperty(propertyName);
        if (property != null) {
            res = getNumberValue(property).intValue();
        } else {
            throw new IllegalArgumentException(String.format(PROPERTY_DOSNT_EXIST_FORMAT, propertyName));
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the value of the custom property with the given name as long.",
        params = {
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "The property value as long.",
        examples = {
            @Example(
                expression = "'MyProperty'.getDocumentPropertyAsLong()",
                result = "The property value as long."
            )
        }
    )
    // @formatter:on
    public long getDocumentPropertyAsLong(String propertyName) {
        final long res;

        final CTProperty property = customProperties.getProperty(propertyName);
        if (property != null) {
            res = getNumberValue(property).longValue();
        } else {
            throw new IllegalArgumentException(String.format(PROPERTY_DOSNT_EXIST_FORMAT, propertyName));
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Tells if the value of the custom property with the given name is a number.",
        params = {
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "True if the value of the custom property with the given name is a number, false otherwise.",
        examples = {
            @Example(
                expression = "'MyProperty'.getDocumentPropertyAsLong()",
                result = "The property value as long."
            )
        }
    )
    // @formatter:on
    public boolean documentPropertyIsNumber(String propertyName) {
        boolean res;

        final CTProperty property = customProperties.getProperty(propertyName);
        // CHECKSTYLE:OFF API driven
        if (property != null) {
            res = property.isSetI1() || property.isSetI2() || property.isSetI4() || property.isSetI8()
                || property.isSetInt() || property.isSetR4() || property.isSetR8() || property.isSetUi1()
                || property.isSetUi2() || property.isSetUi4() || property.isSetUi8() || property.isSetDecimal();
            if (!res) {
                if (property.isSetLpstr()) {
                    try {
                        Integer.valueOf(property.getLpstr());
                        res = true;
                    } catch (NumberFormatException e) {
                        try {
                            Double.valueOf(property.getLpwstr());
                            res = true;
                        } catch (NumberFormatException e1) {
                            res = false;
                        }
                    }
                } else if (property.isSetLpwstr()) {
                    try {
                        Integer.valueOf(property.getLpwstr());
                        res = true;
                    } catch (NumberFormatException e) {
                        try {
                            Double.valueOf(property.getLpwstr());
                            res = true;
                        } catch (NumberFormatException e1) {
                            res = false;
                        }
                    }
                }
            }
        } else {
            res = false;
        }
        // CHECKSTYLE:ON

        return res;
    }

    /**
     * Gets the value of the given {@link CTProperty} as a {@link Number}.
     * 
     * @param property
     *            the {@link CTProperty}
     * @return the value of the given {@link CTProperty} as a {@link Number}
     */
    private Number getNumberValue(CTProperty property) {
        Number res;

        if (property.isSetI1()) {
            res = Byte.valueOf(property.getI1());
        } else if (property.isSetI2()) {
            res = Short.valueOf(property.getI2());
        } else if (property.isSetI4()) {
            res = Integer.valueOf(property.getI4());
        } else if (property.isSetI8()) {
            res = Long.valueOf(property.getI8());
        } else if (property.isSetInt()) {
            res = Integer.valueOf(property.getInt());
        } else if (property.isSetR4()) {
            res = Float.valueOf(property.getR4());
        } else if (property.isSetR8()) {
            res = Double.valueOf(property.getR8());
        } else if (property.isSetUi1()) {
            res = Short.valueOf(property.getUi1());
        } else if (property.isSetUi2()) {
            res = Integer.valueOf(property.getUi2());
        } else if (property.isSetUi4()) {
            res = Long.valueOf(property.getUi4());
        } else if (property.isSetUi8()) {
            res = property.getUi8();
        } else if (property.isSetDecimal()) {
            res = property.getDecimal();
        } else if (property.isSetLpstr()) {
            try {
                res = Integer.valueOf(property.getLpstr());
            } catch (NumberFormatException e) {
                res = Double.valueOf(property.getLpstr());
            }
        } else if (property.isSetLpwstr()) {
            try {
                res = Integer.valueOf(property.getLpwstr());
            } catch (NumberFormatException e) {
                res = Double.valueOf(property.getLpwstr());
            }
        } else {
            throw new IllegalArgumentException(String.format(PROPERTY_ISNT_A_NUMBER_FORMAT, property.getName()));
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the value of the custom property with the given name as string.",
        params = {
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "The property value as string.",
        examples = {
            @Example(
                expression = "'MyProperty'.getDocumentPropertyAsString()",
                result = "The property value as string."
            )
        }
    )
    // @formatter:on
    public String getDocumentPropertyAsString(String propertyName) {
        final String res;

        final CTProperty property = customProperties.getProperty(propertyName);
        if (property != null) {
            if (property.isSetLpstr()) {
                res = property.getLpstr();
            } else if (property.isSetLpwstr()) {
                res = property.getLpwstr();
            } else if (property.isSetBool()) {
                res = Boolean.valueOf(property.getBool()).toString();
            } else if (documentPropertyIsNumber(propertyName)) {
                res = getNumberValue(property).toString();
            } else {
                res = "";
            }
        } else {
            throw new IllegalArgumentException(String.format(PROPERTY_DOSNT_EXIST_FORMAT, propertyName));
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Tells if the custom property with the given name exists.",
        params = {
            @Param(name = "propertyName", value = "The custom property name."),
        },
        result = "true if the custom property with the given name exists, false otherwise.",
        examples = {
            @Example(
                expression = "'MyProperty'.getDocumentPropertyAsString()",
                result = "The property value as string."
            )
        }
    )
    // @formatter:on
    public boolean hasDocumentProperty(String propertyName) {
        return customProperties.contains(propertyName);
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document application.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Application'.setDocumentApplication()",
                result = "Sets the document application to My Application."
            )
        }
    )
    // @formatter:on
    public void setDocumentApplication(String value) {
        extendedProperties.setApplication(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document application.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document application.",
        examples = {
            @Example(
                expression = "''.getDocumentApplication()",
                result = "The document application."
            )
        }
    )
    // @formatter:on
    public String getDocumentApplication(Object reveiver) {
        return extendedProperties.getApplication();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document appplication version.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My AppVersion'.setDocumentAppVersion()",
                result = "Sets the document appplication version to My AppVersion."
            )
        }
    )
    // @formatter:on
    public void setDocumentAppVersion(String value) {
        extendedProperties.setAppVersion(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document appplication version.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document appplication version.",
        examples = {
            @Example(
                expression = "''.getDocumentAppVersion()",
                result = "The document appplication version."
            )
        }
    )
    // @formatter:on
    public String getDocumentAppVersion(Object reveiver) {
        return extendedProperties.getAppVersion();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document company.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Company'.setDocumentCompany()",
                result = "Sets the document company to My Company."
            )
        }
    )
    // @formatter:on
    public void setDocumentCompany(String value) {
        extendedProperties.setCompany(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document company.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document company.",
        examples = {
            @Example(
                expression = "''.getDocumentCompany()",
                result = "The document company."
            )
        }
    )
    // @formatter:on
    public String getDocumentCompany(Object reveiver) {
        return extendedProperties.getCompany();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document manager.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Manager'.setDocumentManager()",
                result = "Sets the document manager to My Manager."
            )
        }
    )
    // @formatter:on
    public void setDocumentManager(String value) {
        extendedProperties.setManager(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document manager.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document manager.",
        examples = {
            @Example(
                expression = "''.getDocumentManager()",
                result = "The document manager."
            )
        }
    )
    // @formatter:on
    public String getDocumentManager(Object reveiver) {
        return extendedProperties.getManager();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document category.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Category'.setDocumentCategory()",
                result = "Sets the document category to My Category."
            )
        }
    )
    // @formatter:on
    public void setDocumentCategory(String value) {
        coreProperties.setCategory(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document category.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document category.",
        examples = {
            @Example(
                expression = "''.getDocumentCategory()",
                result = "The document category."
            )
        }
    )
    // @formatter:on
    public String getDocumentCategory(Object reveiver) {
        return coreProperties.getCategory();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document content status.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My ContentStatus'.setDocumentContentStatus()",
                result = "Sets the document content status to My ContentStatus."
            )
        }
    )
    // @formatter:on
    public void setDocumentContentStatus(String value) {
        coreProperties.setContentStatus(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document content status.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document content status.",
        examples = {
            @Example(
                expression = "''.getDocumentContentStatus()",
                result = "The document content status."
            )
        }
    )
    // @formatter:on
    public String getDocumentContentStatus(Object reveiver) {
        return coreProperties.getContentStatus();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document content type.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My ContentType'.setDocumentContentType()",
                result = "Sets the document content type to My ContentType."
            )
        }
    )
    // @formatter:on
    public void setDocumentContentType(String value) {
        coreProperties.setContentType(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document content type.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document content type.",
        examples = {
            @Example(
                expression = "''.getDocumentContentType()",
                result = "The document content type."
            )
        }
    )
    // @formatter:on
    public String getDocumentContentType(Object reveiver) {
        return coreProperties.getContentType();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document creator.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Creator'.setDocumentCreator()",
                result = "Sets the document creator to My Creator."
            )
        }
    )
    // @formatter:on
    public void setDocumentCreator(String value) {
        coreProperties.setCreator(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document creator.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document creator.",
        examples = {
            @Example(
                expression = "''.getDocumentCreator()",
                result = "The document creator."
            )
        }
    )
    // @formatter:on
    public String getDocumentCreator(Object reveiver) {
        return coreProperties.getCreator();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document description.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Description'.setDocumentDescription()",
                result = "Sets the document description to My Description."
            )
        }
    )
    // @formatter:on
    public void setDocumentDescription(String value) {
        coreProperties.setDescription(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document description.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document description.",
        examples = {
            @Example(
                expression = "''.getDocumentDescription()",
                result = "The document description."
            )
        }
    )
    // @formatter:on
    public String getDocumentDescription(Object reveiver) {
        return coreProperties.getDescription();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document identifier.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Identifier'.setDocumentIdentifier()",
                result = "Sets the document identifier to My Identifier."
            )
        }
    )
    // @formatter:on
    public void setDocumentIdentifier(String value) {
        coreProperties.setIdentifier(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document identifier.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document identifier.",
        examples = {
            @Example(
                expression = "''.getDocumentIdentifier()",
                result = "The document identifier."
            )
        }
    )
    // @formatter:on
    public String getDocumentIdentifier(Object reveiver) {
        return coreProperties.getIdentifier();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document keywords.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Keywords'.setDocumentKeywords()",
                result = "Sets the document keywords to My Keywords."
            )
        }
    )
    // @formatter:on
    public void setDocumentKeywords(String value) {
        coreProperties.setKeywords(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document keywords.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document keywords.",
        examples = {
            @Example(
                expression = "''.getDocumentKeywords()",
                result = "The document keywords."
            )
        }
    )
    // @formatter:on
    public String getDocumentKeywords(Object reveiver) {
        return coreProperties.getKeywords();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document revision.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Revision'.setDocumentRevision()",
                result = "Sets the document revision to My Revision."
            )
        }
    )
    // @formatter:on
    public void setDocumentRevision(String value) {
        coreProperties.setRevision(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document revision.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document revision.",
        examples = {
            @Example(
                expression = "''.getDocumentRevision()",
                result = "The document revision."
            )
        }
    )
    // @formatter:on
    public String getDocumentRevision(Object reveiver) {
        return coreProperties.getRevision();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document subject.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Subject'.setDocumentSubject()",
                result = "Sets the document subject to My Subject."
            )
        }
    )
    // @formatter:on
    public void setDocumentSubject(String value) {
        coreProperties.setSubjectProperty(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document subject.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document subject.",
        examples = {
            @Example(
                expression = "''.getDocumentSubject()",
                result = "The document subject."
            )
        }
    )
    // @formatter:on
    public String getDocumentSubject(Object reveiver) {
        return coreProperties.getSubject();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document title.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Title'.setDocumentTitle()",
                result = "Sets the document title to My Title."
            )
        }
    )
    // @formatter:on
    public void setDocumentTitle(String value) {
        coreProperties.setTitle(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document title.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document title.",
        examples = {
            @Example(
                expression = "''.getDocumentTitle()",
                result = "The document title."
            )
        }
    )
    // @formatter:on
    public String getDocumentTitle(Object reveiver) {
        return coreProperties.getTitle();
    }

    // @formatter:off
    @Documentation(
        value = "Sets the document version.",
        params = {
            @Param(name = "value", value = "The value."),
        },
        result = "Nothing.",
        examples = {
            @Example(
                expression = "'My Version'.setDocumentVersion()",
                result = "Sets the document version to My Version."
            )
        }
    )
    // @formatter:on
    public void setDocumentVersion(String value) {
        coreProperties.setVersion(value);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the document version.",
        params = {
            @Param(name = "reveiver", value = "Any receiver object."),
        },
        result = "The document version.",
        examples = {
            @Example(
                expression = "''.getDocumentVersion()",
                result = "The document version."
            )
        }
    )
    // @formatter:on
    public String getDocumentVersion(Object reveiver) {
        return coreProperties.getVersion();
    }

}
