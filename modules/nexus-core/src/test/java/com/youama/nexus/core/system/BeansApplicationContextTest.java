package com.youama.nexus.core.system;

import org.junit.*;
import org.springframework.beans.factory.BeanCreationException;

/**
 * @author David Belicza
 * @since 2015.08.27.
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