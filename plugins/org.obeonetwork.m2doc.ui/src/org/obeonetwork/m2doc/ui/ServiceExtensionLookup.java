package org.obeonetwork.m2doc.ui;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.obeonetwork.m2doc.service.DeclaredServicesListener;

public class ServiceExtensionLookup {

	public void lookupServiceExtension() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		registry.addListener(new DeclaredServicesListener());
	}

}
