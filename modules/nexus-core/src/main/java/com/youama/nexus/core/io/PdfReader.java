package com.youama.nexus.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

class PdfReader {

    private String filePath;

    void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    String getContentFromPdf() throws FileNotFoundException, IOException {
        return getText(getPdfDocument(getInputStream()));
    }

    private FileInputStream getInputStream() throws FileNotFoundException {
        File file = new File(filePath);
        return new FileInputStream(file);
    }

    private PDDocument getPdfDocument(FileInputStream inputStream) throws IOException {
        PDFParser parser = new PDFParser(inputStream);
        parser.parse();
        COSDocument cosDoc = parser.getDocument();

        return new PDDocument(cosDoc);
    }

    private String getText(PDDocument pdfDocument) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(5);

        return pdfStripper.getText(pdfDocument);
    }

}
