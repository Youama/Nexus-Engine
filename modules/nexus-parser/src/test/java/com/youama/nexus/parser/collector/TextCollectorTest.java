package com.youama.nexus.parser.collector;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author David Belicza
 * @since 0.1.0
 */
public class TextCollectorTest {

    public String stringHTMLSource = "";

    @Before
    public void setUp() {
        try {
            String path = this.getClass().getResource("/simple2.html").getPath();
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
    public void testCollectText() {
        TextCollector textCollector = new TextCollector(stringHTMLSource);

        // 5 sentences
        textCollector.collectText("p");
        assertEquals(5, textCollector.getItems().size());

        // 25 sentences
        textCollector.collectText("body");
        assertEquals(25, textCollector.getItems().size());
    }

    @Test
    public void testParseByRule() {
        TextCollector textCollector = new TextCollector(stringHTMLSource);

        // 25 sentences
        textCollector.parseByRule(CollectorSelector.TEXT_ALL);
        assertEquals(25, textCollector.getItems().size());

        // 25 sentences
        textCollector.parseByRule(CollectorSelector.TEXT_BODY);
        assertEquals(25, textCollector.getItems().size());
    }
}