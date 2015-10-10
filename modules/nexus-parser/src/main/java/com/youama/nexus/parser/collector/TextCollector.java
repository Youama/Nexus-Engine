package com.youama.nexus.parser.collector;

import com.youama.nexus.core.item.BasicItem;
import com.youama.nexus.core.text.WordUtil;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * This class implements the BaseCollector and Collector. TextCollector collects words by different rules. This class
 * should not be referenced directly, it is handled by the FactoryCollector class.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 0.1.0
 */
public class TextCollector extends BaseCollector implements Collector {

    /**
     * It sets the html source the property.
     *
     * @param textBasedHTMLSource A regular text based HTML code as a String.
     */
    public TextCollector(String textBasedHTMLSource) {
        super(textBasedHTMLSource);
    }

    /**
     * It collects the sentences and words by default value.
     */
    public void parse() {
        parseByRule(CollectorSelector.TEXT_ALL);
    }

    /**
     * It collects the sentences and words by the rule identifier.
     *
     * @param rule The ID of the rule.
     */
    public void parseByRule(CollectorSelector rule) {
        switch (rule) {
            case TEXT_ALL:
                collectText("html");
                break;
            case TEXT_BODY:
                collectText("body");
        }
    }

    /**
     * It selects the clear text from an HTML document by the name of the HTML tag then it splits the text to sentences
     * and words. When it finished it retrieves the separated, collected words.
     *
     * @param htmlTag The html tag as a String. "title" for example.
     */
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
