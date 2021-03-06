package com.youama.nexus.parser.collector;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 0.1.0
 */
public class FactoryCollectorTest {

    @Test
    public void testGetCollector() {
        String source = "";
        Collector linkCollector = FactoryCollector.getCollector(FactoryCollector.TYPE_LINK, source);
        assertTrue(linkCollector instanceof LinkCollector);
    }
}