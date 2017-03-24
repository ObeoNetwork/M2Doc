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

import java.io.PrintStream;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;

public class CLIUtils {

	private static CLIDecorator decorator;

	public static CLIDecorator getDecorator() {
		if (decorator == null) {
			String colorTerm = System.getenv().get("COLORTERM");
			if (colorTerm != null && "1".equals(colorTerm)) {
				decorator = new CLIDecorator();
			} else {
				decorator = new NoDecoration();
			}
		}
		return decorator;
	}

	public static class CLIDecorator {

		public String red(String txt) {
			return RED + txt + RESET;
		}

		public String black(String txt) {
			return BLACK + txt + RESET;
		}

		public String green(String txt) {
			return GREEN + txt + RESET;
		}

		public String yellow(String txt) {
			return YELLOW + txt + RESET;
		}

		public String blue(String txt) {
			return BLUE + txt + RESET;
		}

		public String purple(String txt) {
			return PURPLE + txt + RESET;
		}

		public String cyan(String txt) {
			return CYAN + txt + RESET;
		}

		public String white(String txt) {
			return WHITE + txt + RESET;
		}
	}

	public static class NoDecoration extends CLIDecorator {
		public String red(String txt) {
			return txt;
		}

		public String black(String txt) {
			return txt;
		}

		public String green(String txt) {
			return txt;
		}

		public String yellow(String txt) {
			return txt;
		}

		public String blue(String txt) {
			return txt;
		}

		public String purple(String txt) {
			return txt;
		}

		public String cyan(String txt) {
			return txt;
		}

		public String white(String txt) {
			return txt;
		}
	}

	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30;40;1m";
	public static final String RED = "\u001B[31;40;1m";
	public static final String GREEN = "\u001B[32;40;1m";
	public static final String YELLOW = "\u001B[33;40;1m";
	public static final String BLUE = "\u001B[34;40;1m";
	public static final String PURPLE = "\u001B[35;40;1m";
	public static final String CYAN = "\u001B[36;40;1m";
	public static final String WHITE = "\u001B[37;40;1m";

	/**
	 * A simple monitor that prints progress to a print stream.
	 */
	public static class ColoredPrinting extends BasicMonitor {
		protected PrintStream printStream;

		public ColoredPrinting(PrintStream printStream) {
			this.printStream = printStream;
		}

		@Override
		public void beginTask(String name, int totalWork) {
			if (name != null && name.length() != 0) {
				printStream.println(BLUE + ">>> " + name + WHITE);
			}
		}

		@Override
		public void setTaskName(String name) {
			if (name != null && name.length() != 0) {
				printStream.println("<>> " + name);
			}
		}

		@Override
		public void subTask(String name) {
			if (name != null && name.length() != 0) {
				printStream.println(CYAN + ">>  " + name + WHITE);
			}
		}

		@Override
		public void setBlocked(Diagnostic reason) {
			super.setBlocked(reason);
			printStream.println(RED + "#>  " + reason.getMessage() + WHITE);
		}

		@Override
		public void clearBlocked() {
			printStream.println(GREEN + "=>  " + getBlockedReason().getMessage() + WHITE);
			super.clearBlocked();
		}
	}

}
