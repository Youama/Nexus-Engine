package com.youama.nexus.wordplex.model.sentence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author David Belicza
 * @since 0.1.0
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
