/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.launcher.internal;

import java.io.File;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

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
	 * The input models.
	 */
	@Option(name = "-genconfs", usage = "Specify the genconf model to use as inputs of the generation. Relative paths might be used or absolute uris (file://,http:/ or platform:/plugins/  for instance.)", metaVar = "INPUT", handler = StringArrayOptionHandler.class)
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
			System.out.println(" __          __  _                            _          __  __ ___     _            \n"
					+ " \\ \\        / / | |                          | |        |  \\/  |__ \\   | |           \n"
					+ "  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___  | |_ ___   | \\  / |  ) |__| | ___   ___ \n"
					+ "   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  | |\\/| | / // _` |/ _ \\ / __|\n"
					+ "    \\  /\\  /  __/ | (_| (_) | | | | | |  __/ | || (_) | | |  | |/ /| (_| | (_) | (__ \n"
					+ "     \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  |_|  |_|____\\__,_|\\___/ \\___|");
			System.out.println("This is a command-line launcher to generate .docx from your models.");
			parser.parseArgument(args);
			
			System.out.println("<TODO> implementation to actually launch the generators (but that's the easy part).");

			validateArguments(parser);

			final Monitor monitor = new BasicMonitor.Printing(System.out);

		} catch (CmdLineException e) {
			/*
			 * problem in the command line
			 */
			M2DocLauncherPlugin.INSTANCE
					.log(new Status(IStatus.ERROR, M2DocLauncherPlugin.INSTANCE.getSymbolicName(), e.getMessage(), e));

			/*
			 * print the list of available options
			 */
			parser.printUsage(System.err);
			System.err.println();
			somethingWentWrong = true;
		}

		if (somethingWentWrong) {
			return APPLICATION_ERROR;
		}
		return IApplication.EXIT_OK;

	}

	/**
	 * Validate arguments which are mandatory only in some circumstances.
	 * 
	 * @param parser
	 *            the command line parser.
	 * 
	 * @throws CmdLineException
	 *             if the arguments are not valid.
	 */
	private void validateArguments(CmdLineParser parser) throws CmdLineException {
		/*
		 * some arguments are required if one is missing or invalid throw a
		 * CmdLineException
		 */
		if (genconfs == null || genconfs.length == 0) {
			throw new CmdLineException(parser, "You must specify genconfs models.");
		}

	}

	/**
	 * Log a message as an info.
	 * 
	 * @param message
	 *            the error message.
	 */
	private void info(String message) {
		M2DocLauncherPlugin.INSTANCE
				.log(new Status(IStatus.INFO, M2DocLauncherPlugin.INSTANCE.getSymbolicName(), message));
	}

	@Override
	public void stop() {
		/*
		 * nothing special to do when the application stops.
		 */

	}

}
