/*******************************************************************************
 * Copyright (c) 2017, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.launcher.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.parser.DocumentParserException;

/**
 * Application class for the M2Doc Launcher. Parses the arguments and launch
 * user specified document generators..
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class M2DocLauncher implements IApplication {

    /**
     * Return code for the application in case of error.
     */
    private static final Integer APPLICATION_ERROR = Integer.valueOf(-1);

    /**
     * The error message while loading genconf.
     */
    private static final String ERROR_LOADING_GENCONF = "Error loading genconf: '%s' : %s.";

    /**
     * The error message while launching genconf.
     */
    private static final String ERROR_LAUNCHING_GENCONF = "Error launching genconf: '%s' : %s.";

    /**
     * The input models.
     */
    @Option(name = "-genconfs",
        usage = "Specify the genconf model to use as inputs of the generation. Relative paths might be used or absolute uris (file://,http:/ or platform:/plugins/  for instance.)",
        metaVar = "INPUT", handler = StringArrayOptionHandler.class)
    private String[] genconfs = new String[0];

    /**
     * Workspace location. This argument is here only to mimic the OSGi
     * applications common arguments so that they are displayed in usage.
     */
    @Option(name = "-data", usage = "Specify the folder which will keep the workspace.", metaVar = "FOLDER")
    private File dataFolder;

    /**
     * consoleLog. This argument is here only to mimic the OSGi applications
     * common arguments so that they are displayed in usage.
     */
    @Option(name = "-consoleLog", usage = "Log messages in the console.")
    private boolean consoleLog;

    @Override
    public Object start(IApplicationContext context) throws Exception {
        String[] args = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
        return new M2DocLauncher().doMain(args);
    }

    /**
     * Launcher logic.
     * 
     * @param args
     *            application parameters passed by the Equinox framework.
     * @return the return value of the application
     */
    public Object doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        boolean somethingWentWrong = false;
        try {
            System.out.println(CLIUtils.getDecorator()
                    .purple(" __          __  _                            _          __  __ ___     _            \n"
                        + " \\ \\        / / | |                          | |        |  \\/  |__ \\   | |           \n"
                        + "  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___  | |_ ___   | \\  / |  ) |__| | ___   ___ \n"
                        + "   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  | |\\/| | / // _` |/ _ \\ / __|\n"
                        + "    \\  /\\  /  __/ | (_| (_) | | | | | |  __/ | || (_) | | |  | |/ /| (_| | (_) | (__ \n"
                        + "     \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  |_|  |_|____\\__,_|\\___/ \\___|"));
            System.out.println(
                    CLIUtils.getDecorator().yellow("The command-line launcher to generate .docx from your models."));
            parser.parseArgument(args);
            System.out.println(CLIUtils.RESET);
            Collection<URI> genconfsURIs = validateArguments(parser);
            Collection<Generation> loadedGenConfs = new ArrayList<Generation>();

            ResourceSet s = new ResourceSetImpl();
            for (URI uri : genconfsURIs) {
                somethingWentWrong = loadGenerationConfigs(loadedGenConfs, s, uri) || somethingWentWrong;
            }

            final Monitor monitor = new CLIUtils.ColoredPrinting(System.out);
            monitor.beginTask("Generating .docx documents", loadedGenConfs.size());
            for (Generation generation : loadedGenConfs) {
                launchGenerationConfiguration(generation, monitor);
            }
        } catch (CmdLineException e) {
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();
            somethingWentWrong = true;
            // problem in the command line
            M2DocLauncherPlugin.INSTANCE
                    .log(new Status(IStatus.ERROR, M2DocLauncherPlugin.INSTANCE.getSymbolicName(), e.getMessage(), e));
        }

        if (somethingWentWrong) {
            return APPLICATION_ERROR;
        }
        return IApplication.EXIT_OK;

    }

    /**
     * Launches the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation} to launch
     * @param monitor
     *            the {@link Monitor}
     */
    private void launchGenerationConfiguration(Generation generation, final Monitor monitor) {
        final List<Exception> exceptions = new ArrayList<Exception>();
        final Map<String, String> options = GenconfUtils.getOptions(generation);
        final ResourceSet resourceSetForModel = AQLUtils.createResourceSetForModels(exceptions, generation,
                new ResourceSetImpl(), options);
        try {
            System.out.println("Input: " + generation.eResource().getURI());
            List<URI> generated = GenconfUtils.generate(generation, resourceSetForModel, options,
                    M2DocPlugin.getClassProvider(), monitor);
            for (URI uri : generated) {
                System.out.println("Output: " + uri.toString());
            }
            monitor.worked(1);

        } catch (DocumentGenerationException | IOException | DocumentParserException e) {
            final String message = String.format(ERROR_LAUNCHING_GENCONF, generation.eResource().getURI().toString(),
                    e.getMessage());
            M2DocLauncherPlugin.INSTANCE
                    .log(new Status(IStatus.ERROR, M2DocLauncherPlugin.INSTANCE.getSymbolicName(), message, e));
        } finally {
            AQLUtils.cleanResourceSetForModels(generation, resourceSetForModel);
        }
    }

    /**
     * Loads the {@link Generation} at the given {@link URI}.
     * 
     * @param loadedGenConfs
     *            the loaded {@link Generation}
     * @param resourceSet
     *            the {@link ResourceSet}
     * @param uri
     *            the {@link URI} to load from
     * @return <code>true</code> if something went wrong, <code>false</code>
     *         otherwise
     */
    private boolean loadGenerationConfigs(Collection<Generation> loadedGenConfs, ResourceSet resourceSet, URI uri) {
        boolean somethingWentWrong;

        if (resourceSet.getURIConverter().exists(uri, Collections.EMPTY_MAP)) {
            try {
                Resource r = resourceSet.getResource(uri, true);
                r.load(Collections.EMPTY_MAP);
                for (EObject eObj : r.getContents()) {
                    if (eObj instanceof Generation) {
                        loadedGenConfs.add((Generation) eObj);
                    }
                }
                somethingWentWrong = false;
                // CHECKSTYLE:OFF we want to report any error
            } catch (IOException | RuntimeException e) {
                somethingWentWrong = true;
                // CHECKSTYLE:ON
                final String message = String.format(ERROR_LOADING_GENCONF, uri.toString(), e.getMessage());
                M2DocLauncherPlugin.INSTANCE
                        .log(new Status(IStatus.ERROR, M2DocLauncherPlugin.INSTANCE.getSymbolicName(), message, e));
            }
        } else {
            final String message = String.format(ERROR_LOADING_GENCONF, uri.toString(),
                    "does not exist or is not accessible");
            M2DocLauncherPlugin.INSTANCE
                    .log(new Status(IStatus.ERROR, M2DocLauncherPlugin.INSTANCE.getSymbolicName(), message));
            somethingWentWrong = false;
        }
        return somethingWentWrong;
    }

    /**
     * Validate arguments which are mandatory only in some circumstances.
     * 
     * @param parser
     *            the command line parser.
     * @return the {@link Collection} of genconf {@link URI}
     * @throws CmdLineException
     *             if the arguments are not valid.
     */
    private Collection<URI> validateArguments(CmdLineParser parser) throws CmdLineException {
        Collection<URI> result = new ArrayList<URI>();
        /*
         * some arguments are required if one is missing or invalid throw a
         * CmdLineException
         */
        if (genconfs == null || genconfs.length == 0) {
            throw new CmdLineException(parser, "You must specify genconfs models.",
                    new IllegalArgumentException("You must specify genconfs models."));
        }
        for (String modelPath : genconfs) {

            URI rawURI = null;
            try {
                rawURI = URI.createURI(modelPath, true);
            } catch (IllegalArgumentException e) {
                /*
                 * the passed uri is not in the URI format and should be
                 * considered as a direct file denotation.
                 */
            }

            if (rawURI != null && !rawURI.hasAbsolutePath()) {
                rawURI = URI.createFileURI(modelPath);
            }
            result.add(rawURI);
        }

        return result;

    }

    @Override
    public void stop() {
        /*
         * nothing special to do when the application stops.
         */

    }

}
