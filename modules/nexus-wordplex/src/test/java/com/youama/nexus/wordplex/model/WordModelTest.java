package com.youama.nexus.wordplex.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.youama.nexus.wordplex.model.word.WordModel;

public class WordModelTest {

    @Test
    public void testSetWord() {
        WordModel wordModel = new WordModel();
        wordModel.setWord("thing");
        assertEquals("thing", wordModel.getWord());

        // lower case checking
        wordModel.setWord("Thing");
        assertEquals("thing", wordModel.getWord());
    }
}
