package com.youama.nexus.core.validator;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.18.
 */
final public class URLValidator {

    static public boolean isValidLinkSyntax(String url) {
        if (url.length() < 1 || url.startsWith("#")) {
            return false;
        }

        if (url.replaceAll("[#/.&?=|]", "").length() < 1) {
            return false;
        }

        return true;
    }
}
