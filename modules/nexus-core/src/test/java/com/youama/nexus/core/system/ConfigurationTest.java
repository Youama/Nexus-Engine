package com.youama.nexus.core.system;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.08.18.
 */
public class ConfigurationTest {

    @Test
    public void testGetInstace() {
        assertTrue(Configuration.getInstance() != null);
    }

    @Test
    public void testSetRegisteredPrimaryModuleArtifactId() {
        Configuration.getInstance().registerPrimaryModule("something", Configuration.class);
        assertTrue("something".equals(Configuration.getInstance().getRegisteredPrimaryModuleArtifactId()));
    }

    @Test
    public void testGetRegisteredPrimaryModuleArtifactId() {
        String value = Configuration.getInstance().getRegisteredPrimaryModuleArtifactId();
        assertTrue(SystemConstant.DEFAULT_APP.equals(value) || "something".equals(value));
        Configuration.getInstance().registerPrimaryModule("new", Configuration.class);
        assertTrue("new".equals(Configuration.getInstance().getRegisteredPrimaryModuleArtifactId()));
    }

    @Test
    public void testIsOk() {
        assertTrue(Configuration.getInstance().isOk());
    }

    @Test
    public void testIsDriverActive() {
        assertTrue(Configuration.getInstance().isDriverActive(ServiceUtil.getDefaultDBDriver()));
        assertFalse(Configuration.getInstance().isDriverActive("fake"));
    }

    @After
    public void tierDown() {
        Configuration.resetInstance();
    }
}