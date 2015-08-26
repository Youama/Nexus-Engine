package com.youama.nexus.core.system;

import org.junit.*;
import org.springframework.beans.factory.BeanDefinitionStoreException;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.08.26.
 */
public class ServiceManagerTest {


    @Test
    public void testGetInstance() throws InterruptedException {
        testThreadLocalSingleton();
    }

    private void testThreadLocalSingleton() throws InterruptedException {
        assertNull(ServiceManager.getInstance().getCurrentDriverName());

        Thread childThread = new Thread() {
            public void run() {
                ServiceManager.getInstance().switchDriver("postgresql");
                assertEquals("postgresql", ServiceManager.getInstance().getCurrentDriverName());
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
        assertNull(ServiceManager.getInstance().getCurrentDriverName());
    }

    @Test
    public void testResetInstance() {
        ServiceManager.getInstance().switchDriver("some-db");
        assertEquals("some-db", ServiceManager.getInstance().getCurrentDriverName());

        ServiceManager.resetInstance();
        assertNull(ServiceManager.getInstance().getCurrentDriverName());
    }

    @Test
    public void testGetDatasourceId() {
        assertEquals("dataSourceServer", ServiceManager.getInstance().getDatasourceId());
    }

    @Test
    public void testGetCurrentDriverName() {
        // Same as the testSwitchDriver()
    }

    @Test
    public void testSwitchDriver() {
        assertNull(ServiceManager.getInstance().getCurrentDriverName());

        ServiceManager.getInstance().switchDriver("test");
        assertEquals("test", ServiceManager.getInstance().getCurrentDriverName());
    }

    @Test
    public void testGetInstalledDrivers() {
        assertTrue(ServiceManager.getInstance().getInstalledDrivers().size() == 0);
    }

    @Test
    public void testGetService() {
        assertNull(ServiceManager.getInstance().getService(Objects.class));
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testInitServiceDriver() {
        // It throws BeanDefinitionStoreException exception because NexusCoreUtil is not initialized.
        ServiceManager.getInstance().initServiceDriver();
    }

    @After
    public void tierDown() {
        ServiceManager.resetInstance();
    }

}