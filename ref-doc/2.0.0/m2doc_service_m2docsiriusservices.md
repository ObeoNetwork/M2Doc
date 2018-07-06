---
layout: article
title: M2DocSiriusServices
 subtitle: 2.0.0
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

Services available for Sirius. You will have to set the "SiriusSession" option in the generation configuration. It must contains the .aird file URI.

* TOC
{:toc}

## org.eclipse.sirius.viewpoint.DRepresentation.asImage(boolean, OrderedSet{String}) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation if it's a diagram.

### Example

| Expression | Result |
| ---------- | ------ |
| dRepresentation.asImage(true, OrderedSet{'Layer 1', 'Layer 2'}) | insert the image of the given representation if it's a diagram |

## org.eclipse.sirius.viewpoint.DRepresentation.asImage() : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation if it's a diagram.

### Example

| Expression | Result |
| ---------- | ------ |
| dRepresentation.asImage() | insert the image of the given representation if it's a diagram |

## EObject.asImageByRepresentationDescriptionName(String, boolean, OrderedSet{String}) : Sequence{org.obeonetwork.m2doc.element.MImage}

Gets the Sequence of images for the diagrams associated to the given EObject with the given description name.

### Example

| Expression | Result |
| ---------- | ------ |
| ePackage.asImageByRepresentationDescriptionName('class diagram', true, OrderedSet{'Layer 1', 'Layer 2'}) | Sequence{image1, image2} |

## EObject.asImageByRepresentationDescriptionName(String) : Sequence{org.obeonetwork.m2doc.element.MImage}

Gets the Sequence of images for the diagrams associated to the given EObject with the given description name.

### Example

| Expression | Result |
| ---------- | ------ |
| ePackage.asImageByRepresentationDescriptionName('class diagram') | Sequence{image1, image2} |

## String.asImageByRepresentationName(boolean, OrderedSet{String}) : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Example

| Expression | Result |
| ---------- | ------ |
| 'MyEPackage class diagram'.asImageByRepresentationName(true, OrderedSet{'Layer 1', 'Layer 2'}) | insert the image |

## String.asImageByRepresentationName() : org.obeonetwork.m2doc.element.MImage

Insert the image of the given representation name.

### Example

| Expression | Result |
| ---------- | ------ |
| 'MyEPackage class diagram'.asImageByRepresentationName() | insert the image |

## org.eclipse.sirius.table.metamodel.table.DTable.asTable() : org.obeonetwork.m2doc.element.MTable

Insert the table of the given representation table.

### Example

| Expression | Result |
| ---------- | ------ |
| dTable.asTable() | insert the tablee of the given representation table |

## EObject.asTableByRepresentationDescriptionName(String) : Sequence{org.obeonetwork.m2doc.element.MTable}

Gets the Sequence of tables for the tables associated to the given EObject with the given description name.

### Example

| Expression | Result |
| ---------- | ------ |
| ePackage.asTableByRepresentationDescriptionName('dependency table') | Sequence{table1, table2} |

## String.asTableByRepresentationName() : org.obeonetwork.m2doc.element.MTable

Insert the table of the given representation name.

### Example

| Expression | Result |
| ---------- | ------ |
| 'MyEPackage class diagram'.asTableByRepresentationName() | insert the table |

## EObject.isRepresentationDescriptionName(String) : boolean

Returns <code>true</code> if the arguments are not null, the eObject is in a session and there's a representation description with the specified name in it that is associated to the specified eObject. Returns <code>false</code> otherwise.

### Example

| Expression | Result |
| ---------- | ------ |
| ePackage.isRepresentationDescriptionName('class diagram') | True |

## String.isRepresentationName() : boolean

Returns <code>true</code> if the arguments are not null, there's a representation with the specified name in the Sirius session. Returns <code>false</code> otherwise.

### Example

| Expression | Result |
| ---------- | ------ |
| 'MyEPackage class diagram'.isRepresentationName() | True |

## EObject.representationByDescriptionName(String) : Sequence{org.eclipse.sirius.viewpoint.DRepresentation}

Gets the Sequence DRepresentation associated to the given EObject with the given description name.

### Example

| Expression | Result |
| ---------- | ------ |
| ePackage.asImageByRepresentationDescriptionName('class diagram') | Sequence{dRepresentation1, dRepresentation2} |

## String.representationByName() : org.eclipse.sirius.viewpoint.DRepresentation

Gets the DRepresentation of the given representation name.

### Example

| Expression | Result |
| ---------- | ------ |
| 'MyEPackage class diagram'.asImageByRepresentationName() | dRepresentation1 |



