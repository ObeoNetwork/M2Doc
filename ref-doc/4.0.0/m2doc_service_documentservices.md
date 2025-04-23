---
layout: article-services
title: DocumentServices
subtitle: 4.0.0
relativePath: ../..
---

<!--
/********************************************************************************
** Copyright (c) 2015, 2024 Obeo.
** All rights reserved. This program and the accompanying materials
** are made available under the terms of the Eclipse Public License v2.0
** which accompanies this distribution, and is available at
** http://www.eclipse.org/legal/epl-v20.html
**
** Contributors:
**    Stephane Begaudeau (Obeo) - initial API and implementation
*********************************************************************************/
-->

# DocumentServices

Services available to manipulate the document ifself: title, version, ....

* TOC
{:toc}

## boolean.addDocumentProperty(String) : void

Adds a custom property with the given name and boolean value.

### Parameter

* **value**: The boolean value.
* **propertyName**: The custom property name.

### Example

* true.addDocumentProperty('myBoolean')
  * Adds a custom propery named myBoolean with the value true.

## double.addDocumentProperty(String) : void

Adds a custom property with the given name and double value.

### Parameter

* **value**: The double value.
* **propertyName**: The custom property name.

### Example

* myDouble.addDocumentProperty('myDouble')
  * Adds a custom propery named myDouble with the reveiver value.

## float.addDocumentProperty(String) : void

Adds a custom property with the given name and float value.

### Parameter

* **value**: The float value.
* **propertyName**: The custom property name.

### Example

* myFloat.addDocumentProperty('myFloat')
  * Adds a custom propery named myFloat with the reveiver value.

## int.addDocumentProperty(String) : void

Adds a custom property with the given name and integer value.

### Parameter

* **value**: The integer value.
* **propertyName**: The custom property name.

### Example

* myInteger.addDocumentProperty('myInteger')
  * Adds a custom propery named myInteger with the reveiver value.

## String.addDocumentProperty(String) : void

Adds a custom property with the given name and string value.

### Parameter

* **value**: The string value.
* **propertyName**: The custom property name.

### Example

* 'Some Value'.addDocumentProperty('myString')
  * Adds a custom propery named myString with the reveiver value.

## long.addDocumentProperty(String) : void

Adds a custom property with the given name and long value.

### Parameter

* **value**: The long value.
* **propertyName**: The custom property name.

### Example

* myLong.addDocumentProperty('myLong')
  * Adds a custom propery named myLong with the reveiver value.

## String.documentPropertyIsNumber() : boolean

Tells if the value of the custom property with the given name is a number.

### Parameter

* **propertyName**: The custom property name.

### Example

* 'MyProperty'.getDocumentPropertyAsLong()
  * The property value as long.

## Object.getDocumentAppVersion() : String

Gets the document appplication version.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentAppVersion()
  * The document appplication version.

## Object.getDocumentApplication() : String

Gets the document application.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentApplication()
  * The document application.

## Object.getDocumentCategory() : String

Gets the document category.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentCategory()
  * The document category.

## Object.getDocumentCompany() : String

Gets the document company.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentCompany()
  * The document company.

## Object.getDocumentContentStatus() : String

Gets the document content status.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentContentStatus()
  * The document content status.

## Object.getDocumentContentType() : String

Gets the document content type.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentContentType()
  * The document content type.

## Object.getDocumentCreator() : String

Gets the document creator.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentCreator()
  * The document creator.

## Object.getDocumentDescription() : String

Gets the document description.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentDescription()
  * The document description.

## Object.getDocumentIdentifier() : String

Gets the document identifier.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentIdentifier()
  * The document identifier.

## Object.getDocumentKeywords() : String

Gets the document keywords.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentKeywords()
  * The document keywords.

## Object.getDocumentManager() : String

Gets the document manager.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentManager()
  * The document manager.

## String.getDocumentPropertyAsBoolean() : boolean

Gets the value of the custom property with the given name as boolean.

### Parameter

* **propertyName**: The custom property name.

### Example

* 'MyProperty'.getDocumentPropertyAsBoolean()
  * The property value as boolean.

## String.getDocumentPropertyAsDouble() : double

Gets the value of the custom property with the given name as double.

### Parameter

* **propertyName**: The custom property name.

### Example

* 'MyProperty'.getDocumentPropertyAsDouble()
  * The property value as double.

## String.getDocumentPropertyAsFloat() : float

Gets the value of the custom property with the given name as float.

### Parameter

* **propertyName**: The custom property name.

### Example

* 'MyProperty'.getDocumentPropertyAsFloat()
  * The property value as float.

## String.getDocumentPropertyAsInteger() : int

Gets the value of the custom property with the given name as integer.

### Parameter

* **propertyName**: The custom property name.

### Example

* 'MyProperty'.getDocumentPropertyAsInteger()
  * The property value as integer.

## String.getDocumentPropertyAsLong() : long

Gets the value of the custom property with the given name as long.

### Parameter

* **propertyName**: The custom property name.

### Example

* 'MyProperty'.getDocumentPropertyAsLong()
  * The property value as long.

## String.getDocumentPropertyAsString() : String

Gets the value of the custom property with the given name as string.

### Parameter

* **propertyName**: The custom property name.

### Example

* 'MyProperty'.getDocumentPropertyAsString()
  * The property value as string.

## Object.getDocumentRevision() : String

Gets the document revision.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentRevision()
  * The document revision.

## Object.getDocumentSubject() : String

Gets the document subject.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentSubject()
  * The document subject.

## Object.getDocumentTitle() : String

Gets the document title.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentTitle()
  * The document title.

## Object.getDocumentVersion() : String

Gets the document version.

### Parameter

* **reveiver**: Any receiver object.

### Example

* ''.getDocumentVersion()
  * The document version.

## String.hasDocumentProperty() : boolean

Tells if the custom property with the given name exists.

### Parameter

* **propertyName**: The custom property name.

### Example

* 'MyProperty'.getDocumentPropertyAsString()
  * The property value as string.

## String.removeDocumentProperty() : void

Removes a custom property with the given name.

### Parameter

* **propertyName**: The custom property name.

### Example

* 'MyProperty'.removeDocumentCustomProperty()
  * Removes a custom propery named MyProperty.

## String.setDocumentAppVersion() : void

Sets the document appplication version.

### Parameter

* **value**: The value.

### Example

* 'My AppVersion'.setDocumentAppVersion()
  * Sets the document appplication version to My AppVersion.

## String.setDocumentApplication() : void

Sets the document application.

### Parameter

* **value**: The value.

### Example

* 'My Application'.setDocumentApplication()
  * Sets the document application to My Application.

## String.setDocumentCategory() : void

Sets the document category.

### Parameter

* **value**: The value.

### Example

* 'My Category'.setDocumentCategory()
  * Sets the document category to My Category.

## String.setDocumentCompany() : void

Sets the document company.

### Parameter

* **value**: The value.

### Example

* 'My Company'.setDocumentCompany()
  * Sets the document company to My Company.

## String.setDocumentContentStatus() : void

Sets the document content status.

### Parameter

* **value**: The value.

### Example

* 'My ContentStatus'.setDocumentContentStatus()
  * Sets the document content status to My ContentStatus.

## String.setDocumentContentType() : void

Sets the document content type.

### Parameter

* **value**: The value.

### Example

* 'My ContentType'.setDocumentContentType()
  * Sets the document content type to My ContentType.

## String.setDocumentCreator() : void

Sets the document creator.

### Parameter

* **value**: The value.

### Example

* 'My Creator'.setDocumentCreator()
  * Sets the document creator to My Creator.

## String.setDocumentDescription() : void

Sets the document description.

### Parameter

* **value**: The value.

### Example

* 'My Description'.setDocumentDescription()
  * Sets the document description to My Description.

## String.setDocumentIdentifier() : void

Sets the document identifier.

### Parameter

* **value**: The value.

### Example

* 'My Identifier'.setDocumentIdentifier()
  * Sets the document identifier to My Identifier.

## String.setDocumentKeywords() : void

Sets the document keywords.

### Parameter

* **value**: The value.

### Example

* 'My Keywords'.setDocumentKeywords()
  * Sets the document keywords to My Keywords.

## String.setDocumentManager() : void

Sets the document manager.

### Parameter

* **value**: The value.

### Example

* 'My Manager'.setDocumentManager()
  * Sets the document manager to My Manager.

## String.setDocumentRevision() : void

Sets the document revision.

### Parameter

* **value**: The value.

### Example

* 'My Revision'.setDocumentRevision()
  * Sets the document revision to My Revision.

## String.setDocumentSubject() : void

Sets the document subject.

### Parameter

* **value**: The value.

### Example

* 'My Subject'.setDocumentSubject()
  * Sets the document subject to My Subject.

## String.setDocumentTitle() : void

Sets the document title.

### Parameter

* **value**: The value.

### Example

* 'My Title'.setDocumentTitle()
  * Sets the document title to My Title.

## String.setDocumentVersion() : void

Sets the document version.

### Parameter

* **value**: The value.

### Example

* 'My Version'.setDocumentVersion()
  * Sets the document version to My Version.



