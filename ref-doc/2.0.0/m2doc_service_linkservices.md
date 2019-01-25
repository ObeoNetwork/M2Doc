---
layout: article
title: LinkServices
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

# LinkServices

Services available for links

* TOC
{:toc}

## String.asBookmark(String) : org.obeonetwork.m2doc.element.MBookmark

Converts a String to a bookmark declaration

### Example

| Expression | Result |
| ---------- | ------ |
| 'Definition of Artifact1'.asBookmark('Art1') | a bookmark with the ID 'Art1' the label 'Definition of Artifact1' |

## String.asBookmarkRef(String) : org.obeonetwork.m2doc.element.MBookmark

Converts a String to a bookmark reference

### Example

| Expression | Result |
| ---------- | ------ |
| 'Artifact1'.asBookmark('Art1') | a bookmark reference with the ID 'Art1' the label 'Definition of Artifact1' |

## String.asLink(String) : org.obeonetwork.m2doc.element.MHyperLink

Converts a String to an hyperlink

### Example

| Expression | Result |
| ---------- | ------ |
| 'My website'.asLink('http://www.example.org') | a link to http://www.example.org with the label My website |



