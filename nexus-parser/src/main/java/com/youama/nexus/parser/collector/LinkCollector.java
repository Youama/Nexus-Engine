package com.youama.nexus.parser.collector;

import java.util.ArrayList;
import java.util.List;

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
            default:
                collectAttributeValueBy("a", "href");
        }
    }
}
