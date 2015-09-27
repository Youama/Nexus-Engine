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
@Table(name = SentenceTable.TABLE)
public class SentenceModel {
    
    @Id
    @Column(name = SentenceTable.COLUMN_ID)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long sentenceId;
    
    public void setSentenceId(long sentenceId) {
        this.sentenceId = sentenceId;
    }
    
    public long getSentenceId() {
        return sentenceId;
    }
}
