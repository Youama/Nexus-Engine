package com.youama.nexus.core.io;

import java.io.IOException;

import com.youama.nexus.core.Log;

public class IOFacade {

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
