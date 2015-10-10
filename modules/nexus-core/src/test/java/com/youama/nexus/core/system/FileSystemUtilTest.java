package com.youama.nexus.core.system;

import org.junit.*;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 0.1.0
 */
public class FileSystemUtilTest {

    @Test
    public void testSeparator() {
        assertTrue(File.separator.equals(FileSystemUtil.DS));
    }
}