package com.youama.nexus.wordplex.model;

/**
 * @author David Belicza
 * @since 0.1.0
 */
public interface ConnectionWordSentenceTextTable {

    public static final String TABLE = "Connecion_Word_Sentence_Text";

    public static final String COLUMN_ID = "connectionWordSentenceTextId";

    public static final String COLUMN_WORD_ID = "wordId";
    public static final String COLUMN_SENTENCE_ID = "sentenceId";
    public static final String COLUMN_TEXT_ID = "textId";

    public static final String COLUMN_POSITION_WORD = "positionWord";
    public static final String COLUMN_POSITION_SENTENCE = "positionSentence";
}
