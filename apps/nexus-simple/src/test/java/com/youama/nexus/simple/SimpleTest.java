package com.youama.nexus.simple;

import com.youama.nexus.core.system.NexusCoreUtil;
import com.youama.nexus.core.system.ServiceUtil;
import org.junit.Test;

import java.util.List;

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