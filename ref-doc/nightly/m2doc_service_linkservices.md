---
layout: article-services
title: LinkServices
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

# LinkServices

Services available for links. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/linkServices).

* TOC
{:toc}

## String.asBookmarkRef(String) : org.obeonetwork.m2doc.element.MBookmark

Converts a String to a bookmark reference

### Parameter

* **text**: The label of the bookmark reference
* **id**: The ID of the bookmark reference

### Example

* 'Artifact1'.asBookmarkRef('Art1')
  * a bookmark reference with the ID 'Art1' the label 'Artifact1'

## String.asBookmarkRef(String, boolean) : org.obeonetwork.m2doc.element.MBookmark

Converts a String to a bookmark reference

### Parameter

* **text**: The label of the bookmark reference
* **id**: The ID of the bookmark reference
* **optional**: If true, this reference will only generate the text if the corresponding bookmark doesn't exists

### Example

* 'Artifact1'.asBookmarkRef('Art1', true)
  * a bookmark reference with the ID 'Art1' the label 'Artifact1' only if a bookmark with ID 'Art1' exists

## String.asBookmark(String) : org.obeonetwork.m2doc.element.MBookmark

Converts a String to a bookmark declaration

### Parameter

* **text**: The label of the bookmark declaration
* **id**: The ID of the bookmark declaration

### Example

* 'Definition of Artifact1'.asBookmark('Art1')
  * a bookmark with the ID 'Art1' the label 'Definition of Artifact1'

## String.asLink(String) : org.obeonetwork.m2doc.element.MHyperLink

Converts a String to an hyperlink

### Parameter

* **text**: The label of the link
* **url**: The destination of the link

### Example

* 'My website'.asLink('https://www.example.org')
  * a link to https://www.example.org with the label My website

## String.asLink(String, String) : org.obeonetwork.m2doc.element.MHyperLink

Converts a String to an hyperlink

### Parameter

* **text**: The label of the link
* **url**: The destination of the link
* **toolTip**: The tool tip of the link

### Example

* 'My website'.asLink('https://www.example.org', 'My tool tip')
  * a link to https://www.example.org with the label My website with a tool tip



