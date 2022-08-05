package org.ReStudios.utitlitium;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
@SuppressWarnings("unused")
public class FileHandle extends File {
    public FileHandle(@NotNull String pathname) {
        super(pathname);
    }

    public FileHandle(String parent, @NotNull String child) {
        super(parent, child);
    }

    public FileHandle(File parent, @NotNull String child) {
        super(parent, child);
    }
    public FileHandle(@NotNull URI uri) {
        super(uri);
    }
    public InOutStreams open() throws IOException {
        try(InputStream is = new FileInputStream(this); OutputStream os = new FileOutputStream(this)){
            return new InOutStreams(os, is);
        }
    }
    public String read() throws IOException {
        return Files.readString(toPath());
    }
    public void write(String content) throws IOException {
        Files.write(toPath(), content.getBytes());
    }
    public BufferedImage getImage() throws IOException {
        return ImageIO.read(this);
    }
    public String getExtension(){
        return ArrayUtils.getLastItem(getName().split("\\."));
    }
}
