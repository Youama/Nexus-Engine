package com.youama.nexus.parser.collector;

import com.youama.nexus.core.validator.URLValidator;

import java.util.*;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public class LinkCollector extends Collector implements ICollector {

    public LinkCollector(String textBasedHTMLSource) {
        super(textBasedHTMLSource);
    }

    public void parse() {
        parseByRule(HelperCollector.LINK_ALL);
    }

    public void parseByRule(int rule) {
        switch (rule) {
            case HelperCollector.LINK_ALL:
                collectAttributeValueBy("a", "href");
                break;
            case HelperCollector.LINK_NO_FOLLOW:
                List<String> attributeKeyFilters = new ArrayList<String>();
                List<String> attributeValueFilters = new ArrayList<String>();
                attributeKeyFilters.add("rel");
                attributeValueFilters.add("nofollow");
                collectAttributeValueBy("a", "href", attributeKeyFilters, attributeValueFilters);
                break;
            case HelperCollector.LINK_VALID:
                collectAttributeValueBy("a", "href");
                validateLinks();
                break;
            default:
                collectAttributeValueBy("a", "href");
        }
    }

    protected void validateLinks() {
        List<String> links = parsedItems.get(0).getData();
        List<String> validatedLinks = links;

        for (int i = 0; i < links.size(); i++) {
            if (!URLValidator.isValidLinkSyntax(links.get(i))) {
                validatedLinks.remove(i);
            }
        }

        validatedLinks.removeAll(Arrays.asList("", null));

        if (validatedLinks.size() != links.size()) {
            CollectedItem linkCollection = new CollectedItem();
            linkCollection.addItem(validatedLinks);
            parsedItems.add(linkCollection);
        }
    }
}
