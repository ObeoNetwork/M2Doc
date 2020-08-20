/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;

/**
 * Listen to {@link Generation} added and removed.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenconfResourceListener implements IResourceChangeListener {

    /**
     * Mapping from generated document {@link URI} to its {@link Generation} {@link URI}.
     */
    private Map<URI, List<URI>> resultToGenerations = new HashMap<URI, List<URI>>();

    /**
     * Mapping from {@link Generation} {@link URI} to its generated document {@link URI}.
     */
    private Map<URI, URI> generationToResult = new HashMap<URI, URI>();

    /**
     * Mapping from template document {@link URI} to its {@link Generation} {@link URI}.
     */
    private Map<URI, List<URI>> templateToGenerations = new HashMap<URI, List<URI>>();

    /**
     * Mapping from {@link Generation} {@link URI} to template document {@link URI}.
     */
    private Map<URI, URI> generationToTemplate = new HashMap<URI, URI>();

    /**
     * Walks the given {@link IWorkspace} to register {@link Generation}.
     * 
     * @param workspace
     *            the {@link IWorkspace}
     */
    public void walkWorkspace(IWorkspace workspace) {
        walkContainer(workspace.getRoot());
    }

    /**
     * Walks the given {@link IContainer} to search for {@link Generation}.
     * 
     * @param container
     *            the {@link IContainer}
     */
    private void walkContainer(IContainer container) {
        try {
            for (IResource resource : container.members()) {
                if (resource instanceof IContainer) {
                    walkContainer((IContainer) resource);
                } else if (resource instanceof IFile) {
                    addResource(resource);
                }
            }
        } catch (CoreException e) {
            // nothing to do
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
     */
    public void resourceChanged(IResourceChangeEvent event) {
        if (event.getDelta() != null) {
            walkDelta(event.getDelta(), new HashMap<IPath, IResource>());
        }
    }

    /**
     * Walks the {@link IResourceDelta} tree.
     * 
     * @param delta
     *            the root {@link IResourceDelta}
     * @param movedResources
     *            mapping of moved {@link IResource}
     */
    private void walkDelta(IResourceDelta delta, HashMap<IPath, IResource> movedResources) {
        processDelta(delta, movedResources);
        for (IResourceDelta child : delta.getAffectedChildren()) {
            walkDelta(child, movedResources);
        }
    }

    /**
     * Processes the given {@link IResourceDelta} by
     * {@link WorkspaceListener#fireChange(fr.obeo.dsl.workspace.listener.change.IChange) firing}
     * {@link fr.obeo.dsl.workspace.listener.change.IChange IChange}.
     * 
     * @param delta
     *            the {@link IResourceDelta} to process
     * @param movedResources
     *            mapping of moved {@link IResource}
     */
    private void processDelta(IResourceDelta delta, HashMap<IPath, IResource> movedResources) {
        switch (delta.getKind()) {
            case IResourceDelta.ADDED:
                processAddedDelta(delta, movedResources);
                break;

            case IResourceDelta.REMOVED:
                processRemovedDelta(delta, movedResources);
                break;

            case IResourceDelta.CHANGED:
                processChangedDelta(delta);
                break;

            default:
                break;
        }
    }

    /**
     * Process {@link IResourceDelta} with {@link IResourceDelta#CHANGED changed}
     * {@link IResourceDelta#getKind() kind}.
     * 
     * @param delta
     *            the {@link IResourceDelta} with {@link IResourceDelta#CHANGED changed}
     *            {@link IResourceDelta#getKind() kind}
     */
    private void processChangedDelta(IResourceDelta delta) {
        if ((delta.getFlags() & IResourceDelta.OPEN) != 0) {
            if (delta.getResource().isAccessible()) {
                // opened
            } else {
                // closed
            }
        } else if ((delta.getFlags() & IResourceDelta.DESCRIPTION) != 0) {
            // description changed
        } else if ((delta.getFlags() & IResourceDelta.CONTENT) != 0) {
            changedResource(delta.getResource());
        }
    }

    /**
     * Process {@link IResourceDelta} with {@link IResourceDelta#REMOVED removed}
     * {@link IResourceDelta#getKind() kind}.
     * 
     * @param delta
     *            the {@link IResourceDelta} with {@link IResourceDelta#REMOVED removed}
     *            {@link IResourceDelta#getKind() kind}
     * @param movedResources
     *            mapping of moved {@link IResource}
     */
    private void processRemovedDelta(IResourceDelta delta, HashMap<IPath, IResource> movedResources) {
        if ((delta.getFlags() & IResourceDelta.MOVED_TO) != 0) {
            final IResource target = movedResources.get(delta.getMovedToPath());
            if (target != null) {
                moveResource(delta.getResource(), target);
            } else {
                movedResources.put(delta.getResource().getFullPath(), delta.getResource());
            }
        } else {
            removeResource(delta.getResource());
        }
    }

    /**
     * Process {@link IResourceDelta} with {@link IResourceDelta#ADDED added} {@link IResourceDelta#getKind()
     * kind}.
     * 
     * @param delta
     *            the {@link IResourceDelta} with {@link IResourceDelta#ADDED added}
     *            {@link IResourceDelta#getKind() kind}
     * @param movedResources
     *            mapping of moved {@link IResource}
     */
    private void processAddedDelta(IResourceDelta delta, HashMap<IPath, IResource> movedResources) {
        if ((delta.getFlags() & IResourceDelta.MOVED_FROM) != 0) {
            final IResource source = movedResources.get(delta.getMovedFromPath());
            if (source != null) {
                moveResource(source, delta.getResource());
            } else {
                movedResources.put(delta.getResource().getFullPath(), delta.getResource());
            }
        } else {
            addResource(delta.getResource());
        }
    }

    /**
     * Adds the given {@link IResource} if it's a {@link Generation}.
     * 
     * @param resource
     *            the {@link IResource}
     */
    private void addResource(IResource resource) {
        final Generation generation = getGeneration(resource);
        if (generation != null) {
            final URI resultURI;
            if (generation.getResultFileName() != null) {
                resultURI = GenconfUtils.getResolvedURI(generation,
                        URI.createURI(generation.getResultFileName(), false));
            } else {
                resultURI = null;
            }
            if (resultURI != null) {
                List<URI> generations = resultToGenerations.get(resultURI);
                if (generations == null) {
                    generations = new ArrayList<URI>();
                    resultToGenerations.put(resultURI, generations);
                }
                generations.add(generation.eResource().getURI());
                generationToResult.put(generation.eResource().getURI(), resultURI);
            }
            final URI templateURI;
            if (generation.getTemplateFileName() != null) {
                templateURI = GenconfUtils.getResolvedURI(generation,
                        URI.createURI(generation.getTemplateFileName(), false));
            } else {
                templateURI = null;
            }
            if (templateURI != null) {
                List<URI> generations = templateToGenerations.get(templateURI);
                if (generations == null) {
                    generations = new ArrayList<URI>();
                    templateToGenerations.put(templateURI, generations);
                }
                generations.add(generation.eResource().getURI());
                generationToTemplate.put(generation.eResource().getURI(), templateURI);
            }
        }
    }

    /**
     * Removes the given {@link IResource} if it's a {@link Generation}.
     * 
     * @param resource
     *            the {@link IResource}
     */
    private void removeResource(IResource resource) {
        final URI genconfURI = URI.createPlatformResourceURI(resource.getFullPath().toString(), true);
        final URI resultURI = generationToResult.remove(genconfURI);
        if (resultURI != null) {
            resultToGenerations.get(resultURI).remove(genconfURI);
        }
        final URI templateURI = generationToTemplate.remove(genconfURI);
        if (resultURI != null) {
            templateToGenerations.get(templateURI).remove(genconfURI);
        }
    }

    /**
     * Upates the given {@link IResource} if it's a {@link Generation}.
     * 
     * @param resource
     *            the {@link IResource}
     */
    private void changedResource(IResource resource) {
        removeResource(resource);
        addResource(resource);
    }

    /**
     * Moves the {@link IResource} if the source or the target is a {@link Generation}.
     * 
     * @param source
     *            the source {@link IResource}
     * @param target
     *            the target {@link IResource}
     */
    private void moveResource(IResource source, IResource target) {
        removeResource(source);
        addResource(target);
    }

    /**
     * Gets the {@link Generation} from the given {@link IResource}.
     * 
     * @param resource
     *            the {@link IResource}
     * @return the {@link Generation} from the given {@link IResource} if any, <code>null</code> otherwise
     */
    private Generation getGeneration(IResource resource) {
        final Generation res;

        if (GenconfUtils.GENCONF_EXTENSION_FILE.equals(resource.getLocation().getFileExtension())) {
            final URI genconfURI = URI.createPlatformResourceURI(resource.getFullPath().toString(), true);
            res = GenconfUtils.getGeneration(genconfURI);
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the {@link Generation} {@link URI} from the given result {@link URI}.
     * 
     * @param resultURI
     *            the result {@link URI}
     * @return the {@link Generation} {@link URI} from the given result {@link URI} if any, <code>null</code> otherwise
     */
    public List<URI> getGenconfURIsFromResult(URI resultURI) {
        return resultToGenerations.get(resultURI);
    }

    /**
     * Gets the {@link Generation} {@link URI} from the given template {@link URI}.
     * 
     * @param templateURI
     *            the template {@link URI}
     * @return the {@link Generation} {@link URI} from the given template {@link URI} if any, <code>null</code> otherwise
     */
    public List<URI> getGenconfURIsFromTempate(URI templateURI) {
        return templateToGenerations.get(templateURI);
    }

}
