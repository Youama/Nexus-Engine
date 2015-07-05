package com.youama.nexus.core;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.05.
 */
public class Log {

    /**
     * @todo It should save all messages into log files in the future instead of print out.
     * @param message
     */
    public static void warning(Exception message) {
        System.out.println(message);
    }

    /**
     * @todo It should save all messages into log files in the future instead of print out.
     * @param message
     * @param className
     */
    public static void warning(Exception message, String className) {
        System.out.println(className);
        System.out.println(message);
    }

    /**
     * @todo It should save all messages into log files in the future instead of print out.
     * @param message
     * @param className
     */
    public static void notice(String message, String className) {
        System.out.println(className);
        System.out.println(message);
    }
}
