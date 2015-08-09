package com.youama.nexus.core.log;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.09.
 */
public class NexusException extends Exception {

    public NexusException(String message) throws NexusException {
        throw new NexusException(message);
    }
}