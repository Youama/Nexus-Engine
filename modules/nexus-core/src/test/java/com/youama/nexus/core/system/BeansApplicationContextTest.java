package com.youama.nexus.core.system;

import org.junit.*;

import java.util.Objects;

/**
 * @author David Belicza
 * @since 2015.08.27.
 */
public class BeansApplicationContextTest {

    private BeansApplicationContext singleService;

    @Before
    public void setUp() {
        singleService = new BeansApplicationContext();
        Configuration.getInstance().registerPrimaryModule("nexus-module-core", ServiceUtil.class);
    }

    @Test
    public void testSetApplicationContext() {
        singleService.setApplicationContext();
    }

    @Test(expected = NullPointerException.class)
    public void testGetService() {
        // There is no service for core module.
        singleService.getService(Objects.class);
    }

    @After
    public void tierDown() {
        Configuration.resetInstance();
    }
}