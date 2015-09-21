package com.youama.nexus.wordplex.word.model;

/**
 * @author David Belicza
 * @since 2015.09.21.
 */
public interface WordSentenceTextTable {
	
	public static final String TABLE = "Word_Sentence_Text";
	
	public static final String COLUMN_ID = "wordSentenceId";
	
	public static final String COLUMN_WORD_ID = "wordId";
	public static final String COLUMN_SENTENCE_ID = "sentenceId";
	public static final String COLUMN_TEXT_ID = "textId";
	
	public static final String COLUMN_POSITION_WORD = "orderWord";
	public static final String COLUMN_POSITION_SENTENCE = "orderSentence";
}
