package org.ReStudios.utitlitium;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

@SuppressWarnings("unused")
public class FileHandle extends File {

    public static FileHandle file(String path){ return new FileHandle(path); }

    public static FileHandle child(String parent, String child){ return new FileHandle(parent,child); }

    public static FileHandle child(File parent, String child){ return new FileHandle(parent,child); }

    public FileHandle(String pathname) { super(pathname); }

    public FileHandle(String parent, String child) { super(parent, child); }

    public FileHandle(File parent, String child) { super(parent, child); }
    public FileHandle(URI uri) {
        super(uri);
    }

    /**
     * Opens a data channel to write or read this file
     * @return file's data channel
     * @throws IOException if file not exists/ can't close stream
     */
    public DataChannel open() throws IOException {
        try(InputStream is = new FileInputStream(this); OutputStream os = new FileOutputStream(this)){
            return new DataChannel(os, is);
        }
    }

    /**
     * Reads whole file
     * @return file content
     * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
     */
    public String read() throws IOException {
        return Files.readString(toPath());
    }

    /**
     * Writes content to a file
     * @param content to be written
     * @throws IOException if an I/O error occurs while writing
     */
    public void write(String content) throws IOException {
        Files.write(toPath(), content.getBytes());
    }

    /**
     * Appends content to specific file
     * @param content to append
     * @throws IOException if an I/O error occurs while reading/writing
     */
    public void append(String content) throws IOException {
        Files.write(toPath(),content.getBytes(), StandardOpenOption.APPEND);
    }
    public BufferedImage getImage() throws IOException {
        return ImageIO.read(this);
    }

    /**
     * Returns file extension
     * @return file extension
     */
    public String getExtension(){
        return ArrayUtils.getLastItem(getName().split("\\."));
    }
}
