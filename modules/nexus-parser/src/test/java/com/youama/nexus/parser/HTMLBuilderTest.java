package com.youama.nexus.parser;

import org.junit.*;
import static org.junit.Assert.*;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author David Belicza
 * @since 2015.07.12.
 */
public class HTMLBuilderTest {

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