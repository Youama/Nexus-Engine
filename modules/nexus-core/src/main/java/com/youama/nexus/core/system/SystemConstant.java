package com.youama.nexus.core.system;

/**
 * This interface contains the all constants about the Nexus Engine's system.
 * 
 * @author David Belicza
 * @since 2015.08.28.
 */
public interface SystemConstant {

	/**
	 * Required for system initiate.
	 */
    String DEFAULT_APP = "nexus-module-core";
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Required for system files loading.
     */
    String ACCEPTED_DEPENDENCY_PACKAGE = "com.youama.nexus";
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Required for .properties file loading.
     */
    String DIRECTORY_CONFIG = "config";
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Required for .properties file loading.
     */
    String PROPERTIES_FILE_NAME_LOCAL = "nexus.properties";
    
    /**
     * Required for .properties file loading.
     */
    String PROPERTIES_FILE_NAME_TRAVIS = "nexus.example.properties";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Property key part in .properties file.
     */
    String PROPERTY_DATABASE_PREFIX = "nexus.db.";
    
    /**
     * Property key part in .properties file.
     */
    String PROPERTY_DATABASE_DEFAULT_DRIVER_KEY = "nexus.db.driver.default";
    
    /**
     * Property key part in .properties file.
     */
    String PROPERTY_DATABASE_TABLE_PREFIX_KEY = "nexus.db.table.prefix";
    
    /**
     * Property key part in .properties file.
     */
    String PROPERTY_HIBERNATE_PREFIX_KEY = "nexus.hibernate.";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Property value in .properties file.
     */
    String DATABASE_FLAG_ACTIVE = "active";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * System and property keys and values.
     */
    String DATABASE_HSQL = "hsql";
    
    /**
     * System and property keys and values.
     */
    String DATABASE_MYSQL = "mysql";
    
    /**
     * System and property keys and values.
     */
    String DATABASE_POSTGRESQL = "postgresql";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Required for system initiate.
     */
    String DATABASE_SERVER = "dataSourceServer";
    
    /**
     * Required for system initiate.
     */
    String DATABASE_EMBEDDED = "dataSourceClient";

}
