---
layout: article-services
title: PaginationServices
subtitle: Nightly
relativePath: ../..
---

<!--
/********************************************************************************
** Copyright (c) 2015, 2023 Obeo.
** All rights reserved. This program and the accompanying materials
** are made available under the terms of the Eclipse Public License v2.0
** which accompanies this distribution, and is available at
** http://www.eclipse.org/legal/epl-v20.html
**
** Contributors:
**    Stephane Begaudeau (Obeo) - initial API and implementation
*********************************************************************************/
-->

# PaginationServices

Services available for pagination. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/paginationServices).

* TOC
{:toc}

## String.asPagination() : org.obeonetwork.m2doc.element.MPagination

Converts a String to an hyperlink

### Parameter

* **paginationElement**: The name of the pagination element: 'newTableOfContent', 'newPage', 'newParagraph', 'newColumn', 'newTextWrapping'

### Example

* 'newPage'.asPagination()
  * insert a page break

## String.asParagraph() : org.obeonetwork.m2doc.element.MParagraph

Convert a String into a MParagraph.

### Parameter

* **text**: The text

### Example

* 'some text'.asParagraph()
  * insert 'some text' in a new paragraph.

## String.asStyle(String) : org.obeonetwork.m2doc.element.MParagraph

Converts a String with a given style if the style exists in the template, this service will insert a new paragraph. You can add styled text in comment to make sure they are present.

### Parameter

* **text**: The text
* **style**: The style ID. see availableTextStyles().

### Example

* 'Section 1'.asStyle('Title1')
  * insert 'Section 1' as style 'Titre1' in a new paragraph if the style exists in the template.

## org.obeonetwork.m2doc.element.MTable.asStyle(String) : org.obeonetwork.m2doc.element.MTable

Converts a MTable with a given style if the style exists in the template. You can add styled text in blockcomment to make sure they are present.

### Parameter

* **table**: The MTable
* **styleID**: The style ID. see availableTableStyles().

### Example

* myTable.asStyle('Title1')
  * set the given style to myTable.

## Object.availableNumberingIDs() : Sequence{org.obeonetwork.m2doc.element.MParagraph}

Lists available numbering IDs in the template. You can add bullet/numbered list in comment to make sure a numbering is present.

### Parameter

* **obj**: Any object

### Example

* ''.availableNumberingIDs()
  * insert 'List of available numbering IDs:...'.

## Object.availableTableStyles() : Sequence{org.obeonetwork.m2doc.element.MElement}

Lists available table styles in the template. You can add styled table in comment to make sure a style is present.

### Parameter

* **obj**: Any object

### Example

* ''.availableTableStyles()
  * insert 'List of available table styles:...'.

## Object.availableTextStyles() : Sequence{org.obeonetwork.m2doc.element.MParagraph}

Lists available text styles in the template. You can add styled text in comment to make sure a style is present.

### Parameter

* **obj**: Any object

### Example

* ''.availableTextStyles()
  * insert 'List of available text styles:...'.

## org.obeonetwork.m2doc.element.MParagraph.setAlignment(String) : org.obeonetwork.m2doc.element.MParagraph

Sets the alignment of the paragraph.

### Parameter

* **paragraph**: The paragraph
* **alignment**: The alignment name: 'BOTH', 'CENTER', 'DISTRIBUTED', 'HIGH_KASHIDA', 'LEFT', 'LOW_KASHIDA', 'MEDIUM_KASHIDA', 'NUM_TAB', 'RIGHT', 'THAI_DISTRIBUTE'.

### Example

* myParagraph.setAlignment('CENTER')
  * Sets the alignment to center.

## org.obeonetwork.m2doc.element.MParagraph.setNumbering(Integer, Integer) : org.obeonetwork.m2doc.element.MParagraph

Sets the numbering and level of the paragraph.

### Parameter

* **paragraph**: The paragraph
* **numberingID**: The integer idenfying the numbering. see availableNumberingIDs().
* **level**: The level in the numbering. see availableNumberingIDs().

### Example

* myParagraph.setNumbering(0, 3)
  * Sets the numbering to the 0 numbering and level to 3 in this numbering.

## org.obeonetwork.m2doc.element.MParagraph.setStyle(String) : org.obeonetwork.m2doc.element.MParagraph

Sets the style of the paragraph.

### Parameter

* **paragraph**: The paragraph
* **styleID**: The style ID. see availableTextStyles().

### Example

* myParagraph.setStyle('Title1')
  * Sets the style to Title1.



