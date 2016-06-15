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
package org.obeonetwork.m2doc.provider.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderException;

/**
 * A stub {@link AbstractDiagramProvider} used to test generic providers' options usage.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class StubDiagramProvider extends AbstractDiagramProvider {
    /**
     * The key of the option using an AQL expression.
     */
    public static final String AQL_EXPRESSION_OPTION_KEY = "aqlExpression";
    /**
     * When using this result king, the stub will return a list of 0 images paths.
     */
    public static final String ZERO_IMAGE_RESULT_KIND = "zeroImage";
    /**
     * When using this result king, the stub will return a list of one existing image path.
     */
    public static final String ONE_IMAGE_RESULT_KIND = "oneImage";
    /**
     * When using this result king, the stub will return a list of two existing image paths.
     */
    public static final String TWO_IMAGE_RESULT_KIND = "twoImage";
    /**
     * When using this result king, the stub will throw a {@link ProviderException}.
     */
    public static final String EXCEPTION_RESULT_KIND = "exception";
    /**
     * The parameter key used to define what this stub must return.
     */
    public static final String RESULT_KIND_KEY = "resultKind";

    /*
     * (non-Javadoc)
     * 
     * @see org.obeonetwork.m2doc.provider.IProvider#getOptionTypes()
     */
    @Override
    public Map<String, OptionType> getOptionTypes() {
        Map<String, OptionType> options = new HashMap<String, OptionType>();
        options.put(AQL_EXPRESSION_OPTION_KEY, OptionType.AQL_EXPRESSION);
        return options;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.obeonetwork.m2doc.provider.DiagramProvider#getRepresentationImagePath(java.util.Map)
     */
    @Override
    public List<String> getRepresentationImagePath(Map<String, Object> parameters) throws ProviderException {
        String resultKind = (String) parameters.get(RESULT_KIND_KEY);
        String aqlExpression = (String) parameters.get(AQL_EXPRESSION_OPTION_KEY);
        List<String> imagesPaths = new ArrayList<String>();
        if (aqlExpression != null && "testImage".equals(aqlExpression)) {
            imagesPaths.add(new File("images/testImage.jpg").getAbsolutePath());
        } else if (ONE_IMAGE_RESULT_KIND.equals(resultKind)) {
            imagesPaths.add(new File("images/dh1.gif").getAbsolutePath());
        } else if (TWO_IMAGE_RESULT_KIND.equals(resultKind)) {
            imagesPaths.add(new File("images/dh1.gif").getAbsolutePath());
            imagesPaths.add(new File("images/testImage.jpg").getAbsolutePath());
        } else if (EXCEPTION_RESULT_KIND.equals(resultKind)) {
            throw new ProviderException("A problem occured.");
        }
        // Default case 0 images.
        return imagesPaths;
    }

}
