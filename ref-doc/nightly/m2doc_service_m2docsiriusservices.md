---
layout: article
title: M2DocSiriusServices
subtitle: Nightly
relativePath: ../..
---

<!--
/********************************************************************************
** Copyright (c) 2015 Obeo.
** All rights reserved. This program and the accompanying materials
** are made available under the terms of the Eclipse Public License v1.0
** which accompanies this distribution, and is available at
** http://www.eclipse.org/legal/epl-v10.html
**
** Contributors:
**    Stephane Begaudeau (Obeo) - initial API and implementation
*********************************************************************************/
-->

# M2DocSiriusServices

Services available for Sirius. You will have to set the "SiriusSession" option in the generation configuration. It must contains the .aird file URI. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.sirius.tests/resources/m2DocSiriusServices). In addition, you can force the refresh of all representation with option "SiriusForceRefresh" set to "true". Note this option might have an impact on memory usage, calls to services with the refresh parameter should be prefered.

* TOC
{:toc}

## org.eclipse.sirius.viewpoint.DRepresentation.asImage() : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation if it's a diagram.

### Parameter

* **representation**: the DRepresentation

### Example

* dRepresentation.asImage()
  * insert the image of the given representation if it's a diagram

## org.eclipse.sirius.viewpoint.DRepresentation.asImage(boolean) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation if it's a diagram.

### Parameter

* **representation**: the DRepresentation
* **refresh**: true to refresh the representation

### Example

* dRepresentation.asImage(true)
  * insert the image of the given representation after refreshing it if it's a diagram

## org.eclipse.sirius.viewpoint.DRepresentation.asImage(boolean, OrderedSet{String}) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation if it's a diagram.

### Parameter

* **representation**: the DRepresentation
* **refresh**: true to refresh the representation
* **layerNames**: the OrderedSet of layer names to activate

### Example

* dRepresentation.asImage(true, OrderedSet{'Layer 1', 'Layer 2'})
  * insert the image of the given representation if it's a diagram

## EObject.asImageByRepresentationDescriptionName(String) : Sequence{org.obeonetwork.m2doc.element.MImage}

Gets the Sequence of images for the diagrams associated to the given EObject with the given description name.

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description

### Example

* ePackage.asImageByRepresentationDescriptionName('class diagram')
  * Sequence{image1, image2}

## EObject.asImageByRepresentationDescriptionName(String, boolean, OrderedSet{String}) : Sequence{org.obeonetwork.m2doc.element.MImage}

Gets the Sequence of images for the diagrams associated to the given EObject with the given description name.

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description
* **refresh**: true to refresh the representation
* **layerNames**: the OrderedSet of layer names to activate

### Example

* ePackage.asImageByRepresentationDescriptionName('class diagram', true, OrderedSet{'Layer 1', 'Layer 2'})
  * Sequence{image1, image2}

## EObject.asImageByRepresentationDescriptionName(String, boolean) : Sequence{org.obeonetwork.m2doc.element.MImage}

Gets the Sequence of images for the diagrams associated to the given EObject with the given description name.

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description
* **refresh**: true to refresh the representation

### Example

* ePackage.asImageByRepresentationDescriptionName('class diagram', true)
  * Sequence{image1, image2}

## String.asImageByRepresentationName(boolean, OrderedSet{String}) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Parameter

* **representationName**: the name of the searched representation
* **refresh**: true to refresh the representation
* **layerNames**: the OrderedSet of layer names to activate

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName(true, OrderedSet{'Layer 1', 'Layer 2'})
  * insert the image

## String.asImageByRepresentationName(boolean) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Parameter

* **representationName**: the name of the searched representation
* **refresh**: true to refresh the representation

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName(true)
  * insert the image after refreshing the representation

## String.asImageByRepresentationName() : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Parameter

* **representationName**: the name of the searched representation

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName()
  * insert the image

## org.eclipse.sirius.table.metamodel.table.DTable.asTable() : org.obeonetwork.m2doc.element.MTable

Insert the table of the given representation table (with header styles).

### Parameter

* **representation**: the DTable

### Example

* dTable.asTable()
  * insert the table of the given representation table (with header styles)

## org.eclipse.sirius.table.metamodel.table.DTable.asTable(boolean) : org.obeonetwork.m2doc.element.MTable

Insert the table of the given representation table.

### Parameter

* **representation**: the DTable
* **withHeader**: true to add header styles, false otherwise

### Example

* dTable.asTable(false)
  * insert the table of the given representation table

## EObject.asTableByRepresentationDescriptionName(String) : Sequence{org.obeonetwork.m2doc.element.MTable}

Gets the Sequence of tables for the tables associated to the given EObject with the given description name (with header styles).

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description (with header styles)

### Example

* ePackage.asTableByRepresentationDescriptionName('dependency table')
  * Sequence{table1, table2}

## EObject.asTableByRepresentationDescriptionName(String, boolean) : Sequence{org.obeonetwork.m2doc.element.MTable}

Gets the Sequence of tables for the tables associated to the given EObject with the given description name.

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description
* **withHeader**: true to had header styles, false otherwise

### Example

* ePackage.asTableByRepresentationDescriptionName('dependency table', false)
  * Sequence{table1, table2}

## String.asTableByRepresentationName() : org.obeonetwork.m2doc.element.MTable

Insert the table of the given representation name (with header styles).

### Parameter

* **representationName**: the name of the searched representation (with header styles)

### Example

* 'MyEPackage class diagram'.asTableByRepresentationName()
  * insert the table

## String.asTableByRepresentationName(boolean) : org.obeonetwork.m2doc.element.MTable

Insert the table of the given representation name.

### Parameter

* **representationName**: the name of the searched representation
* **withHeader**: true to add header styles, false otherwise

### Example

* 'MyEPackage class diagram'.asTableByRepresentationName(false)
  * insert the table

## EObject.isRepresentationDescriptionName(String) : boolean

Returns <code>true</code> if the arguments are not null, the eObject is in a session and there's a representation description with the specified name in it that is associated to the specified eObject. Returns <code>false</code> otherwise.

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description

### Example

* ePackage.isRepresentationDescriptionName('class diagram')
  * True

## String.isRepresentationName() : boolean

Returns <code>true</code> if the arguments are not null, there's a representation with the specified name in the Sirius session. Returns <code>false</code> otherwise.

### Parameter

* **representationName**: the name of the searched representation

### Example

* 'MyEPackage class diagram'.isRepresentationName()
  * True

## EObject.representationByDescriptionName(String) : Sequence{org.eclipse.sirius.viewpoint.DRepresentation}

Gets the Sequence DRepresentation associated to the given EObject with the given description name.

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description

### Example

* ePackage.representationByDescriptionName('class diagram')
  * Sequence{dRepresentation1, dRepresentation2}

## String.representationByName() : org.eclipse.sirius.viewpoint.DRepresentation

Gets the DRepresentation of the given representation name.

### Parameter

* **representationName**: the name of the searched representation

### Example

* 'MyEPackage class diagram'.representationByName()
  * dRepresentation1



