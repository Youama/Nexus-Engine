package com.youama.nexus.core.system;

/**
 * @author David Belicza
 * @since 2015.08.28.
 */
public interface SystemConstant {

    String DEFAULT_APP = "nexus-module-core";

    String ACCEPTED_DEPENDENCY_PACKAGE = "com.youama.nexus";

    String DIRECTORY_CONFIG = "config";

    String PROPERTIES_FILE_NAME_LOCAL = "nexus.properties";
    String PROPERTIES_FILE_NAME_TRAVIS = "nexus.example.properties";

    String PROPERTY_DATABASE_PREFIX = "nexus.db.";
    String PROPERTY_DATABASE_DEFAULT_DRIVER_KEY = "nexus.db.driver.default";
    String PROPERTY_DATABASE_TABLE_PREFIX_KEY = "nexus.db.table.prefix";
    String PROPERTY_HIBERNATE_PREFIX_KEY = "nexus.hibernate.";

    String DATABASE_FLAG_ACTIVE = "active";

    String DATABASE_HSQL = "hsql";
    String DATABASE_MYSQL = "mysql";
    String DATABASE_POSTGRESQL = "postgresql";

    String DATABASE_SERVER = "dataSourceServer";
    String DATABASE_EMBEDDED = "dataSourceClient";

}
