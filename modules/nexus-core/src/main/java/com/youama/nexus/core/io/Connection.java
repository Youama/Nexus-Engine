package com.youama.nexus.core.io;

import com.youama.nexus.core.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class handles a single HTTP or HTTPS connection. It support redirection and history recording.
 *
 * @author David Belicza
 * @since 0.1.0
 */
public class Connection {

    /**
     * The connection object for later streaming.
     */
    protected HttpURLConnection connection;

    /**
     * The history of the redirection by status code. The index is also the redirection counter.
     */
    protected ArrayList<Integer> statusHistory = new ArrayList<Integer>();

    /**
     * The history of the redirection by URLs. The index is also the redirection counter.
     */
    protected ArrayList<String> urlHistory = new ArrayList<String>();

    /**
     * It enables HTTP and HTTPS redirection.
     */
    protected boolean redirectionEnabled = true;

    /**
     * The limit number. How many redirection should be allowed.
     */
    protected int redirectionLimit = 10;

    /**
     * The redirection what are already processed.
     */
    protected int redirectionCounter = 0;

    /**
     * Connection timeout in seconds.
     */
    protected int connectionTimeout = 30 * 1000;

    /**
     * Reading timeout in seconds.
     */
    protected int readTimeout = 30 * 1000;

    /**
     * It sets the redirection limit to the property.
     *
     * @param redirectionLimit The limit number. How many redirection should be allowed.
     */
    public void setRedirectionLimit(int redirectionLimit) {
        this.redirectionLimit = redirectionLimit;
    }

    /**
     * @param connectionTimeout Timeout in seconds.
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout * 1000;
    }

    /**
     * @param readTimeout Timeout in seconds.
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout * 1000;
    }

    /**
     * It sets redirection in property to enabled or disabled.
     *
     * @param redirectionEnabled It has to be true to enable redirection.
     */
    protected void setRedirectionEnabled(boolean redirectionEnabled) {
        this.redirectionEnabled = redirectionEnabled;
    }

    /**
     * It retrieves the stored status codes from responses.
     *
     * @return The status codes from response.
     */
    public ArrayList<Integer> getStatusHistory() {
        return statusHistory;
    }

    /**
     * It retrieves the stored urls from responses.
     *
     * @return The urls of connection.
     */
    public ArrayList<String> getUrlHistory() {
        return urlHistory;
    }

    /**
     * @return The connection object. It can be null.
     */
    public HttpURLConnection getConnection() {
        return connection;
    }

    /**
     * Connects to the host by URL. This connection method allows the redirection. Anyway, connection object will be
     * stored in the property for later streaming.
     *
     * @param stringUrl Regular HTTP or HTTPS URL.
     * @return It is false when the connection has failed.
     */
    public boolean connectWithEnabledRedirects(String stringUrl) {
        redirectionEnabled = true;
        return makeNewGetConnection(stringUrl);
    }

    /**
     * Connects to the host by URL. This connection method does not allow the redirection. Anyway, connection object
     * will be stored in the property for later streaming.
     *
     * @param stringUrl Regular HTTP or HTTPS url.
     * @return It is false when the connection has failed.
     */
    public boolean connectWithoutRedirects(String stringUrl) {
        redirectionEnabled = false;
        return makeNewGetConnection(stringUrl);
    }

    /**
     * This method builds the connection and saves it to the property. However, it is a recursive method, so it is
     * capable to follows the redirection. When there no more redirection, it saves the connection object to the
     * property and it retrieves true. It watches the number of redirection and timeout.
     *
     * @param stringUrl Regular HTTP or HTTPS URL.
     * @return It is true when connection has succeed.
     */
    protected boolean makeNewGetConnection(String stringUrl) {
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection connection = null;

            connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(connectionTimeout);
            connection.setReadTimeout(readTimeout);
            connection.disconnect();

            if (connection.getResponseCode() >= 0) {
                addToHistory(connection);

                if (isRedirectionLoop()) {
                    Log.notice(stringUrl + " has redirection", getClass().getName());
                    return false;
                } else if (redirectionEnabled && isRedirectionRequired(connection)) {
                    redirectionCounter++;
                    return makeNewGetConnection(connection.getHeaderField("location"));
                } else {
                    this.connection = connection;
                }

                return true;
            }

            return false;

        } catch (MalformedURLException e) {
            Log.warning(e, getClass().getName(), false);
            return false;
        } catch (IOException e) {
            Log.warning(e, getClass().getName(), false);
            return false;
        }
    }

    /**
     * It retrieves that there are too many redirection or are not by the setted property.
     *
     * @return It is true when there are too many redirection. It means it is an infinite loop.
     */
    protected boolean isRedirectionLoop() {
        return redirectionCounter >= redirectionLimit;
    }

    /**
     * It checks that response header contains request for redirection.
     *
     * @param connection The connection object.
     * @return It is true when redirection is required by response.
     */
    protected boolean isRedirectionRequired(HttpURLConnection connection) {
        return connection.getHeaderField("location") != null;
    }

    /**
     * It adds the current URL and response status to the property.
     *
     * @param connection The connection object.
     */
    protected void addToHistory(HttpURLConnection connection) {
        try {
            statusHistory.add(connection.getResponseCode());
            urlHistory.add(connection.getURL().toString());
        } catch (IOException e) {
            Log.warning(e, getClass().getName());
        }
    }
}
