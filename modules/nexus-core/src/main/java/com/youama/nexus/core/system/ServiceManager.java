package com.youama.nexus.core.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    List<String> getInstalledDrivers() {
        return installedDrivers;
    }

    Object getService(Class classType) {
        return services.get(currentDriverName).getService(classType);
    }

    void switchDriver(String driverName) {
        currentDriverName = driverName;
    }

    void initServiceDriver() {
        Configuration configuration = Configuration.getInstance();

        for (String driver : supportedDrivers) {
            if (configuration.isDriverActive(driver)) {
                currentDriverName = driver;
                SingleService serviceManager = new SingleService();
                configuration.setDatabaseType(driver);
                serviceManager.setApplicationContext();
                services.put(driver, serviceManager);
                installedDrivers.add(driver);
            }
        }
    }
}
