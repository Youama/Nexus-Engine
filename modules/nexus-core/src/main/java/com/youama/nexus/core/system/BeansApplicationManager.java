package com.youama.nexus.core.system;

import org.springframework.beans.factory.BeanDefinitionStoreException;

import java.util.*;

/**
 * @author David Belicza
 * @since 2015.08.22.
 */
final class BeansApplicationManager {

    private static final ThreadLocal<BeansApplicationManager> localStorage = new ThreadLocal<BeansApplicationManager>() {
        protected BeansApplicationManager initialValue() {
            return new BeansApplicationManager();
        }
    };

    static BeansApplicationManager getInstance() {
        return localStorage.get();
    }

    static void resetInstance() {
        localStorage.remove();
    }

    private String[] supportedDrivers = {
        SystemConstant.DATABASE_HSQL,
        SystemConstant.DATABASE_MYSQL,
        SystemConstant.DATABASE_POSTGRESQL
    };

    private List<String> installedDrivers = new ArrayList<String>();

    private Map<String, BeansApplicationContext> services = new HashMap<String, BeansApplicationContext>();

    private String currentDriverName;

    String getDatasourceId() {
        if (SystemConstant.DATABASE_HSQL.equals(currentDriverName)) {
            return SystemConstant.DATABASE_EMBEDDED;
        } else {
            return SystemConstant.DATABASE_SERVER;
        }
    }

    String getCurrentDriverName() {
        return currentDriverName;
    }

    void switchDriver(String driverName) {
        currentDriverName = driverName;
    }

    List<String> getInstalledDrivers() {
        return installedDrivers;
    }

    Object getService(Class<?> classType) {
        if (services.get(currentDriverName) != null) {
            return services.get(currentDriverName).getService(classType);
        }

        return null;
    }

    void initServiceDriver() throws BeanDefinitionStoreException {
        Configuration configuration = Configuration.getInstance();

        for (String driver : supportedDrivers) {
            if (configuration.isDriverActive(driver)) {
                currentDriverName = driver;
                BeansApplicationContext serviceManager = new BeansApplicationContext();
                serviceManager.setApplicationContext();
                services.put(driver, serviceManager);
                installedDrivers.add(driver);
            }
        }
    }
}
