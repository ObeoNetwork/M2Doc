---
layout: article
title: PaginationServices
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

# PaginationServices

Services available for pagination

* TOC
{:toc}

## String.asPagination() : org.obeonetwork.m2doc.element.MPagination

Converts a String to an hyperlink

### Example

| Expression | Result |
| ---------- | ------ |
| 'newPage'.asPagination() | insert a page break |

## String.asStyle(String) : org.obeonetwork.m2doc.element.MParagraph

Converts a String with a given style if the style exists in the template, this service will insert a new paragraph. You can add styled text in comment to make sure they are present.

### Example

| Expression | Result |
| ---------- | ------ |
| 'Section 1'.asStyle('Title1') | insert 'Section 1' as style 'Titre1' in a new paragraph if the style exists in the template. |



