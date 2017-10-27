---
layout: article
title: Overview
subtitle: Discover M2Doc
relativePath: ..
---

The M2Doc project provides Word document (.docx files) generation based on a document template and [EMF](https://www.eclipse.org/modeling/emf/) models. 

The overall approach consists in creating templates in the [OOXML](https://en.wikipedia.org/wiki/Office_Open_XML) format where static text authoring benefits from the WYSIWYG capabilities of Microsoft Word. Dynamic parts are inserted using a dedicated vocabulary of [OOXML](https://en.wikipedia.org/wiki/Office_Open_XML) fields code. Fields are mainly used to insert page numbers, references, etc. M2Doc makes use of them to describe documentation generation directives. This allows a total separation between the document and the M2Doc directives.

![Overview]({{page.relativePath}}/images/m2doc-overview.png)

As an example, here is a fragment of a M2Doc template used to generate the documentation of a database model:

![DB Template]({{page.relativePath}}/images/DBTemplate.png)
 
The generation looks like this: 

![DB Result]({{page.relativePath}}/images/DBResult.png)

The template language makes an extensive use of the [Acceleo Query Language](https://www.eclipse.org/acceleo/documentation/aql.html) which provides a full-fledged, extensible model query language and engine. 
The M2Doc templates can be validated. If errors are found, an annotated templates is produced describing the problems found.

![M2Doc Workflow]({{page.relativePath}}/images/M2DocWorkflow.png)

The architecture of M2Doc is organized around three building blocks:
* [Apache POI](https://poi.apache.org/) for the parsing and generation of [OOXML](https://en.wikipedia.org/wiki/Office_Open_XML) documents
* [AQL](https://www.eclipse.org/acceleo/documentation/aql.html) for querying the models
* [EMF](https://www.eclipse.org/modeling/emf/) as a general platform

![Technical architecture]({{page.relativePath}}/images/TechnicalArchitecture.png)

You probably want to start with the [Download]({{page.relativePath}}/download) section. 
