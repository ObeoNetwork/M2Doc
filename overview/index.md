---
layout: article
title: Overview
subtitle: Discover M2Doc
relativePath: ..
---

The M2Doc project provides Word document (.docx files) generation. It is based on a document template and [EMF](https://www.eclipse.org/modeling/emf/) models. The overall approach consists in creating templates in the OOX format where static text authoring benefits from the WYSIWYG capabilities of the usual tools (e.g. Microsoft Word, Libre Office, Open Office). Dynamic parts are inserted using a dedicated vocabulary of OOX fields code. Fields are mainly used to insert page numbers, references, etc. M2Doc makes use of them to describe documentation generation directives. This allows a total separation between the document and the M2Doc directives.

The architecture of M2Doc is organized around three building blocs:
* [Apache POI](https://poi.apache.org/) for the parsing and generation of [OOXML](https://fr.wikipedia.org/wiki/Office_Open_XML) documents
* [AQL](https://www.eclipse.org/acceleo/documentation/aql.html) for querying the models
* [EMF](https://www.eclipse.org/modeling/emf/) as a general platform

![Technical architecture]({{page.relativePath}}/images/TechnicalArchitecture.png)

The workflow of M2Doc is depicted below: 

![M2Doc Workflow]({{page.relativePath}}/images/M2DocWorkflow.png)

As a preview here follows a screenshot of a template's fragment and the corresponding generation result for a database model.

![DB Template]({{page.relativePath}}/images/DBTemplate.png)
 
The generation results looks like this: 

![DB Result]({{page.relativePath}}/images/DBResult.png)

The template language makes an extensive use of the [Acceleo Query Language](https://www.eclipse.org/acceleo/documentation/aql.html) which provides a full-fledged, extensible model query language and engine.

You probably want to start with the [Download]({{page.relativePath}}/download) section. 