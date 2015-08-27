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
        Configuration.getInstance().setRegisteredPrimaryModuleArtifactId("nexus-module-core");
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

    @Test
    public void tierDown() {
        Configuration.resetInstance();
    }
}