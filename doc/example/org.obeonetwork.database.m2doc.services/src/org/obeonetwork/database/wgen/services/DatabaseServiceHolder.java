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
package org.obeonetwork.database.wgen.services;

import org.obeonetwork.m2doc.services.IServiceHolder;

/**
 * Holder for the data base services.
 * 
 * @author Romain Guider
 *
 */
public class DatabaseServiceHolder implements IServiceHolder {

	@Override
	public Class<?> getServiceClass() {
		return DatabaseServices.class;
	}

}
