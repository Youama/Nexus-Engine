package com.youama.nexus.parser.collector;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.01.
 */
public class TextCollector extends Collector implements ICollector {

    /**
     * It sets the html source the property.
     *
     * @param textBasedHTMLSource A regular text based HTML code as a String.
     */
    public TextCollector(String textBasedHTMLSource) {
        super(textBasedHTMLSource);
    }

    public void parse() {
        parseByRule(HelperCollector.TEXT_ALL);
    }

    public void parseByRule(int rule) {
        switch (rule) {
            case HelperCollector.TEXT_ALL:
                collectAllText();
                break;
            case HelperCollector.TEXT_BODY:
                collectBodyText();
        }
    }

    protected void collectAllText() {
        resetParsedItems();
        Elements elements = DOM.getElementsByTag("body");

        if (elements != null) {
            String body = elements.get(0).toString();
            String fullText = Jsoup.parse(body).text();
            System.out.println(fullText);
        }
    }

    protected void collectBodyText() {
        resetParsedItems();
        Elements elements = DOM.getElementsByTag("body");

        if (elements != null) {
            String body = elements.get(0).toString();
            String fullText = Jsoup.parse(body).text();
            System.out.println(fullText);
        }
    }
}
