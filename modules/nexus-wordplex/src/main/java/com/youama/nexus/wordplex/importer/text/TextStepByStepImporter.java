package com.youama.nexus.wordplex.importer.text;

import java.util.List;

import com.youama.nexus.core.item.BasicItem;
import com.youama.nexus.core.system.ServiceUtil;
import com.youama.nexus.wordplex.model.ConnectionWordSentenceTextModel;
import com.youama.nexus.wordplex.model.sentence.SentenceModel;
import com.youama.nexus.wordplex.model.text.TextModel;
import com.youama.nexus.wordplex.model.word.WordModel;
import com.youama.nexus.wordplex.service.ConnectionWordSentenceTextService;
import com.youama.nexus.wordplex.service.sentence.SentenceService;
import com.youama.nexus.wordplex.service.text.TextService;
import com.youama.nexus.wordplex.service.word.WordService;

/**
 * @author David Belicza
 * @since 2015.09.27.
 */
public class TextStepByStepImporter {

    private long textId = -1;

    private int isValid = 0;

    private List<BasicItem> text = null;

    private SentenceService sentenceService = (SentenceService) ServiceUtil.getService(SentenceService.class);
    private WordService wordService = (WordService) ServiceUtil.getService(WordService.class);
    private ConnectionWordSentenceTextService connectionService = (ConnectionWordSentenceTextService) ServiceUtil
            .getService(ConnectionWordSentenceTextService.class);

    public void setText(List<BasicItem> text) {
        this.text = text;
    }

    public boolean isValid() {
        return false;
    }

    private boolean getImportSate() {
        if (isValid == 0) {
            return isValid();
        } else if (isValid == 1) {
            return true;
        }

        return false;
    }

    public boolean runImport() {
        addText();
        importWordSentenceConnectionEntities();

        return getImportSate();
    }

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

    private long addSentence() {
        SentenceModel sentenceModel = new SentenceModel();
        sentenceModel = sentenceService.add(sentenceModel);

        if (sentenceModel != null) {
            return sentenceModel.getSentenceId();
        }

        return 0L;
    }

    private long addWord(String word) {
        WordModel wordModel = new WordModel();
        wordModel.setWord(word);
        wordModel = wordService.save(wordModel);

        if (wordModel != null) {
            return wordModel.getWordId();
        }

        return 0L;
    }

    private void addConnection(long sentenceId, long wordId, int positionSentence, int positionWord) {
        ConnectionWordSentenceTextModel connectionModel = new ConnectionWordSentenceTextModel();
        connectionModel.setTextId(textId);
        connectionModel.setSentenceId(sentenceId);
        connectionModel.setWordId(wordId);
        connectionModel.setPositionSentence(positionSentence);
        connectionModel.setPositionWord(positionWord);
        connectionModel = connectionService.add(connectionModel);
    }
}
