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
package org.obeonetwork.m2doc.word.addin.ide.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.Preferences;

/**
 * Add-in preferences.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AddInPreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * The scope.
     */
    public static final String SCOPE = "org.obeonetwork.m2doc.word.addin.ide.ui";

    /**
     * The host preference.
     */
    public static final String HOST_PREFERENCE = "host";

    /**
     * The port preference.
     */
    public static final String PORT_PREFERENCE = "port";

    /**
     * The started preference.
     */
    public static final String STARTED_PREFERENCE = "started";

    /**
     * The default host.
     */
    public static final String DEFAULT_HOST = "0.0.0.0";

    /**
     * The default port.
     */
    public static final int DEFAULT_PORT = 3000;

    @Override
    public void initializeDefaultPreferences() {
        final Preferences preferences = InstanceScope.INSTANCE.getNode(SCOPE);
        preferences.put(HOST_PREFERENCE, DEFAULT_HOST);
        preferences.put(PORT_PREFERENCE, String.valueOf(DEFAULT_PORT));
    }

}
