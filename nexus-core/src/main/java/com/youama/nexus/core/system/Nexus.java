package com.youama.nexus.core.system;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.09.
 */
final public class Nexus {

    public static void initiateSystem() {
        System system = System.getInstance();
        system.initServices();
    }

    public static void testSystem() {

    }

    public static Object getService(Class classType) {
        return System.getInstance().getServiceProvider().getService(classType);
    }
}
