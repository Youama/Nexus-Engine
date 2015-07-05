package com.youama.nexus.core.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.06.28.
 */
public class StreamReader {

    /**
     * @param connection The connection object should be already connected to the host.
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
