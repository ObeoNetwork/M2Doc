---
layout: article
title: Reference Documentation
subtitle: Nightly
relativePath: ../..
---

# Table of Content

Following you will find the reference documentation corresponding to the **Nightly** version.
If you need an other version use one of our [released versions]({{page.relativePath}}/download/#all-versions). 

* TOC
{:toc}

## Overview

The M2Doc project provides Word document (.docx files) generation based on a document template and [EMF](https://www.eclipse.dev/modeling/emf/) models. 

The overall approach consists in creating templates in the [OOXML](https://en.wikipedia.org/wiki/Office_Open_XML) format where static text authoring benefits from the WYSIWYG capabilities of Microsoft Word. Dynamic parts are inserted using a dedicated vocabulary of [OOXML](https://en.wikipedia.org/wiki/Office_Open_XML) fields code. Fields are mainly used to insert page numbers, references, etc. M2Doc makes use of them to describe documentation generation directives. This allows a total separation between the document and the M2Doc directives.

![Overview]({{page.relativePath}}/ref-doc/nightly/images/m2doc-overview.png)

As an example, here is a fragment of a M2Doc template used to generate the documentation of a database model:

![DB Template]({{page.relativePath}}/ref-doc/nightly/images/DBTemplate.png)
 
The generation looks like this: 

![DB Result]({{page.relativePath}}/ref-doc/nightly/images/DBResult.png)

The template language makes an extensive use of the [Acceleo Query Language](https://www.eclipse.dev/acceleo/documentation/aql.html) which provides a full-fledged, extensible model query language and engine. 
The M2Doc templates can be [validated](index.html#validating-a-generation-setup). If errors are found, an annotated templates is produced describing the problems found.

### Principles

* Definition of model entry points
  * Declaration of variables (see the [Template properties wizard](index.html#template-properties-wizard) section)
  * Mapping with model elements (see the [Generation configuration wizard](index.html#generation-configuration-wizard) section)

![Variable definition]({{page.relativePath}}/ref-doc/nightly/images/Variable%20definition.png "Variable definition.")

* Extraction of information by navigating the model (see the [Template authoring](index.html#template-authoring) section)
  * From the entry points
  * Using model relations

![Extracting information]({{page.relativePath}}/ref-doc/nightly/images/Extracting%20information.png "Extracting information.")

* Formatting of the output information
  * Using Microsoft Word functionalities

![Formatting template]({{page.relativePath}}/ref-doc/nightly/images/Formatting%20template.png "Formatting template.")

### Main steps

* Definition of content, navigation, and format (see the [Template authoring](index.html#template-authoring) section)
* Declaration of variables (see the [Template properties wizard](index.html#template-properties-wizard) section)
* Mapping of variables with model elements, definition of input model and output file (see the [Generation configuration wizard](index.html#generation-configuration-wizard) section)
* Generation of output document (see the [Generate a document](index.html#generating-a-document) section)

![Main steps]({{page.relativePath}}/ref-doc/nightly/images/Main%20steps.png "Main steps.")

### M2Doc roles

Ready to try M2Doc! You might have one of the following roles:

#### Template user

You already have the template and want to generate the document:

* see [Generate a document](index.html#generating-a-document)
* see [Maven](index.html#maven) (optional)

#### Template developer

You want to create your own template:

* see [Template authoring](index.html#template-authoring)
* see [Provide new services](index.html#providing-new-services) (optional)
* see [Validating a generation](index.html#validating-a-generation-setup) (optional)
* see [Template testing](index.html#template-testing) (optional)

#### Integrator

You want to provide document generation in your own project using M2Doc:

* see [Using M2Doc programmatically](index.html#using-m2doc-programmatically)
* see [Using AQL programmatically](https://www.eclipse.dev/acceleo/documentation/aql.html#UsingAQLprogrammatically) (optional)

## Architecture

The main workflow:

![M2Doc Workflow]({{page.relativePath}}/ref-doc/nightly/images/M2DocWorkflow.png)

The template is parsed in an internal representation (the AST). From here you can bind the variables to their values, using elements from a model for instance and evaluate the template to generate the output document. An added benefit of using M2Doc is the hability to [validate](index.html#validating-a-generation-setup) a template to produce an annotated template containing informations, warnings and errors the template might contain.

The architecture of M2Doc is organized around three building blocks:
* [Apache POI](https://poi.apache.org/) for the parsing and generation of [OOXML](https://en.wikipedia.org/wiki/Office_Open_XML) documents
* [AQL](https://www.eclipse.dev/acceleo/documentation/aql.html) for querying the models
* [EMF](https://www.eclipse.dev/modeling/emf/) as a general platform

![Technical architecture]({{page.relativePath}}/ref-doc/nightly/images/TechnicalArchitecture.png)

<a name="generate-a-document"></a>
## Generating a document

To generate a document you will need at least a template file (.docx file) and a generation configuration model (.genconf file). If you don&#8217;t have the template you can read the [template authoring](index.html#template-authoring) section. To create a generation model file you can read the [initialize generation](index.html#initializing-a-generation-configuration) section.

### Launching a generation

To launch a generation you can right click on the generation configuration model (.genconf file) or the Generation object and select the &#171;Generate Documentation menu&#187; as shown here:

![The Generate Documentation menu.]({{page.relativePath}}/ref-doc/nightly/images/Generate%20Documentation%20menu.png "The Generate Documentation menu.")

You can also use the &#171;M2Doc Generate Template&#187; menu on the template that is referenced by one or more generation configuration models (.genconf file):

![The M2Doc Generate Template menu.]({{page.relativePath}}/ref-doc/nightly/images/M2Doc%20Generate%20Template%20menu.png "The M2Doc Generate Template menu.")

Or the &#171;M2Doc Update Document&#187; menu on the generated document that is referenced by one or more generation configuration models (.genconf file):

![The M2Doc Update Document menu.]({{page.relativePath}}/ref-doc/nightly/images/M2Doc%20Update%20Document%20menu.png "The M2Doc Generate Document menu.")

If you don&#8217;t have the the generation model configuration file you can create it using the [initialize generation](index.html#initializing-a-generation-configuration) section.

### Initializing a generation configuration

To initialize a generation configuration you will need a template file (.docx file). If you don&#8217;t have the template you can read the [template authoring](index.html#template-authoring) section.

Right click on the template file and select the &#171;Initialize Documentation Configuration&#187; menu:

![The Initialize Documentation Configuration menu.]({{page.relativePath}}/ref-doc/nightly/images/Initialize%20Documentation%20Configuration%20menu.png "The Initialize Documentation Configuration menu.")

To edit the created generation model file you can use the [Generation configuration wizard](index.html#generation-configuration-wizard).

### Validating a generation setup

To validate a generation setup (template plus generation configuration) you can right click on the generation configuration model (.genconf file) or the Generation object and select the &#171;Validate Documentation menu&#187; as shown here:

![The Validate Documentation menu.]({{page.relativePath}}/ref-doc/nightly/images/Validate%20Documentation%20menu.png "The Validate Documentation menu.")

If you don&#8217;t have the the generation configuration model file you can create it using the [initialize generation](index.html#initializing-a-generation-configuration) section. If the validation succeeds with no informations, warnings, or errors you will be prompted. If something went wrong a .docx file with the name of the template and suffixed with &#171;info&#187;, &#171;warning&#187;, or &#171;error&#187; will be created. This new file contains details of the validation issues. The template is *not* modified.

### Generation configuration wizard

You can access the generation configuration wizard in two different ways, one for creating it and one for further edition when the .genconf file already exists.

To create and initialize the generation configuration, select the template .docx file to use for generation and right click on it:

![The Initialize Documentation Configuration menu.]({{page.relativePath}}/ref-doc/nightly/images/Initialize%20Documentation%20Configuration%20menu.png "The Initialize Documentation Configuration menu.")

To edit an existing generation configuration .genconf file you can select the .genconf file and right click on it:

![The Edit Documentation Configuration menu.]({{page.relativePath}}/ref-doc/nightly/images/Edit%20Documentation%20Configuration%20menu.png "The Edit Documentation Configuration menu.")

At this point you should be on the first page of the generation configuration wizard:

![The Generation Configuration Wizard.]({{page.relativePath}}/ref-doc/nightly/images/Generation%20Configuration%20Wizard1.png "The Generation Configuration Wizard.")

On this page you can select the path of the generation configuration .genconf file. It can be changed to create a copy of an existing file for instance. Other file paths are relative to the generation configuration file. This allow to move a folder containing all the files needed for a generation without breaking the generation configuration. The template can be selected from the workspace, the [Template registry](index.html#template-registry), or [Template libraries](index.html#template-libraries) (in the two last cases the path will be absolute):

* workspace: allows to browse templates in the workspace.
* [Template registry](index.html#template-registry): allows to browse templates in deployed plug-ins.
* [Template libraries](index.html#template-libraries): allows to browse templates in template libraries.

The next page of the wizard is dedicated to variables and binding values to them:

![The Generation Configuration Wizard.]({{page.relativePath}}/ref-doc/nightly/images/Generation%20Configuration%20Wizard2.png "The Generation Configuration Wizard.")

To reference new values you can load new resources using the `Load resource` button. Note the `Options (expert)` tab that allow to set advanced options to find values from a Sirius session for instance.

If you are using M2Doc with [Sirius](https://www.eclipse.dev/sirius/), [Obeo Designer](https://www.obeodesigner.com/en/), [Obeo Designer Team](https://www.obeodesigner.com/en/collaborative-features), [Capella](https://mbse-capella.org/), or [Team for Capella](https://www.obeosoft.com/en/capella-professional-offer), you need to set the SiriusSession option. This will allow you to use [Sirius services](index.html#services) like exporting diagrams as images:

![SiriusSession Option.]({{page.relativePath}}/capella/images/SiriusSession%20Option.png "SiriusSession Option.")

Setting this option should allow you to select model elements for each model variable you have in your template. By default the first element matching the declared type of the variable will be selected, but you can edit the value by selecting the variable and using the edit button:

![Edit Variable.]({{page.relativePath}}/capella/images/Edit%20Variable.png "Edit Variable.")

When the `Finish` button is pushed the generation configuration file is created or edited.

You can also use M2Doc with [Team for Capella](https://www.obeosoft.com/en/capella-professional-offer) or [Obeo Designer Team](https://www.obeodesigner.com/en/collaborative-features). If you had existing .genconf files before you shared your project to Team for Capella or Obeo Designer Team, you will need to edit them.

First you need to change the SiriusSession since the .aird file has been renamed. You should need to add "team" like this:

* In-Flight Entertainment System.aird

becomes:

* In-Flight Entertainment System.team.aird

Secondly you will need to set the value of your model variables since the model has been moved to the Team server. By default the first element matching the declared type of the variable will be selected, but you can edit the value by selecting the variable and using the edit button:

![Edit Variable.]({{page.relativePath}}/capella/images/Edit%20Variable.png "Edit Variable.")

You will see your model to select the value from:

![Select Variable Value.]({{page.relativePath}}/capella/images/Select%20Variable%20Value.png "Select Variable Value.")


## Template authoring

You need to have a model already to be able to generate a document with M2Doc. Then you can [create a project](index.html#new-project-wizard) or if you already have a project, you can [create a template](index.html#new-template-wizard).

### New project wizard

This wizard is the fastest and easiest way to get you started with M2Doc. But it wont allow you to use [custom services](index.html#providing-new-services). To do so, you need to create a Java project and make sure your service Class is accessible in the classpath of the project.

This wizard allows to create all needed artifacts:

- an Eclipse project
- a default template
- the genconf file

You only need your model as input.

To launch the wizard right click in the project exporer and select new project:

![The New project Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Project%20Wizard1.png "The New project Wizard.")

Then select M2Doc project in the M2Doc category:

![The New project Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Project%20Wizard2.png "The New project Wizard.")

Enter your project name and click next:

![The New project Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Project%20Wizard3.png "The New project Wizard.")

Enter your template name and click next:

![The New project Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Project%20Wizard4.png "The New project Wizard.")

Select the main variable value and name then click next:

![The New project Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Project%20Wizard5.png "The New project Wizard.")

![The New project Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Project%20Wizard6.png "The New project Wizard.")

The last page of the wizard is dedicated to file pathes:

![The New project Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Project%20Wizard7.png "The New project Wizard.")

NOTE: you can also start the generation on finish.

### New template wizard

This wizard creates a simple template from a model.

To launch the wizard right click in the project exporer and select new other:

![The New template Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Template%20Wizard1.png "The New template Wizard.")

Then select new template in the M2Doc category and click next:

![The New template Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Template%20Wizard2.png "The New template Wizard.")

Enter your template name:

![The New template Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Template%20Wizard3.png "The New template Wizard.")

Select the main variable value and name then click next:

![The New template Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Template%20Wizard4.png "The New template Wizard.")

![The New template Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Template%20Wizard5.png "The New template Wizard.")

The template is created after clicking finish:

![The New template Wizard.]({{page.relativePath}}/ref-doc/nightly/images/New%20Template%20Wizard6.png "The New template Wizard.")

### M2Doc interpreter view

The M2Doc interpreter view provides completion for [AQL](https://www.eclipse.dev/acceleo/documentation/aql.html) expressions in the context of a generation model (.genconf file). To create a generation model file you can read the [initialize generation](index.html#initializing-a-generation-configuration) section.

To open the M2Doc interpreter view, select the Windows / Show View / Other... :

![M2Doc Interpreter View.]({{page.relativePath}}/ref-doc/nightly/images/Interpreter%20view1.png "M2Doc Interpreter View.")

Then select the M2Doc interpreter in the M2Doc category:

![M2Doc Interpreter View.]({{page.relativePath}}/ref-doc/nightly/images/Interpreter%20view2.png "M2Doc Interpreter View.")

To start using the view, you need to select a generation model (.genconf file):

![M2Doc Interpreter View.]({{page.relativePath}}/ref-doc/nightly/images/Interpreter%20view3.png "M2Doc Interpreter View.")

Select your generation model (.genconf file) in the following dialog:

![M2Doc Interpreter View.]({{page.relativePath}}/ref-doc/nightly/images/Interpreter%20view4.png "M2Doc Interpreter View.")

You can then start typing [AQL](https://www.eclipse.dev/acceleo/documentation/aql.html) expressions and use CTRL+SPACE for completion:

![M2Doc Interpreter View.]({{page.relativePath}}/ref-doc/nightly/images/Interpreter%20view5.png "M2Doc Interpreter View.")

Note that you have access to variables declared in your template and also to a variable named &#171;selection&#187; that contains the current selected element.

You can also use the reload command to refresh the generation context, for instance when the template is modified or the source code of your services is modified:

![M2Doc Interpreter View.]({{page.relativePath}}/ref-doc/nightly/images/Interpreter%20view6.png "M2Doc Interpreter View.")

When an error/warning/info is detected, you can over the message to highlight the corresponding part of the expression:

![M2Doc Interpreter View.]({{page.relativePath}}/ref-doc/nightly/images/Interpreter%20view7.png "M2Doc Interpreter View.")

### Syntax

The template should be edited using your Microsoft Word document editor. Older versions of M2Doc used Word fields to declare dynamic elements. Since version 4.0.0 M2Doc statements are in plain text:

`{m:...}`

The start `{m:` can't contain spaces nor tabulation (for instance `{ m:` is not a M2Doc statement). Also each instruction should start and end in the same paragraph.

M2Doc is an imperative template language built on top of [AQL](https://www.eclipse.dev/acceleo/documentation/aql.html) for querying. The language supports type inference and can be extended using [custom services](index.html#providing-new-services).

In order to create your [AQL](https://www.eclipse.dev/acceleo/documentation/aql.html) expressions with completion and see the result for a selected object you can use the [M2Doc interpreter](index.html#m2doc-interpreter-view).

#### Comment ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/comment/nominal))

A simple comment, nothing is generated. It can be used to add specific text style to your document without having the text generated. The style can then be used later in the template.

`{m:comment any text}`

If you want to use a variable named 'comment' you can use an extra space after 'm:'.

#### Comment block ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/comment/commentBlockNominal))

A comment block, the commented block is not generated. It can be used to add specific text or table style, or numbering to your document without having the text or table generated. The style or numbering can then be used later in the template.

`{m:commentblock any text}`

`...commented block...`

`{m:endcommentblock}`

If you want to use a variable named 'commentblock' or 'endcommentblock' you can use an extra space after 'm:'.

#### Static statement

Anything that is not a M2Doc field is generated as is.

#### Query ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/query/nominal))

The [AQL expression](https://www.eclipse.dev/acceleo/documentation/aql.html) is evaluated and its result is inserted in the generated document. [M2Doc services](index.html#services) and [provided services](index.html#providing-new-services) can be used. Some [specific return types](index.html#special-return-types) will be interpreted by M2Doc.

`{m:AQL expression}`

#### Conditional ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/conditional/nominal))

If the condition is true the &#171;then block&#187; is inserted in the generated document. if it&#8217;s false and the &#171;else if&#187; condition is true the &#171;else if block&#187; is inserted. if all condition are false the &#171;else block&#187; is inserted. You can have zero or more &#171;elseif blocks&#187;, zero or one &#171;else block&#187;. All [AQL expressions](https://www.eclipse.dev/acceleo/documentation/aql.html) should evaluate to a Boolean.

`{m:if AQL expression}`

`...then block...`

`{m:elseif AQL expression}`

`...else if block...` 

`{m:else}` 

`...else block...`

` {m:endif}`

If you want to use a variable named 'elseif', 'else', 'endif' you can use an extra space after 'm:'. You can also use the AQL if exression if you add a space after 'm:'.

#### Repetition ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/repetition/nominal))

Generates the &#171;repetition block&#187; for each value of the [AQL expression](https://www.eclipse.dev/acceleo/documentation/aql.html).

`{m:for variable | AQL expression} ...repetition block... {m:endfor}`

If you want to use a variable named 'for' or 'endfor' you can use an extra space after 'm:'.

#### Let ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/repetition/nominal))

Declares a variable named &#171;variable&#187; with the result of the [AQL expression](https://www.eclipse.dev/acceleo/documentation/aql.html) as its value. The variable is accessible in the &#171;let block&#187;.

`{m:let variable = AQL expression} ...let block... {m:endlet}`

If you want to use a variable named 'endlet' you can use an extra space after 'm:'. You can also use the AQL let exression if you add a space after 'm:'.

#### Bookmark ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/bookmark/nominal))

Generates a bookmark with the result of the [AQL expression](https://www.eclipse.dev/acceleo/documentation/aql.html) as identifier and the &#171;bookmark block&#187; as content.

`{m:bookmark AQL expression} ...bookmark block... {m:endbookmark}`

If you want to use a variable named 'bookmark' or 'endbookmark' you can use an extra space after 'm:'.

#### Link ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/bookmark/nominal))

Generates a link to the [bookmark](index.html#bookmark-examplehttpsgithubcomobeonetworkm2doctreemastertestsorgobeonetworkm2doctestsresourcesbookmarknominal) with the given identifier and text. 

`{m:link AQL expression AQL expression}`

The first [AQL expression](https://www.eclipse.dev/acceleo/documentation/aql.html) id the identifier and the second one is the text to display.
If you want to use a variable named 'link' you can use an extra space after 'm:'.

#### User Documentation ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/userDoc/nominal))

Generates a user content block in the resulting document. If the generated document exists and already has a user content with the same id the previous user content is preserved, otherwise the &#171;userdoc block&#187; is generated.

`{m:userdoc AQL expression} ...user block... {m:enduserdoc}`

If a user content exists in a previously generated document and the id is not re-generated, the user content will by removed from the generated document and saved in a &#171;lost&#187; file.
If you want to use a variable named 'userdoc' or 'enduserdoc' you can use an extra space after 'm:'.

#### Template construct ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/template/nominal))

A template construct is basically a block of template with parameters that can be called like a service. When the template construct is called its contents is inserted after all dynamic parts have been interpreted by M2Doc.

`{m:template public myTemplate(a : Integer, b : Integer)} ...template block... {m:endtemplate}`

This template can be called as follow using a simple [query](index.html#query-example):

`{m:1.myTemplate(3)}`

The template contruct visibility can be one of:

* `public`: the template construct is visible by any document
* `protected`: the template construct is visible by the current document and any other document extending it directly or indirectly
* `private`: the template construct is visible only in the current document

If you want more examples of M2Doc syntax you can have a look at the nominal cases in [unit tests](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources).
If you want to use a variable named 'template' or 'endtemplate' you can use an extra space after 'm:'.

### Template construct reuse

You can import and extend template to reuse and override [template constructs](index.html#template-construct-example). In both cases all templates needs to be in a Java project. Imported and extended template needs to be accessible by the classpath of the project.

#### Extend

When you import a template, you can override public and protected [template constructs](index.html#template-construct-example) from the extended template. To override a template construct, you need to write a template construct with the same signature (name and template parameter types).
When generating the top most template in the extend hierarchy will be used as the entry of the generation and all call to overridden template construct will be delegated to the bottom most override template construct in the extend hierarchy.

To extend the template you need to use the [Template properties wizard](index.html#template-properties-wizard).

#### Import

A template can be imported like any Java class. Once imported each public [template construct](index.html#template-construct-example) can be called like any other service in AQL expressions. To import the template you need to use the [Template properties wizard](index.html#template-properties-wizard).

### Services

All [AQL services](https://www.eclipse.dev/acceleo/documentation/aql.html#LanguageReference) are available. On top of that, specific services are provided by M2Doc. See the following reference documentation. If you don&#8217;t find your needs in this list, you can [provide custom services](index.html#providing-new-services).

* [Boolean services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_booleanservices.html)
* [Document services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_documentservices.html)
* [EObject services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_m2doceobjectservices.html)
* [Excel services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_excelservices.html)
* [HTML services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_m2dochtmlservices.html)
* [Image services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_imageservices.html)
* [Link services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_linkservices.html)
* [Prompt services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_promptservices.html)
* [Prompt services (SWT)]({{page.relativePath}}/ref-doc/nightly/m2doc_service_swtpromptservices.html)
* [Pagination services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_paginationservices.html)
* [Sirius services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_m2docsiriusservices.html) (Note: M2Doc versions 1.1.0 and above are compatible with [Obeo Designer Team Edition](https://www.obeodesigner.com/en/collaborative-features).)
  * you will have to add the following packages nsURI (see [Template properties wizard](index.html#template-properties-wizard) section): http://www.eclipse.org/sirius/1.1.0, http://www.eclipse.org/sirius/diagram/1.1.0, and/or http://www.eclipse.org/sirius/diagram/sequence/2.0.0.
* [Wikitext services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_m2docwikitextservices.html) (exprerimental)

Services specific to the [M2Doc Capella extensions](../../capella/):

* [Capella extensions]({{page.relativePath}}/ref-doc/nightly/m2doc_service_m2docgenservices.html)
* [Capella semantic browser]({{page.relativePath}}/ref-doc/nightly/m2doc_service_semanticbrowserservices.html)

NOTE: you will have to manually import the two services classes or select the "Capella" package in the [Template properties wizard](index.html#template-properties-wizard):

* org.obeonetwork.capella.m2doc.aql.queries.M2DocGenServices
* org.obeonetwork.capella.m2doc.aql.queries.SemanticBrowserServices

### Template properties wizard

The template propeties wizard allows to configure the template. You will be able to add or remove used metamodels (nsURI), service classes, you will also be able to specify the type of the variable used as input of your template.

To open the template properties wizard, you can select a template .docx file and right click on it:

![The Edit Template properties menu.]({{page.relativePath}}/ref-doc/nightly/images/Edit%20Template%20Properties%20menu.png "The Edit Template properties menu.")

The first page of the wizard let you select template metamodels and services packages.

![The Edit Template properties wizard.]({{page.relativePath}}/ref-doc/nightly/images/Template%20Properties%20Wizard1.png "The Edit Template properties wizard.")

When a service package is included in one or more other service packages you can have the following page:

![The Edit Template properties wizard.]({{page.relativePath}}/ref-doc/nightly/images/Template%20Properties%20Wizard1%20with%20included%20tokens.png "The Edit Template properties wizard.")

In this example, the "other test token" is included in the "test token". "other test token" can't be deselected without deselecting "test token".

Note you can also use the `nsURI (expert)` and `Services (expert)` to select metamodels and services manually. The service tab can be used to import Java service classes and select a template to extend.

The second page is dedicated to variable declarations. The list of declarations is initialized from the variable used in the template.

![The Edit Template properties wizard.]({{page.relativePath}}/ref-doc/nightly/images/Template%20Properties%20Wizard2.png "The Edit Template properties wizard.")

The M2Doc version to use with this template can be changed on this page. Not having this property set will trigger a warning while [validating](index.html#validating-a-generation-setup) with M2Doc 2.0.0 or above.

### Template custom properties

You can use the [Template properties wizard](index.html#template-properties-wizard). But you can also edit them using your Microsoft Word document editor (see [this page](https://support.office.com/en-us/article/View-or-change-the-properties-for-an-Office-file-21d604c2-481e-4379-8e54-1dd4622c6b75?CorrelationId=866914c3-b0b5-42e8-aeb2-e9f7bcc216e2&ui=en-US&rs=en-US&ad=US&ocmsassetID=HA010047524)). This section will describe possible properties name and value used by M2Doc.

#### M2Doc version custom properties

To define the version of M2Doc to use with this template you can set a custom property as follow

* Name: `m:M2DocVersion`
* Value: **M2Doc version**

For example:

* Name: `m:M2DocVersion`
* Value: `2.0.0`

Not having this property set will trigger a warning while [validating](index.html#validating-a-generation-setup) with M2Doc 2.0.0 or above.

#### Variable custom properties

To define variables you can set a custom property as follow

* Name: `m:var:`**variable name**
* Value: **variable type**

For example:

* Name: `m:var:self`
* Value: `ecore::EPackage`

#### Package custom properties

To use new EMF packages for typing you can add the following custom property:

* Name: `m:uri:`**EPackage nsURI**
* Value:

For example:

* Name: `m:uri:https://www.eclipse.org/uml2/5.0.0/UML` 
* Value:

#### Extend custom properties

To extend an other template and override its [template constructs](index.html#template-construct-example) you can add the following custom property:

* Name: `m:extend:`**qualified name**
* Value:

For example:

* Name: `m:extend:org.obeonetwork.m2doc.rcptt.a.otherTemplate` 
* Value:

#### Import custom properties

To import a service class with a [default constructor](index.html#default-constructor). To use a service class with a custom constructor you can read the [custom constructor](index.html#custom-constructor) section.

* Name: `m:import:`**qualified name**
* Value:

For example:

* Name: `m:import:org.obeonetwork.m2doc.rcptt.a.ServiceClassA`
* Value:

### Template Libraries

Template libraries are a list of paths from the file system. They can be used to share M2Doc templates across environment via a network file system for instance. M2Doc will scan provided library paths for template with one model element parameter and provides menu to launch the generation when a compatible element is selected.

First you will need to add path to your libraries using the M2Doc/libraries preference page. Select the Window Preferences menu:

![The preferences menu.]({{page.relativePath}}/ref-doc/nightly/images/Preferences%20menu.png "The preferences menu.")

Then select the M2Doc/Libraries page:

![The libraries preferences page.]({{page.relativePath}}/ref-doc/nightly/images/Libraries%20preferences.png "The libraries preferences page.")

You can use this page to add/remove library paths. Once you are done, you can click the apply and close button to validate your changes or the cancel button to cancel them.

In this example the selected path contains a template with one parameter of type EPackage. So we will demonstrate menus by selecting an EPackage object. But in your case it can be any model element type.

You can now select a model element that has at least one compatible template in one of the provided libraries and launch a generation using the "M2Doc Generate with Template Library" menu. You can select your element and right click on it:

![The M2Doc Generate with Template Library menu.]({{page.relativePath}}/ref-doc/nightly/images/M2Doc%20Generate%20with%20Template%20Library menu.png "The M2Doc Generate with Template Library menu.")

Selecting this menu will open the following dialog:

![The Generate with Template Library dialog.]({{page.relativePath}}/ref-doc/nightly/images/Generate%20with%20Template%20Library%20dialog.png "The Generate with Template Library dialog.")

In this dialog, you can select the template you want to generate with from the libraries and the output folder. The selected element will be used as the parameter of the generation. You can then click the finish button to launch the generation or the cancel button to cancel your action.

## Providing new services

You can extends M2Doc by adding services to your templates using the [Template properties wizard](index.html#template-properties-wizard). A service is a simple Java method with at least one parameter and a return value or a [template construct](index.html#template-construct-example) from an other template.

To be able to access the service Class you template needs to be in a Java project and the Class needs to be accessible in the project classpath. When deployed the service Class needs to be in the current classpath.

You can create different types of Java projects:

* [simple Java project](https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2FgettingStarted%2Fqs-3.htm)
* [Eclipse plug-in project](https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.pde.doc.user%2Fguide%2Ftools%2Fproject_wizards%2Fnew_plugin_project.htm&cp%3D4_3_9_0_0)
* [Maven project](https://www.vogella.com/tutorials/EclipseMaven/article.html)

### Service class

There are two cases for the class containing the service: either it has a [default constructor](index.html#default-constructor), or it has a [constructor with a parameter](index.html#custom-constructor).

#### Default constructor

When the class has no explicit constructor or the constructor doesn&#8217;t have any parameters. In this case you don&#8217;t need any specific development except for your service method. You can have a look at [specific return types](index.html#special-return-types) if you want to insert images, table, etc... To use your services in your template simply add them through the [Template properties wizard](index.html#template-properties-wizard).

#### Custom constructor

When there is an explicit constructor with at least a parameter M2Doc can&#8217;t instantiate your class since there is no default constructor. You need to implement the [IServicesConfigurator](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/services/configurator/IServicesConfigurator.java) interface. This interface link one or more options in the generation configuration to the service class in order to instantiate it. You need to use the org.obeonetwork.m2doc.ide.servicesConfigurator extension point to declare your implementation of [IServicesConfigurator](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/services/configurator/IServicesConfigurator.java). If you want to use your implementation in standalone you can register it programmatically using M2DocUtils.registerServicesConfigurator().

An example of implementation can be found in the [Sirius](https://www.eclipse.dev/sirius/) integration plug-in see the class [SiriusServiceConfigurator](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc.sirius/src/org/obeonetwork/m2doc/sirius/services/configurator/SiriusServiceConfigurator.java) and the extension in the [plugin.xml](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc.sirius/plugin.xml).

#### Special return types

The M2Doc evaluation engine converts any object to a string in order to insert it in the generated document. To add flexibility in the produced document we chose to expose some special types to service creators. Those types are [MElement](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MElement.java) and allow directly inserting document artifacts:

* [MPagination](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MPagination.java) to insert a table of contents, a new page, a new paragraph, a new column, or a new text wrapping. 
* [MBookmark](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MBookmark.java) to insert a new bookmark or a reference to a bookmark.
* [MHyperLink](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MHyperLink.java) to insert a new link to an [URL](https://en.wikipedia.org/wiki/URL).
* [MImage](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MImage.java) to insert a new image.
* [MTable](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MTable.java) to insert a new table.
* [MText](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MText.java) to insert styled text.
* [MList](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MList.java) to insert a list of [MElement](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MElement.java).

Default implementations are also provided by M2Doc in [this package](https://github.com/ObeoNetwork/M2Doc/tree/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/impl).

### Template

You can import a template with public [template construct](index.html#template-construct-example) to reuse them. You just need to specify the qualified name of the template (.docx file) and once imported public template construct can be called the same way as local template construct.

One important thing about styling. Styles are indexed using their ID. If a style is used in the imported template but not present in the calling template, it will be copied. If the style ID is already used in the calling template, then the calling template style is used. This allows the caller to override the styling when calling a template construct.

One example where this is useful is when you need to provide some document to several entities. For instance several suppliers or customers that have different style guides, You can create a template construct in a template that you will later import in the template for supplier A and an other template for customer B. And by reusing the same style IDs apply the needed style for supplier A and customer B without changing the content nor styling of the imported template construct.

## Template migration

The template format has changed between version 3.x and 4.x. In version 3.x M2Doc statements were stored in MS Word fields that could be visualized with `ALT+F9` and inserted with `CTRL+F9`. Since version 4.x the fields has been replaced by plain text (`{m:...}`).

You can migrate your template toward the current version of M2Doc by right clicking on the template an selecting the `Migrate template` menu.

![The migrate template menu.]({{page.relativePath}}/ref-doc/nightly/images/Migrate%20template%20menu.png "The migrate template menu.")

This menu is only available if the template needs to be migrated according to its version number (see the [Template properties wizard](index.html#template-properties-wizard) section).

## Extension points

### Services and packages token

The services and packages token extension point org.obeonetwork.m2doc.ide.services.register can be used to reference a set of [service classes](index.html#default-constructor) and packages using a token name. This token can be selected using &#171;Select tokens&#187; menu in the [Template properties wizard](index.html#template-properties-wizard). You can find an example of this extension point [here](https://github.com/ObeoNetwork/M2Doc/blob/master/tests/org.obeonetwork.m2doc.ide.tests/plugin.xml).

### Template registry

It's possible to package your templates and deploy them in Eclipse plugins. To register them you can use the extension point org.obeonetwork.m2doc.ide.templates.register, ther is an example [here](https://github.com/ObeoNetwork/M2Doc/blob/master/tests/org.obeonetwork.m2doc.ide.tests/plugin.xml).

#### Import registered template

Once a template is registered (see the [Template registry](index.html#template-registry) section), you can import it in the workspace for further modifications for instance:

Select the template import wizard:

![The import menu.]({{page.relativePath}}/ref-doc/nightly/images/Import%20menu.png "The import menu.")

![Import Wizard Selection.]({{page.relativePath}}/ref-doc/nightly/images/Import%20Wizard%20Selection.png "Import Wizard Selection.")

![Select a template.]({{page.relativePath}}/ref-doc/nightly/images/Template%20Import%20Wizard1.png "Select a template.")

![Select the target folder.]({{page.relativePath}}/ref-doc/nightly/images/Template%20Import%20Wizard2.png "Select the target folder.")


## Template testing

To simplify unit testing while developing M2Doc, a [JUnit](https://junit.org/junit4/) test suite has been implemented. It uses a given folder as input and lists each sub directory following a naming pattern as a test case. You can use the same test suite for your own tests. An example of the test suite implementation [QueryTests](https://github.com/ObeoNetwork/M2Doc/blob/master/tests/org.obeonetwork.m2doc.tests/src/org/obeonetwork/m2doc/tests/QueryTests.java) with the folder [resources/query](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/query).

## Maven/Tycho

You can launch your generation using [Maven](https://maven.apache.org/) and [Tycho](https://eclipse.org/tycho/). An example is available [here](https://github.com/ObeoNetwork/M2Doc/tree/master/releng/generate-with-maven-tycho). You may probably need to have a look at the [pom.xml](https://github.com/ObeoNetwork/M2Doc/blob/master/releng/generate-with-maven-tycho/myModelToDocx/pom.xml) file. To launch the build and the generation you can simply use the following command:

`mvn clean verify`

## Using M2Doc programmatically

This section is for developers wanting to integrate M2Doc in their own project. It will show how to use the main features of M2Doc programmatically. Since M2Doc uses AQL for expressions it can be useful to have a look at [Using AQL programmatically](https://www.eclipse.dev/acceleo/documentation/aql.html#UsingAQLprogrammatically).

### Parsing

The following sample code shows how to load a template .docx file using M2Doc:

{% highlight Java %}
final URI templateURI = ...; // the URI of the template
        
// can be empty, if you are using a Generation use GenconfUtils.getOptions(generation)
final Map<String, String> options = ...;
final String qualifiedName = ...,
List<Exception> exceptions = new ArrayList<>();

AQLUtils.registerServicesConfigurator(new ServicesConfiguratorDescriptor(AQLUtils.AQL_LANGUAGE,
		new AQLServiceConfigurator()));

final ResourceSet resourceSetForModels = AQLUtils.createResourceSetForModels(exceptions, this,
		new ResourceSetImpl(), options);
resourceSetForModels.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*",
		new XMIResourceFactoryImpl());

final Registry ePackageRegistry = EPackage.Registry.INSTANCE;
final IQualifiedNameResolver resolver = new ClassLoaderQualifiedNameResolver(this.getClass()
		.getClassLoader(), ePackageRegistry, M2DocUtils.QUALIFIER_SEPARATOR);
final IQualifiedNameQueryEnvironment queryEnvironment = M2DocUtils.getQueryEnvironment(resolver,
		resourceSetForModels, templateURI, options, false);
final M2DocEvaluationEnvironment m2docEnv = new M2DocEvaluationEnvironment(resolver,
		resourceSetForModels, templateURI, outputURI);

resolver.addLoader(new M2DocDocumentTemplateLoader(m2docEnv, new BasicMonitor(),
		M2DocUtils.QUALIFIER_SEPARATOR));
final ILoader javaLoader = new JavaLoader(M2DocUtils.QUALIFIER_SEPARATOR, false);
resolver.addLoader(javaLoader);

try (DocumentTemplate documentTemplate = (DocumentTemplate)resolver.resolve(qualifiedName)) {
	M2DocUtils.prepareEnvironment(queryEnvironment, ePackageRegistry, documentTemplate);
	// use the template here
} finally {
	AQLUtils.cleanResourceSetForModels(this, resourceSetForModels);
	AQLUtils.cleanServices(M2DocUtils.M2DOC_LANGUAGE, queryEnvironment, resourceSetForModels);
}
{% endhighlight %}

### Validation

The validation is optional:

#### Core validation API

{% highlight Java %}
final ValidationMessageLevel validationLevel = M2DocUtils.validate(documentTemplate, queryEnvironment, monitor);
if (validationLevel != ValidationMessageLevel.OK) {
    final URI validationResulURI = ...; // some place to serialize the result of the validation
    M2DocUtils.serializeValidatedDocumentTemplate(resourceSetForModels.getURIConverter(), documentTemplate, validationResulURI);
}
{% endhighlight %}

#### Generation configuration API

{% highlight Java %}
final boolean hasErrors = GenconfUtils.validate(generation, m2docEnv, options, exceptions, monitor);
{% endhighlight %}

### Generation

The generation will produce the final document where M2Doc template is evaluated against the given variables. There are two levels of API for the generation: directly from the DocumentTemplate or from the Generation.

#### Core generation API

{% highlight Java %}
final Map<String, Object> variables = ...; // your variables and values
final GenerationResult generationResult = M2DocUtils.generate(m2docEnv, documentTemplate, variables, false, monitor);
{% endhighlight %}

#### Generation configuration API

{% highlight Java %}
final Generation generation = ...; // load from a serialized EMF model or create in memory
final List<URI> generatedURIs = GenconfUtils.generate(generation, m2docEnv, options, monitor);
{% endhighlight %}

## Maven

You can use M2Doc in your maven project with the following repository and dependency:

{% highlight XML %}
<repositories>
  <repository>
    <id>Acceleo Repository</id>
    <url>https://download.eclipse.org/acceleo/updates/releases/4.1/...</url>
  </repository>
  <repository>
    <id>M2Doc Repository</id>
    <url>https://s3-eu-west-1.amazonaws.com/obeo-m2doc-releases/.../repository</url>
  </repository>
</repositories>
<dependencies>
  <dependency>
    <groupId>org.obeonetwork.m2doc</groupId>
    <artifactId>m2doc</artifactId>
    <version>4.1.0</version>
  </dependency>
</dependencies>
{% endhighlight %}

If you use the source folder for your template make sure you include them as resources:

{% highlight XML %}
<build>
  <resources>
    <resource>
      <directory>${project.basedir}/src/main/java</directory>
        <includes>
          <include>**/*.docx</include>
        </includes>
      </resource>
    </resources>
  </build>
</project>
{% endhighlight %}


You can check the [test project](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/maven/org.obeonetwork.m2doc.maven.tests) for more details. You can also check the [AQL Maven documentation](https://github.com/eclipse-acceleo/acceleo/blob/master/query/plugins/org.eclipse.acceleo.query.doc/pages/index.adoc#maven) for more details on the AQL dependency.

You can optionally add other M2Doc dependencies:

* m2doc-genconf: to use the *.genconf file API (see the [test project](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/maven/org.obeonetwork.m2doc.genconf.maven.tests) for more details).
* m2doc-html: to use the [HTML services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_m2dochtmlservices.html) (see the [test project](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/maven/org.obeonetwork.m2doc.html.maven.tests) for more details).
* m2doc-wikitext: to use [Wikitext services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_m2docwikitextservices.html) (see the [test project](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/maven/org.obeonetwork.m2doc.wikitext.maven.tests) for more details).
