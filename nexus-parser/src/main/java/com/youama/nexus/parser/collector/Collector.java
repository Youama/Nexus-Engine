package com.youama.nexus.parser.collector;

import com.youama.nexus.parser.HTMLBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public abstract class Collector implements ICollector {

    protected Document DOM;

    /**
     * The parsed, collected data in a list of objects.
     */
    protected List<CollectedItem> parsedItems = new ArrayList<CollectedItem>();

    public Collector(String textBasedHTMLSource) {
        DOM = HTMLBuilder.getDOM(textBasedHTMLSource);
    }

    /**
     * It retrieves the collected, parsed data in a list of objects.
     *
     * @return The value from the property.
     */
    public List<CollectedItem> getItems() {
        return parsedItems;
    }

    protected void collectAttributeValueBy(String tagName, String attributeKeyName) {
        Elements elements = DOM.getElementsByTag(tagName);
        CollectedItem item = new CollectedItem();

        for (Element element : elements) {
            item.addItem(element.attr(attributeKeyName));
        }
        if (item.hasData()) parsedItems.add(item);
    }

    protected void collectAttributeValueBy(String tagName, String attributeKeyName,
                                                          List<String> attributeKeyFilters,
                                                          List<String> attributeValueFilters) {

        if (attributeKeyFilters.size() == attributeValueFilters.size()) {
            Elements elements = DOM.getElementsByTag(tagName);
            CollectedItem item = new CollectedItem();

            for (Element element : elements) {
                boolean matchingByRule = true;
                for (int i = 0; i < attributeKeyFilters.size(); i++) {
                    String attributeKey = attributeKeyFilters.get(i);
                    String attributeValue = attributeValueFilters.get(i);
                    boolean attributeIsMatching = true;
                    boolean valueIsMatching = true;

                    if (attributeKey.length() > 0 && !element.hasAttr(attributeKey)) {
                        attributeIsMatching = false;
                    }

                    if (attributeValue.length() > 0 && !element.attr(attributeKey).toLowerCase()
                            .equals(attributeValue)) {
                        valueIsMatching = false;
                    }

                    if (!valueIsMatching || !attributeIsMatching) {
                        matchingByRule = false;
                        break;
                    }
                }

                if (matchingByRule) item.addItem(element.attr(attributeKeyName));
            }

            if (item.hasData()) parsedItems.add(item);
        }
    }
}
