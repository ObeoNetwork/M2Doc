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
package org.obeonetwork.m2doc.word.addin;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Backend server for the M2Doc add-in.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CompletionServer {

    /**
     * The server.
     */
    private Server server;

    /**
     * Starts the server.
     * 
     * @param host
     *            the host to listen
     * @param port
     *            the port to listen
     * @throws Exception
     *             if the server can't be started
     */
    public void start(String host, int port) throws Exception {
        server = new Server();

        @SuppressWarnings("resource")
        final ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setHost(host);
        server.setConnectors(new Connector[] {connector });

        final ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
        servletHandler.addServletWithMapping(AddInServlet.class, "/*");

        final HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {servletHandler, new DefaultHandler() });
        server.setHandler(handlers);

        server.start();
    }

    /**
     * Stops the server.
     * 
     * @throws Exception
     *             if the server can't be stopped
     */
    public void stop() throws Exception {
        server.stop();
    }

}
