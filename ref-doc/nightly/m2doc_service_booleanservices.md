---
layout: article
title: BooleanServices
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

# BooleanServices

Services available for Booleans

* TOC
{:toc}

## boolean.check() : String

Returns "X" for boolean true.

### Example

| Expression | Result |
| ---------- | ------ |
| true.check() | X |
| false.check() |  |

## boolean.yesNo() : String

Returns "Yes" for boolean true, "No" otherwise.

### Example

| Expression | Result |
| ---------- | ------ |
| true.yesNo() | Yes |
| false.yesNo() | No |



