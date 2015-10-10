package com.youama.nexus.core.system;

import org.junit.*;
import org.springframework.beans.factory.BeanCreationException;

/**
 * @author David Belicza
 * @since 0.1.0
 */
public class BeansApplicationContextTest {

    private BeansApplicationContext singleService;

    @Before
    public void setUp() {
        singleService = new BeansApplicationContext();
    }

    @Test(expected = BeanCreationException.class)
    public void testSetApplicationContext() {
        singleService.setApplicationContext();
    }

    @Test(expected = NullPointerException.class)
    public void testGetService() {
        singleService.getService(Object.class);
    }
}