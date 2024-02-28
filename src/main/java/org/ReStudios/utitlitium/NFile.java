package org.ReStudios.utitlitium;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

@SuppressWarnings("unused")
public class NFile extends File {

    public NFile(String pathname) {
        super(pathname);
    }

    public NFile(String parent, String child) {
        super(parent, child);
    }

    public NFile(File parent, String child) {
        super(parent, child);
    }

    public NFile(URI uri) {
        super(uri);
    }
    public NFile(File f){
        super(f.getAbsolutePath());
    }

    @Override
    public NFile getParentFile() {
        return new NFile(super.getParentFile());
    }

    @Override
    public NFile getAbsoluteFile() {
        return new NFile(super.getAbsoluteFile());
    }

    @Override
    public NFile getCanonicalFile() throws IOException {
        return new NFile(super.getCanonicalFile());
    }

    @Override
    public NFile[] listFiles(FilenameFilter filter) {
        return fileArrayToNFile(super.listFiles(filter));
    }

    @Override
    public NFile[] listFiles() {
        return fileArrayToNFile(super.listFiles());
    }

    @Override
    public File[] listFiles(FileFilter filter) {
        return fileArrayToNFile(super.listFiles(filter));
    }

    public byte[] readBytes() throws IOException {
        return Files.readAllBytes(toPath());
    }
    public String readString(Charset charset) throws IOException {
        return new String(readBytes(), charset);
    }
    public String readString() throws IOException {
        return readString(Charset.defaultCharset());
    }
    public NFile write(byte[] bytes, int offset, int length) throws IOException {
        try(FileOutputStream fos = openOutput()){
            fos.write(bytes, offset, length);
        }
        return this;
    }
    public NFile write(byte[] bytes) throws IOException{
        return write(bytes, 0, bytes.length);
    }
    public NFile write(String str, Charset charset) throws IOException {
        return write(str.getBytes(charset));
    }
    public NFile write(String str) throws IOException {
        return write(str, Charset.defaultCharset());
    }

    public FileOutputStream openOutput() throws FileNotFoundException {
        return new FileOutputStream(this);
    }

    public static NFile[] fileArrayToNFile(File[] files){
        if(files == null) return new NFile[0];
        NFile[] fList = new NFile[files.length];
        for (int i = 0; i < files.length; i++) {
            fList[i] = new NFile(files[i]);
        }
        return fList;

    }

    public NFile child(String child) {
        return new NFile(this, child);
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
        if(!getName().contains(".")){
            return "";
        }
        return ArrayUtils.getLastItem(getName().split("\\."));
    }
}
