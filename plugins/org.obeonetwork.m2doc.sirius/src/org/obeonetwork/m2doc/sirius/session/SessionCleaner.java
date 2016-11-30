package org.obeonetwork.m2doc.sirius.session;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.generator.TemplateGenerator;
import org.obeonetwork.m2doc.properties.TemplateInfo;
import org.obeonetwork.m2doc.provider.configuration.IConfigurationProvider;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * The {@link SessionCleaner} is used to clean up the Sirius Session after M2Doc generation finishes.
 * The representations that were created during generation must be destroyed so as to leave the aird unchanged.
 * 
 * @author Romain Guider
 */
public class SessionCleaner implements IConfigurationProvider {

    @Override
    public void postCreateConfigurationModel(TemplateInfo templateInfo, IFile templateFile, Generation generation) {
        // unused.

    }

    @Override
    public void preCreateConfigurationModel(TemplateInfo templateInfo, IFile templateFile) {
        // unused.
    }

    @Override
    public boolean postValidateTemplate(IFile templateFile, DocumentTemplate template, Generation generation,
            TemplateGenerator generator) {
        // unused.
        return false;
    }

    @Override
    public void preValidateTemplate(IFile templateFile, DocumentTemplate template, Generation generation) {
        // unused.
    }

    @Override
    public void preGenerate(Generation generation, IProject project, IFile templateFile, IFile generatedFile) {
        // unused.
    }

    @Override
    public List<IFile> postGenerate(Generation generation, IProject project, IFile templateFile, IFile generatedFile,
            DocumentTemplate template, DocumentGenerator generator) {
        CleaningJobRegistry.INSTANCE.clean(generation);
        return Collections.emptyList();
    }

}
