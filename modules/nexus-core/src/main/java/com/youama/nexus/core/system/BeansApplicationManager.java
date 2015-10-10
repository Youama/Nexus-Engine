package com.youama.nexus.core.system;

import org.springframework.beans.factory.BeanDefinitionStoreException;

import java.util.*;

/**
 * This class is a thread local Singleton, what means each thread has separated Singleton object. An instance of this 
 * BeansApplicationManager stores the Spring's applications contexts and manages them. This class has package level, 
 * it can not be used from outside the system package.
 * 
 * @author David Belicza
 * @since 0.1.0
 */
final class BeansApplicationManager {

    /**
     * Thread local singleton object for each threads.
     */
    private static final ThreadLocal<BeansApplicationManager> localStorage = new ThreadLocal<BeansApplicationManager>() {
        protected BeansApplicationManager initialValue() {
            return new BeansApplicationManager();
        }
    };

    /**
     * It retrieves the singleton instance from the current thread.
     * 
     * @return The BeansApplicationManager thread singleton object.
     */
    static BeansApplicationManager getInstance() {
        return localStorage.get();
    }

    /**
     * It resets the singleton instance for the current thread.
     */
    static void resetInstance() {
        localStorage.remove();
    }

    /**
     * These are the possible supported database drivers for Nexus.
     */
    private String[] supportedDrivers = {
        SystemConstant.DATABASE_HSQL,
        SystemConstant.DATABASE_MYSQL,
        SystemConstant.DATABASE_POSTGRESQL
    };

    /**
     * These are the installed drivers, these have active setup in the nexus.properties file.
     */
    private List<String> installedDrivers = new ArrayList<String>();

    /**
     * Each database driver has its own Spring application context for each thread.
     */
    private Map<String, BeansApplicationContext> services = new HashMap<String, BeansApplicationContext>();

    /**
     * This is the Current driver name for the whole thread. The possible value defined in the SystemConstant interface.
     */
    private String currentDriverName;

    /**
     * It retrieves that the current driver is for client side or server side.
     * 
     * @return The constant in the SystemConstant interface.
     */
    String getDatasourceId() {
        if (SystemConstant.DATABASE_HSQL.equals(currentDriverName)) {
            return SystemConstant.DATABASE_EMBEDDED;
        } else {
            return SystemConstant.DATABASE_SERVER;
        }
    }

    /**
     * It retrieves the current driver name what is in use.
     * 
     * @return The constant in the SystemConstant interface.
     */
    String getCurrentDriverName() {
        return currentDriverName;
    }

    /**
     * It switches the driver from the current one to the another one. The parameter in the new driver name.
     * 
     * @param driverName
     */
    void switchDriver(String driverName) {
        currentDriverName = driverName;
    }

    /**
     * It retrieves the successfully installed driver names.
     * 
     * @return The driver names from the property.
     */
    List<String> getInstalledDrivers() {
        return installedDrivers;
    }

    /**
     * It retrieves the service object by the implemented service class. It retrieves null when service does not exist.
     * 
     * @param classType The implemented service class.
     * @return The service object for persistent data.
     */
    Object getService(Class<?> classType) {
        if (services.get(currentDriverName) != null) {
            return services.get(currentDriverName).getService(classType);
        }

        return null;
    }

    /**
     * It initiates the active drivers. The Configuration class reads in the nexus.properties file and the 
     * BeansApplicationContext class initiates the Spring application contexts.
     * 
     * @throws BeanDefinitionStoreException
     */
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
