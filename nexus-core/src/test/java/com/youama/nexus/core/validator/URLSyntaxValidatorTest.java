package com.youama.nexus.core.validator;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.07.18.
 */
public class URLSyntaxValidatorTest extends TestCase {

    @Test
    public void testIsValidLink() {
        // Invalid links
        assertFalse(URLSyntaxValidator.isValidLink(""));
        assertFalse(URLSyntaxValidator.isValidLink("#"));
        assertFalse(URLSyntaxValidator.isValidLink("/"));
        assertFalse(URLSyntaxValidator.isValidLink("//"));
        assertFalse(URLSyntaxValidator.isValidLink("#newpage"));
        assertFalse(URLSyntaxValidator.isValidLink("."));
        assertFalse(URLSyntaxValidator.isValidLink("/."));

        // Valid links
        assertTrue(URLSyntaxValidator.isValidLink("/1"));
        assertTrue(URLSyntaxValidator.isValidLink("1"));
        assertTrue(URLSyntaxValidator.isValidLink("1#"));
        assertTrue(URLSyntaxValidator.isValidLink("1.html"));
        assertTrue(URLSyntaxValidator.isValidLink("/news/entry?start=1&end=3"));
    }

    @Test
    public void testIsResourceUrl() {
        assertTrue(URLSyntaxValidator.isResourceUrl("test.png"));
        assertTrue(URLSyntaxValidator.isResourceUrl("test.jpg"));
        assertTrue(URLSyntaxValidator.isResourceUrl("test.jpeg"));
        assertTrue(URLSyntaxValidator.isResourceUrl("test.gif"));
        assertTrue(URLSyntaxValidator.isResourceUrl("test.css"));
        assertTrue(URLSyntaxValidator.isResourceUrl("test.js"));
        assertTrue(URLSyntaxValidator.isResourceUrl(".js"));

        assertFalse(URLSyntaxValidator.isResourceUrl("js"));
        assertFalse(URLSyntaxValidator.isResourceUrl("test.php"));
        assertFalse(URLSyntaxValidator.isResourceUrl("test.asp"));
    }
}