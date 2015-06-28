package com.youama.nexus.crawler;

import org.junit.Test;

import static org.junit.Assert.*;


public class UrlTest {

    @Test
    public void getUrl() {
        Url url = new Url();

        url.setUrl("domain.com");
        assertEquals("domain.com", url.getUrl());
    }

    @Test
    public void setUrl() {
        Url url = new Url();

        url.setUrl("http://domain.com");
        assertEquals("domain.com", url.getUrl());

        url.setUrl("https://domain.com");
        assertEquals("domain.com", url.getUrl());

        url.setUrl("http:/domain.com");
        assertEquals("http:/domain.com", url.getUrl());
    }

    @Test
    public void isSecure() {
        Url url = new Url();

        url.setUrl("http://domain.com");
        assertEquals(false, url.isSecure());

        url.setUrl("https://domain.com");
        assertEquals(true, url.isSecure());

        url.setUrl("http:/domain.com");
        assertEquals(false, url.isSecure());
    }

    @Test
    public void getDomain() {
        Url url = new Url();

        assertEquals("", url.getDomain());

        url.setUrl("domain.com");
        assertEquals("domain.com", url.getDomain());

        url.setUrl("http://domain.com");
        assertEquals("domain.com", url.getDomain());

        url.setUrl("https://domain.com/");
        assertEquals("domain.com", url.getDomain());

        url.setUrl("http://www.domain.com");
        assertEquals("www.domain.com", url.getDomain());

        url.setUrl("http://domain.com?id=2");
        assertEquals("domain.com", url.getDomain());

        url.setUrl("sub.domain.com/root");
        assertEquals("sub.domain.com", url.getDomain());
    }

    @Test
    public void isResourceExtension() {
        Url url = new Url();

        assertEquals(true, url.isResourceExtension("css"));
        assertEquals(true, url.isResourceExtension("cSs"));
        assertEquals(true, url.isResourceExtension("sass"));
        assertEquals(true, url.isResourceExtension("less"));
        assertEquals(true, url.isResourceExtension("js"));
        assertEquals(true, url.isResourceExtension("png"));
        assertEquals(true, url.isResourceExtension("jpg"));
        assertEquals(true, url.isResourceExtension("jpeg"));
        assertEquals(true, url.isResourceExtension("gif"));
        assertEquals(true, url.isResourceExtension("bmp"));
        assertEquals(false, url.isResourceExtension("asp"));
        assertEquals(false, url.isResourceExtension("dsadds"));
    }

    @Test
    public void getExtensionFromUrl() {
        Url url = new Url();

        url.setUrl("http://domain.com/test.css");
        assertEquals("css", url.getExtensionFromUrl());

        url.setUrl("http://domain.com/testabcdef");
        assertEquals("abcdef", url.getExtensionFromUrl());

        url.setUrl("http://domain.com");
        assertEquals("com", url.getExtensionFromUrl());
    }

    @Test
    public void isResourceUrl() {
        Url url = new Url();

        url.setUrl("http://domain.com/test.css");
        assertEquals(true, url.isResourceUrl());

        url.setUrl("http://domain.com/test.CSS");
        assertEquals(true, url.isResourceUrl());

        url.setUrl("http://domain.com/test.asp");
        assertEquals(false, url.isResourceUrl());

        url.setUrl("http://domain.com");
        assertEquals(false, url.isResourceUrl());

        url.setUrl("http://domain.com/test.html");
        assertEquals(false, url.isResourceUrl());

        url.setUrl("http://domain.com/test");
        assertEquals(false, url.isResourceUrl());
    }
}