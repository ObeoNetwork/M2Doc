---
layout: article-services
title: M2DocHTMLServices
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

# M2DocHTMLServices

Services available for HTML insertion.

* TOC
{:toc}

## String.fromHTMLBodyString() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given body String.

### Parameter

* **bodyString**: The body String.

### Example

* '<h2 id="starting-with-m2doc">Starting with M2Doc</h2>'.fromHTMLBodyString()
  * The Sequence of MElement corresponding to the given HTML String.

## String.fromHTMLBodyString(String) : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given body String.

### Parameter

* **bodyString**: The HTML body.
* **baseURI**: The base URI to use for link and images resolving.

### Example

* '<h2 id="starting-with-m2doc">Starting with M2Doc</h2>'.fromHTMLBodyString('http://www.m2doc.org/')
  * The Sequence of MElement corresponding to the given body String.

## String.fromHTMLString() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given HTML String.

### Parameter

* **htmlString**: The HTML document.

### Example

* '<html><head><title>Sample HTML for test purpose</title></head><body><h2 id="starting-with-m2doc">Starting with M2Doc</h2></body></html>').fromHTMLString()
  * The Sequence of MElement corresponding to the given HTML String.

## String.fromHTMLString(String) : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given HTML String.

### Parameter

* **htmlString**: The HTML document.
* **baseURI**: The base URI to use for link and images resolving.

### Example

* '<html><head><title>Sample HTML for test purpose</title></head><body><h2 id=\"starting-with-m2doc\">Starting with M2Doc</h2></body></html>'.fromHTMLString('http://www.m2doc.org/')
  * The Sequence of MElement corresponding to the given HTML String.

## String.fromHTMLURI() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given web page.

### Parameter

* **uriStr**: The URI.

### Example

* 'http://www.m2doc.org/'.fromHTMLURI()
  * The Sequence of MElement corresponding to the given web page.



