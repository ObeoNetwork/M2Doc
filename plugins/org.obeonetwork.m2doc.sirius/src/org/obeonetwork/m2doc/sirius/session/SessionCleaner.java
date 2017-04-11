package org.obeonetwork.m2doc.sirius.session;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.provider.IConfigurationProvider;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * The {@link SessionCleaner} is used to clean up the Sirius Session after M2Doc generation finishes.
 * The representations that were created during generation must be destroyed so as to leave the aird unchanged.
 * 
 * @author Romain Guider
 */
public class SessionCleaner implements IConfigurationProvider {

    @Override
    public void postCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateURI, Generation generation) {
        // unused.

    }

    @Override
    public void preCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateURI) {
        // unused.
    }

    @Override
    public boolean postValidateTemplate(URI templateURI, DocumentTemplate template, Generation generation) {
        // unused.
        return false;
    }

    @Override
    public void preValidateTemplate(URI templateURI, DocumentTemplate template, Generation generation) {
        // unused.
    }

    @Override
    public void preGenerate(Generation generation, URI templateURI, URI generatedURI) {
        // unused.
    }

    @Override
    public List<URI> postGenerate(Generation generation, URI templateURI, URI generatedURI,
            DocumentTemplate template) {
        CleaningJobRegistry.INSTANCE.clean(generation);
        return Collections.emptyList();
    }

    @Override
    public ResourceSet createResourceSetForModels(Generation generation) {
        // unused
        return null;
    }

}
