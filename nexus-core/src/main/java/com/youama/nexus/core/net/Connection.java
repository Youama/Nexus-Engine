package com.youama.nexus.core.net;

import com.youama.nexus.core.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class handles a single HTTP or HTTPS connection. It support redirection and history recording.
 *
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.06.28.
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
     * @return The connection object. It can be null.
     */
    public HttpURLConnection getConnection() {
        return connection;
    }

    /**
     * Connects to the host by URL. This connection method allows the redirection. Anyway, connection object will be
     * stored in the property for later streaming.
     *
     * @param stringUrl Regular HTTP or HTTPS url.
     * @return It is false when the connection has failed.
     */
    public boolean connectWithAllowedRedirects(String stringUrl) {
        return makeNewGetConnection(stringUrl);
    }

    /**
     * This method builds the connection and saves it to the property. However, it is a recursive method, so it is
     * capable to follows the redirection. When there no more redirection, it saves the connection object to the
     * property and it retrieves true. It watches the number of redirection and timeout.
     *
     * @param stringUrl Regular HTTP or HTTPS url.
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

            addToHistory(connection);

            if (isRedirectionLoop()) {
                Log.notice(stringUrl + " has redirection", getClass().getName());
                return false;
            } else if (isRedirectionRequired(connection)) {
                redirectionCounter++;
                return makeNewGetConnection(connection.getHeaderField("location"));
            } else {
                this.connection = connection;
            }

            return true;

        } catch (MalformedURLException e) {
            Log.warning(e, getClass().getName());
            return false;
        } catch (IOException e) {
            Log.warning(e, getClass().getName());
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
     * @param connection
     * @return It is true when redirection is required by response.
     */
    protected boolean isRedirectionRequired(HttpURLConnection connection) {
        return connection.getHeaderField("location") != null;
    }

    /**
     * It adds the current URL and repsonse status to the property.
     *
     * @param connection
     * @throws IOException
     */
    protected void addToHistory(HttpURLConnection connection) throws IOException {
        statusHistory.add(connection.getResponseCode());
        urlHistory.add(connection.getURL().toString());
    }
}
