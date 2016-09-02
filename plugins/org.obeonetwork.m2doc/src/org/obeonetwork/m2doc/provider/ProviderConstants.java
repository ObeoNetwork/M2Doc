/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.provider;

/**
 * Class containing constants that can be used in custom {@link IProvider}.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public interface ProviderConstants {
    /**
     * The key used in the map passed to {@link IProvider} to insert and retrieve the root object of the configuration model.
     */
    String CONF_ROOT_OBJECT_KEY = "confRootObject";
    /**
     * The key used in the map passed to {@link IProvider} to define the root path of the project source of the document generation.
     */
    String PROJECT_ROOT_PATH_KEY = "projectRootPath";
    /**
     * The key used in the map passed to {@link IProvider} to define the height the image should have. Value associated is of type int.
     */
    String IMAGE_HEIGHT_KEY = "imageHeight";
    /**
     * The key used in the map passed to {@link IProvider} to define the width the image should have. Value associated is of type int.
     */
    String IMAGE_WIDTH_KEY = "imageWidth";

}
