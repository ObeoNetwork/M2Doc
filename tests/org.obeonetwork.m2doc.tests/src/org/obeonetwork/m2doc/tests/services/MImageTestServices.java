/*******************************************************************************
 *  Copyright (c) 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests.services;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.impl.MImageImpl;
import org.obeonetwork.m2doc.element.impl.MListImpl;
import org.obeonetwork.m2doc.element.impl.MParagraphImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;

/**
 * Test services for {@link MImage}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MImageTestServices {

    public MElement getMElements(Object object) {
        final MList res = new MListImpl();

        final MParagraphImpl paragraph1 = new MParagraphImpl(new MTextImpl("Some text", null), null);
        res.add(paragraph1);
        final MImage image = new MImageImpl(URIConverter.INSTANCE, URI.createURI("resources/dh1.gif"));
        final MParagraphImpl paragraph2 = new MParagraphImpl(image, null);
        res.add(paragraph2);
        final MParagraphImpl paragraph3 = new MParagraphImpl(new MTextImpl("More text", null), null);
        res.add(paragraph3);

        return res;
    }

}
