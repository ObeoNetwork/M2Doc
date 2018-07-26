package org.obeonetwork.m2doc.sirius;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.resource.strategy.AbstractResourceStrategyImpl;
import org.eclipse.sirius.business.api.session.Session;
import org.obeonetwork.m2doc.genconf.GenconfUtils;

/**
 * Prevents genfonc models from being added to Sirius {@link Session}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenconfResourceStrategy extends AbstractResourceStrategyImpl {

    @Override
    public boolean canHandle(Resource resource, ResourceStrategyType resourceStrategyType) {
        final boolean res;

        if (resource != null && resource.getURI() != null) {
            res = canHandle(resource.getURI(), resourceStrategyType);
        } else {
            res = super.canHandle(resource, resourceStrategyType);
        }

        return res;
    }

    @Override
    public boolean canHandle(URI resourceURI, ResourceStrategyType resourceStrategyType) {
        final boolean res;

        if (resourceURI != null && GenconfUtils.GENCONF_EXTENSION_FILE.equals(resourceURI.fileExtension())
            && resourceStrategyType == ResourceStrategyType.SEMANTIC_RESOURCE) {
            res = true;
        } else {
            res = super.canHandle(resourceURI, resourceStrategyType);
        }

        return res;
    }

    @Override
    public boolean isPotentialSemanticResource(URI uri) {
        return false;
    }

    @Override
    public boolean isLoadableModel(URI uri, Session session) {
        return false;
    }

}
