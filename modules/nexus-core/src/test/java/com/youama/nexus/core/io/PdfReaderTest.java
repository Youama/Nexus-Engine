package com.youama.nexus.core.io;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class PdfReaderTest {

    @Test
    public void testGetContentFromPdfOk() throws FileNotFoundException, IOException {
        String filePath = this.getClass().getResource("/simple1.pdf").getPath();

        PdfReader reader = new PdfReader();
        reader.setFilePath(filePath);
        String content = reader.getContentFromPdf();
        assertTrue(content != null && content.length() > 0);
    }

    @Test(expected = FileNotFoundException.class)
    public void testGetContentFromPdfError() throws FileNotFoundException, IOException {
        PdfReader reader = new PdfReader();
        reader.setFilePath("fake.pdf");
        reader.getContentFromPdf();
    }
}
