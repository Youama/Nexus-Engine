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
@Table(name = ConnectionWordSentenceTextTable.TABLE)
public class ConnectionWordSentenceTextModel {
	
	@Id
    @Column(name = ConnectionWordSentenceTextTable.COLUMN_ID)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long connectionWordSentenceTextId;
	
	public void setConnectionWordSentenceTextId(long connectionWordSentenceTextId) {
		this.connectionWordSentenceTextId = connectionWordSentenceTextId;
	}
	
	public long getConenctionWordSentenceTextId() {
		return connectionWordSentenceTextId;
	}
}
