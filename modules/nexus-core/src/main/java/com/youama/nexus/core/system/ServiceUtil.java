package com.youama.nexus.core.system;

import java.util.List;

/**
 * @author David Belicza
 * @since 2015.08.20.
 */
final public class ServiceUtil {

    static void enableServiceDriver() {
        BeansApplicationManager.getInstance().initServiceDriver();
    }

    static void disableServiceDriver() {
        BeansApplicationManager.resetInstance();
    }

    public static List<String> getInstalledDrivers() {
        return BeansApplicationManager.getInstance().getInstalledDrivers();
    }

    public static void switchDriver(String driverName) {
        BeansApplicationManager.getInstance().switchDriver(driverName);
    }

    /**
     * It retrieves the entity's service implementation by the service class name.
     *
     * @param classType Any class type what is a service class. Service classes should be defined as a Bean.
     * @return It is the service implementation. Return type is an Object what can be cast to any service class.
     */
    public static Object getService(Class<?> classType) {
        return BeansApplicationManager.getInstance().getService(classType);
    }

    public static String getDefaultDBDriver() {
        return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_DEFAULT_DRIVER_KEY);
    }

    public static String getTablePrefix() {
        return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_TABLE_PREFIX_KEY);
    }

    public static String getDatasourceId() {
        return BeansApplicationManager.getInstance().getDatasourceId();
    }

	public static String getDBDriver() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".driver");
	}

	public static String getDBUrl() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".url");
	}

	public static String getDBUser() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".user");
	}

	public static String getDBPassword() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".password");
	}

	public static String getDBDialect() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_HIBERNATE_PREFIX_KEY
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".dialect");
	}

	public static String getDBCreation() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_HIBERNATE_PREFIX_KEY
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".hbm2ddl_auto");
	}

	public static String getDBSessionContext() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_HIBERNATE_PREFIX_KEY
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".current_session_context_class");
	}

	public static String getDBLog() {
		return Configuration.getInstance().getProperty(SystemConstant.PROPERTY_HIBERNATE_PREFIX_KEY
				+ BeansApplicationManager.getInstance().getCurrentDriverName() + ".show_sql");
	}
}
