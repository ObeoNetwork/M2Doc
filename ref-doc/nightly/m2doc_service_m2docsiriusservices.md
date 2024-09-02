---
layout: article-services
title: M2DocSiriusServices
subtitle: Nightly
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

# M2DocSiriusServices

Services available for Sirius. You will have to set the "SiriusSession" option in the generation configuration. It must contains the .aird file URI. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.sirius.tests/resources/m2DocSiriusServices). In addition, you can force the refresh of all representation with option "SiriusForceRefresh" set to "true". Note this option might have an impact on memory usage, calls to services with the refresh parameter should be prefered. You can also use the two following options to define the size of the exported images from Sirius diagrams: "SiriusScalingPolicy" that can be one of: WORKSPACE_DEFAULT, AUTO_SCALING, NO_SCALING, AUTO_SCALING_IF_LARGER and "SiriusScaleLevel" and integer.

* TOC
{:toc}

## EObject.asImageByRepresentationDescriptionName(String) : Sequence{org.obeonetwork.m2doc.element.MImage}

Gets the Sequence of images for the diagrams associated to the given EObject with the given description name.

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description

### Example

* ePackage.asImageByRepresentationDescriptionName('class diagram')
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

## EObject.asImageByRepresentationDescriptionName(String, String, boolean) : Sequence{org.obeonetwork.m2doc.element.MImage}

Gets the Sequence of images for the diagrams associated to the given EObject with the given description name.

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description
* **format**: the image format: BMP, GIF, JPG, JPEG, PNG, SVG
* **refresh**: true to refresh the representation

### Example

* ePackage.asImageByRepresentationDescriptionName('class diagram', 'JPG', true)
  * Sequence{image1, image2}

## EObject.asImageByRepresentationDescriptionName(String, String, boolean, OrderedSet{String}) : Sequence{org.obeonetwork.m2doc.element.MImage}

Gets the Sequence of images for the diagrams associated to the given EObject with the given description name.

### Parameter

* **eObject**: Any eObject that is in the session where to search
* **representationDescriptionName**: the name of the searched representation description
* **format**: the image format: BMP, GIF, JPG, JPEG, PNG, SVG
* **refresh**: true to refresh the representation
* **layerNames**: the OrderedSet of layer names to activate

### Example

* ePackage.asImageByRepresentationDescriptionName('class diagram', 'JPG', true, OrderedSet{'Layer 1', 'Layer 2'})
  * Sequence{image1, image2}

## String.asImageByRepresentationName() : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Parameter

* **representationName**: the name of the searched representation

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName()
  * insert the image

## String.asImageByRepresentationName(boolean) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Parameter

* **representationName**: the name of the searched representation
* **refresh**: true to refresh the representation

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName(true)
  * insert the image after refreshing the representation

## String.asImageByRepresentationName(boolean, OrderedSet{String}) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Parameter

* **representationName**: the name of the searched representation
* **refresh**: true to refresh the representation
* **layerNames**: the OrderedSet of layer names to activate

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName(true, OrderedSet{'Layer 1', 'Layer 2'})
  * insert the image

## String.asImageByRepresentationName(String) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Parameter

* **representationName**: the name of the searched representation
* **format**: the image format: BMP, GIF, JPG, JPEG, PNG, SVG

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName('JPG')
  * insert the image

## String.asImageByRepresentationName(String, boolean) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Parameter

* **representationName**: the name of the searched representation
* **format**: the image format: BMP, GIF, JPG, JPEG, PNG, SVG
* **refresh**: true to refresh the representation

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName('JPG', true)
  * insert the image after refreshing the representation

## String.asImageByRepresentationName(String, boolean, OrderedSet{String}) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Parameter

* **representationName**: the name of the searched representation
* **format**: the image format: BMP, GIF, JPG, JPEG, PNG, SVG
* **refresh**: true to refresh the representation
* **layerNames**: the OrderedSet of layer names to activate

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName('JPG, 'true, OrderedSet{'Layer 1', 'Layer 2'})
  * insert the image

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

## org.eclipse.sirius.viewpoint.DRepresentation.asImage(String) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation if it's a diagram.

### Parameter

* **representation**: the DRepresentation
* **format**: the image format: BMP, GIF, JPG, JPEG, PNG, SVG

### Example

* dRepresentation.asImage()
  * insert the image of the given representation if it's a diagram

## org.eclipse.sirius.viewpoint.DRepresentation.asImage(String, boolean) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation if it's a diagram.

### Parameter

* **representation**: the DRepresentation
* **format**: the image format: BMP, GIF, JPG, JPEG, PNG, SVG
* **refresh**: true to refresh the representation

### Example

* dRepresentation.asImage('JPG', true)
  * insert the image of the given representation after refreshing it if it's a diagram

## org.eclipse.sirius.viewpoint.DRepresentation.asImage(String, boolean, OrderedSet{String}) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation if it's a diagram.

### Parameter

* **representation**: the DRepresentation
* **format**: the image format: BMP, GIF, JPG, JPEG, PNG, SVG
* **refresh**: true to refresh the representation
* **layerNames**: the OrderedSet of layer names to activate

### Example

* dRepresentation.asImage('JPG', true, OrderedSet{'Layer 1', 'Layer 2'})
  * insert the image of the given representation if it's a diagram

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

## Object.availableRepresentations() : org.obeonetwork.m2doc.element.MTable

List all available DRepresentation in a table.

### Parameter

* **obj**: Any object

### Example

* ''.availableRepresentations()
  * the table listing available DRepresentation

## String.computeAndConvertPathsToHtmlFromOriginal(EObject) : String

Transforms the serialized text to a text that will be used for html page. This method only changes the image paths. It also ensures that the image is available at the modified location

### Parameter

* **htmlString**: The HTML text
* **context**: An EObject context

## org.eclipse.sirius.viewpoint.DRepresentation.getDescriptor() : org.eclipse.sirius.viewpoint.DRepresentationDescriptor

Gets the DRepresentationDescriptor of the given DRepresentation.

### Parameter

* **representation**: the representation

### Example

* myDiagram.getDescriptor().name
  * the name of the DRepresentationDescriptor

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

* ePackage.asImageByRepresentationDescriptionName('class diagram')
  * Sequence{dRepresentation1, dRepresentation2}

## String.representationByName() : org.eclipse.sirius.viewpoint.DRepresentation

Gets the DRepresentation of the given representation name.

### Parameter

* **representationName**: the name of the searched representation

### Example

* 'MyEPackage class diagram'.asImageByRepresentationName()
  * dRepresentation1



