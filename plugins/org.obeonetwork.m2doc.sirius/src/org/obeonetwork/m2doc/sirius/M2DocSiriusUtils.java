package org.obeonetwork.m2doc.sirius;

import java.util.Set;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.sirius.business.api.session.Session;
import org.obeonetwork.m2doc.sirius.services.M2DocSiriusServices;

/**
 * M2Doc Sirius utility class.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class M2DocSiriusUtils {

    /**
     * The Sirius session option.
     */
    public static final String SIRIUS_SESSION_OPTION = "SiriusSession";

    /**
     * Constructor.
     */
    private M2DocSiriusUtils() {
        // nothing to do here
    }

    /**
     * Prepares the given {@link IQueryEnvironment} for M2Doc Sirius services.
     * 
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @param session
     *            the {@link Session Sirius session}
     * @return an {@link AutoCloseable} for services cleanup
     */
    public static M2DocSiriusServices prepareEnvironmentServices(IQueryEnvironment queryEnvironment, Session session) {

        final M2DocSiriusServices servicesInstance = new M2DocSiriusServices(session);
        final Set<IService> services = ServiceUtils.getServices(queryEnvironment, servicesInstance);
        ServiceUtils.registerServices(queryEnvironment, services);

        return servicesInstance;
    }

}
