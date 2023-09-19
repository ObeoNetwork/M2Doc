---
layout: article-services
title: BooleanServices
subtitle: 3.3.1
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

# BooleanServices

Services available for Booleans. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/3.3.1/tests/org.obeonetwork.m2doc.tests/resources/booleanServices).

* TOC
{:toc}

## boolean.check() : String

Returns "X" for boolean true.

### Parameter

* **value**: The boolean value

### Example

* true.check()
  * X
* false.check()
  * 

## boolean.yesNo() : String

Returns "Yes" for boolean true, "No" otherwise.

### Parameter

* **value**: The boolean value

### Example

* true.yesNo()
  * Yes
* false.yesNo()
  * No



