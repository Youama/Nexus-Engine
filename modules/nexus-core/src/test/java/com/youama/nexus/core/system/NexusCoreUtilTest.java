package com.youama.nexus.core.system;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanDefinitionStoreException;

/**
 * @author David Belicza
 * @since 2015.08.23.
 */
public class NexusCoreUtilTest {

    @Test
    public void testIsAllowInternalProperties() {
        assertTrue(NexusCoreUtil.isAllowInternalProperties());
    }
    
    @Test
    public void testInitServices_success() {
        NexusCoreUtil.initServices("nexus-module-core", NexusCoreUtil.class);
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testInitServices_fail() {
        NexusCoreUtil.initServices("nexus-module-scheduler", NexusCoreUtil.class);
    }
    
    @Test
    public void testSetProperties() {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        propertiesMap.put("testKey", "testValue");
        NexusCoreUtil.setProperties(propertiesMap);
        assertNotNull(Configuration.getInstance().getProperty("testKey"));
    }

    @After
    public void tierDown() {
        NexusCoreUtil.removeServices();
    }
}