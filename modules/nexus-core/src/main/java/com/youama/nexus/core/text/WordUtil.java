package com.youama.nexus.core.text;

import com.youama.nexus.core.item.BasicItem;

import java.util.ArrayList;
import java.util.List;

/**
 * The WordUtil class can split the text to sentences and words, prepares them to store with relations.
 *
 * @author David Belicza
 * @since 0.1.0
 */
public class WordUtil {

    /**
     * It computes a text to words to a BasicItem List and then retrieves it. Each List item is a BasicItem object. The
     * BasicItem object is a sentence. The BasicItem object contains a String List what are the words. The marks are
     * removed from the sentences.
     *
     * @param text A regular text what has been not contained any HTML or XML tags.
     * @return Collection of BasicItem objects.
     */
    public static List<BasicItem> getSentencesFromText(String text) {
        List<BasicItem> sentenceList = new ArrayList<BasicItem>();
        String[] sentences = sanitizeText(text).split("[.!?]");

        for (int i = 0; i < sentences.length; i++) {
            String[] words = sentences[i].split("\\s+");
            BasicItem sentenceObject = new BasicItem();

            for (int j = 0; j < words.length; j++) {
                if (words[j].length() > 0) {
                    sentenceObject.addItem(words[j]);
                }
            }

            if (sentenceObject.hasData()) {
                sentenceList.add(sentenceObject);
            }
        }

        return sentenceList;
    }

    /**
     * It removes the unnecessary characters from the whole text. The unnecessary characters are like a noise when
     * the relation build between the words.
     *
     * @param text A regular text what has been not contained any HTML or XML tags.
     * @return The cleared text as String.
     */
    public static String sanitizeText(String text) {
        return text.replaceAll("[:;,]", "");
    }
}
