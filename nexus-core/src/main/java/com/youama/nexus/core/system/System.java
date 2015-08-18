package com.youama.nexus.core.system;

import com.youama.nexus.core.Log;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The System class initiate the main core classes and store them in its properties. System class always should be an
 * singleton. The visibility of the methods of this System class are package level. These methods are not available
 * from other packages.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.08.09.
 */
final class System {

    /**
     * Instance of this object.
     */
    private static System instance = null;

    private String baseDirectory = "";

    private Configuration configuration = null;

    /**
     * The object of the ServiceProvider class.
     */
    private ServiceProvider serviceProvider = null;

    /**
     * It retrieves the persistence/singleton System object.
     * @return
     */
    static System getInstance() {
        if(instance == null) {
            instance = new System();
        }
        return instance;
    }

    void initDirectoryMapping() {
        Path path = null;

        try {
            path = Paths.get(SystemUtil.class.getResource(".").toURI());
            baseDirectory = path.getParent().getParent().getParent().getParent().getParent().getParent().getParent()
                    .getParent().toString();
        } catch (URISyntaxException e) {
            Log.warning(e);
        }

    }

    void initConfiguration(String version, String database) {
        configuration = new Configuration();
        configuration.readConfig(version, database);
    }

    /**
     * Initiate the ServiceProvider.
     */
    void initServices() {
        serviceProvider = new ServiceProvider();
        serviceProvider.setApplicationContext();
    }

    /**
     * It retrieves the ServiceProvider object. If the SystemUtil engine is not initiated it loads the application context.
     * This means the ServiceProvider will be able to provides access only on the level of the module in this case.
     *
     * @return The instance of ServiceProvider class.
     */
    ServiceProvider getServiceProvider() {
        if (serviceProvider == null) {
            initServices();
        }

        return serviceProvider;
    }

    String getBaseDirectory() {
        if (baseDirectory.length() < 1) {
            initDirectoryMapping();
        }

        return baseDirectory;
    }

    Configuration getConfiguration() {
        if (configuration == null) {
            initConfiguration("test", "mysql");
        }

        return configuration;
    }

    Configuration getConfiguration(String version, String database) {
        if (configuration == null) {
            initConfiguration(version, database);
        }

        return configuration;
    }

}
