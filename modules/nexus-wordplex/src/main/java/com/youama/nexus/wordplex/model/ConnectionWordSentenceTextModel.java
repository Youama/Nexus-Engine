package com.youama.nexus.wordplex.model;

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
@Table(name = ConnectionWordSentenceTextTable.TABLE)
public class ConnectionWordSentenceTextModel {

    @Id
    @Column(name = ConnectionWordSentenceTextTable.COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long connectionWordSentenceTextId;

    @Column(name = ConnectionWordSentenceTextTable.COLUMN_WORD_ID)
    private long wordId;

    @Column(name = ConnectionWordSentenceTextTable.COLUMN_SENTENCE_ID)
    private long sentenceId;

    @Column(name = ConnectionWordSentenceTextTable.COLUMN_TEXT_ID)
    private long textId;

    @Column(name = ConnectionWordSentenceTextTable.COLUMN_POSITION_WORD)
    private int positionWord;

    @Column(name = ConnectionWordSentenceTextTable.COLUMN_POSITION_SENTENCE)
    private int positionSentence;

    public void setConnectionWordSentenceTextId(long connectionWordSentenceTextId) {
        this.connectionWordSentenceTextId = connectionWordSentenceTextId;
    }

    public long getConenctionWordSentenceTextId() {
        return connectionWordSentenceTextId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public long getWordId() {
        return wordId;
    }

    public void setSentenceId(long sentenceId) {
        this.sentenceId = sentenceId;
    }

    public long getSentenceI() {
        return sentenceId;
    }

    public void setTextId(long textId) {
        this.textId = textId;
    }

    public long getTextId() {
        return textId;
    }

    public void setPositionWord(int positionWord) {
        this.positionWord = positionWord;
    }

    public int getPositionWord() {
        return positionWord;
    }

    public void setPositionSentence(int positionSentence) {
        this.positionSentence = positionSentence;
    }

    public int getPositionSentence() {
        return positionSentence;
    }
}
