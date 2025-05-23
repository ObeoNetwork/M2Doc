/*******************************************************************************
 *  Copyright (c) 2016, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.services;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.m2doc.element.MBookmark;
import org.obeonetwork.m2doc.element.MBookmarkCustomTextRef;
import org.obeonetwork.m2doc.element.MBookmarkPageRef;
import org.obeonetwork.m2doc.element.MBookmarkSectionRef;
import org.obeonetwork.m2doc.element.MBookmarkTextRef;
import org.obeonetwork.m2doc.element.MHyperLink;
import org.obeonetwork.m2doc.element.impl.MBookmarkCustomTextRefImpl;
import org.obeonetwork.m2doc.element.impl.MBookmarkImpl;
import org.obeonetwork.m2doc.element.impl.MBookmarkPageRefImpl;
import org.obeonetwork.m2doc.element.impl.MBookmarkSectionRefImpl;
import org.obeonetwork.m2doc.element.impl.MBookmarkTextRefImpl;
import org.obeonetwork.m2doc.element.impl.MHyperLinkImpl;

//@formatter:off
@ServiceProvider(
  value = "Services available for links. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/linkServices)."
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
            @Example(expression = "'My website'.asLink('https://www.example.org')", result = "a link to https://www.example.org with the label My website"),
        }
    )
    // @formatter:on
    public MHyperLink asLink(String text, String url) {
        return asLink(text, url, null);
    }

    // @formatter:off
    @Documentation(
        value = "Converts a String to an hyperlink",
        params = {
            @Param(name = "text", value = "The label of the link"),
            @Param(name = "url", value = "The destination of the link"),
            @Param(name = "toolTip", value = "The tool tip of the link"),
        },
        result = "A link with the given label that point to the given url.",
        examples = {
            @Example(expression = "'My website'.asLink('https://www.example.org', 'My tool tip')", result = "a link to https://www.example.org with the label My website with a tool tip"),
        }
    )
    // @formatter:on
    public MHyperLink asLink(String text, String url, String toolTip) {
        final String localText;
        if (text != null) {
            localText = text;
        } else {
            localText = "";
        }
        final String localURL;
        if (url != null) {
            localURL = url;
        } else {
            localURL = "";
        }

        return new MHyperLinkImpl(localText, null, localURL, toolTip);
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
        return new MBookmarkImpl(text, id);
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
          @Example(expression = "'Artifact1'.asBookmarkRef('Art1')", result = "a bookmark reference with the ID 'Art1' the label 'Artifact1'"),
      }
    )
    // @formatter:on
    public MBookmarkCustomTextRef asBookmarkRef(String text, String id) {
        return asBookmarkRef(text, id, false);
    }

    // @formatter:off
    @Documentation(
      value = "Converts a String to a bookmark reference",
      params = {
          @Param(name = "text", value = "The label of the bookmark reference"),
          @Param(name = "id", value = "The ID of the bookmark reference"),
          @Param(name = "optional", value = "If true, this reference will only generate the text if the corresponding bookmark doesn't exists"),
      },
      result = "A bookmark reference with the given label and ID.",
      examples = {
          @Example(expression = "'Artifact1'.asBookmarkRef('Art1', true)", result = "a bookmark reference with the ID 'Art1' the label 'Artifact1' only if a bookmark with ID 'Art1' exists, otherwise only the label 'Artifact1'"),
      }
    )
    // @formatter:on
    public MBookmarkCustomTextRef asBookmarkRef(String text, String id, boolean optional) {
        return new MBookmarkCustomTextRefImpl(text, id, optional);
    }

    // @formatter:off
    @Documentation(
      value = "Inserts a page number bookmark reference",
      params = {
          @Param(name = "id", value = "The ID of the bookmark reference"),
      },
      result = "A bookmark reference with the page numder.",
      examples = {
          @Example(expression = "'Art1'.asBookmarkPageRef()", result = "a bookmark reference with the ID 'Art1' with the page number"),
      }
    )
    // @formatter:on
    public MBookmarkPageRef asBookmarkPageRef(String id) {
        return asBookmarkPageRef(id, false);
    }

    // @formatter:off
    @Documentation(
      value = "Inserts a page number bookmark reference",
      params = {
          @Param(name = "id", value = "The ID of the bookmark reference"),
          @Param(name = "optional", value = "If true, the reference is omitted if the corresponding bookmark doesn't exists"),
      },
      result = "A bookmark reference with the page numder.",
      examples = {
          @Example(expression = "'Art1'.asBookmarkPageRef(true)", result = "a bookmark reference with the ID 'Art1' with the page number only if a bookmark with ID 'Art1' exists"),
      }
    )
    // @formatter:on
    public MBookmarkPageRef asBookmarkPageRef(String id, boolean optional) {
        return new MBookmarkPageRefImpl(id, optional);
    }

    // @formatter:off
    @Documentation(
      value = "Inserts a section bookmark reference",
      params = {
          @Param(name = "id", value = "The ID of the bookmark reference"),
      },
      result = "A bookmark reference with the section.",
      examples = {
          @Example(expression = "'Art1'.asBookmarkSectionRef(true)", result = "a bookmark reference with the ID 'Art1' with the section number"),
      }
    )
    // @formatter:on
    public MBookmarkSectionRef asBookmarkSectionRef(String id) {
        return asBookmarkSectionRef(id, false);
    }

    // @formatter:off
    @Documentation(
      value = "Inserts a section bookmark reference",
      params = {
          @Param(name = "id", value = "The ID of the bookmark reference"),
          @Param(name = "optional", value = "If true, the reference is omitted if the corresponding bookmark doesn't exists"),
      },
      result = "A bookmark reference with the section.",
      examples = {
          @Example(expression = "'Art1'.asBookmarkPageRef(true)", result = "a bookmark reference with the ID 'Art1' with the section number"),
      }
    )
    // @formatter:on
    public MBookmarkSectionRef asBookmarkSectionRef(String id, boolean optional) {
        return new MBookmarkSectionRefImpl(id, optional);
    }

    // @formatter:off
    @Documentation(
      value = "Inserts a text bookmark reference",
      params = {
          @Param(name = "id", value = "The ID of the bookmark reference"),
      },
      result = "A bookmark reference with the bookmark text.",
      examples = {
          @Example(expression = "'Art1'.asBookmarkPageRef()", result = "a bookmark reference with the ID 'Art1' with the bookmark text"),
      }
    )
    // @formatter:on
    public MBookmarkTextRef asBookmarkTextRef(String id) {
        return asBookmarkTextRef(id, false);
    }

    // @formatter:off
    @Documentation(
      value = "Inserts a text bookmark reference",
      params = {
          @Param(name = "id", value = "The ID of the bookmark reference"),
          @Param(name = "optional", value = "If true, the reference is omitted if the corresponding bookmark doesn't exists"),
      },
      result = "A bookmark reference with the bookmark text.",
      examples = {
          @Example(expression = "'Art1'.asBookmarkPageRef(true)", result = "a bookmark reference with the ID 'Art1' with the bookmark text only if a bookmark with ID 'Art1' exists"),
      }
    )
    // @formatter:on
    public MBookmarkTextRef asBookmarkTextRef(String id, boolean optional) {
        return new MBookmarkTextRefImpl(id, optional);
    }

}
