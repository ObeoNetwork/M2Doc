---
layout: article-services
title: ExcelServices
subtitle: 3.2.2
relativePath: ../..
---

<!--
/********************************************************************************
** Copyright (c) 2015 Obeo.
** All rights reserved. This program and the accompanying materials
** are made available under the terms of the Eclipse Public License v2.0
** which accompanies this distribution, and is available at
** http://www.eclipse.org/legal/epl-v20.html
**
** Contributors:
**    Stephane Begaudeau (Obeo) - initial API and implementation
*********************************************************************************/
-->

# ExcelServices

Services available for Excel. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/3.2.2/tests/org.obeonetwork.m2doc.tests/resources/excelServices).

* TOC
{:toc}

## String.asTable(String, String, String) : org.obeonetwork.m2doc.element.MTable

Insert a table from an Excel .xlsx file.

### Parameter

* **uri**: The Excel .xlsx file uri, it can be relative to the template
* **sheetName**: The sheet name
* **topLeftCellAdress**: The top left cell address
* **bottomRightCellAdress**: The bottom right cell address

### Example

* 'excel.xlsx'.asTable('Feuil1', 'C3', 'F7')
  * insert the table from 'excel.xlsx'

## String.asTable(String, String, String, String) : org.obeonetwork.m2doc.element.MTable

Insert a table from an Excel .xlsx file.

### Parameter

* **uri**: The Excel .xlsx file uri, it can be relative to the template
* **sheetName**: The sheet name
* **topLeftCellAdress**: The top left cell address
* **bottomRightCellAdress**: The bottom right cell address
* **languageTag**: The language tag for the locale

### Example

* 'excel.xlsx'.asTable('Feuil1', 'C3', 'F7', 'fr-FR')
  * insert the table from 'excel.xlsx'



