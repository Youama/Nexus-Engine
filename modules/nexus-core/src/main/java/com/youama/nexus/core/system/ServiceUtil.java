package com.youama.nexus.core.system;

import java.util.List;

/**
 * This static Service utility provides access to the base service's features for modules and applications.
 * 
 * @author David Belicza
 * @since 2015.08.20.
 */
final public class ServiceUtil {

	/**
	 * It initiates the database driver. It should be used from NexusCoreUtil.
	 */
    static void enableServiceDriver() {
        BeansApplicationManager.getInstance().initServiceDriver();
    }

    /**
     * It disables the previously initiated database driver. It should be used from NexusCoreUtil.
     */
    static void disableServiceDriver() {
        BeansApplicationManager.resetInstance();
    }

    /**
     * It retrieves the list of the available drivers.
     * 
     * @return List of drivers what are already installed.
     */
    public static List<String> getInstalledDrivers() {
        return BeansApplicationManager.getInstance().getInstalledDrivers();
    }

    /**
     * It switches between drivers. It can be useful for testing or multiple databases. One thread can use only one 
     * driver in the same time.
     * 
     * @param driverName Name of the driver what is defined in {@link SystemConstant}.
     */
    public static void switchDriver(String driverName) {
        BeansApplicationManager.getInstance().switchDriver(driverName);
    }

    /**
     * It retrieves the entity's service implementation by the service class.
     *
     * @param classType Any class type what is a service class. Service classes should be defined as a Bean.
     * @return It is the service implementation. Return type is an Object what can be cast to any service class.
     */
    public static Object getService(Class<?> classType) {
        return BeansApplicationManager.getInstance().getService(classType);
    }

    /**
     * It retrieves the default database driver.
     * 
     * @return Defined in .properties file.
     */
    public static String getDefaultDBDriver() {
        return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_DEFAULT_DRIVER_KEY);
    }

    /**
     * It retrieves the table prefix for database.
     * 
     * @return Defined in .properties file.
     */
    public static String getTablePrefix() {
        return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_TABLE_PREFIX_KEY);
    }

    /**
     * It retrieves the data source Id client/server.
     * 
     * @return It can be client side driver or server side driver.
     */
    public static String getDatasourceId() {
        return BeansApplicationManager.getInstance().getDatasourceId();
    }

    /**
     * It retrieves the current database driver name.
     * 
     * @return Loaded in {@link Configuration}.
     */
	public static String getDBDriver() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".driver");
	}

	/**
	 * It retrieves the current database access URL.
	 * 
	 * @return Defined in .properties file.
	 */
	public static String getDBUrl() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".url");
	}

	/**
	 * It retrieves the current database user name.
	 * 
	 * @return Defined in .properties file.
	 */
	public static String getDBUser() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".user");
	}

	/**
	 * It retrieves the current database user's password.
	 * 
	 * @return Defined in .properties file.
	 */
	public static String getDBPassword() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".password");
	}

	/**
	 * It retrieves the current database SQL dialect name.
	 * 
	 * @return Defined in .properties file.
	 */
	public static String getDBDialect() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_HIBERNATE_PREFIX_KEY
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".dialect");
	}

	/**
	 * It retrieves the current database schema creation methodology.
	 * 
	 * @return Defined in .properties file.
	 */
	public static String getDBCreation() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_HIBERNATE_PREFIX_KEY
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".hbm2ddl_auto");
	}

	/**
	 * It retrieves the current database session context scope.
	 * 
	 * @return Defined in .properties file.
	 */
	public static String getDBSessionContext() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_HIBERNATE_PREFIX_KEY
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".current_session_context_class");
	}

	/**
	 * It retrieves the current database logging flag.
	 * 
	 * @return Defined in .properties file.
	 */
	public static String getDBLog() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_HIBERNATE_PREFIX_KEY
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".show_sql");
	}
}
