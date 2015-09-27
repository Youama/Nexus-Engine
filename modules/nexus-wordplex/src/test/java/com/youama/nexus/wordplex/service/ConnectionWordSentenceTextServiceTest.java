package com.youama.nexus.wordplex.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.youama.nexus.core.system.NexusCoreUtil;
import com.youama.nexus.core.system.ServiceUtil;
import com.youama.nexus.wordplex.model.word.WordModel;

import static org.junit.Assert.*;

import java.util.List;

/**
 * @author David Belicza
 * @since 2015.09.20.
 */
public class ConnectionWordSentenceTextServiceTest {
    
    List<String> drivers = ServiceUtil.getInstalledDrivers();
    
    @Before
    public void setUp() {
        NexusCoreUtil.initServices("nexus-module-wordplex", WordModel.class);;
    }
    
    @Test
    public void testServiceMethods() {
        
        for (String driver : drivers) {
            ServiceUtil.switchDriver(driver);
            // TODO
        }
    }
    
    @After
    public void tierDown() {
        NexusCoreUtil.removeServices();
    }
}
