package com.youama.nexus.wordplex.word.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author David Belicza
 * @since 2015.09.21.
 */
@Entity
@Table(name = WordSentenceTextTable.TABLE)
public class WordSentenceTextModel {
	
	@Id
    @Column(name = WordSentenceTextTable.COLUMN_ID)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long wordSentenceTextId;
	
	public void setWordSentenceTextId(long wordSentenceTextId) {
		this.wordSentenceTextId = wordSentenceTextId;
	}
	
	public long getWordSentenceTextId() {
		return wordSentenceTextId;
	}
}
