package com.youama.nexus.core.text;

import com.youama.nexus.core.item.BasicItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.01.
 */
public class WordUtil {

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

    public static String sanitizeText(String text) {
        return text.replaceAll("[:;,]", "");
    }
}
