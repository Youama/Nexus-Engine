package com.youama.nexus.parser.collector;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.12.
 */
public class LinkCollectorTest extends TestCase {

    public String stringHTMLSource;

    @Before
    public void setUp() {
        stringHTMLSource = "<a href=\"/1.html\">l1</a><p></p><a rel=\"nofollow\" href=\"#\">s</a>";
    }

    @Test
    public void testParse() {
        LinkCollector linkCollector = new LinkCollector(stringHTMLSource);
        linkCollector.parseByRule(HelperCollector.LINK_NO_FOLLOW);
        List<CollectedItem> items = linkCollector.getItems();
        System.out.println(items.get(0).getData());
    }
}