package org.obeonetwork.m2doc.sirius.services;

import org.obeonetwork.m2doc.services.IServiceHolder;

/**
 * {@link IServiceHolder} implementation for the {@link M2DocSiriusServices} class.
 * 
 * @author Romain Guider
 */
public class M2DocSiriusServiceHolder implements IServiceHolder {

    @Override
    public Class<?> getServiceClass() {
        return M2DocSiriusServices.class;
    }

}
