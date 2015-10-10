package com.youama.nexus.core.io;

import java.io.IOException;

import com.youama.nexus.core.Log;

/**
 * This class handles the IO operations.
 * 
 * @since 0.1.0
 * @author david
 */
public class IOFacade {

    /**
     * It reads a PDF file and it retrieves its content. If file not exists it returns empty string and logs the
     * exception.
     * 
     * @param filePath
     *        The file path to the target PDF file.
     * @return The content of the PDF file. When it doesn't have content or the file doesn't exist it will be an empty
     *         string.
     */
    @SuppressWarnings("finally")
    public String getPdfContent(String filePath) {
        PdfReader reader = new PdfReader();
        reader.setFilePath(filePath);
        String content = null;

        try {
            content = reader.getContentFromPdf();
        } catch (IOException e) {
            Log.warning(e);
        } finally {
            if (content != null && content.length() > 0) {
                return content;
            } else {
                return "";
            }
        }
    }
}
