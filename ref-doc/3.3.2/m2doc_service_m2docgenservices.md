---
layout: article-services
title: M2DocGenServices
subtitle: 3.3.2
relativePath: ../..
---

<!--
/********************************************************************************
** Copyright (c) 2015, 2023 Obeo.
** All rights reserved. This program and the accompanying materials
** are made available under the terms of the Eclipse Public License v2.0
** which accompanies this distribution, and is available at
** http://www.eclipse.org/legal/epl-v20.html
**
** Contributors:
**    Stephane Begaudeau (Obeo) - initial API and implementation
*********************************************************************************/
-->

# M2DocGenServices

Services available for Capella extensions.

* TOC
{:toc}

## org.polarsys.capella.core.data.information.DataPkg.getAllDataPkgs() : Sequence{org.polarsys.capella.core.data.information.DataPkg}

Return recursively all the data subpackages of the given data package.

### Parameter

* **dataPkg**: The given data package

### Example

* myDataPkg.getAllDataPkgs()
  * a list of all data subpackages

## org.polarsys.capella.core.data.cs.InterfacePkg.getAllSubInterfacesCount() : int

Compute the amount of contained interface subpackages.

### Parameter

* **interfacePkg**: The given interface package

### Example

* myInterfacePkg.getAllSubInterfacesCount()
  * the number of contained interface subpackages

## org.polarsys.capella.core.data.fa.ComponentExchange.getCeDirection(org.polarsys.capella.core.data.cs.Component) : String

Return the component exchange direction (UNSET/IN/OUT/INOUT) for the given component.

### Parameter

* **ce**: The given component exchange
* **component**: The given component

### Example

* myDataPkg.getDataPkgQualifiedName(myComponent)
  * 'UNSET' or 'IN' or 'OUT' or 'INOUT'

## org.polarsys.capella.core.data.information.DataPkg.getDataPkgQualifiedName() : String

Compute a "qualified name" of the given data package. The root of the qualified name is the upper level data package.

### Parameter

* **dataPkg**: The given data package

### Example

* myDataPkg.getDataPkgQualifiedName()
  * the qualified name of the data package

## org.polarsys.capella.core.data.fa.ComponentExchange.getDestinationComponent(org.polarsys.capella.core.data.cs.Component) : String

Return the component at the other end of the given component exchange and component.

### Parameter

* **ce**: The given component exchange
* **component**: The given component

### Example

* myDataPkg.getDataPkgQualifiedName(myComponent)
  * the component at the other end of the given component exchange and component

## org.polarsys.kitalpha.emde.model.ExtensibleElement.getExtensions(OrderedSet{EClass}) : Sequence{EObject}

Return the list of extension of the given types for the given extensible element.

### Parameter

* **element**: The given extensible element
* **eClasses**: the extension EClasses

### Example

* myExtensibleElement.getExtensions({some::Extension1 | some::Extension2})
  * the list of extension of the given types for the given extensible element

## org.polarsys.kitalpha.emde.model.ExtensibleElement.getExtensions(EClass) : Sequence{EObject}

Return the list of extension of the given type for the given extensible element.

### Parameter

* **element**: The given extensible element
* **eCls**: the extension EClass

### Example

* myExtensibleElement.getExtensions(some::Extension)
  * the list of extension of the given type for the given extensible element

## org.polarsys.capella.core.data.cs.InterfacePkg.getInterfaceSubPkg() : Sequence{org.polarsys.capella.core.data.cs.InterfacePkg}

Return recursively all the interface subpackages of the given interface packages.

### Parameter

* **interfacePkg**: The given interface package

### Example

* myInterfacePkg.getInterfaceSubPkg()
  * a list of all interface subpackages

## org.eclipse.sirius.viewpoint.DRepresentation.isVisibleInDoc() : boolean

Tell if the given DRepresentation is visible in documentation.

### Parameter

* **rep**: The given DRepresentation

### Example

* myDRepresentation.isVisibleInDoc({some::Extension1 | some::Extension2})
  * true if the given DRepresentation is visible in documentation, false otherwise

## org.eclipse.sirius.viewpoint.DRepresentation.isVisibleInLM() : boolean

Tell if the given DRepresentation is visible in LM.

### Parameter

* **rep**: The given DRepresentation

### Example

* myDRepresentation.isVisibleInDoc({some::Extension1 | some::Extension2})
  * true if the given DRepresentation is visible in LM, false otherwise

## org.obeonetwork.m2doc.element.MElement.reduceAllImages(Integer, Integer) : org.obeonetwork.m2doc.element.MElement

Reduces all MImages from the given MElement to a maximum of the given size. Smaller images will not be changed.

### Parameter

* **element**: The given MElement
* **width**: The maximum width
* **height**: The maximum height

### Example

* myMElement.reduceAllImages(200, 300)
  * all reduced MImages from the given MElement

## org.obeonetwork.m2doc.element.MElement.replaceLink(EObject) : org.obeonetwork.m2doc.element.MElement

Replace the MLink uri from the given MElement to reference document bookmarks.

### Parameter

* **element**: The given MElement
* **reference**: a reference EObject to reteive the Sirius Session

### Example

* myMElement.replaceLink(eObj)
  * replaced the MLink uri from the given MElement to reference document bookmarks



