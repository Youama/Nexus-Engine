package com.youama.nexus.core.system;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author David Belicza
 * @since 2015.08.18.
 */
public class ConfigurationTest extends TestCase {

    @Test
    public void testReadConfig() {
        Configuration config = new Configuration();
        config.readConfig("production", "mysql");
    }
}