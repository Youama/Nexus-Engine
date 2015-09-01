package com.youama.nexus.parser.collector;

import com.youama.nexus.core.item.BasicItem;
import com.youama.nexus.core.validator.URLSyntaxValidator;

import java.util.*;

/**
 * This class implements the BaseCollector and Collector. LinkCollector collects links by different rules. This class
 * should not be referenced directly, it is handled by the FactoryCollector class.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.07.11.
 */
public class LinkCollector extends BaseCollector implements Collector {

    /**
     * It gives the source to the parent constructor to make the DOM.
     *
     * @param textBasedHTMLSource A regular text based HTML code as a String.
     */
    public LinkCollector(String textBasedHTMLSource) {
        super(textBasedHTMLSource);
    }

    /**
     * It parsed the DOM. The rule will be the default LINK_ALL.
     */
    public void parse() {
        parseByRule(CollectorSelector.LINK_ALL);
    }

    /**
     * It parses the DOM by rule ID. It collects urls and it sets them to the property - in the parent class.
     *
     * @param rule The ID of the rule.
     */
    public void parseByRule(CollectorSelector rule) {
        switch (rule) {
            case LINK_ALL:
                collectAttributeValueBy("a", "href");
                break;
            case LINK_NO_FOLLOW:
                List<String> attributeKeyFilters = new ArrayList<String>();
                List<String> attributeValueFilters = new ArrayList<String>();
                attributeKeyFilters.add("rel");
                attributeValueFilters.add("nofollow");
                collectAttributeValueBy("a", "href", attributeKeyFilters, attributeValueFilters);
                break;
            case LINK_VALID:
                collectAttributeValueBy("a", "href");
                validateLinks(false);
                break;
            case LINK_VALID_NO_RESOURCES:
                collectAttributeValueBy("a", "href");
                validateLinks(true);
                break;
            default:
                collectAttributeValueBy("a", "href");
        }
    }

    /**
     * It removes the non-valid URL-s from the parsed results. It also removes all empty Strings.
     *
     * @param noResource When this is true, this method will removes the resource links from the parsed results.
     */
    protected void validateLinks(boolean noResource) {
        List<String> links = parsedItems.get(0).getData();
        int originalSize = links.size();
        Iterator<String> iterator = links.iterator();

        if (!noResource) {
            while (iterator.hasNext()) {
                if (!URLSyntaxValidator.isValidLink(iterator.next())) {
                    iterator.remove();
                }
            }
        } else {
            while (iterator.hasNext()) {
                String currentLink = iterator.next();
                if (!URLSyntaxValidator.isValidLink(currentLink)
                        || URLSyntaxValidator.isResourceUrl(currentLink)) {
                    iterator.remove();
                }
            }
        }

        links.removeAll(Arrays.asList("", null));

        if (originalSize != links.size()) {
            BasicItem linkCollection = new BasicItem();
            linkCollection.addItem(links);
            parsedItems.add(linkCollection);
        }
    }
}
