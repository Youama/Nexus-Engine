package com.youama.nexus.core.validator;

/**
 * This class methods validate the URLs Strings.
 *
 * @author David Belicza
 * @since 0.1.0
 */
final public class URLSyntaxValidator {

    /**
     * It retrieves the given URL is valid or is not. Valid means that the URL syntax is correct and it can be able to
     * clicked by the correct syntax.
     *
     * @param url Any kind of url as a String.
     * @return True when it is valid.
     */
    static public boolean isValidLink(String url) {
        if (url.length() < 1 || url.startsWith("#")) {
            return false;
        }

        if (url.replaceAll("[#/.&?=|]", "").length() < 1) {
            return false;
        }

        return true;
    }

    /**
     * It retrieves the given URL is a resource URL or is not. Resource URL means styles, scripts or images.
     *
     * @param url Any kind of url as a String.
     * @return True when it is an resource url.
     */
    static public boolean isResourceUrl(String url) {
        url = url.toLowerCase();

        if (url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") || url.endsWith(".bmp")
                || url.endsWith(".gif") || url.endsWith(".js") || url.endsWith(".css")) {
            return true;
        }

        return false;
    }
}
