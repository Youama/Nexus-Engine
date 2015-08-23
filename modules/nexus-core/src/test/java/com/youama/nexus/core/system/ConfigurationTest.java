package com.youama.nexus.core.system;

import org.junit.Test;

/**
 * @author David Belicza
 * @since 2015.08.18.
 */
public class ConfigurationTest {

    @Test
    public void testReadConfig() {
        Configuration config = Configuration.getInstance();
        config.readConfig();
    }
}