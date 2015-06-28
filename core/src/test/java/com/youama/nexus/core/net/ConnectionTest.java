package com.youama.nexus.core.net;

import org.apache.http.HttpResponse;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.06.28.
 */
public class ConnectionTest {

    @Test
    public void testConnectWithAllowedRedirects() throws Exception {
        Connection connection = new Connection();
        boolean response;

        // Simple, no redirection
        response = connection.connectWithAllowedRedirects("http://edition.cnn.com");
        assertTrue(response);

        // Simple, wrong url, no response
        response = connection.connectWithAllowedRedirects("http://abcdef.cnn.com");
        assertFalse(response);

        // Simple, wrong url by HTTPS, unsecured response
        response = connection.connectWithAllowedRedirects("https://edition.cnn.com");
        assertFalse(response);

        // Simple redirection from HTTP to HTTP
        response = connection.connectWithAllowedRedirects("http://google.com");
        assertTrue(response);

        // Redirection from HTTP to HTTPS
        response = connection.connectWithAllowedRedirects("http://wordpress.com");
        assertTrue(response);

        // Redirection from HTTPS to HTTP
        response = connection.connectWithAllowedRedirects("https://non-exist-but-redirecting-url-test.wordpress.com");
        assertTrue(response);
    }
}