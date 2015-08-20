package com.youama.nexus.core.system;

import com.youama.nexus.core.Log;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author David Belicza
 * @since 2015.08.20.
 */
final public class FileSystemUtil {

    public static String DS = File.separator;

    private static String baseDirectory;

    public static String getBaseDirectory() {
        if (baseDirectory == null) {
            setBaseDirectory();
        }

        return baseDirectory;
    }

    private static void setBaseDirectory() {
        Path paths;

        try {
            //@todo only one cd .. needed in the production version
            paths = Paths.get(FileSystemUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            baseDirectory = paths.getParent().getParent().getParent().getParent().toString();
        } catch (URISyntaxException e) {
            Log.warning(e);
        }
    }
}
