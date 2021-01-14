/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.wikitext.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.mylyn.wikitext.asciidoc.AsciiDocLanguage;
import org.eclipse.mylyn.wikitext.confluence.ConfluenceLanguage;
import org.eclipse.mylyn.wikitext.markdown.MarkdownLanguage;
import org.eclipse.mylyn.wikitext.mediawiki.MediaWikiLanguage;
import org.eclipse.mylyn.wikitext.parser.MarkupParser;
import org.eclipse.mylyn.wikitext.textile.TextileLanguage;
import org.eclipse.mylyn.wikitext.tracwiki.TracWikiLanguage;
import org.eclipse.mylyn.wikitext.twiki.TWikiLanguage;
import org.obeonetwork.m2doc.element.MElement;

//@formatter:off
@ServiceProvider(
value = "Services available for WikiText insertion."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class M2DocWikiTextServices {

    /**
     * The URI converter to use.
     */
    private final URIConverter uriConverter;

    /**
     * The template {@link URI}.
     */
    private final URI templateURI;

    /**
     * The {@link M2DocMElementBuilder}.
     */
    private M2DocMElementBuilder builder;

    /**
     * The asciidoc parser.
     */
    private final MarkupParser asciiDocParser = new MarkupParser(new AsciiDocLanguage());

    /**
     * The confluence parser.
     */
    private final MarkupParser confluenceParser = new MarkupParser(new ConfluenceLanguage());

    /**
     * The markdown parser.
     */
    private final MarkupParser markdownParser = new MarkupParser(new MarkdownLanguage());

    /**
     * The mediawiki parser.
     */
    private final MarkupParser mediaWikiParser = new MarkupParser(new MediaWikiLanguage());

    /**
     * The textile parser.
     */
    private final MarkupParser textileParser = new MarkupParser(new TextileLanguage());

    /**
     * The tracwiki parser.
     */
    private final MarkupParser tracWikiParser = new MarkupParser(new TracWikiLanguage());

    /**
     * The twiki parser.
     */
    private final MarkupParser tWikiParser = new MarkupParser(new TWikiLanguage());

    /**
     * Constructor.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            the template {@link URI}
     */
    public M2DocWikiTextServices(URIConverter uriConverter, URI templateURI) {
        this.uriConverter = uriConverter;
        this.templateURI = templateURI;
    }

    /**
     * Parses the given markup contents with the given {@link MarkupParser}.
     * 
     * @param parser
     *            the {@link MarkupParser}
     * @param baseURI
     *            the base {@link URI}
     * @param markupContents
     *            the markup contents
     * @return the {@link List} of parsed {@link MElement}
     */
    protected List<MElement> parse(MarkupParser parser, URI baseURI, String markupContents) {
        builder.setBaseURI(baseURI);
        parser.parse(markupContents);

        return builder.getResult();
    }

    /**
     * Parses the given markup contents with at the given {@link URI}.
     * 
     * @param parser
     *            the {@link MarkupParser}
     * @param baseURI
     *            the base {@link URI}
     * @param srcURI
     *            the source {@link URI}
     * @return the {@link List} of parsed {@link MElement}
     * @throws IOException
     *             if the source {@link URI} can't be read
     */
    protected List<MElement> parse(MarkupParser parser, URI baseURI, URI markupURI) throws IOException {
        builder.setBaseURI(baseURI);
        final URI uri = markupURI.resolve(templateURI);

        try (InputStream input = uriConverter.createInputStream(uri);) {
            final String markupContents = new String(IOUtils.toByteArray(input));
            parser.parse(markupContents);
        }

        return builder.getResult();
    }

    /**
     * Sets the destination {@link XWPFDocument}.
     * 
     * @param destinationDocument
     *            the destination {@link XWPFDocument}
     */
    public void setDestinationDocument(XWPFDocument destinationDocument) {
        this.builder = new M2DocMElementBuilder(uriConverter, destinationDocument);
        asciiDocParser.setBuilder(builder);
        confluenceParser.setBuilder(builder);
        markdownParser.setBuilder(builder);
        mediaWikiParser.setBuilder(builder);
        textileParser.setBuilder(builder);
        tracWikiParser.setBuilder(builder);
        tWikiParser.setBuilder(builder);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"http://asciidoc.org/\">AsciiDoc</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'The First Chapter\\n-----------------'.fromAsciiDocString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"http://asciidoc.org/\">AsciiDoc</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromAsciiDocString(String markupContent) {
        return parse(asciiDocParser, templateURI, markupContent);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"http://asciidoc.org/\">AsciiDoc</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
            @Param(name = "baseURI", value = "The base URI to use for link and images resolving.")
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'The First Chapter\\n-----------------'.fromAsciiDocString('https://www.m2doc.org/')",
                result = "The Sequence of MElement corresponding to the given <a href=\"http://asciidoc.org/\">AsciiDoc</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromAsciiDocString(String markupContents, String baseURI) {
        return parse(asciiDocParser, URI.createURI(baseURI, false), markupContents);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"http://asciidoc.org/\">AsciiDoc</a> contents at the given URI.",
        params = {
            @Param(name = "srcURI", value = "The markup contents URI."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'contents.txt'.fromAsciiDocString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"http://asciidoc.org/\">AsciiDoc</a> contents at the given URI."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromAsciiDocURI(String srcURI) throws IOException {
        return parse(asciiDocParser, templateURI, URI.createURI(srcURI, false));
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html\">Confluence</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'h1. The First Chapter'.fromConfluenceString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html\">Confluence</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromConfluenceString(String markupContent) {
        return parse(confluenceParser, templateURI, markupContent);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html\">Confluence</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
            @Param(name = "baseURI", value = "The base URI to use for link and images resolving.")
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'h1. The First Chapter'.fromConfluenceString('https://www.m2doc.org/')",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html\">Confluence</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromConfluenceString(String markupContents, String baseURI) {
        return parse(confluenceParser, URI.createURI(baseURI, false), markupContents);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html\">Confluence</a> contents at the given URI.",
        params = {
            @Param(name = "srcURI", value = "The markup contents URI."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'contents.txt'.fromConfluenceString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://confluence.atlassian.com/doc/confluence-wiki-markup-251003035.html\">Confluence</a> contents at the given URI."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromConfluenceURI(String srcURI) throws IOException {
        return parse(confluenceParser, templateURI, URI.createURI(srcURI, false));
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://en.wikipedia.org/wiki/Markdown\">Markdown</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'The First Chapter\\n================='.fromMarkdownString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://en.wikipedia.org/wiki/Markdown\">Markdown</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromMarkdownString(String markupContent) {
        return parse(markdownParser, templateURI, markupContent);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://en.wikipedia.org/wiki/Markdown\">Markdown</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
            @Param(name = "baseURI", value = "The base URI to use for link and images resolving.")
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'The First Chapter\\n================='.fromMarkdownString('https://www.m2doc.org/')",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://en.wikipedia.org/wiki/Markdown\">Markdown</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromMarkdownString(String markupContents, String baseURI) {
        return parse(markdownParser, URI.createURI(baseURI, false), markupContents);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://en.wikipedia.org/wiki/Markdown\">Markdown</a> contents at the given URI.",
        params = {
            @Param(name = "srcURI", value = "The markup contents URI."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'contents.txt'.fromMarkdownString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://en.wikipedia.org/wiki/Markdown\">Markdown</a> contents at the given URI."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromMarkdownURI(String srcURI) throws IOException {
        return parse(markdownParser, templateURI, URI.createURI(srcURI, false));
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://www.mediawiki.org/wiki/Help:Formatting\">MediaWiki</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'== The First Chapter =='.fromMediaWikiString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://www.mediawiki.org/wiki/Help:Formatting\">MediaWiki</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromMediaWikiString(String markupContent) {
        return parse(mediaWikiParser, templateURI, markupContent);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://www.mediawiki.org/wiki/Help:Formatting\">MediaWiki</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
            @Param(name = "baseURI", value = "The base URI to use for link and images resolving.")
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'== The First Chapter =='.fromMediaWikiString('https://www.m2doc.org/')",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://www.mediawiki.org/wiki/Help:Formatting\">MediaWiki</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromMediaWikiString(String markupContents, String baseURI) {
        return parse(mediaWikiParser, URI.createURI(baseURI, false), markupContents);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://www.mediawiki.org/wiki/Help:Formatting\">MediaWiki</a> contents at the given URI.",
        params = {
            @Param(name = "srcURI", value = "The markup contents URI."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'contents.txt'.fromMediaWikiString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://www.mediawiki.org/wiki/Help:Formatting\">MediaWiki</a> contents at the given URI."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromMediaWikiURI(String srcURI) throws IOException {
        return parse(mediaWikiParser, templateURI, URI.createURI(srcURI, false));
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://textile-lang.com/\">Textile</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'h1. The First Chapter'.fromTextileString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://textile-lang.com/\">Textile</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromTextileString(String markupContent) {
        return parse(textileParser, templateURI, markupContent);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://textile-lang.com/\">Textile</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
            @Param(name = "baseURI", value = "The base URI to use for link and images resolving.")
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'h1. The First Chapter'.fromTextileString('https://www.m2doc.org/')",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://textile-lang.com/\">Textile</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromTextileString(String markupContents, String baseURI) {
        return parse(textileParser, URI.createURI(baseURI, false), markupContents);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://textile-lang.com/\">Textile</a> contents at the given URI.",
        params = {
            @Param(name = "srcURI", value = "The markup contents URI."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'contents.txt'.fromTextileString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://textile-lang.com/\">Textile</a> contents at the given URI."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromTextileURI(String srcURI) throws IOException {
        return parse(textileParser, templateURI, URI.createURI(srcURI, false));
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://trac.edgewall.org/wiki/WikiFormatting/\">TracWiki</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'= The First Chapter ='.fromTracWikiString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://trac.edgewall.org/wiki/WikiFormatting/\">TracWiki</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromTracWikiString(String markupContent) {
        return parse(tracWikiParser, templateURI, markupContent);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://trac.edgewall.org/wiki/WikiFormatting/\">TracWiki</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
            @Param(name = "baseURI", value = "The base URI to use for link and images resolving.")
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'= The First Chapter ='.fromTracWikiString('https://www.m2doc.org/')",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://trac.edgewall.org/wiki/WikiFormatting/\">TracWiki</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromTracWikiString(String markupContents, String baseURI) {
        return parse(tracWikiParser, URI.createURI(baseURI, false), markupContents);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://trac.edgewall.org/wiki/WikiFormatting/\">TracWiki</a> contents at the given URI.",
        params = {
            @Param(name = "srcURI", value = "The markup contents URI."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'contents.txt'.fromTracWikiString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://trac.edgewall.org/wiki/WikiFormatting/\">TracWiki</a> contents at the given URI."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromTracWikiURI(String srcURI) throws IOException {
        return parse(tracWikiParser, templateURI, URI.createURI(srcURI, false));
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/\">TWiki</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'---+ The First Chapter'.fromTWikiString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/\">TWiki</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromTWikiString(String markupContent) {
        return parse(tWikiParser, templateURI, markupContent);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/\">TWiki</a> String.",
        params = {
            @Param(name = "markupContents", value = "The markup contents."),
            @Param(name = "baseURI", value = "The base URI to use for link and images resolving.")
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'---+ The First Chapter'.fromTWikiString('https://www.m2doc.org/')",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/\">TWiki</a> String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromTWikiString(String markupContents, String baseURI) {
        return parse(tWikiParser, URI.createURI(baseURI, false), markupContents);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given <a href=\"https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/\">TWiki</a> contents at the given URI.",
        params = {
            @Param(name = "srcURI", value = "The markup contents URI."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'contents.txt'.fromTWikiString()",
                result = "The Sequence of MElement corresponding to the given <a href=\"https://twiki.org/cgi-bin/view/TWiki/TWikiDocumentation#TWiki%20Text%20Formatting/\">TWiki</a> contents at the given URI."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromTWikiURI(String srcURI) throws IOException {
        return parse(tWikiParser, templateURI, URI.createURI(srcURI, false));
    }

}
