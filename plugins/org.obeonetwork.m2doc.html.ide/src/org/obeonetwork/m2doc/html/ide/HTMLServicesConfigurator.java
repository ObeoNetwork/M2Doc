package org.obeonetwork.m2doc.html.ide;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.html.services.HttpURIHandler;
import org.obeonetwork.m2doc.html.services.M2DocHTMLServices;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;

/**
 * HTML {@link IServicesConfigurator}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class HTMLServicesConfigurator implements IServicesConfigurator {

    /**
     * The {@link HttpURIHandler}.
     */
    private final URIHandler httpHandler = new HttpURIHandler();

    /**
     * Mapping from {@link IReadOnlyQueryEnvironment} to its instance of {@link M2DocHTMLServices}.
     */
    private final Map<IReadOnlyQueryEnvironment, M2DocHTMLServices> instancies = new HashMap<IReadOnlyQueryEnvironment, M2DocHTMLServices>();

    @Override
    public List<String> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options) {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment,
            Map<String, String> options) {
        return Collections.emptyMap();
    }

    @Override
    public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, URIConverter uriConverter,
            Map<String, String> options) {
        uriConverter.getURIHandlers().add(0, httpHandler);
        M2DocHTMLServices instance = new M2DocHTMLServices(uriConverter,
                URI.createURI(options.get(GenconfUtils.TEMPLATE_URI_OPTION)));
        instancies.put(queryEnvironment, instance);

        return ServiceUtils.getServices(queryEnvironment, instance);
    }

    @Override
    public void startGeneration(IReadOnlyQueryEnvironment queryEnvironment, XWPFDocument destinationDocument) {
        instancies.get(queryEnvironment).setDestinationDocument(destinationDocument);
    }

    @Override
    public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment, URIConverter uriConverter) {
        uriConverter.getURIHandlers().remove(httpHandler);
        instancies.remove(queryEnvironment);
    }

    @Override
    public ResourceSet createResourceSetForModels(Object context, Map<String, String> options) {
        // nothing to do here
        return null;
    }

    @Override
    public void cleanResourceSetForModels(Object context) {
        // nothing to do here
    }

}
