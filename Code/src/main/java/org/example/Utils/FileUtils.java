package org.example.Utils;

import java.io.*;

public class FileUtils {

    /**
     * Opens a file for writing.
     *
     * @param path the path to the file to open
     * @param append if true, then bytes will be written to the end of the file rather than the beginning
     * @return an OutputStream for writing to the file
     * @throws IOException if an I/O error occurs while opening the file
     */
    public static OutputStream openFileForWriting(String path, Boolean append) throws IOException {
        return new FileOutputStream(path, append);
    }

    /**
     * Opens a file for reading.
     *
     * @param path the path to the file to open
     * @return an InputStream for reading from the file
     * @throws FileNotFoundException if the file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading
     */
    public static InputStream openFileForReading(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }
}
