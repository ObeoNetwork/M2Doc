package org.obeonetwork.m2doc.sirius.util;

import java.util.Map;

import org.obeonetwork.m2doc.provider.ProviderConstants;

/**
 * Option Util.
 * 
 * @author ohaegi
 */
public final class OptionUtil {

    /**
     * Constructor.
     */
    private OptionUtil() {
        // Prevent instantiation
    }

    /**
     * Extract Refresh Representation option.
     * 
     * @param options
     *            Option map
     * @return refresh Representation option
     */
    public static boolean mustRefreshRepresentation(Map<String, Object> options) {
        Object refreshRepresentations = options.get(ProviderConstants.REFRESH_REPRESENTATIONS_KEY);
        if (refreshRepresentations instanceof Boolean) {
            return ((Boolean) refreshRepresentations).booleanValue();
        }
        return false;
    }
}
