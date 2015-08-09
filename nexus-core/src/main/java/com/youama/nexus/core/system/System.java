package com.youama.nexus.core.system;

/**
 * The System class initiate the main core classes and store them in its properties. System class always should be an
 * singleton. The visibility of the methods of this System class are package level. These methods are not available
 * from other packages.
 *
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.09.
 */
final class System {

    /**
     * Instance of this object.
     */
    private static System instance = null;

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

    /**
     * Initiate the ServiceProvider.
     */
    void initServices() {
        serviceProvider = new ServiceProvider();
        serviceProvider.setApplicationContext();
    }

    /**
     * It retrieves the ServiceProvider object. If the Nexus engine is not initiated it loads the application context.
     * This means the ServiceProvider will be able to provides access only on the level of the module in this case.
     *
     * @return The instance of ServiceProvider class.
     */
    ServiceProvider getServiceProvider() {
        if (serviceProvider == null) initServices();
        return serviceProvider;
    }


}
