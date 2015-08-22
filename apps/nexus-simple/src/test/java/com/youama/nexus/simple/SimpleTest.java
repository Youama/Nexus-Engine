package com.youama.nexus.simple;

import com.youama.nexus.simple.Simple;
import org.junit.Test;

/**
 * @author David Belicza
 * @since 2015.08.22.
 */
public class SimpleTest {

    @Test
    public void testApplicationRunningSimulation() {
        Simple simple = new Simple();
        simple.applicationRunningSimulation();
    }
}