---
layout: article
title: Reference Documentation
subtitle: 3.1.0
relativePath: ../..
---

# Table of Content

Following you will find the reference documentation corresponding to the **3.1.0** version.
If you need an other version use one of our [released versions]({{page.relativePath}}/download/#all-versions). 

* TOC
{:toc}

## Overview

The M2Doc project provides Word document (.docx files) generation based on a document template and [EMF](https://www.eclipse.org/modeling/emf/) models. 

The overall approach consists in creating templates in the [OOXML](https://en.wikipedia.org/wiki/Office_Open_XML) format where static text authoring benefits from the WYSIWYG capabilities of Microsoft Word. Dynamic parts are inserted using a dedicated vocabulary of [OOXML](https://en.wikipedia.org/wiki/Office_Open_XML) fields code. Fields are mainly used to insert page numbers, references, etc. M2Doc makes use of them to describe documentation generation directives. This allows a total separation between the document and the M2Doc directives.

![Overview]({{page.relativePath}}/ref-doc/3.1.0/images/m2doc-overview.png)

As an example, here is a fragment of a M2Doc template used to generate the documentation of a database model:

![DB Template]({{page.relativePath}}/ref-doc/3.1.0/images/DBTemplate.png)
 
The generation looks like this: 

![DB Result]({{page.relativePath}}/ref-doc/3.1.0/images/DBResult.png)

The template language makes an extensive use of the [Acceleo Query Language](https://www.eclipse.org/acceleo/documentation/aql.html) which provides a full-fledged, extensible model query language and engine. 
The M2Doc templates can be [validated](index.html#validating-a-generation-setup). If errors are found, an annotated templates is produced describing the problems found.

### Principles

* Definition of model entry points
  * Declaration of variables (see the [Template properties wizard](index.html#template-properties-wizard) section)
  * Mapping with model elements (see the [Generation configuration wizard](index.html#generation-configuration-wizard) section)

![Variable definition]({{page.relativePath}}/ref-doc/3.1.0/images/Variable%20definition.png "Variable definition.")

* Extraction of information by navigating the model (see the [Template authoring](index.html#template-authoring) section)
  * From the entry points
  * Using model relations

![Extracting information]({{page.relativePath}}/ref-doc/3.1.0/images/Extracting%20information.png "Extracting information.")

* Formatting of the output information
  * Using Microsoft Word functionalities

![Formatting template]({{page.relativePath}}/ref-doc/3.1.0/images/Formatting%20template.png "Formatting template.")

### Main steps

* Definition of content, navigation, and format (see the [Template authoring](index.html#template-authoring) section)
* Declaration of variables (see the [Template properties wizard](index.html#template-properties-wizard) section)
* Mapping of variables with model elements, definition of input model and output file (see the [Generation configuration wizard](index.html#generation-configuration-wizard) section)
* Generation of output document (see the [Generate a document](index.html#generating-a-document) section)

![Main steps]({{page.relativePath}}/ref-doc/3.1.0/images/Main%20steps.png "Main steps.")

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
* see [Using AQL programmatically](https://www.eclipse.org/acceleo/documentation/aql.html#UsingAQLprogrammatically) (optional)

## Architecture

The main workflow:

![M2Doc Workflow]({{page.relativePath}}/ref-doc/3.1.0/images/M2DocWorkflow.png)

The template is parsed in an internal representation (the AST). From here you can bind the variables to their values, using elements from a model for instance and evaluate the template to generate the output document. An added benefit of using M2Doc is the hability to [validate](index.html#validating-a-generation-setup) a template to produce an annotated template containing informations, warnings and errors the template might contain.

The architecture of M2Doc is organized around three building blocks:
* [Apache POI](https://poi.apache.org/) for the parsing and generation of [OOXML](https://en.wikipedia.org/wiki/Office_Open_XML) documents
* [AQL](https://www.eclipse.org/acceleo/documentation/aql.html) for querying the models
* [EMF](https://www.eclipse.org/modeling/emf/) as a general platform

![Technical architecture]({{page.relativePath}}/ref-doc/3.1.0/images/TechnicalArchitecture.png)

<a name="generate-a-document"></a>
## Generating a document

To generate a document you will need at least a template file (.docx file) and a generation configuration model (.genconf file). If you don&#8217;t have the template you can read the [template authoring](index.html#template-authoring) section. To create a generation model file you can read the [initialize generation](index.html#initializing-a-generation-configuration) section.

### Launching a generation

To launch a generation you can right click on the generation configuration model (.genconf file) or the Generation object and select the &#171;Generate Documentation menu&#187; as shown here:

![The Generate Documentation menu.]({{page.relativePath}}/ref-doc/3.1.0/images/Generate%20Documentation%20menu.png "The Generate Documentation menu.")

If you don&#8217;t have the the generation model configuration file you can create it using the [initialize generation](index.html#initializing-a-generation-configuration) section.

### Initializing a generation configuration

To initialize a generation configuration you will need a template file (.docx file). If you don&#8217;t have the template you can read the [template authoring](index.html#template-authoring) section.

Right click on the template file and select the &#171;Initialize Documentation Configuration&#187; menu:

![The Initialize Documentation Configuration menu.]({{page.relativePath}}/ref-doc/3.1.0/images/Initialize%20Documentation%20Configuration%20menu.png "The Initialize Documentation Configuration menu.")

To edit the created generation model file you can use the [Generation configuration wizard](index.html#generation-configuration-wizard).

### Validating a generation setup

To validate a generation setup (template plus generation configuration) you can right click on the generation configuration model (.genconf file) or the Generation object and select the &#171;Validate Documentation menu&#187; as shown here:

![The Validate Documentation menu.]({{page.relativePath}}/ref-doc/3.1.0/images/Validate%20Documentation%20menu.png "The Validate Documentation menu.")

If you don&#8217;t have the the generation configuration model file you can create it using the [initialize generation](index.html#initializing-a-generation-configuration) section. If the validation succeeds with no informations, warnings, or errors you will be prompted. If something went wrong a .docx file with the name of the template and suffixed with &#171;info&#187;, &#171;warning&#187;, or &#171;error&#187; will be created. This new file contains details of the validation issues. The template is *not* modified.

### Generation configuration wizard

You can access the generation configuration wizard in two different ways, one for creating it and one for further edition when the .genconf file already exists.

To create and initialize the generation configuration, select the template .docx file to use for generation and right click on it:

![The Initialize Documentation Configuration menu.]({{page.relativePath}}/ref-doc/3.1.0/images/Initialize%20Documentation%20Configuration%20menu.png "The Initialize Documentation Configuration menu.")

To edit an existing generation configuration .genconf file you can select the .genconf file and right click on it:

![The Edit Documentation Configuration menu.]({{page.relativePath}}/ref-doc/3.1.0/images/Edit%20Documentation%20Configuration%20menu.png "The Edit Documentation Configuration menu.")

At this point you should be on the first page of the generation configuration wizard:

![The Generation Configuration Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/Generation%20Configuration%20Wizard1.png "The Generation Configuration Wizard.")

On this page you can select the path of the generation configuration .genconf file. It can be changed to create a copy of an existing file for instance. Other file paths are relative to the generation configuration file. This allow to move a folder containing all the files needed for a generation without breaking the generation configuration. The template can be selected from the workspace or the [Template registry](index.html#template-registry). In the later case the path will be absolute to reference the template in the deployed plug-ins.

The next page of the wizard is dedicated to variables and binding values to them:

![The Generation Configuration Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/Generation%20Configuration%20Wizard2.png "The Generation Configuration Wizard.")

To reference new values you can load new resources using the `Load resource` button. Note the `Options (expert)` tab that allow to set advanced options to find values from a Sirius session for instance.

When the `Finish` button is pushed the generation configuration file is created or edited.

## Template authoring

You need to have a model already to be able to generate a document with M2Doc. Then you can [create a project](index.html#new-project-wizard) or if you already have a project, you can [create a template](index.html#new-template-wizard).

### New project wizard

This wizard allows to create all needed artifacts:

- an Eclipse project
- a default template
- the genconf file

You only need your model as input.

To launch the wizard right click in the project exporer and select new project:

![The New project Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Project%20Wizard1.png "The New project Wizard.")

Then select M2Doc project in the M2Doc category:

![The New project Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Project%20Wizard2.png "The New project Wizard.")

Enter your project name and click next:

![The New project Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Project%20Wizard3.png "The New project Wizard.")

Enter your template name and click next:

![The New project Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Project%20Wizard4.png "The New project Wizard.")

Select the main variable value and name then click next:

![The New project Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Project%20Wizard5.png "The New project Wizard.")

![The New project Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Project%20Wizard6.png "The New project Wizard.")

The last page of the wizard is dedicated to file pathes:

![The New project Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Project%20Wizard7.png "The New project Wizard.")

NOTE: you can also start the generation on finish.

### New template wizard

This wizard creates a simple template from a model.

To launch the wizard right click in the project exporer and select new other:

![The New template Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Template%20Wizard1.png "The New template Wizard.")

Then select new template in the M2Doc category and click next:

![The New template Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Template%20Wizard2.png "The New template Wizard.")

Enter your template name:

![The New template Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Template%20Wizard3.png "The New template Wizard.")

Select the main variable value and name then click next:

![The New template Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Template%20Wizard4.png "The New template Wizard.")

![The New template Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Template%20Wizard5.png "The New template Wizard.")

The template is created after clicking finish:

![The New template Wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/New%20Template%20Wizard6.png "The New template Wizard.")

### MS Word add-in

To help you through the process of writing [AQL](https://www.eclipse.org/acceleo/documentation/aql.html) expressions, you can use the MS Word add-in. You will need to have a template and a generation model (.genconf file). To create a generation model file you can read the [initialize generation](index.html#initializing-a-generation-configuration) section.

Once installed and a generation model (.genconf file) selected the add-in provide completion for [AQL](https://www.eclipse.org/acceleo/documentation/aql.html) expressions.

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in02.png "MS Word add-in.")

#### MS Word add-in installation

You need to have an Eclipse with M2Doc installed, see the [download page]({{page.relativePath}}/download/) for more details.

To start the MS word add-in backend you need to go to the preference page Window > Preferences and select the M2Doc add-in:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation01a.png "MS Word add-in.")

You can select the host and port the add-in back end will listen to. By default it will listen to all your ip address on the port 3000.

Once the setting is done, you can click the start button. It will start the add-in back end and wait for the front end to connect to it.

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation01b.png "MS Word add-in.")

When started a link appears to copy the link of the MS Word add-in manifest that will be needed in the next step.

Now the backend is started and you can proceed with the front end installation. For the front end installation, you need to download the manifest.xml file from the back end and place it in a network shared folder.

You can download the manifest.xml file using the following link by default (or the link  copied from the preference page in previous step):

[http://localhost:3000/assets/manifest.xml](http://localhost:3000/assets/manifest.xml)

NOTE: if your back end is hosted on a different computer, you can change the link to match you IP or domain.

Download the manifest.xml in a folder and share the folder to your self (and other if needed):

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation02.png "MS Word add-in.")

Click in the folder and select properties:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation03.png "MS Word add-in.")

Select the share tab:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation04.png "MS Word add-in.")

Click on the share button:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation05.png "MS Word add-in.")

Select your user name or the list of user to share the folder with:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation06.png "MS Word add-in.")

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation07.png "MS Word add-in.")

Click the share button:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation08.png "MS Word add-in.")

Click the finish button:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation09.png "MS Word add-in.")

Copy the shared path, we will need it later:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation10.png "MS Word add-in.")

Click finish:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation11.png "MS Word add-in.")

We are almost there, the last step is to load the add-in into MS Word, click file menu:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation12.png "MS Word add-in.")

Click the options menu:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation13.png "MS Word add-in.")

Click the privacy management center:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation14.png "MS Word add-in.")

Click the privacy management center parameters button:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation15.png "MS Word add-in.")

Click the aprouved application catalogs:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation16.png "MS Word add-in.")

Past the network share path:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation17.png "MS Word add-in.")

Click the add catalog button:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation18.png "MS Word add-in.")

Check the checkbox so the shared folder appears in the list of applications:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation19.png "MS Word add-in.")

Click the OK button:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation20.png "MS Word add-in.")

Click the OK button:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation21.png "MS Word add-in.")

Click the OK button:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation22.png "MS Word add-in.")

Restart MS Word:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation23.png "MS Word add-in.")

Select the insertion menu:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation24.png "MS Word add-in.")

Click on the My applications menu:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation25.png "MS Word add-in.")

Select the shared folder tab:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation26.png "MS Word add-in.")

Select the M2Doc add-in (NOTE: the back end should be running at this point):

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation27.png "MS Word add-in.")

The following panel should open:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in%20Installation28.png "MS Word add-in.")

#### MS Word add-in in action

To use the add in you need to reference a generation model (.genconf file) using its URI. If the template is in the generation model is in the workspace you can prefix it path with "platform:/resource/" and it path in the workspace.

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in01.png "MS Word add-in.")

You can start typing your expression to get the completion:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in02.png "MS Word add-in.")

The blue div contains the result of the evaluation. NOTE: in some cases it's not possible to provide a result (parameters of a template construct), or the result may be based on arbitrary value (let and for):

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in03.png "MS Word add-in.")

In the red div you have the validation of the expression entered so far:

![MS Word add-in.]({{page.relativePath}}/ref-doc/3.1.0/images/Add-in04.png "MS Word add-in.")

### Syntax

The template should be edited using your Microsoft Word document editor. M2Doc uses Word fields to declare dynamic elements. To make fields visible during the edition of your template in Microsoft Word, use `ALT+F9`. To insert a new field, use `CTRL+F9`.

All syntax elements of M2Doc should be placed into fields using `ALT+F9` to show fields and `CTRL+F9` to insert a new field. M2Doc is an imperative template language built on top of [AQL](https://www.eclipse.org/acceleo/documentation/aql.html) for querying. The language supports type inference and can be extended using [custom services](index.html#providing-new-services).

In order to create your [AQL](https://www.eclipse.org/acceleo/documentation/aql.html) expressions with completion and see the result for a selected object you can use the [Sirius interpreter](https://www.eclipse.org/sirius/doc/specifier/general/Writing_Queries.html) after installing [Sirius](https://eclipse.org/sirius/). The interpreter view is as follow and you should select `Sirius interpreter` and prefix your expressions with `aql:`:

![Sirius interpreter.]({{page.relativePath}}/ref-doc/3.1.0/images/Sirius%20interpreter.png "Sirius interpreter.")

In the following explanations `{` and `}` will denote a Word field.

Note that fields are also used by Microsoft Word page number, table of contents, ... Switching from field visualization mode to field edition mode will change the visualization of the document:

![Fields edition.]({{page.relativePath}}/ref-doc/3.1.0/images/Field%20edition.png "Fields edition.")

`ALT+F9`

![Fields visualization.]({{page.relativePath}}/ref-doc/3.1.0/images/Field%20visualization.png "Fields visualization.")

#### Comment ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/comment/nominal))

A simple comment, nothing is generated. It can be used to add specific text style to your document without having the text generated. The style can then be used later in the template.

`{m:comment any text}`

If you want to use a variable named 'comment' you can use an extra space after 'm:'.

#### Comment block ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/comment/commentBlockNominal))

A comment block, the commented block is not generated. It can be used to add specific text or table style, or numbering to your document without having the text or table generated. The style or numbering can then be used later in the template.

`{m:commentblock any text}`

`...commented block...`

`{m:endcommentblock}`

If you want to use a variable named 'commentblock' or 'endcommentblock' you can use an extra space after 'm:'.

#### Static statement

Anything that is not a M2Doc field is generated as is.

#### Query ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/query/nominal))

The [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html) is evaluated and its result is inserted in the generated document. [M2Doc services](index.html#services) and [provided services](index.html#providing-new-services) can be used. Some [specific return types](index.html#special-return-types) will be interpreted by M2Doc.

`{m:AQL expression}`

#### Conditional ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/conditional/nominal))

If the condition is true the &#171;then block&#187; is inserted in the generated document. if it&#8217;s false and the &#171;else if&#187; condition is true the &#171;else if block&#187; is inserted. if all condition are false the &#171;else block&#187; is inserted. You can have zero or more &#171;elseif blocks&#187;, zero or one &#171;else block&#187;. All [AQL expressions](https://www.eclipse.org/acceleo/documentation/aql.html) should evaluate to a Boolean.

`{m:if AQL expression}`

`...then block...`

`{m:elseif AQL expression}`

`...else if block...` 

`{m:else}` 

`...else block...`

` {m:endif}`

If you want to use a variable named 'elseif', 'else', 'endif' you can use an extra space after 'm:'. You can also use the AQL if exression if you add a space after 'm:'.

#### Repetition ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/repetition/nominal))

Generates the &#171;repetition block&#187; for each value of the [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html).

`{m:for variable | AQL expression} ...repetition block... {m:endfor}`

If you want to use a variable named 'for' or 'endfor' you can use an extra space after 'm:'.

#### Let ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/repetition/nominal))

Declares a variable named &#171;variable&#187; with the result of the [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html) as its value. The variable is accessible in the &#171;let block&#187;.

`{m:let variable = AQL expression} ...let block... {m:endlet}`

If you want to use a variable named 'endlet' you can use an extra space after 'm:'. You can also use the AQL let exression if you add a space after 'm:'.

#### Bookmark ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/bookmark/nominal))

Generates a bookmark with the result of the [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html) as identifier and the &#171;bookmark block&#187; as content.

`{m:bookmark AQL expression} ...bookmark block... {m:endbookmark}`

If you want to use a variable named 'bookmark' or 'endbookmark' you can use an extra space after 'm:'.

#### Link ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/bookmark/nominal))

Generates a link to the [bookmark](index.html#bookmark-examplehttpsgithubcomobeonetworkm2doctree3.1.0testsorgobeonetworkm2doctestsresourcesbookmarknominal) with the given identifier and text. 

`{m:link AQL expression AQL expression}`

The first [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html) id the identifier and the second one is the text to display.
If you want to use a variable named 'link' you can use an extra space after 'm:'.

#### User Documentation ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/userDoc/nominal))

Generates a user content block in the resulting document. If the generated document exists and already has a user content with the same id the previous user content is preserved, otherwise the &#171;userdoc block&#187; is generated.

`{m:userdoc AQL expression} ...user block... {m:enduserdoc}`

If a user content exists in a previously generated document and the id is not re-generated, the user content will by removed from the generated document and saved in a &#171;lost&#187; file.
If you want to use a variable named 'userdoc' or 'enduserdoc' you can use an extra space after 'm:'.

#### Template construct ([example](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/template/nominal))

A template construct is basically a block of template with parameters that can be called like a service. When the template construct is called its contents is inserted after all dynamic parts have been interpreted by M2Doc.

`{m:template myTemplate(a : Integer, b : Integer)} ...user block... {m:endtemplate}`

This template can be called as follow using a simple [query](index.html#query-example):

`{m:1.myTemplate(3)}`

If you want more examples of M2Doc syntax you can have a look at the nominal cases in [unit tests](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources).
If you want to use a variable named 'template' or 'endtemplate' you can use an extra space after 'm:'.

### Services

All [AQL services](https://www.eclipse.org/acceleo/documentation/aql.html#LanguageReference) are available. On top of that, specific services are provided by M2Doc. See the following reference documentation. If you don&#8217;t find your needs in this list, you can [provide custom services](index.html#providing-new-services).

* [Boolean services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_booleanservices.html)
* [EObject services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_m2doceobjectservices.html)
* [Excel services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_excelservices.html)
* [HTML services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_m2dochtmlservices.html)
* [Image services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_imageservices.html)
* [Link services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_linkservices.html)
* [Prompt services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_promptservices.html)
* [Prompt services (SWT)]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_swtpromptservices.html)
* [Pagination services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_paginationservices.html)
* [Sirius services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_m2docsiriusservices.html) (Note: M2Doc versions 1.1.0 and above are compatible with [Obeo Designer Team Edition](https://www.obeodesigner.com/en/collaborative-features).)
  * you will have to add the following packages nsURI (see [Template properties wizard](index.html#template-properties-wizard) section): http://www.eclipse.org/sirius/1.1.0, http://www.eclipse.org/sirius/diagram/1.1.0, and/or http://www.eclipse.org/sirius/diagram/sequence/2.0.0.
* [Wikitext services]({{page.relativePath}}/ref-doc/3.1.0/m2doc_service_m2docwikitextservices.html) (exprerimental)

### Template properties wizard

To open the template properties wizard, you can select a template .docx file and right click on it:

![The Edit Template properties menu.]({{page.relativePath}}/ref-doc/3.1.0/images/Edit%20Template%20Properties%20menu.png "The Edit Template properties menu.")

The first page of the wizard let you select template metamodels and services packages.

![The Edit Template properties wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/Template%20Properties%20Wizard1.png "The Edit Template properties wizard.")

When a service package is included in one or more other service packages you can have the following page:

![The Edit Template properties wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/Template%20Properties%20Wizard1%20with%20included%20tokens.png "The Edit Template properties wizard.")

In this example, the "other test token" is included in the "test token". "other test token" can't be deselected without deselecting "test token".

Note you can also use the `nsURI (expert)` and `Services (expert)` to select metamodels and services manually.

The second page is dedicated to variable declarations. The list of declarations is initialized from the variable used in the template.

![The Edit Template properties wizard.]({{page.relativePath}}/ref-doc/3.1.0/images/Template%20Properties%20Wizard2.png "The Edit Template properties wizard.")

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

To use new Ecore packages for typing you can add the following custom property:

* Name: `m:uri:`**EPackage nsURI**
* Value:

For example:

* Name: `m:uri:https://www.eclipse.org/uml2/5.0.0/UML` 
* Value:

#### Service custom properties

To import a service class with a [default constructor](index.html#default-constructor). To use a service class with a custom constructor you can read the [custom constructor](index.html#custom-constructor) section.

* Name: `m:import:`**qualified class name**
* Value: **bundle symbolic name**

For example:

* Name: `m:import:org.obeonetwork.m2doc.rcptt.a.ServiceClassA`
* Value: `org.obeonetwork.m2doc.rcptt.a` 

## Providing new services

You can extends M2Doc by adding services to your templates using the [Template properties wizard](index.html#template-properties-wizard). A service is a simple Java method with at least one parameter and a return value.

### Service class

There are two cases for the class containing the service: either it has a [default constructor](index.html#default-constructor), or it has a [constructor with a parameter](index.html#custom-constructor).

#### Default constructor

When the class has no explicit constructor or the constructor doesn&#8217;t have any parameters. In this case you don&#8217;t need any specific development except for your service method. You can have a look at [specific return types](index.html#special-return-types) if you want to insert images, table, etc... To use your services in your template simply add them through the [Template properties wizard](index.html#template-properties-wizard).

#### Custom constructor

When there is an explicit constructor with at least a parameter M2Doc can&#8217;t instantiate your class since there is no default constructor. You need to implement the [IServicesConfigurator](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/services/configurator/IServicesConfigurator.java) interface. This interface link one or more options in the generation configuration to the service class in order to instantiate it. You need to use the org.obeonetwork.m2doc.ide.servicesConfigurator extension point to declare your implementation of [IServicesConfigurator](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/services/configurator/IServicesConfigurator.java). If you want to use your implementation in standalone you can register it programmatically using M2DocUtils.registerServicesConfigurator().

An example of implementation can be found in the [Sirius](https://eclipse.org/sirius/) integration plug-in see the class [SiriusConfigurationProvider](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc.sirius/src/org/obeonetwork/m2doc/sirius/providers/configuration/SiriusConfigurationProvider.java) and the extension in the [plugin.xml](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc.sirius/plugin.xml).

### Special return types

The M2Doc evaluation engine converts any object to a string in order to insert it in the generated document. To add flexibility in the produced document we chose to expose some special types to service creators. Those types are [MElement](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MElement.java) and allow directly inserting document artifacts:

* [MPagination](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MPagination.java) to insert a table of contents, a new page, a new paragraph, a new column, or a new text wrapping. 
* [MBookmark](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MBookmark.java) to insert a new bookmark or a reference to a bookmark.
* [MHyperLink](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MHyperLink.java) to insert a new link to an [URL](https://en.wikipedia.org/wiki/URL).
* [MImage](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MImage.java) to insert a new image.
* [MTable](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MTable.java) to insert a new table.
* [MText](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MText.java) to insert styled text.
* [MList](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MList.java) to insert a list of [MElement](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MElement.java).

Default implementations are also provided by M2Doc in [this package](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/impl).

## Extension points

### Services and packages token

The services and packages token extension point org.obeonetwork.m2doc.ide.services.register can be used to reference a set of [service classes](index.html#default-constructor) and packages using a token name. This token can be selected using &#171;Select tokens&#187; menu in the [Template properties wizard](index.html#template-properties-wizard). You can find an example of this extension point [here](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/tests/org.obeonetwork.m2doc.ide.tests/plugin.xml).

### Template registry

It's possible to package your templates and deploy them in Eclipse plugins. To register them you can use the extension point org.obeonetwork.m2doc.ide.templates.register, ther is an example [here](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/tests/org.obeonetwork.m2doc.ide.tests/plugin.xml).

#### Import registered template

Once a template is registered (see the [Template registry](index.html#template-registry) section), you can import it in the workspace for further modifications for instance:

Select the template import wizard:

![The import menu.]({{page.relativePath}}/ref-doc/3.1.0/images/Import%20menu.png "The import menu.")

![Import Wizard Selection.]({{page.relativePath}}/ref-doc/3.1.0/images/Import%20Wizard%20Selection.png "Import Wizard Selection.")

![Select a template.]({{page.relativePath}}/ref-doc/3.1.0/images/Template%20Import%20Wizard1.png "Select a template.")

![Select the target folder.]({{page.relativePath}}/ref-doc/3.1.0/images/Template%20Import%20Wizard2.png "Select the target folder.")


## Template testing

To simplify unit testing while developing M2Doc, a [JUnit](https://junit.org/junit4/) test suite has been implemented. It uses a given folder as input and lists each sub directory following a naming pattern as a test case. You can use the same test suite for your own tests. An example of the test suite implementation [QueryTests](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/tests/org.obeonetwork.m2doc.tests/src/org/obeonetwork/m2doc/tests/QueryTests.java) with the folder [resources/query](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/tests/org.obeonetwork.m2doc.tests/resources/query).

## Maven

You can launch your generation using [Maven](https://maven.apache.org/) and [Tycho](https://eclipse.org/tycho/). An example is available [here](https://github.com/ObeoNetwork/M2Doc/tree/3.1.0/docs/generate-with-maven). You may probably need to have a look at the [pom.xml](https://github.com/ObeoNetwork/M2Doc/blob/3.1.0/docs/generate-with-maven/myModelToDocx/pom.xml) file. To launch the build and the generation you can simply use the following command:

`mvn clean verify`

## Using M2Doc programmatically

This section is for developers wanting to integrate M2Doc in their own project. It will show how to use the main features of M2Doc programmatically. Since M2Doc uses AQL for expressions it can be useful to have a look at [Using AQL programmatically](https://www.eclipse.org/acceleo/documentation/aql.html#UsingAQLprogrammatically).

### Parsing

The following sample code shows how to load a template .docx file using M2Doc:

{% highlight Java %}
final URI templateURI = ...; // the URI of the template
        
// can be empty, if you are using a Generation use GenconfUtils.getOptions(generation)
final Map<String, String> options = ...;
List<Exception> exceptions = new ArrayList<>();
        
final ResourceSet resourceSetForModels = M2DocUtils.createResourceSetForModels(exceptions , key, new ResourceSetImpl(), options);

// if you are using a Generation use GenconfUtils.getQueryEnvironment(resourceSetForModels, generation)
final IQueryEnvironment queryEnvironment = M2DocUtils.getQueryEnvironment(resourceSetForModels, templateURI, options); // delegate to IServicesConfigurator
        
final IClassProvider classProvider = new ClassProvider(this.getClass().getClassLoader()); // use M2DocPlugin.getClassProvider() when running inside Eclipse
try (DocumentTemplate template = M2DocUtils.parse(resourceSetForModels.getURIConverter(), templateURI, queryEnvironment, classProvider, monitor)) {
    // use the template
} finally {
    M2DocUtils.cleanResourceSetForModels(key, resourceSetForModels);
}
{% endhighlight %}

### Validation

The validation is optional:

{% highlight Java %}
final ValidationMessageLevel validationLevel = M2DocUtils.validate(template, queryEnvironment, monitor);
if (validationLevel != ValidationMessageLevel.OK) {
    final URI validationResulURI = ...; // some place to serialize the result of the validation
    M2DocUtils.serializeValidatedDocumentTemplate(resourceSetForModels.getURIConverter(), documentTemplate, validationResulURI);
}
{% endhighlight %}

### Generation

The generation will produce the final document where M2Doc template is evaluated against the given variables. There are two levels of API for the generation: directly from the DocumentTemplate or from the Generation.

#### Core generation API

{% highlight Java %}
final Map<String, Object> variables = ...; // your variables and values
final URI outputURI = ...; // some place to serialize the result of the generation
M2DocUtils.generate(template, queryEnvironment, variables, resourceSetForModels, outputURI, monitor);
{% endhighlight %}

#### Generation configuration API

{% highlight Java %}
final Generation generation = ...; // load from a serialized EMF model or create in memory
final IClassProvider classProvider = new ClassProvider(this.getClass().getClassLoader()); // use M2DocPlugin.getClassProvider() when running inside Eclipse
GenconfUtils.generate(generation, classProvider, monitor);
{% endhighlight %}
