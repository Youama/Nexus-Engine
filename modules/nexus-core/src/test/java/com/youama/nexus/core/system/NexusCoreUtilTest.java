package com.youama.nexus.core.system;

import org.junit.*;
import org.springframework.beans.factory.BeanDefinitionStoreException;

/**
 * @author David Belicza
 * @since 2015.08.23.
 */
public class NexusCoreUtilTest {

    @Test
    public void testInitServices_success() {
        NexusCoreUtil.initServices("nexus-module-core", NexusCoreUtil.class);
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testInitServices_fail() {
        NexusCoreUtil.initServices("nexus-module-scheduler", NexusCoreUtil.class);
    }

    @After
    public void tierDown() {
        NexusCoreUtil.removeServices();
    }
}