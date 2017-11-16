/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.cdo;

import java.io.Console;

import org.eclipse.net4j.util.security.IPasswordCredentials;
import org.eclipse.net4j.util.security.IPasswordCredentialsProvider;
import org.eclipse.net4j.util.security.PasswordCredentials;

/**
 * Prompt for login and password on the command line shell.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ShellCredentialsProvider implements IPasswordCredentialsProvider {

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public IPasswordCredentials getCredentials() {
        final Console console = System.console();
        final String login = console.readLine("login: ");
        final char[] pass = console.readPassword("password: ");

        return new PasswordCredentials(login, pass);
    }

}
