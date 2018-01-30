---
layout: article
title: Capella M2Doc tutorial
subtitle: In-Flight Entertainment System (IFE) example
relativePath: ..
---

# Table of Content

Following you will find the tutorial to use [M2Doc](http://m2Doc.org) document generation with [Capella](http://polarsys.org/capella/).
This will cover installation of integration plugins and deployement of the In-Flight Entertainment System (IFE) example.

Note: M2Doc 1.1.0 and above are compatible with [Team for Capella](https://www.obeo.fr/en/capella-professional-offer). You can test the generation by sharing the IFE example project provided in the extensions. Then edit the .genconf file to reference the shared SystemEngineering model element. You might need to open the Capella session first by double clicking the .aird file.

* TOC
{:toc}

## Installation

First you need to download [Capella](http://polarsys.org/capella/). It can be downloaded from this [download page](http://polarsys.org/capella/download.html).

Once your download is finished, extract the downloaded archive and run the eclipe executable in the eclipse sub folder. You are now ready for the last step of the installation. You will need to [add a new update site](https://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.platform.doc.user%2Ftasks%2Ftasks-127.htm):

### Installation Capella 1.2.x

* [http://obeo-networkaggregation-releases.s3-website-eu-west-1.amazonaws.com/capella-extensions/1.2.0/full](http://obeo-networkaggregation-releases.s3-website-eu-west-1.amazonaws.com/capella-extensions/1.2.0/full)


### Installation Capella 1.1.x

* [http://obeo-networkaggregation-releases.s3-website-eu-west-1.amazonaws.com/capella-extensions/1.1.0/full](http://obeo-networkaggregation-releases.s3-website-eu-west-1.amazonaws.com/capella-extensions/1.1.0/full)

This update site contains [M2Doc](http://m2Doc.org) and the [Capella](http://polarsys.org/capella/) integration. Select the following feature:

![The feature to install.]({{page.relativePath}}/capella/images/Install.png "The feature to install.")

## Deploying the In-Flight Entertainment System (IFE) example

After restarting Capella, you can deploye the In-Flight Entertainment System (IFE) example in your workspace with the following steps:

Use the New > Other menu.

![The new menu.]({{page.relativePath}}/capella/images/New%20Menu.png "The new menu.")

The select the Capella - M2Doc > Capella IFE example with M2Doc templates project.

![The new project wizard page 1.]({{page.relativePath}}/capella/images/New%20Wizard%20Page%201.png "The new project wizard page 1.")

Click the next button.

![The new project wizard page 2.]({{page.relativePath}}/capella/images/New%20Wizard%20Page%202.png "The new project wizard page 2.")

Click the finish button and you should see the following project in your workspace.

![The IFE project content.]({{page.relativePath}}/capella/images/IFE Project.png "The IFE project content.")

## Templates

You can run templates by using the corresponding .genconf file at the root of the project, to do so use the .genconf file as shown [here](http://www.m2doc.org/ref-doc/1.0.0/index.html#launching-a-generation). Templates are located in the template sub folder of the project.

If you want to go further in template editing you should have a look at the [M2Doc documentation](http://www.m2doc.org/ref-doc/1.0.0/index.html).

In the following you will find a description of each templates.

### LA Complete

This template allows the generation of a logical specification of the content of the system. It starts by a description of the hierarchy of logical components, with, for each, its allocated functions and provided/required interfaces. Then the function decomposition is presented including functional exchanges. At the end, a reference of data structures is generated.

### SA Complete

This template allows the generation of a document concerning the whole specification of a system. Starting from mission and capabilities of the system, the template presents the context of the system, its state machines and communicating actors, and functional specifications. At the end a reference of data structures is generated.

