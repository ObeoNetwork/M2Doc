---
layout: article-services
title: ImageServices
subtitle: Nightly
relativePath: ../..
---

<!--
/********************************************************************************
** Copyright (c) 2015 Obeo.
** All rights reserved. This program and the accompanying materials
** are made available under the terms of the Eclipse Public License v2.0
** which accompanies this distribution, and is available at
** http://www.eclipse.org/legal/epl-v20.html
**
** Contributors:
**    Stephane Begaudeau (Obeo) - initial API and implementation
*********************************************************************************/
-->

# ImageServices

Services available for Images. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/imageServices).

* TOC
{:toc}

## String.asImage() : org.obeonetwork.m2doc.element.MImage

Convert a String representing an URI to an Image.

### Parameter

* **uri**: The Image uri, it can be relative to the template

### Example

* 'image.png'.asImage()
  * insert the image 'image.png'

## String.asImage(String) : org.obeonetwork.m2doc.element.MImage

Convert a String representing an URI to an Image and serialize it in the given format.

### Parameter

* **uri**: The Image uri, it can be relative to the template

### Example

* 'image.png'.asImage('jpg')
  * insert the image 'image.jpg'

## org.obeonetwork.m2doc.element.MImage.fit(Integer, Integer) : org.obeonetwork.m2doc.element.MImage

Fits the Image in the given rectangle width and height.

### Parameter

* **image**: The Image
* **width**: The width to fit
* **height**: The height to fit

### Example

* myImage.fit(200, 300)
  * will fit the image in a rectangle (width=200, height=300)

## org.obeonetwork.m2doc.element.MImage.getHeight() : Integer

Gets the height of the image.

### Parameter

* **image**: The Image

### Example

* myImage.getHeight()
  * 300

## org.obeonetwork.m2doc.element.MImage.getWidth() : Integer

Gets the width of the image.

### Parameter

* **image**: The Image

### Example

* myImage.getWidth()
  * 300

## org.obeonetwork.m2doc.element.MImage.resize(Double) : org.obeonetwork.m2doc.element.MImage

Resizes the Image by the given factor.

### Parameter

* **image**: The Image
* **factor**: The resize factor

### Example

* myImage.resize(0.5)
  * will resize the image by a factor 0.5

## org.obeonetwork.m2doc.element.MImage.rotate(Integer) : org.obeonetwork.m2doc.element.MImage

Rotates the Image by the given angle in degres.

### Parameter

* **image**: The Image
* **angle**: The angle in degres

### Example

* myImage.rotate(90)
  * will rotate the image by an angle of 90 degres

## org.obeonetwork.m2doc.element.MImage.setConserveRatio(Boolean) : org.obeonetwork.m2doc.element.MImage

Sets the conserve ratio of the image.

### Parameter

* **image**: The Image
* **conserve**: A Boolean

### Example

* myImage.setConserveRatio(false)
  * set the conserve ratio to false

## org.obeonetwork.m2doc.element.MImage.setHeight(Integer) : org.obeonetwork.m2doc.element.MImage

Sets the height of the image.

### Parameter

* **image**: The Image
* **height**: The height

### Example

* myImage.setHeight(300)
  * set the height to 300

## org.obeonetwork.m2doc.element.MImage.setWidth(Integer) : org.obeonetwork.m2doc.element.MImage

Sets the width of the image.

### Parameter

* **image**: The Image
* **width**: The width

### Example

* myImage.setWidth(300)
  * set the witdh to 300



