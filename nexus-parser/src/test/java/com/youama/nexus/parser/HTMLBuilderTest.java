package com.youama.nexus.parser;

import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.12.
 */
public class HTMLBuilderTest extends TestCase {

    @Test
    public void testGetDOM() {
        Document doc = HTMLBuilder
                .getDOM("<a href=\"/okok.html\">test link</a><p></p><a href=\"/okok2.html\">test2 link</a>");
        Elements elements = doc.getAllElements();
        // Seven because of parent and the HTML, HEAD, etc built tags.
        assertEquals(7, elements.size());

        doc = HTMLBuilder.getDOM(
                "<a href=\"/okok.html\">test link</a><p></p><a href=\"/okok2.html\">test2 link</a>",
                HTMLBuilder.UTF8);
        elements = doc.getAllElements();
        // Seven because of parent and the HTML, HEAD, etc built tags.
        assertEquals(7, elements.size());
    }
}