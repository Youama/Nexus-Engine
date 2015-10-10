package com.youama.nexus.wordplex.importer;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.youama.nexus.core.item.BasicItem;
import com.youama.nexus.core.system.NexusCoreUtil;
import com.youama.nexus.core.system.ServiceUtil;
import com.youama.nexus.wordplex.model.word.WordModel;

public class ImporterFacadeTest {

    List<String> drivers = ServiceUtil.getInstalledDrivers();

    @Before
    public void setUp() {
        NexusCoreUtil.initServices("nexus-module-wordplex", WordModel.class);
    }

    @Test
    public void testImportTextStepByStep() {
        BasicItem item1 = new BasicItem();
        item1.addItem("Lorem");
        item1.addItem("ipsum");
        item1.addItem("sit");
        item1.addItem("dolor");
        item1.addItem("amet");

        BasicItem item2 = new BasicItem();
        item2.addItem("Consectetur");
        item2.addItem("amet");
        item2.addItem("elit");

        List<BasicItem> text = new ArrayList<BasicItem>();
        text.add(item1);
        text.add(item2);

        ImporterFacade importApi = new ImporterFacade();
        importApi.importTextStepByStep(text);

        assertTrue(importApi.getImportTextStepByStepStatus());
    }
}
