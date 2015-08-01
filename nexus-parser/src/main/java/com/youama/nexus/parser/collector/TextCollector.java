package com.youama.nexus.parser.collector;

import com.youama.nexus.core.item.BasicItem;
import com.youama.nexus.core.text.WordUtil;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.List;

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
                collectText("html");
                break;
            case HelperCollector.TEXT_BODY:
                collectText("body");
        }
    }

    protected void collectText(String htmlTag) {
        resetParsedItems();
        Elements elements = DOM.getElementsByTag(htmlTag);

        if (elements != null) {
            String parentTag = elements.get(0).toString();
            List<BasicItem> sentences = WordUtil.getSentencesFromText(Jsoup.parse(parentTag).text());
            if (sentences != null) {
                parsedItems = sentences;
            }
        }
    }
}
