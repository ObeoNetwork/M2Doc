---
layout: article-services
title: M2DocWikiTextServices
subtitle: 3.3.4
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

# M2DocWikiTextServices

Services available for WikiText insertion.

* TOC
{:toc}

## String.fromAsciiDocString() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="http://asciidoc.org/">AsciiDoc</a> String.

### Parameter

* **markupContents**: The markup contents.

### Example

* 'The First Chapter\n-----------------'.fromAsciiDocString()
  * The Sequence of MElement corresponding to the given <a href="http://asciidoc.org/">AsciiDoc</a> String.

## String.fromAsciiDocString(String) : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="http://asciidoc.org/">AsciiDoc</a> String.

### Parameter

* **markupContents**: The markup contents.
* **baseURI**: The base URI to use for link and images resolving.

### Example

* 'The First Chapter\n-----------------'.fromAsciiDocString('https://www.m2doc.org/')
  * The Sequence of MElement corresponding to the given <a href="http://asciidoc.org/">AsciiDoc</a> String.

## String.fromAsciiDocURI() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="http://asciidoc.org/">AsciiDoc</a> contents at the given URI.

### Parameter

* **srcURI**: The markup contents URI.

### Example

* 'contents.txt'.fromAsciiDocString()
  * The Sequence of MElement corresponding to the given <a href="http://asciidoc.org/">AsciiDoc</a> contents at the given URI.

## String.fromConfluenceString() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html">Confluence</a> String.

### Parameter

* **markupContents**: The markup contents.

### Example

* 'h1. The First Chapter'.fromConfluenceString()
  * The Sequence of MElement corresponding to the given <a href="https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html">Confluence</a> String.

## String.fromConfluenceString(String) : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html">Confluence</a> String.

### Parameter

* **markupContents**: The markup contents.
* **baseURI**: The base URI to use for link and images resolving.

### Example

* 'h1. The First Chapter'.fromConfluenceString('https://www.m2doc.org/')
  * The Sequence of MElement corresponding to the given <a href="https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html">Confluence</a> String.

## String.fromConfluenceURI() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html">Confluence</a> contents at the given URI.

### Parameter

* **srcURI**: The markup contents URI.

### Example

* 'contents.txt'.fromConfluenceString()
  * The Sequence of MElement corresponding to the given <a href="https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html">Confluence</a> contents at the given URI.

## String.fromMarkdownString() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://en.wikipedia.org/wiki/Markdown">Markdown</a> String.

### Parameter

* **markupContents**: The markup contents.

### Example

* 'The First Chapter\n================='.fromMarkdownString()
  * The Sequence of MElement corresponding to the given <a href="https://en.wikipedia.org/wiki/Markdown">Markdown</a> String.

## String.fromMarkdownString(String) : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://en.wikipedia.org/wiki/Markdown">Markdown</a> String.

### Parameter

* **markupContents**: The markup contents.
* **baseURI**: The base URI to use for link and images resolving.

### Example

* 'The First Chapter\n================='.fromMarkdownString('https://www.m2doc.org/')
  * The Sequence of MElement corresponding to the given <a href="https://en.wikipedia.org/wiki/Markdown">Markdown</a> String.

## String.fromMarkdownURI() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://en.wikipedia.org/wiki/Markdown">Markdown</a> contents at the given URI.

### Parameter

* **srcURI**: The markup contents URI.

### Example

* 'contents.txt'.fromMarkdownString()
  * The Sequence of MElement corresponding to the given <a href="https://en.wikipedia.org/wiki/Markdown">Markdown</a> contents at the given URI.

## String.fromMediaWikiString() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://www.mediawiki.org/wiki/Help:Formatting">MediaWiki</a> String.

### Parameter

* **markupContents**: The markup contents.

### Example

* '== The First Chapter =='.fromMediaWikiString()
  * The Sequence of MElement corresponding to the given <a href="https://www.mediawiki.org/wiki/Help:Formatting">MediaWiki</a> String.

## String.fromMediaWikiString(String) : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://www.mediawiki.org/wiki/Help:Formatting">MediaWiki</a> String.

### Parameter

* **markupContents**: The markup contents.
* **baseURI**: The base URI to use for link and images resolving.

### Example

* '== The First Chapter =='.fromMediaWikiString('https://www.m2doc.org/')
  * The Sequence of MElement corresponding to the given <a href="https://www.mediawiki.org/wiki/Help:Formatting">MediaWiki</a> String.

## String.fromMediaWikiURI() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://www.mediawiki.org/wiki/Help:Formatting">MediaWiki</a> contents at the given URI.

### Parameter

* **srcURI**: The markup contents URI.

### Example

* 'contents.txt'.fromMediaWikiString()
  * The Sequence of MElement corresponding to the given <a href="https://www.mediawiki.org/wiki/Help:Formatting">MediaWiki</a> contents at the given URI.

## String.fromTWikiString() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/">TWiki</a> String.

### Parameter

* **markupContents**: The markup contents.

### Example

* '---+ The First Chapter'.fromTWikiString()
  * The Sequence of MElement corresponding to the given <a href="https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/">TWiki</a> String.

## String.fromTWikiString(String) : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/">TWiki</a> String.

### Parameter

* **markupContents**: The markup contents.
* **baseURI**: The base URI to use for link and images resolving.

### Example

* '---+ The First Chapter'.fromTWikiString('https://www.m2doc.org/')
  * The Sequence of MElement corresponding to the given <a href="https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/">TWiki</a> String.

## String.fromTWikiURI() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/">TWiki</a> contents at the given URI.

### Parameter

* **srcURI**: The markup contents URI.

### Example

* 'contents.txt'.fromTWikiString()
  * The Sequence of MElement corresponding to the given <a href="https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/">TWiki</a> contents at the given URI.

## String.fromTextileString() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://textile-lang.com/">Textile</a> String.

### Parameter

* **markupContents**: The markup contents.

### Example

* 'h1. The First Chapter'.fromTextileString()
  * The Sequence of MElement corresponding to the given <a href="https://textile-lang.com/">Textile</a> String.

## String.fromTextileString(String) : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://textile-lang.com/">Textile</a> String.

### Parameter

* **markupContents**: The markup contents.
* **baseURI**: The base URI to use for link and images resolving.

### Example

* 'h1. The First Chapter'.fromTextileString('https://www.m2doc.org/')
  * The Sequence of MElement corresponding to the given <a href="https://textile-lang.com/">Textile</a> String.

## String.fromTextileURI() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://textile-lang.com/">Textile</a> contents at the given URI.

### Parameter

* **srcURI**: The markup contents URI.

### Example

* 'contents.txt'.fromTextileString()
  * The Sequence of MElement corresponding to the given <a href="https://textile-lang.com/">Textile</a> contents at the given URI.

## String.fromTracWikiString() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://trac.edgewall.org/wiki/WikiFormatting/">TracWiki</a> String.

### Parameter

* **markupContents**: The markup contents.

### Example

* '= The First Chapter ='.fromTracWikiString()
  * The Sequence of MElement corresponding to the given <a href="https://trac.edgewall.org/wiki/WikiFormatting/">TracWiki</a> String.

## String.fromTracWikiString(String) : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://trac.edgewall.org/wiki/WikiFormatting/">TracWiki</a> String.

### Parameter

* **markupContents**: The markup contents.
* **baseURI**: The base URI to use for link and images resolving.

### Example

* '= The First Chapter ='.fromTracWikiString('https://www.m2doc.org/')
  * The Sequence of MElement corresponding to the given <a href="https://trac.edgewall.org/wiki/WikiFormatting/">TracWiki</a> String.

## String.fromTracWikiURI() : Sequence{org.obeonetwork.m2doc.element.MElement}

Returns a Sequence of MElement corresponding to the given <a href="https://trac.edgewall.org/wiki/WikiFormatting/">TracWiki</a> contents at the given URI.

### Parameter

* **srcURI**: The markup contents URI.

### Example

* 'contents.txt'.fromTracWikiString()
  * The Sequence of MElement corresponding to the given <a href="https://trac.edgewall.org/wiki/WikiFormatting/">TracWiki</a> contents at the given URI.



