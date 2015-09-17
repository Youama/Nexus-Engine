package com.youama.nexus.core.validator;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.07.18.
 */
public class URLSyntaxValidatorTest {

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