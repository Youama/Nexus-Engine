package com.youama.nexus.core.io;

import org.junit.*;

import com.youama.nexus.core.io.Connection;
import com.youama.nexus.core.io.StreamReader;

import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.07.11.
 */
public class StreamReaderTest {

    @Test
    public void testGetBodyAsStringFromConnection() {
        Connection connection = new Connection();
        connection.connectWithEnabledRedirects("http://wordpress.com");

        String htmlBodyAsString = StreamReader.getBodyAsStringFromConnection(connection.getConnection());

        assertTrue(htmlBodyAsString.length() > 100);
        assertTrue(htmlBodyAsString.toLowerCase().contains("click-wpcom-login"));
    }
}