package org.example.Utils;

import java.io.*;

public class FileUtils {
    public static OutputStream openFileForWriting(String path, Boolean append) throws IOException {
        return new FileOutputStream(path, append);
    }

    public static InputStream openFileForReading(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }
}
