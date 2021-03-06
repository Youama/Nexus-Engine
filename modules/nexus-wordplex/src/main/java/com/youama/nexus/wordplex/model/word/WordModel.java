package com.youama.nexus.wordplex.model.word;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity contains all words for all languages.
 * 
 * @author David Belicza
 * @since 0.1.0
 */
@Entity
@Table(name = WordTable.TABLE)
public class WordModel {

    @Id
    @Column(name = WordTable.COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long wordId;

    @Column(name = WordTable.COLUMN_WORD, length = 255, unique = true)
    private String word;

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public long getWordId() {
        return wordId;
    }

    public void setWord(String word) {
        this.word = word.toLowerCase();
    }

    public String getWord() {
        return word;
    }
}
