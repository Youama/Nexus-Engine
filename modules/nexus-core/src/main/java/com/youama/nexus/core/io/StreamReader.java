package com.youama.nexus.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * @author David Belicza
 * @since 2015.06.28.
 */
public class StreamReader {

    /**
     * It retrieves the response body as a String from a HttpURLConnection object.
     *
     * @param connection The connection object should be already connected at least once to the host.
     * @return The return value is the response body as a String.
     */
    public static String getBodyAsStringFromConnection(HttpURLConnection connection) {
        try {
            InputStreamReader in = new InputStreamReader((InputStream) connection.getContent());
            BufferedReader buff = new BufferedReader(in);
            String body = "";
            String line;
            do {
                line = buff.readLine();
                body = body + line;
            } while (line != null);

            return body;
        } catch (IOException e) {
            return "";
        }
    }
}
