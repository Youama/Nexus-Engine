package com.youama.nexus.wordplex.importer.text;

import java.util.List;

import com.youama.nexus.core.item.BasicItem;
import com.youama.nexus.core.system.ServiceUtil;
import com.youama.nexus.wordplex.model.connection.ConnectionWordSentenceTextModel;
import com.youama.nexus.wordplex.model.sentence.SentenceModel;
import com.youama.nexus.wordplex.model.text.TextModel;
import com.youama.nexus.wordplex.model.word.WordModel;
import com.youama.nexus.wordplex.service.connection.ConnectionWordSentenceTextService;
import com.youama.nexus.wordplex.service.sentence.SentenceService;
import com.youama.nexus.wordplex.service.text.TextService;
import com.youama.nexus.wordplex.service.word.WordService;

/**
 * This class imports text data to the database step by step. This importer imports each word separately. It also saves
 * the sentence and text IDs and their connection entities. It is the slowest and most secure import type.
 *
 * @author David Belicza
 * @since 0.1.0
 */
public class StepByStepImporter {

    /**
     * ID of the imported text entity. One import process contains the only one text.
     */
    private long textId = -1;

    /**
     * Connection entity counter. How many connections have been imported.
     */
    private long connectionCounter = 0;

    /**
     * The object what contains the prepared text.
     */
    private List<BasicItem> text = null;

    /**
     * Service of Sentence entity.
     */
    private SentenceService sentenceService = (SentenceService) ServiceUtil.getService(SentenceService.class);

    /**
     * Service of the Word entity.
     */
    private WordService wordService = (WordService) ServiceUtil.getService(WordService.class);

    /**
     * Service of the Connection_Word_Sentence_Text entity.
     */
    private ConnectionWordSentenceTextService connectionService = (ConnectionWordSentenceTextService) ServiceUtil
            .getService(ConnectionWordSentenceTextService.class);

    /**
     * It sets the Text's object to the property.
     * 
     * @param text
     *        The object what contains the prepared text.
     */
    public void setText(List<BasicItem> text) {
        this.text = text;
    }

    /**
     * It retrieves that the text what should be imported is valid or isn't.
     * 
     * @return It is true when valid.
     */
    public boolean isValid() {
        // @TODO validating
        return true;
    }

    /**
     * It retrieves how many connections have been saved.
     * 
     * @return Zero when it is not imported.
     */
    public long getConnectionCounter() {
        return connectionCounter;
    }

    /**
     * It saves the Text entity and runs the importing process. The Word, Sentence, and Connection entities will be
     * saved to the database while importing.
     */
    public void runImport() {
        addText();
        importWordSentenceConnectionEntities();
    }

    /**
     * It saves the free entity: Sentence, Word and Connection. Connection contains the relations between these entities
     * and also contains the position of the Word in the Sentence.
     */
    private void importWordSentenceConnectionEntities() {
        int sentencePosition = 1;
        for (BasicItem basicItem : text) {
            long sentenceId = addSentence();
            sentencePosition++;

            if (basicItem.hasData()) {
                List<String> words = basicItem.getData();

                int wordPosition = 1;
                for (String word : words) {
                    long wordId = addWord(word);
                    addConnection(sentenceId, wordId, sentencePosition, wordPosition);
                    wordPosition++;
                }
            }
        }
    }

    /**
     * It creates a new Text entity.
     * 
     * @return The ID of the inserted Word entity.
     */
    private long addText() {
        TextService textService = (TextService) ServiceUtil.getService(TextService.class);
        TextModel textModel = new TextModel();
        textModel = textService.add(textModel);

        if (textModel != null) {
            this.textId = textModel.getTextId();
            return textModel.getTextId();
        }

        return 0L;
    }

    /**
     * It creates a new Sentence entity.
     * 
     * @return The ID of the inserted Sentence entity.
     */
    private long addSentence() {
        SentenceModel sentenceModel = new SentenceModel();
        sentenceModel = sentenceService.add(sentenceModel);

        if (sentenceModel != null) {
            return sentenceModel.getSentenceId();
        }

        return 0L;
    }

    /**
     * It saves a Word entity. It doesn't duplicate the word when it already exits.
     * 
     * @param word
     *        A single word as String.
     * @return The ID of the inserted or updated Word entity.
     */
    private long addWord(String word) {
        WordModel wordModel = new WordModel();
        wordModel.setWord(word);
        wordModel = wordService.save(wordModel);

        if (wordModel != null) {
            return wordModel.getWordId();
        }

        return -1;
    }

    /**
     * It creates a new Connection entity by previously saved text, sentence, and word. It also stores the position of
     * the word in the current sentence.
     * 
     * @param sentenceId
     *        ID of the saved Sentence entity.
     * @param wordId
     *        ID of the saved Word entity.
     * @param positionSentence
     *        position of the Word inside a sentence.
     * @param positionWord
     *        ID of the saved Word entity.
     */
    private void addConnection(long sentenceId, long wordId, int positionSentence, int positionWord) {
        ConnectionWordSentenceTextModel connectionModel = new ConnectionWordSentenceTextModel();
        connectionModel.setTextId(textId);
        connectionModel.setSentenceId(sentenceId);
        connectionModel.setWordId(wordId);
        connectionModel.setPositionSentence(positionSentence);
        connectionModel.setPositionWord(positionWord);
        connectionModel = connectionService.add(connectionModel);
        connectionCounter++;
    }
}
