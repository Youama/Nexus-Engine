package com.youama.nexus.core.system;

import com.youama.nexus.core.Log;
import org.springframework.beans.factory.BeanDefinitionStoreException;

import java.util.*;

/**
 * @author David Belicza
 * @since 2015.08.22.
 */
final class ServiceManager {

    private static final ThreadLocal<ServiceManager> localStorage = new ThreadLocal<ServiceManager>() {
        protected ServiceManager initialValue() {
            return new ServiceManager();
        }
    };

    static ServiceManager getInstance() {
        return localStorage.get();
    }

    static void resetInstance() {
        localStorage.remove();
    }

    private String[] supportedDrivers = {"mysql", "postgresql", "hsql"};

    private List<String> installedDrivers = new ArrayList<String>();

    private Map<String, SingleService> services = new HashMap<String, SingleService>();

    private String currentDriverName;

    String getDatasourceId() {
        if ("hsql".equals(currentDriverName)) {
            return "dataSourceClient";
        } else {
            return "dataSourceServer";
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

    Object getService(Class classType) {
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
                SingleService serviceManager = new SingleService();
                serviceManager.setApplicationContext();
                services.put(driver, serviceManager);
                installedDrivers.add(driver);
            }
        }
    }
}
