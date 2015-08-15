package com.youama.nexus.core.net;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.07.11.
 */
public class StreamReaderTest extends TestCase {

    @Test
    public void testGetBodyAsStringFromConnection() {
        Connection connection = new Connection();
        connection.connectWithEnabledRedirects("http://wordpress.com");

        String htmlBodyAsString = StreamReader.getBodyAsStringFromConnection(connection.getConnection());

        assertTrue(htmlBodyAsString.length() > 100);
        assertTrue(htmlBodyAsString.toLowerCase().contains("click-wpcom-login"));
    }
}