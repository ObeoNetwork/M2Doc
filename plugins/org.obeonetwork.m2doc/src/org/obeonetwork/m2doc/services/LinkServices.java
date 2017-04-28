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
package org.obeonetwork.m2doc.services;

import com.google.common.base.Strings;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.m2doc.element.MBookmark;
import org.obeonetwork.m2doc.element.MHyperLink;
import org.obeonetwork.m2doc.element.impl.MBookmarkImpl;
import org.obeonetwork.m2doc.element.impl.MHyperLinkImpl;

//@formatter:off
@ServiceProvider(
  value = "Services available for links"
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class LinkServices {

    // @formatter:off
    @Documentation(
        value = "Converts a String to an hyperlink",
        params = {
            @Param(name = "text", value = "The label of the link"),
            @Param(name = "url", value = "The destination of the link"),
        },
        result = "A link with the given label that point to the given url.",
        examples = {
            @Example(expression = "'My website'.asLink('http://www.example.org')", result = "a link to http://www.example.org with the label My website"),
        }
    )
    // @formatter:on
    public MHyperLink asLink(String text, String url) {
        return new MHyperLinkImpl(Strings.nullToEmpty(text), Strings.nullToEmpty(url));
    }

    // @formatter:off
    @Documentation(
      value = "Converts a String to a bookmark declaration",
      params = {
          @Param(name = "text", value = "The label of the bookmark declaration"),
          @Param(name = "id", value = "The ID of the bookmark declaration"),
      },
      result = "A bookmark declaration with the given label and ID.",
      examples = {
          @Example(expression = "'Definition of Artifact1'.asBookmark('Art1')", result = "a bookmark with the ID 'Art1' the label 'Definition of Artifact1'"),
      }
    )
    // @formatter:on
    public MBookmark asBookmark(String text, String id) {
        return new MBookmarkImpl(text, id, false);
    }

    // @formatter:off
    @Documentation(
      value = "Converts a String to a bookmark reference",
      params = {
          @Param(name = "text", value = "The label of the bookmark reference"),
          @Param(name = "id", value = "The ID of the bookmark reference"),
      },
      result = "A bookmark reference with the given label and ID.",
      examples = {
          @Example(expression = "'Artifact1'.asBookmark('Art1')", result = "a bookmark reference with the ID 'Art1' the label 'Definition of Artifact1'"),
      }
    )
    // @formatter:on
    public MBookmark asBookmarkRef(String text, String id) {
        return new MBookmarkImpl(text, id, true);
    }

}
