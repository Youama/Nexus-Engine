package com.youama.nexus.core.system;

import org.junit.*;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.08.27.
 */
public class BeansApplicationContextTest {

    private BeansApplicationContext singleService;

    @Before
    public void setUp() {
        singleService = new BeansApplicationContext();
    }

    @Test
    public void testSetApplicationContext() {
        // It sets contethe default primary module's artifact id
        singleService.setApplicationContext();
    }

    @Test
    public void testGetService() {
        assertNull(singleService.getService(Objects.class));
    }
}