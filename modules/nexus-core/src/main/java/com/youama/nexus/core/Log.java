package com.youama.nexus.core;

/**
 * This class handles all logging.
 *
 * @author David Belicza
 * @since 0.1.0
 */
public class Log {

    /**
     * It logs an exception stack trace.
     *
     * @param exception Exception object.
     */
    public static void warning(Exception exception) {
        exception.printStackTrace();
    }

    /**
     * It logs an exception stack trace with the class name.
     *
     * @param exception Exception object.
     * @param className The name of the class when the exception is happened.
     */
    public static void warning(Exception exception, String className) {
        System.out.println(className);
        exception.printStackTrace();
    }

    /**
     * It logs an exception stack trace with the class name.
     *
     * @param exception Exception object.
     * @param className The name of the class when the exception is happened.
     * @param alert When alert is true it is print out the exception.
     */
    public static void warning(Exception exception, String className, boolean alert) {
        if (alert) {
            System.out.println(className);
            exception.printStackTrace();
        } else {
            //@todo Store exception
        }
    }

    /**
     * It logs notify log with class name.
     *
     * @param message Simple description.
     * @param className The name of the class when log comes from.
     */
    public static void notice(String message, String className) {
        System.out.println(className);
        System.out.println(message);
    }
}
