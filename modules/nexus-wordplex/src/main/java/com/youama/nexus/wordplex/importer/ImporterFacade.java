package com.youama.nexus.wordplex.importer;

import java.util.List;

import com.youama.nexus.core.item.BasicItem;
import com.youama.nexus.wordplex.importer.text.TextStepByStepImporter;

/**
 * @author David Belicza
 * @since 2015.09.27.
 */
public class ImporterFacade {

    public boolean importTextStepByStep(List<BasicItem> text) {
        TextStepByStepImporter importer = new TextStepByStepImporter();
        importer.setText(text);

        if (importer.isValid()) {
            return importer.runImport();
        }

        return false;
    }
}
