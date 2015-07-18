package com.youama.nexus.core.validator;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.18.
 */
public class URLValidatorTest extends TestCase {

    @Test
    public void testIsValidLinkSyntax() {
        // Invalid links
        assertFalse(URLValidator.isValidLinkSyntax(""));
        assertFalse(URLValidator.isValidLinkSyntax("#"));
        assertFalse(URLValidator.isValidLinkSyntax("/"));
        assertFalse(URLValidator.isValidLinkSyntax("//"));
        assertFalse(URLValidator.isValidLinkSyntax("#newpage"));
        assertFalse(URLValidator.isValidLinkSyntax("."));
        assertFalse(URLValidator.isValidLinkSyntax("/."));

        // Valid links
        assertTrue(URLValidator.isValidLinkSyntax("/1"));
        assertTrue(URLValidator.isValidLinkSyntax("1"));
        assertTrue(URLValidator.isValidLinkSyntax("1#"));
        assertTrue(URLValidator.isValidLinkSyntax("1.html"));
        assertTrue(URLValidator.isValidLinkSyntax("/news/entry?start=1&end=3"));
    }
}