package com.youama.nexus.core.system;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.09.
 */
final class System {

    private static System instance = null;

    private ServiceProvider serviceProvider = null;

    static System getInstance() {
        if(instance == null) {
            instance = new System();
        }
        return instance;
    }

    void initServices() {
        serviceProvider = new ServiceProvider();
        serviceProvider.setApplicationContext();
    }

    ServiceProvider getServiceProvider() {
        if (serviceProvider == null) initServices();
        return serviceProvider;
    }


}
