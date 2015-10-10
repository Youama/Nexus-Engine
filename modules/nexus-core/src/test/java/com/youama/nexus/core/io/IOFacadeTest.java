package com.youama.nexus.core.io;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IOFacadeTest {

    @Test
    public void testGetPdfContent() {
        String filePath = this.getClass().getResource("/simple1.pdf").getPath();
        IOFacade io = new IOFacade();
        String content = io.getPdfContent(filePath);
        assertTrue(content != null && content.length() > 0);
    }
}
