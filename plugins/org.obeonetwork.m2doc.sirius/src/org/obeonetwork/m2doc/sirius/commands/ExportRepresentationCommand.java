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
package org.obeonetwork.m2doc.sirius.commands;

import com.google.common.collect.Lists;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Export representation: copy the representation and activate/desactivate configurated layers.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ExportRepresentationCommand extends RecordingCommand {

    /**
     * Progress Monitor.
     */
    private static final NullProgressMonitor MONITOR = new NullProgressMonitor();
    /**
     * Copy constant.
     */
    private static final String SUFFIXE_COPY = "_copy";
    /**
     * Activated layers.
     */
    private List<Layer> layers;
    /**
     * Sirius representation.
     */
    private DRepresentation representation;
    /**
     * Exported diagram.
     */
    private DDiagram exportedDiagram;
    /**
     * Sirius Session.
     */
    private Session session;
    /**
     * TransactionalEditingDomain.
     */
    private TransactionalEditingDomain editingDomain;
    /**
     * Is representation opened.
     */
    private boolean isRepresentationOpened;
    /**
     * Refresh Representations.
     */
    private boolean refreshRepresentations;

    /**
     * Export diagram.
     * 
     * @param domain
     *            TransactionalEditingDomain
     * @param layers
     *            List<Layer> to activate
     * @param diagram
     *            DDiagram to export
     * @param session
     *            Session
     * @param isDiagramOpened
     *            boolean
     * @param refreshRepresentations
     *            refresh Representations
     */
    public ExportRepresentationCommand(TransactionalEditingDomain domain, List<Layer> layers, DDiagram diagram,
            Session session, boolean isDiagramOpened, boolean refreshRepresentations) {
        super(domain);
        this.editingDomain = domain;
        this.layers = layers;
        this.representation = diagram;
        this.session = session;
        this.isRepresentationOpened = isDiagramOpened;
        this.refreshRepresentations = refreshRepresentations;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if (this.layers.isEmpty() && this.representation instanceof DDiagram) {
            this.exportedDiagram = (DDiagram) this.representation;
            if (!isRepresentationOpened && refreshRepresentations) {
                // Force a refresh of the representation
                DialectManager.INSTANCE.refresh(exportedDiagram, new NullProgressMonitor());
            }
        } else {
            // In this case, we are in layers mode and we refresh whatever refreshRepresentations status
            CompoundCommand compoundCmd = new CompoundCommand();
            // copy representation
            this.exportedDiagram = (DDiagram) DialectManager.INSTANCE.copyRepresentation(this.representation,
                    this.representation.getName() + SUFFIXE_COPY, session, MONITOR);
            // activate layers list.
            compoundCmd.append(activateLayers(this.exportedDiagram));
            session.getTransactionalEditingDomain().getCommandStack().execute(compoundCmd);
        }
    }

    /**
     * Activate layers list.
     * 
     * @param diagram
     *            DDiagram
     * @return Command
     */
    protected Command activateLayers(DDiagram diagram) {
        CompoundCommand compoundCmd = new CompoundCommand();
        // deactivate current activated layers
        for (Layer layer : Lists.newArrayList(diagram.getActivatedLayers())) {
            ChangeLayerActivationCommand cmd = new ChangeLayerActivationCommand(this.editingDomain, diagram, layer,
                    MONITOR);
            compoundCmd.append(cmd);
        }

        // activate layers list.
        for (Layer layer : layers) {
            ChangeLayerActivationCommand cmd = new ChangeLayerActivationCommand(this.editingDomain, diagram, layer,
                    MONITOR);
            compoundCmd.append(cmd);
        }
        return compoundCmd;
    }

    /**
     * Return exported DDiagram.
     * 
     * @return exported DDiagram
     */
    public DDiagram getExportedDiagram() {
        return this.exportedDiagram;
    }
}
