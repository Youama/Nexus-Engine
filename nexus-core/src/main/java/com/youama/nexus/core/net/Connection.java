package com.youama.nexus.core.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.06.28.
 */
public class Connection {

    protected HttpURLConnection connection;

    protected ArrayList<Integer> statusHistory = new ArrayList<Integer>();

    protected ArrayList<String> urlHistory = new ArrayList<String>();

    public boolean connectWithAllowedRedirects(String stringUrl) {
        return makeNewGetConnection(stringUrl);
    }

    protected boolean makeNewGetConnection(String stringUrl) {
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection connection = null;

            connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.disconnect();

            addToHistory(connection);

            if (isRedirectionRequired(connection)) {
                return makeNewGetConnection(connection.getHeaderField("location"));
            } else {
                this.connection = connection;
            }

            return true;

        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    protected boolean isRedirectionRequired(HttpURLConnection connection) {
        return connection.getHeaderField("location") != null;
    }

    protected void addToHistory(HttpURLConnection connection) throws IOException {
        statusHistory.add(connection.getResponseCode());
        urlHistory.add(connection.getURL().toString());
    }
}
