package org.obeonetwork.database.wgen.services;

import org.obeonetwork.m2doc.service.IServiceHolder;

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
