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
package org.obeonetwork.m2doc.tests.services;

import java.awt.Color;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.element.MBookmark;
import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.MPagination;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MText;
import org.obeonetwork.m2doc.element.impl.MBookmarkImpl;
import org.obeonetwork.m2doc.element.impl.MImageImpl;
import org.obeonetwork.m2doc.element.impl.MListImpl;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;

/**
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MListTestServices {

    /**
     * {@link List} of sample {@link MElement}.
     */
    private final static List<MElement> SAMPLE_MELEMENTS = initSampleMElements();

    private final static MStyle HEADER_STYLE = new MStyleImpl(null, 14, Color.GRAY, null,
            MStyle.FONT_BOLD | MStyle.FONT_UNDERLINE);

    /**
     * @return
     */
    private static MList initSampleMElements() {
        final MList res = new MListImpl();

        final MBookmark bookmark = new MBookmarkImpl("bookmark", "id", false);
        res.add(bookmark);
        res.add(MPagination.newParagraph);

        final MText text = new MTextImpl("some text",
                new MStyleImpl(null, 12, Color.ORANGE, null, MStyle.FONT_BOLD | MStyle.FONT_ITALIC));
        res.add(text);
        res.add(MPagination.newParagraph);

        final MBookmark bookmarkRef = new MBookmarkImpl("bookmarkRef", "id", true);
        res.add(bookmarkRef);
        res.add(MPagination.newParagraph);

        final MText text1 = new MTextImpl("some text1",
                new MStyleImpl(null, 12, Color.ORANGE, null, MStyle.FONT_BOLD | MStyle.FONT_ITALIC));
        res.add(text1);
        res.add(MPagination.newParagraph);

        final MImage image = new MImageImpl(URIConverter.INSTANCE,
                URI.createURI("resources/mList/sampleMList/dh1.gif"));
        image.setHeight(200);
        res.add(image);
        res.add(MPagination.newParagraph);

        final MText text2 = new MTextImpl("some text2",
                new MStyleImpl(null, 12, Color.ORANGE, null, MStyle.FONT_BOLD | MStyle.FONT_ITALIC));
        res.add(text2);

        final MTable table = new MTableTestServices().sampleTable("sample table");
        res.add(table);
        res.add(MPagination.newParagraph);

        final MText text3 = new MTextImpl("some text3",
                new MStyleImpl(null, 12, Color.ORANGE, null, MStyle.FONT_BOLD | MStyle.FONT_ITALIC));
        res.add(text3);
        res.add(MPagination.newParagraph);

        final MList list = new MListImpl();
        final MText textInList = new MTextImpl("text in a list",
                new MStyleImpl(null, 12, Color.GREEN, null, MStyle.FONT_BOLD));
        list.add(textInList);
        res.add(list);

        return res;
    }

    public MList emptyList(Object object) {
        return new MListImpl();
    }

    public MList sampleList(Object object) {
        final MList res = new MListImpl();

        for (MElement element : SAMPLE_MELEMENTS) {
            res.add(element);
        }

        return res;
    }

}
