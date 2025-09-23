---
layout: article-services
title: GenerationResultServices
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

# GenerationResultServices

Services available for GenerationResult after a template construct call.

* TOC
{:toc}

## org.obeonetwork.m2doc.generator.GenerationResult.asHTML() : String

Insert the HTML representation of the given GenerationResult.

### Parameter

* **result**: The generation result

### Example

* self.myTemplate().asHTML()
  * the HTML representation of the given GenerationResult

## org.obeonetwork.m2doc.generator.GenerationResult.asText() : String

Insert the text representation of the given GenerationResult.

### Parameter

* **result**: The generation result

### Example

* self.myTemplate().asText()
  * the text representation of the given GenerationResult



