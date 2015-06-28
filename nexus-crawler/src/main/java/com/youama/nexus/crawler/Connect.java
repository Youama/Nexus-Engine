package com.youama.nexus.crawler;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import java.io.IOException;

/**
 * This class handles the HTTP connection.
 *
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.06.28.
 */
public class Connect {

    Header[] responseHead;

    String responseBody;

    public boolean request(String urlString) {
        return true;
    }

    public Header[] getResponseHead() {
        return this.responseHead;
    }

    public String getResponseBody() {
        return this.responseBody;
    }
}
