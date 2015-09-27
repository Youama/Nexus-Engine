package com.youama.nexus.wordplex.word.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * @author David Belicza
 * @since 2015.09.21.
 */
@Embeddable
public class WordSentenceTextId {
    
    private WordModel word;
    
    private SentenceModel sentence;
    
    private TextModel text;
    
    @ManyToOne
    public WordModel getWord() {
        return word;
    }
    
    public void setWord(WordModel word) {
        this.word = word;
    }
    
    @ManyToOne
    public SentenceModel getSentence() {
        return sentence;
    }
    
    public void setSentence(SentenceModel sentence) {
        this.sentence = sentence;
    }
    
    @ManyToOne
    public TextModel geText() {
        return text;
    }
    
    public void setText(TextModel text) {
        this.text = text;
    }
    
}
