---
layout: article
title: ExcelServices
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

# ExcelServices

Services available for Excel

* TOC
{:toc}

## String.asTable(String, String, String, String) : org.obeonetwork.m2doc.element.MTable

Insert a table from an Excel .xlsx file.

### Example

| Expression | Result |
| ---------- | ------ |
| 'excel.xlsx'.asTable('Feuil1', 'C3', 'F7', 'fr-FR') | insert the table from 'excel.xlsx' |

## String.asTable(String, String, String) : org.obeonetwork.m2doc.element.MTable

Insert a table from an Excel .xlsx file.

### Example

| Expression | Result |
| ---------- | ------ |
| 'excel.xlsx'.asTable('Feuil1', 'C3', 'F7') | insert the table from 'excel.xlsx' |



