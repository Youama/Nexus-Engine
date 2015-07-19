package com.youama.nexus.parser.collector;

import com.youama.nexus.parser.HTMLBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an abstract implementation of the ICollector interface. This class implements the common logic of the
 * Collector classes.
 *
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public abstract class Collector implements ICollector {

    /**
     * HTML Document model. It set by the constructor.
     */
    protected Document DOM;

    /**
     * The parsed, collected data in a list of objects. This is the result of the parsing.
     */
    protected List<CollectedItem> parsedItems = new ArrayList<CollectedItem>();

    /**
     * It makes the DOM from the source and sets it to the property.
     *
     * @param textBasedHTMLSource Text based HTML source code.
     */
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

    /**
     * It resets the results of the parsing in the property.
     */
    protected void resetParsedItems() {
        parsedItems.removeAll(parsedItems);
    }

    /**
     * It collects the values of HTML attributes by the tag name and the attribute name. It resets the previously parsed
     * data before starts new parsing. If the new parsing does not have results the property will be empty.
     *
     * @param tagName The name of the HTML tag.
     * @param attributeKeyName The name of the attribute in the HTML tag.
     */
    protected void collectAttributeValueBy(String tagName, String attributeKeyName) {
        resetParsedItems();
        Elements elements = DOM.getElementsByTag(tagName);
        CollectedItem item = new CollectedItem();

        for (Element element : elements) {
            item.addItem(element.attr(attributeKeyName));
        }
        if (item.hasData()) parsedItems.add(item);
    }

    /**
     * It collects the values of HTML attributes by the tag name, attribute name and by filters for attribute name and
     * attribute value. The filter parameters are like 'WHERE' conditions. The first element in the attributeKeyFilters
     * has relation to the first element in the attributeValueFilters. When one of the elements in attributeValueFilters
     * is an empty String but the element with same index in the attributeKeyFilters parameter has a value then it will
     * filters for all HTML tags what have the filtered/given attribute name and it will not be depended on the value of
     * the attribute - and vice versa. So the filter can check full sameness by AND condition or can ignore them by
     * empty String. It resets the previously parsed data before starts new parsing. If the new parsing does not have
     * results the property will be empty.
     *
     * @param tagName The name of the HTML tag.
     * @param attributeKeyName The name of the attribute in the HTML tag.
     * @param attributeKeyFilters List of Strings for find by attribute names.
     * @param attributeValueFilters List of Strings for find by attribute values.
     */
    protected void collectAttributeValueBy(String tagName, String attributeKeyName,
                                                          List<String> attributeKeyFilters,
                                                          List<String> attributeValueFilters) {

        resetParsedItems();
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
