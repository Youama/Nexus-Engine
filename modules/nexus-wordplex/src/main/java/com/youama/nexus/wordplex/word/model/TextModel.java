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
@Table(name = TextTable.TABLE)
public class TextModel {

    @Id
    @Column(name = TextTable.COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long textId;

    public void setTextId(long textId) {
        this.textId = textId;
    }

    public long getTextId() {
        return textId;
    }
}
