package com.youama.nexus.wordplex.importer;

import java.util.List;

import com.youama.nexus.core.item.BasicItem;
import com.youama.nexus.wordplex.importer.text.StepByStepImporter;

/**
 * @author David Belicza
 * @since 2015.09.27.
 */
public class ImporterFacade {

    private StepByStepImporter stepByStepImporter;

    public boolean getImportTextStepByStepStatus() {
        if (stepByStepImporter.getConnectionCounter() > 0) {
            return true;
        }

        return false;
    }

    public void importTextStepByStep(List<BasicItem> text) {
        stepByStepImporter = new StepByStepImporter();
        stepByStepImporter.setText(text);

        if (stepByStepImporter.isValid()) {
            stepByStepImporter.runImport();
        }
    }
}
