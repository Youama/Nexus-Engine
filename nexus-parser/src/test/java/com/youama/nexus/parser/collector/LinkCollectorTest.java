package com.youama.nexus.parser.collector;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.12.
 */
public class LinkCollectorTest extends TestCase {

    public String stringHTMLSource = "";

    @Before
    public void setUp() {
        try {
            String path = this.getClass().getResource("/simple1.html").getPath();
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            stringHTMLSource = new String(encoded, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            stringHTMLSource = "";
            e.printStackTrace();
        } catch (IOException e) {
            stringHTMLSource = "";
            e.printStackTrace();
        }
    }

    @Test
    public void testParse() {

        LinkCollector linkCollector = new LinkCollector(stringHTMLSource);
        List<CollectedItem> items;

        // All no-follow links
        linkCollector.parseByRule(HelperCollector.LINK_NO_FOLLOW);
        items = linkCollector.getItems();
        assertEquals(1, items.get(0).getData().size());

        // All kind of links
        linkCollector.parseByRule(HelperCollector.LINK_ALL);
        items = linkCollector.getItems();
        assertEquals(6, items.get(0).getData().size());

        // Only valid links
        linkCollector.parseByRule(HelperCollector.LINK_VALID);
        items = linkCollector.getItems();
        assertEquals(4, items.get(0).getData().size());
    }
}