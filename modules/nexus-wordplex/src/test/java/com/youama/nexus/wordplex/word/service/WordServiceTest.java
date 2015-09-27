package com.youama.nexus.wordplex.word.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.youama.nexus.core.system.NexusCoreUtil;
import com.youama.nexus.core.system.ServiceUtil;
import com.youama.nexus.wordplex.word.model.WordModel;

import static org.junit.Assert.*;

import java.util.List;

/**
 * @author David Belicza
 * @since 2015.09.20.
 */
public class WordServiceTest {
    
    List<String> drivers = ServiceUtil.getInstalledDrivers();
    
    @Before
    public void setUp() {
        NexusCoreUtil.initServices("nexus-module-wordplex", WordModel.class);;
    }
    
    @Test
    public void testServiceMethods() {
        
        for (String driver : drivers) {
            ServiceUtil.switchDriver(driver);
            
            WordModel wordModel = new WordModel();
            wordModel.setWord("super");
            
            WordService wordService = (WordService) ServiceUtil.getService(WordService.class);
            WordModel savedModel = wordService.save(wordModel);
            
            assertTrue(savedModel != null && savedModel.getWordId() >= 0);
            assertTrue(savedModel != null && "super".equals(savedModel.getWord()));
        
            wordModel = new WordModel();
            wordModel.setWord("new");
            wordService.save(wordModel);            
            
            wordModel = new WordModel();
            wordModel.setWord("super");
            wordService.save(wordModel);
            
            List<WordModel> list = wordService.getCollection();
            assertTrue(list != null && list.size() == 2);
        }
    }
    
    @After
    public void tierDown() {
        NexusCoreUtil.removeServices();
    }
}
