package com.youama.nexus.core.text;

import com.youama.nexus.core.item.BasicItem;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.01.
 */
public class WordUtilTest extends TestCase {

    public String text;

    @Before
    public void setUp() {
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ultricies tellus ac massa hendrerit, " +
                "eu pulvinar massa semper. Fusce sit amet pharetra risus. Fusce placerat facilisis libero, quis " +
                "egestas nunc ullamcorper vel. Nulla consectetur; ac lacus sed commodo. Nunc auctor tempor lectus. " +
                "Sed iaculis: dignissim ex nec congue. Ut nibh sapien, eleifend at ipsum eu, placerat vestibulum " +
                "orci. Duis dolor libero, porta nec semper sit amet, aliquet vitae neque. Nam eu augue ex.";
    }

    @Test
    public void testSanitizeText() {
        // 9 because of coma, semicolon, etc.
        assertEquals(text.length() - 9, WordUtil.sanitizeText(text).length());
    }

    @Test
    public void testGetSentencesFromText() {
        List<BasicItem> sentences = WordUtil.getSentencesFromText(text);
        assertEquals(10, sentences.size());
    }
}