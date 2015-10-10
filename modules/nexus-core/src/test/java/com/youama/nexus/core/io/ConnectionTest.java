package com.youama.nexus.core.io;

import org.junit.Test;

import com.youama.nexus.core.io.Connection;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.06.28.
 */
public class ConnectionTest {

    @Test
    public void testSetRedirectionEnabled() {
        Connection connection = new Connection();
        assertTrue(connection.redirectionEnabled);

        connection.setRedirectionEnabled(false);
        assertFalse(connection.redirectionEnabled);
    }

    @Test
    public void testSetRedirectionLimit() {
        Connection connection = new Connection();
        connection.setRedirectionLimit(2);
        assertEquals(2, connection.redirectionLimit);
    }

    @Test
    public void testSetConnectionTimeout() {
        Connection connection = new Connection();
        connection.setConnectionTimeout(2);
        assertEquals(2000, connection.connectionTimeout);
    }

    @Test
    public void testSetReadTimeout() {
        Connection connection = new Connection();
        connection.setReadTimeout(2);
        assertEquals(2000, connection.readTimeout);
    }

    @Test
    public void testIsRedirectionLoop() {
        Connection connection = new Connection();

        // redirectionLimit should be bigger than zero.
        connection.redirectionCounter = 0;
        assertFalse(connection.isRedirectionLoop());

        connection.redirectionCounter = 10;
        connection.redirectionLimit = 10;
        assertTrue(connection.isRedirectionLoop());

        connection.redirectionCounter = 9;
        connection.redirectionLimit = 10;
        assertFalse(connection.isRedirectionLoop());
    }

    @Test
    public void testGetConnection() {
        Connection connection = new Connection();
        assertEquals(null, connection.getConnection());

        connection.connectWithoutRedirects("http://google.com");
        assertTrue(connection.getConnection() instanceof HttpURLConnection);
    }

    @Test
    public void testAddHistory() {
        Connection connection = new Connection();
        connection.connectWithoutRedirects("http://google.com");
        connection.addToHistory(connection.getConnection());

        connection = new Connection();
        connection.connectWithoutRedirects("http://wordpress.com");
        connection.addToHistory(connection.getConnection());

        assertEquals(2, connection.statusHistory.size());
        assertEquals(2, connection.urlHistory.size());
    }

    @Test
    public void testGetStatusHistory() {
        Connection connection = new Connection();
        connection.connectWithEnabledRedirects("http://wordpress.com");
        ArrayList<Integer> statusHistory = connection.getStatusHistory();

        assertEquals(301, statusHistory.get(0).intValue());
        assertEquals(200, statusHistory.get(1).intValue());
    }

    @Test
    public void testGetUrlHistory() {
        Connection connection = new Connection();
        connection.connectWithEnabledRedirects("http://wordpress.com");
        ArrayList<String> urlHistory = connection.getUrlHistory();

        assertEquals("http://wordpress.com", urlHistory.get(0));
        assertEquals("https://wordpress.com/", urlHistory.get(1));
    }

    @Test
    public void testIsRedirectionRequired() {
        // google.com should redirects but redirection is disabled in property.
        Connection connection = new Connection();
        connection.connectWithoutRedirects("http://google.com");
        assertTrue(connection.isRedirectionRequired(connection.getConnection()));

        // google.com has redirected but redirection is enabled and the last connection object contains 200 status code.
        // So there shouldn't be redirection in the last object.
        connection = new Connection();
        connection.connectWithEnabledRedirects("http://google.com");
        assertFalse(connection.isRedirectionRequired(connection.getConnection()));

    }

    @Test
    public void testConnectWithoutRedirects() {
        Connection connection = new Connection();
        boolean response;

        // Simple, no redirection
        response = connection.connectWithoutRedirects("http://edition.cnn.com");
        assertTrue(response);

        // Simple redirection from HTTP to HTTP - but redirection is disabled.
        response = connection.connectWithoutRedirects("http://google.com");
        assertTrue(response);

        // Redirection from HTTP to HTTPS - but redirection is disabled.
        response = connection.connectWithoutRedirects("http://wordpress.com");
        assertTrue(response);

        // Redirection from HTTPS to HTTP - but redirection is disabled.
        response = connection.connectWithoutRedirects("https://non-exist-but-redirecting-url-test.wordpress.com");
        assertTrue(response);
    }

    @Test
    public void testConnectWithEnabledRedirects() {
        Connection connection = new Connection();
        boolean response;

        // Simple, no redirection
        response = connection.connectWithEnabledRedirects("http://edition.cnn.com");
        assertTrue(response);

        // Simple, wrong url, no response
        response = connection.connectWithEnabledRedirects("http://abcdef.cnn.com");
        assertFalse(response);

        // Simple, wrong url by HTTPS, unsecured response
        response = connection.connectWithEnabledRedirects("https://edition.cnn.com");
        assertFalse(response);

        // Simple redirection from HTTP to HTTP
        response = connection.connectWithEnabledRedirects("http://google.com");
        assertTrue(response);

        // Redirection from HTTP to HTTPS
        response = connection.connectWithEnabledRedirects("http://wordpress.com");
        assertTrue(response);

        // Redirection from HTTPS to HTTP
        response = connection.connectWithEnabledRedirects("https://non-exist-but-redirecting-url-test.wordpress.com");
        assertTrue(response);
    }
}