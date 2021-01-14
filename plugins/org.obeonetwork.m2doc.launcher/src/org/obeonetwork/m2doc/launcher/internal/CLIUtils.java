/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.launcher.internal;

import java.io.PrintStream;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;

/**
 * Command line interface utilities.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public final class CLIUtils {

    /**
     * Resets the ANSI modifications.
     */
    public static final String RESET = "\u001B[0m";

    /**
     * Sets the ANSI color to black.
     */
    public static final String BLACK = "\u001B[30;40;1m";

    /**
     * Sets the ANSI color to red.
     */
    public static final String RED = "\u001B[31;40;1m";

    /**
     * Sets the ANSI color to green.
     */
    public static final String GREEN = "\u001B[32;40;1m";

    /**
     * Sets the ANSI color to yellow.
     */
    public static final String YELLOW = "\u001B[33;40;1m";

    /**
     * Sets the ANSI color to blue.
     */
    public static final String BLUE = "\u001B[34;40;1m";

    /**
     * Sets the ANSI color to purple.
     */
    public static final String PURPLE = "\u001B[35;40;1m";

    /**
     * Sets the ANSI color to cyan.
     */
    public static final String CYAN = "\u001B[36;40;1m";

    /**
     * Sets the ANSI color to white.
     */
    public static final String WHITE = "\u001B[37;40;1m";

    /**
     * The {@link CLIDecorator}.
     */
    private static CLIDecorator decorator;

    /**
     * Constructor.
     */
    private CLIUtils() {
        // nothing to do here
    }

    /**
     * Gets the {@link CLIDecorator} accroding to the current terminal.
     * 
     * @return the {@link CLIDecorator}
     */
    public static CLIDecorator getDecorator() {
        if (decorator == null) {
            String colorTerm = System.getenv().get("COLORTERM");
            String term = System.getenv().get("TERM");
            if (colorTerm != null || (term != null && term.contains("color"))) {
                decorator = new CLIDecorator();
            } else {
                decorator = new NoDecoration();
            }
        }
        return decorator;
    }

    /**
     * A {@link CLIDecorator} for color terminal.
     * 
     * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
     */
    public static class CLIDecorator {

        /**
         * Gets the red ANSI string of the given text.
         * 
         * @param txt
         *            the text
         * @return the red ANSI string of the given text
         */
        public String red(String txt) {
            return RED + txt + RESET;
        }

        /**
         * Gets the black ANSI string of the given text.
         * 
         * @param txt
         *            the text
         * @return the black ANSI string of the given text
         */
        public String black(String txt) {
            return BLACK + txt + RESET;
        }

        /**
         * Gets the green ANSI string of the given text.
         * 
         * @param txt
         *            the text
         * @return the green ANSI string of the given text
         */
        public String green(String txt) {
            return GREEN + txt + RESET;
        }

        /**
         * Gets the yellow ANSI string of the given text.
         * 
         * @param txt
         *            the text
         * @return the yellow ANSI string of the given text
         */
        public String yellow(String txt) {
            return YELLOW + txt + RESET;
        }

        /**
         * Gets the blue ANSI string of the given text.
         * 
         * @param txt
         *            the text
         * @return the blue ANSI string of the given text
         */
        public String blue(String txt) {
            return BLUE + txt + RESET;
        }

        /**
         * Gets the purple ANSI string of the given text.
         * 
         * @param txt
         *            the text
         * @return the purple ANSI string of the given text
         */
        public String purple(String txt) {
            return PURPLE + txt + RESET;
        }

        /**
         * Gets the cyan ANSI string of the given text.
         * 
         * @param txt
         *            the text
         * @return the cyan ANSI string of the given text
         */
        public String cyan(String txt) {
            return CYAN + txt + RESET;
        }

        /**
         * Gets the white ANSI string of the given text.
         * 
         * @param txt
         *            the text
         * @return the white ANSI string of the given text
         */
        public String white(String txt) {
            return WHITE + txt + RESET;
        }
    }

    /**
     * A {@link CLIDecorator} for non color terminal.
     * 
     * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
     */
    public static class NoDecoration extends CLIDecorator {

        @Override
        public String red(String txt) {
            return txt;
        }

        @Override
        public String black(String txt) {
            return txt;
        }

        @Override
        public String green(String txt) {
            return txt;
        }

        @Override
        public String yellow(String txt) {
            return txt;
        }

        @Override
        public String blue(String txt) {
            return txt;
        }

        @Override
        public String purple(String txt) {
            return txt;
        }

        @Override
        public String cyan(String txt) {
            return txt;
        }

        @Override
        public String white(String txt) {
            return txt;
        }
    }

    /**
     * A simple monitor that prints progress to a print stream.
     */
    public static class ColoredPrinting extends BasicMonitor {

        /**
         * The {@link PrintStream}.
         */
        protected PrintStream printStream;

        /**
         * Constructor.
         * 
         * @param printStream
         *            the {@link PrintStream}
         */
        public ColoredPrinting(PrintStream printStream) {
            this.printStream = printStream;
        }

        @Override
        public void beginTask(String name, int totalWork) {
            if (name != null && name.length() != 0) {
                printStream.println(getDecorator().blue(">>> " + name));
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
                printStream.println(getDecorator().cyan("  > " + name));
            }
        }

        @Override
        public void setBlocked(Diagnostic reason) {
            super.setBlocked(reason);
            printStream.println(getDecorator().red("#>  " + reason.getMessage()));
        }

        @Override
        public void clearBlocked() {
            printStream.println(getDecorator().green("=>  " + getBlockedReason().getMessage()));
            super.clearBlocked();
        }
    }

}
