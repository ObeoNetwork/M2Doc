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

## Starting with M2Doc

First a have look at the M2Doc's overall architecture available on the [Overview]({{page.relativePath}}/overview) page.

Then you probably want to start with the [Download]({{page.relativePath}}/download) section. 

Ready to try M2Doc! You might have one of the following roles:

### Template user

You already have the template and want to generate the document:

* see [Generate a document](index.html#generating-a-document)
* see [Maven](index.html#maven) (optional)

### Template developper

You want to create your own template:

* see [Template authoring](index.html#template-authoring)
* see [Provide new services](index.html#providing-new-services) (optional)
* see [Validating a generation](index.html#validating-a-generation-setup) (optional)
* see [Template testing](index.html#template-testing) (optional)

### Integrator

<a name="generate-a-document"></a>

You want to provide document generation in your own project using M2Doc:

* see [Using M2Doc programmatically](index.html#using-m2doc-programmatically)
* see [Using AQL programmatically](https://www.eclipse.org/acceleo/documentation/aql.html#UsingAQLprogrammatically) (optional)

## Generating a document

To generate a document you will need at least a template file (.docx file) and a generation configuration model (.genconf file). If you don&#8217;t have the template you can read the [template authoring](index.html#template-authoring) section. To create a generation model file you can read the [initialize generation](index.html#initializing-a-generation-configuration) section.

### Launching a generation

To launch a generation you can right click on the generation configuration model (.genconf file) or the Generation object and select the &#171;Generate Documentation menu&#187; as shown here:

![The Generate Documentation menu.]({{page.relativePath}}/ref-doc/nightly/images/Generate%20Documentation%20menu.png "The Generate Documentation menu.")

If you don&#8217;t have the the generation model configuration file you can create it using the [initialize generation](index.html#initializing-a-generation-configuration) section.

### Initializing a generation configuration

To initialize a generation configuration you will need a template file (.docx file). If you don&#8217;t have the template you can read the [template authoring](index.html#template-authoring) section.

Right click on the template file and select the &#171;Initialize Documentation Configuration&#187; menu:

![The Initialize Documentation Configuration menu.]({{page.relativePath}}/ref-doc/nightly/images/Initialize%20Documentation%20Configuration%20menu.png "The Initialize Documentation Configuration menu.")

To edit the created generation model file you can use the [generation configuration editor](index.html#generation-configuration-editor).

### Validating a generation setup

To validate a generation setup (template plus generation configuration) you can right click on the generation configuration model (.genconf file) or the Generation object and select the &#171;Validate Documentation menu&#187; as shown here:

![The Validate Documentation menu.]({{page.relativePath}}/ref-doc/nightly/images/Validate%20Documentation%20menu.png "The Validate Documentation menu.")

If you don&#8217;t have the the generation configuration model file you can create it using the [initialize generation](index.html#initializing-a-generation-configuration) section. If the validation succeeds with no informations, warnings, or errors you will be prompted. If something went wrong a .docx file with the name of the template and suffixed with &#171;info&#187;, &#171;warning&#187;, or &#171;error&#187; will be created. This new file contains details of the validation issues. The template is *not* modified.

### Generation configuration editor

The generation configuration model (.genconf file) references the template [URI](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier) and the result [URI](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier). It also binds the variables defined using the [template editor](index.html#template-editor) to the desired value. It can also contain a set of options to configure [specific services](index.html#custom-constructor).

![The generation configuration editor.]({{page.relativePath}}/ref-doc/nightly/images/Generation%20configuration%20editor.png "The generation configuration editor.")

In this example we reference the template named &#171;template.docx&#187; using a relative [URI](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier) and the result [URI](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier) is set to &#171;template-generated.docx&#187;. All relative [URI](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier) are relative to the .genconf file, note absolute [URI](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier) can be used as well.

The first table of the editor is used to bind variables. The first column is the variable name and the second column is its value. The value can be a reference to an EMF model element, or be a primitive type value. Two menus are accessible using right click on the variable table.

![The variable menu.]({{page.relativePath}}/ref-doc/nightly/images/Generation%20configuration%20editor%20-%20variables%20menu.png "The variable menu.")

* `Add variables` will add all variables defined in the referenced template. To define variables you can use the [template editor](index.html#template-editor)
* `Remove definition` will remove the selected bindings

Another menu can be useful to set EMF model values. It&#8217;s the &#171;Load Resource...&#187; menu. It adds the EMF elements of the selected resources to possible values of a variable.

The second table of the editor at the bottom can be used to set options for specific services. For instance if you use the [Sirius](https://eclipse.org/sirius/) integration an option should be set to define the Sirius session model. Three menus are accessible using right click on the option table.

![The option menu.]({{page.relativePath}}/ref-doc/nightly/images/Generation%20configuration%20editor%20-%20options%20menu.png "The option menu.")
<a name="write-a-template"></a>
* `Add option` will add an option (see [services with custom constructor](index.html#custom-constructor) for more details)
* `Initialize option` will initialize an option (see [services with custom constructor](index.html#custom-constructor) for more details)
* `Delete` will delete the selected options 

## Template authoring

The template should be edited using your Microsoft Word document editor. M2Doc uses Word fields to declare dynamic elements. To make fields visible during the edition of your template in Microsoft Word, use `ALT+F9`. To insert a new field, use `CTRL+F9`.

### Syntax

All syntax elements of M2Doc should be placed into fields using `ALT+F9` to show fields and `CTRL+F9` to insert a new field. M2Doc is an imperative template language built on top of [AQL](https://www.eclipse.org/acceleo/documentation/aql.html) for querying. The language supports type inference and can be extended using [custom services](index.html#providing-new-services).

In the following table`{` and `}` denote a Word field. 

#### Comment ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/comment/nominal))

A simple comment, nothing is generated.

`{m:comment any text}`

#### Static statement

Anything that is not a M2Doc field is generated as is.

#### Query ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/query/nominal))

The [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html) is evaluated and its result is inserted in the generated document. [M2Doc services](index.html#services) and [provided services](index.html#providing-new-services) can be used. Some [specific return types](index.html#special-return-types) will be interpreted by M2Doc.

`{m:AQL expression}`

#### Conditional ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/conditional/nominal))

If the condition is true the &#171;then block&#187; is inserted in the generated document. if it&#8217;s false and the &#171;else if&#187; condition is true the &#171;else if block&#187; is inserted. if all condition are false the &#171;else block&#187; is inserted. You can have zero or more &#171;elseif blocks&#187;, zero or one &#171;else block&#187;. All [AQL expressions](https://www.eclipse.org/acceleo/documentation/aql.html) should evaluate to a Boolean.

`{m:if AQL expression}`

`...then block...`

`{m:elseif AQL expression}`

`...else if block...` 

`{m:else}` 

`...else block...`

` {m:endif}`


#### Repetition ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/repetition/nominal))

Generates the &#171;repetition block&#187; for each value of the [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html).

`{m:for variable | AQL expression} ...repetition block... {m:endfor}`


#### Let ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/repetition/nominal))

Declares a variable named &#171;variable&#187; with the result of the [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html) as its value. The variable is accessible in the &#171;let block&#187;.

`{m:let variable = AQL expression} ...let block... {m:endlet}`

#### Bookmark ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/bookmark/nominal))

Generates a bookmark with the result of the [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html) as identifier and the &#171;bookmark block&#187; as content.

`{m:bookmark AQL expression} ...bookmark block... {m:endbookmark}`

#### Link ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/bookmark/nominal))

Generates a link to the [bookmark](index.html#bookmark-examplehttpsgithubcomobeonetworkm2doctreemastertestsorgobeonetworkm2doctestsresourcesbookmarknominal) with the given identifier and text. 

`{m:link AQL expression AQL expression}`

The first [AQL expression](https://www.eclipse.org/acceleo/documentation/aql.html) id the identifier and the second one is the text to display.

#### User Documentation ([example](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/userDoc/nominal))

Generates a user content block in the resulting document. If the generated document exists and already has a user content with the same id the previous user content is preserved, otherwise the &#171;userdoc block&#187; is generated.

`{m:userdoc AQL expression} ...user block... {m:enduserdoc}`

If a user content exists in a previously generated document and the id is not re-generated, the user content will by removed from the generated document and saved in a &#171;lost&#187; file.

If you want more examples of templates you can have a look at the nominal cases in [unit tests](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources).

### Services

All [AQL services](https://www.eclipse.org/acceleo/documentation/aql.html#LanguageReference) are available. On top of that, specific services are provided by M2Doc. See the following reference documentation. If you don&#8217;t find your needs in this list, you can [provide custom services](index.html#providing-new-services).

* [Boolean services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_booleanservices.html)
* [Image services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_imageservices.html)
* [Link services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_linkservices.html)
* [Pagination services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_paginationservices.html)
* [Sirius services]({{page.relativePath}}/ref-doc/nightly/m2doc_service_m2docsiriusservices.html)

### Template editor

The template editor can set M2Doc-specific custom properties in your Word template. Those properties are used to define variables, EMF packages URIs, and service imports. When editing a template you need to be in a Java or a plug-in project to be able to add services. The template editor modifies template custom properties. You can also read [template custom properties](index.html#template-custom-properties) section to change them manually. 

![The template editor.]({{page.relativePath}}/ref-doc/nightly/images/Template%20editor.png "The template editor.")

The first table at the top lists variables needed by the template. Each variable has a declared [type](https://www.eclipse.org/acceleo/documentation/aql.html#Typeliterals). This type is used to validate the template and to select a value in the [generation configuration editor](index.html#generation-configuration-editor). Three menus are accessible using right click on the variable table.

![The variables menu.]({{page.relativePath}}/ref-doc/nightly/images/Template%20editor%20-%20variables%20menu.png "The variables menu.")

* `Add missing variables` will add all variables used in the template but not declared yet.
* `Add variable` will add a variable
* `Delete variable` will delete selected variables

The second table in the middle lists packages used in the template. It reference EMF EPackages used to type expressions in the template. Two menus are accessible using right click on the package table.

![The variables menu.]({{page.relativePath}}/ref-doc/nightly/images/Template%20editor%20-%20packages%20menu.png "The variables menu.")

* `Add` will open the following dialog to add selected packages

![The package selection dialog.]({{page.relativePath}}/ref-doc/nightly/images/Package%20selection%20dialog.png "The package selection dialog.")

* `Delete` will remove the selected packages

The last table at the bottom lists services used in the template. If you have created your [own services](index.html#providing-new-services) you can add them here to be able to use them in the template. Three menus are accessible using right click on the service table.

![The variables menu.]({{page.relativePath}}/ref-doc/nightly/images/Template%20editor%20-%20services%20menu.png "The variables menu.")

* `Add` will open a dialog where you can select a Java class

![The service selection dialog.]({{page.relativePath}}/ref-doc/nightly/images/Service%20selection%20dialog.png "The service selection dialog.")

If your class doesn't appear in the dialog you might want to check the Window>Preferences>Plug-in Development>Include all plug-ins from target in Java search option. This will list deployed classes.

* `Delete` will remove the selection
* `Select token` will add/remove services according to the selected [tokens](index.html#service-token) in the dialog

![The service token selection dialog.]({{page.relativePath}}/ref-doc/nightly/images/Service%20token%20selection%20dialog.png "The service token selection dialog.")

### Template custom properties

You can use the [template editor](index.html#template-editor) or edit them using your Microsoft Word document editor (see [this page](https://support.office.com/en-us/article/View-or-change-the-properties-for-an-Office-file-21d604c2-481e-4379-8e54-1dd4622c6b75?CorrelationId=866914c3-b0b5-42e8-aeb2-e9f7bcc216e2&ui=en-US&rs=en-US&ad=US&ocmsassetID=HA010047524)). This section will describe possible properties name and value used by M2Doc.

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

* Name: `m:uri:http://www.eclipse.org/uml2/5.0.0/UML` 
* Value:

#### Service custom properties

To import a service class with a [default constructor](index.html#default-constructor). To use a service class with a custom constructor you can read the [custom constructor](index.html#custom-constructor) section.

* Name: `m:import:`**qualified class name**
* Value: **bundle symbolic name**

For example:

* Name: `m:import:org.obeonetwork.m2doc.rcptt.a.ServiceClassA`
* Value: `org.obeonetwork.m2doc.rcptt.a` 

## Providing new services

You can extends M2Doc by adding services to your templates using the [template editor](index.html#template-editor). A service is a simple Java method with at least one parameter and a return value.

### Service class

There are two cases for the class containing the service: either it has a [default constructor](index.html#default-constructor), or it has a [constructor with a parameter](index.html#custom-constructor).

#### Default constructor

When the class has no explicit constructor or the constructor doesn&#8217;t have any parameters. In this case you don&#8217;t need any specific development except for your service method. You can have a look at [specific return types](index.html#special-return-types) if you want to insert images, table, etc... To use your services in your template simply add them through the [template editor](index.html#template-editor).

#### Custom constructor

When there is an explicit constructor with at least a parameter M2Doc can&#8217;t instantiate your class since there is no default constructor. You need to implement the [IServicesConfigurator](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/services/configurator/IServicesConfigurator.java) interface. This interface link one or more options in the generation configuration to the service class in order to instantiate it. You need to use the org.obeonetwork.m2doc.ide.servicesConfigurator extension point to declare your implementation of [IServicesConfigurator](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/services/configurator/IServicesConfigurator.java). If you want to use your implementation in standalone you can register it programmatically using M2DocUtils.registerServicesConfigurator().

An example of implementation can be found in the [Sirius](https://eclipse.org/sirius/) integration plug-in see the class [SiriusConfigurationProvider](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc.sirius/src/org/obeonetwork/m2doc/sirius/providers/configuration/SiriusConfigurationProvider.java) and the extension in the [plugin.xml](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc.sirius/plugin.xml).

### Service token

The service token extension point org.obeonetwork.m2doc.ide.services.register can be used to reference a set of [service classes](index.html#default-constructor) using a token name. This token can be selected using &#171;Select tokens&#187; menu in the [template editor](index.html#template-editor).

### Special return types

The M2Doc evaluation engine converts any object to a string in order to insert it in the generated document. To add flexibility in the produced document we chose to expose some special types to service creators. Those types are [MElement](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MElement.java) and allow directly inserting document artifacts:

* [MPagination](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MPagination.java) to insert a table of contents, a new page, a new paragraph, a new column, or a new text wrapping. 
* [MBookmark](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MBookmark.java) to insert a new bookmark or a reference to a bookmark.
* [MHyperLink](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MHyperLink.java) to insert a new link to an [URL](https://en.wikipedia.org/wiki/URL).
* [MImage](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MImage.java) to insert a new image.
* [MTable](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MTable.java) to insert a new table.
* [MText](https://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/MText.java) to insert styled text.

Default implementations are also provided by M2Doc in [this package](https://github.com/ObeoNetwork/M2Doc/tree/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/element/impl).

## Template testing

To simplify unit testing while developing M2Doc, a [JUnit](http://junit.org/junit4/) test suite has been implemented. It uses a given folder as input and lists each sub directory following a naming pattern as a test case. You can use the same test suite for your own tests. An example of the test suite implementation [QueryTests](https://github.com/ObeoNetwork/M2Doc/blob/master/tests/org.obeonetwork.m2doc.tests/src/org/obeonetwork/m2doc/tests/QueryTests.java) with the folder [resources/query](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/query).

## Maven

You can launch your generation using [Maven](https://maven.apache.org/) and [Tycho](https://eclipse.org/tycho/). An example is available [here](https://github.com/ObeoNetwork/M2Doc/tree/master/docs/generate-with-maven). You may probably need to have a look at the [pom.xml](https://github.com/ObeoNetwork/M2Doc/blob/master/docs/generate-with-maven/myModelToDocx/pom.xml) file. To launch the build and the generation you can simply use the following command:

`mvn clean verify`

## Using M2Doc programmatically

This section is for developers wanting to integrate M2Doc in their own project. It will show how to use the main features of M2Doc programmatically. Since M2Doc uses AQL for expressions it can be useful to have a look at [Using AQL programmatically](https://www.eclipse.org/acceleo/documentation/aql.html#UsingAQLprogrammatically).

### Parsing

The following sample code shows how to load a template .docx file using M2Doc:

{% highlight Java %}
final IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);
final Map<String, String> options = ...; // can be empty
M2DocUtils.prepareEnvironmentServices(queryEnvironment, templateURI, options); // delegate to IServicesConfigurator
final IClassProvider classProvider = new ClassProvider(this.getClass().getClassLoader()); // use M2DocPlugin.getClassProvider() when running inside Eclipse
try (DocumentTemplate template = M2DocUtils.parse(templateURI, queryEnvironment, classProvider)) {
  // use the template
}
{% endhighlight %}

### Validation

The validation is optional:

{% highlight Java %}
final ValidationMessageLevel validationLevel = M2DocUtils.validate(template, queryEnvironment);
if (validationLevel != ValidationMessageLevel.OK) {
  final URI validationResulrURI = ...; // some place to serialize the result of the validation
  M2DocUtils.serializeValidatedDocumentTemplate(documentTemplate, validationResulrURI);
}
{% endhighlight %}

### Generation

The generation will produce the final document where M2Doc template is evaluated against the given variables. There are two levels of API for the generation: directly from the DocumentTemplate or from the Generation.

#### Core generation API

{% highlight Java %}
final Map<String, Object> variables = ...; // your variables and values
final URI outputURI = ...; // some place to serialize the result of the generation
M2DocUtils.generate(template, queryEnvironment, variables, outputURI, monitor);
{% endhighlight %}

#### Generation configuration API

{% highlight Java %}
final Generation generation = ...; // load from a serialized EMF model or create in memory
final IClassProvider classProvider = new ClassProvider(this.getClass().getClassLoader()); // use M2DocPlugin.getClassProvider() when running inside Eclipse
GenconfUtils.generate(generation, classProvider, monitor);
{% endhighlight %}
