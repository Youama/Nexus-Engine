package com.youama.nexus.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * It builds DOM from String.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.07.12.
 */
public class HTMLBuilder {

    /**
     * Define the standard UTF-8 character encoding.
     */
    public final static String UTF8 = "UTF-8";

    /**
     * It retrieves the HTML DOM from a String what is originally was a HTML source code.
     *
     * @param textBasedHtml Text based HTML as String.
     * @return The DOM.
     */
    public static Document getDOM(String textBasedHtml) {
        return Jsoup.parse(textBasedHtml, UTF8);
    }

    /**
     * It retrieves the HTML DOM from a String what is originally was a HTML source code.
     *
     * @param textBasedHtml Text based HTML as String.
     * @param characterCoding Defined in the property as constant.
     * @return The DOM.
     */
    public static Document getDOM(String textBasedHtml, String characterCoding) {
        return Jsoup.parse(textBasedHtml, characterCoding);
    }
}
