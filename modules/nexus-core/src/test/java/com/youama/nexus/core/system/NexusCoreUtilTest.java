package com.youama.nexus.core.system;

import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.beans.factory.BeanDefinitionStoreException;

import java.io.FileNotFoundException;

/**
 * @author David Belicza
 * @since 2015.08.23.
 */
public class NexusCoreUtilTest {

    @Test
    public void testInitServices_success() {
        NexusCoreUtil.initServices("nexus-module-core");
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testInitServices_fail() {
        NexusCoreUtil.initServices("nexus-module-scheduler");
    }

    @After
    public void tierDown() {
        NexusCoreUtil.removeServices();
    }
}