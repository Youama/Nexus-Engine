package com.youama.nexus.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * This package protected class reads a single PDF file.
 * 
 * @since 0.1.0
 * @author david
 */
class PdfReader {

    /**
     * File path to the target PDF file.
     */
    private String filePath;

    /**
     * It sets the file path to property.
     * 
     * @param filePath
     *        File path to the target PDF file.
     */
    void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * It retrieves the content of PDF file.
     * 
     * @return The String content of the PDF file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    String getContentFromPdf() throws FileNotFoundException, IOException {
        return getText(getPdfDocument(getInputStream()));
    }

    /**
     * It retrieves the input stream for the PDF file.
     * 
     * @return Input stream.
     * @throws FileNotFoundException
     */
    private FileInputStream getInputStream() throws FileNotFoundException {
        File file = new File(filePath);
        return new FileInputStream(file);
    }

    /**
     * It retrieves the PDF document by the input stream.
     * 
     * @param inputStream
     *        Input stream of the PDF file.
     * @return PDF document.
     * @throws IOException
     */
    private PDDocument getPdfDocument(FileInputStream inputStream) throws IOException {
        PDFParser parser = new PDFParser(inputStream);
        parser.parse();
        COSDocument cosDoc = parser.getDocument();

        return new PDDocument(cosDoc);
    }

    /**
     * It retrieves the text content from the PDF file by the PDF document.
     * 
     * @param pdfDocument
     *        The loaded PDF document.
     * @return The content of the PDF file.
     * @throws IOException
     */
    private String getText(PDDocument pdfDocument) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        return pdfStripper.getText(pdfDocument);
    }

}
