package com.youama.nexus.core;

/**
 * This class handles all logging.
 *
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.05.
 */
public class Log {

    /**
     * It logs an exception stack trace.
     *
     * @todo It should save all messages into log files in the future instead of print out.
     * @param exception Exception object.
     */
    public static void warning(Exception exception) {
        exception.printStackTrace();
    }

    /**
     * It logs an exception stack trace with the class name.
     *
     * @todo It should save all messages into log files in the future instead of print out.
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
     * @todo It should save all messages into log files in the future instead of print out.
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
     * @todo It should save all messages into log files in the future instead of print out.
     * @param message Simple description.
     * @param className The name of the class when log comes from.
     */
    public static void notice(String message, String className) {
        System.out.println(className);
        System.out.println(message);
    }
}
