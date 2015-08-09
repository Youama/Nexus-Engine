package com.youama.nexus.core.system;

/**
 * This static class is the main class of the Nexus engine. The Nexus class provides access to other classes and objects
 * what are provide global, resource and system/configuration specified features.
 *
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.09.
 */
final public class Nexus {

    /**
     * Initiate the Nexus Engine. This method has to be called from outside because of the application context and
     * other global settings and hooks. If a module calls this method that downgrades the access to the module's local
     * level instead application level.
     */
    public static void initiateSystem() {
        System system = System.getInstance();
        system.initServices();
    }

    /**
     * This method does not trigger any unit or story tests, it is for checking the current system/device before
     * initiateSystem boots the engine.
     */
    public static void testSystem() {

    }

    /**
     * It retrieves the entity's service implementation by the service class name.
     *
     * @param classType Any class type what is a service class. Service classes should be defined as a Bean.
     * @return It is the service implementation. Return type is an Object what can be cast to any service class.
     */
    public static Object getService(Class classType) {
        return System.getInstance().getServiceProvider().getService(classType);
    }
}
