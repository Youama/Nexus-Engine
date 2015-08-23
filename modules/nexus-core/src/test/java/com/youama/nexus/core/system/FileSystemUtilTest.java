package com.youama.nexus.core.system;

import org.junit.*;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.08.23.
 */
public class FileSystemUtilTest {

    @Test
    public void testSeparator() {
        assertTrue(File.separator.equals(FileSystemUtil.DS));
    }

    @Test
    public void testGetBaseDirectory() {
        assertNotNull(FileSystemUtil.getBaseDirectory());
    }
}