package com.youama.nexus.core.system;

import org.junit.*;
import org.springframework.beans.factory.BeanDefinitionStoreException;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.08.26.
 */
public class BeansApplicationManagerTest {


    @Test
    public void testGetInstance() throws InterruptedException {
        testThreadLocalSingleton();
    }

    private void testThreadLocalSingleton() throws InterruptedException {
        assertNull(BeansApplicationManager.getInstance().getCurrentDriverName());

        Thread childThread = new Thread() {
            public void run() {
                BeansApplicationManager.getInstance().switchDriver("postgresql");
                assertEquals("postgresql", BeansApplicationManager.getInstance().getCurrentDriverName());
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        childThread.start();
        childThread.join();

        // This singleton has to be equal with the first one. The singleton in the child thread should not touch the
        // parent thread's singleton.
        assertNull(BeansApplicationManager.getInstance().getCurrentDriverName());
    }

    @Test
    public void testResetInstance() {
        BeansApplicationManager.getInstance().switchDriver("some-db");
        assertEquals("some-db", BeansApplicationManager.getInstance().getCurrentDriverName());

        BeansApplicationManager.resetInstance();
        assertNull(BeansApplicationManager.getInstance().getCurrentDriverName());
    }

    @Test
    public void testGetDatasourceId() {
        assertEquals("dataSourceServer", BeansApplicationManager.getInstance().getDatasourceId());
    }

    @Test
    public void testGetCurrentDriverName() {
        // Same as the testSwitchDriver()
    }

    @Test
    public void testSwitchDriver() {
        assertNull(BeansApplicationManager.getInstance().getCurrentDriverName());

        BeansApplicationManager.getInstance().switchDriver("test");
        assertEquals("test", BeansApplicationManager.getInstance().getCurrentDriverName());
    }

    @Test
    public void testGetInstalledDrivers() {
        assertTrue(BeansApplicationManager.getInstance().getInstalledDrivers().size() == 0);
    }

    @Test
    public void testGetService() {
        assertNull(BeansApplicationManager.getInstance().getService(Objects.class));
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testInitServiceDriver() {
        // It throws BeanDefinitionStoreException exception because NexusCoreUtil is not initialized.
        BeansApplicationManager.getInstance().initServiceDriver();
    }

    @After
    public void tierDown() {
        BeansApplicationManager.resetInstance();
    }

}