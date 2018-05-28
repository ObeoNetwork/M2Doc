---
layout: article
title: Capella M2Doc extension user guide for M2Doc 1.0.0
subtitle: In-Flight Entertainment System (IFE) example
relativePath: ..
---

This user guid will help you to get started with the [Capella M2Doc extension]({{page.relativePath}}/capella/).

This documentation is based on the work of

Aurélien Pinsonneau

THALES CORPORATE ENGINEERING

# Table of Content

* TOC
{:toc}

# Overview

This section explain the basic principles and steps to achieve document generation with M2Doc.

## Principles

* Definition of model entry points
  * Declaration of variables (see the [template editor](index.html#template-editor) section)
  * Mapping with model elements (see the [generation configuration editor](index.html#generation-configuration-editor) section)

![Variable definition]({{page.relativePath}}/ref-doc/nightly/images/Variable%20definition.png "Variable definition.")

* Extraction of information by navigating the model (see the [Template authoring](index.html#template-authoring) section)
  * From the entry points
  * Using model relations

![Extracting information]({{page.relativePath}}/ref-doc/nightly/images/Extracting%20information.png "Extracting information.")

* Formatting of the output information
  * Using Microsoft Word functionalities

![Formatting template]({{page.relativePath}}/ref-doc/nightly/images/Formatting%20template.png "Formatting template.")

## Main steps

* Definition of content, navigation, and format (see the [Template authoring](index.html#template-authoring) section)
* Declaration of variables (see the [template editor](index.html#template-editor) section)
* Mapping of variables with model elements, definition of input model and output file (see the [generation configuration editor](index.html#generation-configuration-editor) section)
* Generation of output document (see the [Generate a document](index.html#generating-a-document) section)

![Main steps]({{page.relativePath}}/ref-doc/nightly/images/Main%20steps.png "Main steps.")

# Installation

## Capella extension

![Install New Software...]({{page.relativePath}}/capella/images/extension/Install1.png "Install New Software...")

![Add Repositiory.]({{page.relativePath}}/capella/images/extension/Install2.png "Add Repositiory.")

You can either add
* from remote server
  * Copy past the link in Eclipse update site window
* install from file
  * Download zip
  * Select zip in Eclipse update site window

Have a look at the Capella extension [download page](https://github.com/ObeoNetwork/Capella-Extensions#download). It look like this:

![Download page.]({{page.relativePath}}/capella/images/extension/Download%20page.png "Download page.")

After adding the update site you can select it and see the following content:

![Available software.]({{page.relativePath}}/capella/images/extension/Available%20software.png "Available software.")

* select everything
* Click next and accept the licence
* Finish
* Restart Capella

## In-Flight Entertainment System (IFE) sample project

![Project menu.]({{page.relativePath}}/capella/images/extension/Project%20menu.png "Project menu.")

![New project.]({{page.relativePath}}/capella/images/extension/New%20project.png "New project.")

You should have the following project in your workspace:

![Project content.]({{page.relativePath}}/capella/images/extension/Project%20content.png "Project content.")

# Word template edition

Template code is writtent in "fields".
Text outside of fields is rendered as is.

![Query.]({{page.relativePath}}/capella/images/extension/Query.png "Query.")

Fields are created in Microsoft Word using `CTRL+F9`.

Word view can be switched between fields definition and fields visualization using `ALT+F9`.

Fields are also used in Microsoft Word for classic content such as automatic table of contents or page numbers.

![Fields edition.]({{page.relativePath}}/ref-doc/nightly/images/Field%20edition.png "Fields edition.")

`ALT+F9`

![Fields visualization.]({{page.relativePath}}/ref-doc/nightly/images/Field%20visualization.png "Fields visualization.")

M2Doc fields shall start with `m:`, do not leave any spaces after the colon `:`.

Fields are based on a specific language based of [AQL language](https://www.eclipse.org/acceleo/documentation/aql.html). They use Capella datamodel relations to navigate and model elements attritutes to display information. You can lern more in the [Tips and tricks](capella-user-guide.html#tips-and-trics) section. More specific M2Doc services exists to display diagrams and other purposes, please have a look are the [M2Doc documenation](http://www.m2doc.org/ref-doc/1.0.0/index.html).

## Conditional block

Key words `if`, `elseif`, `else`, `endif` are supported. See [Conditional](http://www.m2doc.org/ref-doc/1.0.0/index.html#conditional-example) for more details.

![Conditional block.]({{page.relativePath}}/capella/images/extension/Conditional%20block.png "Conditional block.")

## Repetition

Key words `for` and `endfor` are supported. See [Reprtition](http://www.m2doc.org/ref-doc/1.0.0/index.html#repetition-example) for more details.

![Repetition.]({{page.relativePath}}/capella/images/extension/Repetition.png "Repetition.")

## User documentation

Allow to create a block that will not be overwritten between two output document generation.

![User documentation.]({{page.relativePath}}/capella/images/extension/User%20documentation.png "User documentation.")

## Main diagram services

* fit (width,height).
  * Allow to resize and image so that it is included in a rectangle of size width x heigh.
* ‘diagram name’.asImageByRepresentationName();
  * Display the first representation found with the name ‘diagram name’.

![asImageByRepresentationName() and fit().]({{page.relativePath}}/capella/images/extension/Diagram%20services1.png "asImageByRepresentationName() and fit().")

* modelElement.asImageByRepresentationDescriptionName(‘diagram type’).
  * Return the list of all the representations of the type ‘diagram type’ attached to the variable modelElement.

![asImageByRepresentationDescriptionName().]({{page.relativePath}}/capella/images/extension/Diagram%20services2.png "asImageByRepresentationDescriptionName().")

For more details see [Sirius services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_m2docsiriusservices.html).

* modelElement.isRepresentationDescriptionName(‘diagram type’).
  * Return `true` if there is at least one representation of the type ‘diagram type’.
* ‘diagram name’.isRepresentationName();
  * Return true if there is at least one representation with the name ‘diagram name’.

![isRepresentationDescriptionName() and isRepresentationName().]({{page.relativePath}}/capella/images/extension/Diagram%20services3.png "isRepresentationDescriptionName() and isRepresentationName().")

## Main other services

* asBookmarkRef(‘reference’)
  * Create a navigable link toward the asBookmark() with the same ‘reference’ the reference is usully an identifier.
* asBookmark(‘reference’)
  * Creates the anchor for the asBookmark link with the same ‘reference’ the reference is usully an identifier.

## Template example

![Example.]({{page.relativePath}}/capella/images/extension/Template%20example.png "Example.")

# M2Doc template editor and variables

To open a template with the template editor right click on the template .docx file and select open with. If the template editor is not in the list select `other ...`:

![Open with menu.]({{page.relativePath}}/ref-doc/nightly/images/Open%20with%20menu.png "Open with menu.")

Make sure to select `Internal editors` in the editor selection dialog:

![Editor selection dialog.]({{page.relativePath}}/ref-doc/nightly/images/Editor%20selection%20dialog.png "Editor selection dialog.")

You should then see the following editor:

![Template editor.]({{page.relativePath}}/capella/images/extension/Template%20editor.png "Template editor.")

Import Capella datamodel

![Add Capella datamodel.]({{page.relativePath}}/capella/images/extension/Add%20Capella%20datamodel1.png "Add Capella datamodel.")

![Add Capella datamodel.]({{page.relativePath}}/capella/images/extension/Add%20Capella%20datamodel2.png "Add Capella datamodel.")

Define variables

![Define variable.]({{page.relativePath}}/capella/images/extension/Define%20variables1.png "Define variables.")

![Define variable.]({{page.relativePath}}/capella/images/extension/Define%20variables2.png "Define variables.")

Need to map the variable with an eClass (type of model element). See [Tips and tricks](capella-user-guide.html#tips-and-trics) section to find out which eClass to use.

![Define variable.]({{page.relativePath}}/capella/images/extension/Define%20variables3.png "Define variables.")

Import services.

Right click, Select tokens.

![Import services.]({{page.relativePath}}/capella/images/extension/Import%20services1.png "Import services.")

Choose default.

![Import services.]({{page.relativePath}}/capella/images/extension/Import%20services2.png "Import services.")

![Import services.]({{page.relativePath}}/capella/images/extension/Import%20services3.png "Import services.")

# Genconf file and generation

Initialize the genconf file.

![Create genconf.]({{page.relativePath}}/capella/images/extension/Create%20genconf1.png "Create genconf.")

![Create genconf.]({{page.relativePath}}/capella/images/extension/Create%20genconf2.png "Create genconf.")

Genconf editor.

![Genconf editor.]({{page.relativePath}}/capella/images/extension/Genconf%20editor.png "Genconf editor.")

Sets the value of a variable.

* Go to selection tab.
* right click laod resource.

![Bind variable.]({{page.relativePath}}/capella/images/extension/Bind%20variable1.png "Bind variable.")

* Browse workspace.

![Bind variable.]({{page.relativePath}}/capella/images/extension/Bind%20variable2.png "Bind variable.")

* Select melodymodeler file.

![Bind variable.]({{page.relativePath}}/capella/images/extension/Bind%20variable3.png "Bind variable.")

* Go back to the overview tab
* Select a value, only instance of compatible eClass will be proposed

![Bind variable.]({{page.relativePath}}/capella/images/extension/Bind%20variable4.png "Bind variable.")


![Bind variable.]({{page.relativePath}}/capella/images/extension/Bind%20variable4.png "Bind variable.")

Define options

* Convert ptoject to modeling project.

![Define options.]({{page.relativePath}}/capella/images/extension/Define%20options1.png "Define options.")

* Ensure that aird session is open

![Define options.]({{page.relativePath}}/capella/images/extension/Define%20options2.png "Define options.")

* Right click, initialize option

![Define options.]({{page.relativePath}}/capella/images/extension/Define%20options3.png "Define options.")

![Define options.]({{page.relativePath}}/capella/images/extension/Define%20options4.png "Define options.")

Document generation.

* Right click on genconf file, Generate Documentation.
* Creates the output document
* And an error file (if there is errors in the template)

![Document generation.]({{page.relativePath}}/capella/images/extension/Document%20generation.png "Document generation.")

# Tips and tricks

## Expert view

![Expect view.]({{page.relativePath}}/capella/images/extension/Expert%20view.png "Expect view.")

## Interpreter views

![Interpreter view.]({{page.relativePath}}/capella/images/extension/Interpreter%20view1.png "Interpreter view.")

* Capella 1.1.x and lower

![Interpreter view.]({{page.relativePath}}/capella/images/extension/Interpreter%20view2.png "Interpreter view.")

* Capella 1.2.x and higher

![Interpreter view.]({{page.relativePath}}/capella/images/extension/Interpreter%20view3.png "Interpreter view.")

## Model request interpreter (Capella 1.1.x and lower)

* Queries shall be written inside <% %>.
* Key word « self » is used to refer to the current selected element.
* Syntax in this view is slightly diffferent from M2Doc queries.

![Model request interpreter view.]({{page.relativePath}}/capella/images/extension/Model%20request%20interpreter.png "Model request interpreter view.")

## Interpreter (Capella 1.2.x and higher)

* Queries starts with `aql:`.
* Key word `self` is used to refer to the current selected element.
* Select Sirius interpreter!

![Interpreter view.]({{page.relativePath}}/capella/images/extension/Interpreter.png "Interpreter view.")

## Interpreter views selection

Make sure to always use the selection from the project explorer.

![Project explorer.]({{page.relativePath}}/capella/images/extension/Project%20explorer.png "Project explorer.")

Do not select from the diagram directly!

![Diagram.]({{page.relativePath}}/capella/images/extension/Diagram.png "Diagram.")

## Useful queries

### Capella 1.1.x and lower

Get the eClass of a model element.

![EClass 1.1.x.]({{page.relativePath}}/capella/images/extension/EClass%201.1.x.png "EClass 1.1.x.")

Get all the possible relations starting from this model element.

![eReferences 1.1.x.]({{page.relativePath}}/capella/images/extension/EReferences%201.1.x.png "eReferences 1.1.x.")

Gets the container of a model element.

![eContainer 1.1.x.]({{page.relativePath}}/capella/images/extension/EContainer%201.1.x.png "eContainer 1.1.x.")

Gets all contained model elements of a model element.

![eAllContents 1.1.x.]({{page.relativePath}}/capella/images/extension/EAllContents%201.1.x.png "eAllContents 1.1.x.")

### Capella 1.2.x and higher

Get the eClass of a model element.

![EClass 1.2.x.]({{page.relativePath}}/capella/images/extension/EClass%201.2.x.png "EClass 1.2.x.")

Get all the possible relations starting from this model element.

![eReferences 1.2.x.]({{page.relativePath}}/capella/images/extension/EReferences%201.2.x.png "eReferences 1.2.x.")

Gets the container of a model element.

![eContainer 1.2.x.]({{page.relativePath}}/capella/images/extension/EContainer%201.2.x.png "eContainer 1.2.x.")

Gets all contained model elements of a model element.

![eAllContents 1.2.x.]({{page.relativePath}}/capella/images/extension/EAllContents%201.2.x.png "eAllContents 1.2.x.")

## Representation Description Name exceptions

Most diagrams exactly fits the name displayed in Capella, however, there is some exceptions:

|Diagram name (as seen in Capella)|Representation Description Name (for M2Doc template)|
|---------------------------------|----------------------------------------------------|
|Modes and States (DEPRECATED)|Modes & States|
|Interface Scenario|Component Interfaces Scenario|
|Exchange Scenario|Component Exchanges Scenario|
|Function Scenari|Functional Scenario|
|Operational Architecture Blank|Operational Entity Blank|
|Entity Scenario|Operational Interaction Scenario|
|Activity Scenario|Activity Interaction Scenario|
|State Machine and Capability Function Matrix|State And Mode - Matrix|
|Capability Function and State Machine|Matrix Contextual State And Mode - Matrix|
|System Actors - Operational Actors/Operational Entities|System Actor - Operational Actor|
|Physical Components/Actors - Physical Functions|Physical Components - Physical Functions|
|Physical Interfaces - Logical Interfaces|Physical Interface - Logical Interface|
|Physical Actors - Logical Actors|Physical Actor - Logical Actor|
|Logical Components/Actors - Logical Functions|Logical Components - Logical Functions|
|Logical Interfaces - System Interfaces|Logical Interface - Context Interface|
|Logical Actors - System Actors|Logical Actor - Context Actor|

# Support and References

* Capella forum: Ask questions!
  * [https://polarsys.org/forums/index.php/i/4/](https://polarsys.org/forums/index.php/i/4/)
* M2Doc website (include M2Doc documentation)
  * [http://www.m2doc.org/](http://www.m2doc.org/)
* AQL documentation
  * [https://www.eclipse.org/acceleo/documentation/aql.html](https://www.eclipse.org/acceleo/documentation/aql.html)
* Capella website:
  * [http://www.polarsys.org/capella/](http://www.polarsys.org/capella/)
* LinkedIn
  * [https://www.linkedin.com/groups/8605600](https://www.linkedin.com/groups/8605600)
* Twitter
  * [https://twitter.com/capella_arcadia](https://twitter.com/capella_arcadia)
* Arcadia forum:
  * [https://polarsys.org/forums/index.php/f/12/](https://polarsys.org/forums/index.php/f/12/)
* Capella forum:
  * [https://polarsys.org/forums/index.php/f/13/](https://polarsys.org/forums/index.php/f/13/)
* IFE model & doc.:
  * [http://www.polarsys.org/capella/start.html](http://www.polarsys.org/capella/start.html)
