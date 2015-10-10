package com.youama.nexus.wordplex.importer;

import java.util.List;

import com.youama.nexus.core.item.BasicItem;
import com.youama.nexus.wordplex.importer.text.StepByStepImporter;

/**
 * This API imports text, sentences and words.
 * 
 * @author David Belicza
 * @since 0.1.0
 */
public class ImporterFacade {

    /**
     * The StepByStepImporter object.
     */
    private StepByStepImporter stepByStepImporter;

    /**
     * It retrieves the status of the "step by step importer" process. It doesn't look for the logical results but basic
     * data import results.
     * 
     * @return It is true when it finished successfully.
     */
    public boolean getImportTextStepByStepStatus() {
        if (stepByStepImporter.getConnectionCounter() > 0) {
            return true;
        }

        return false;
    }

    /**
     * It imports text data to the database by step by step importer. This importer imports each word separately. It is
     * the slowest and most secure import type.
     * 
     * @param text
     *        The text what should be imported. Text must be prepared as BasicItem.
     */
    public void importTextStepByStep(List<BasicItem> text) {
        stepByStepImporter = new StepByStepImporter();
        stepByStepImporter.setText(text);

        if (stepByStepImporter.isValid()) {
            stepByStepImporter.runImport();
        }
    }
}
