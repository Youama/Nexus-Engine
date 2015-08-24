package com.youama.nexus.core.system;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.08.23.
 */
public class MavenUtilTest {

    @Test
    public void testGetModuleConfigBeanFiles() {

        String[] configBeanFiles = MavenUtil.getModuleConfigBeanFiles();
        assertTrue(configBeanFiles != null && configBeanFiles.length > 0);
    }
}