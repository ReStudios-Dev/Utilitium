package org.ReStudios.utitlitium;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
@SuppressWarnings("unused")
public class InOutStreams {
    OutputStream os;
    InputStream is;

    public InOutStreams(OutputStream os, InputStream is) {
        this.os = os;
        this.is = is;
    }

    public InOutStreams(OutputStream os) {
        this.os = os;
    }

    public InOutStreams(InputStream is) {
        this.is = is;
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public void write(int b) throws IOException {
        os.write(b);
    }

    public void write(byte @NotNull [] b) throws IOException {
        os.write(b);
    }

    public void write(byte @NotNull [] b, int off, int len) throws IOException {
        os.write(b, off, len);
    }

    public void flushOutput() throws IOException {
        os.flush();
    }

    public int read() throws IOException {
        return getIs().read();
    }

    public int read(byte @NotNull [] b) throws IOException {
        return getIs().read(b);
    }
    public int read(byte @NotNull [] b, int off, int len) throws IOException {
        return getIs().read(b, off, len);
    }

    public byte[] readAllBytes() throws IOException {
        return getIs().readAllBytes();
    }

    public byte[] readNBytes(int len) throws IOException {
        return getIs().readNBytes(len);
    }

    public long skip(long n) throws IOException {
        return getIs().skip(n);
    }

    public void skipNBytes(long n) throws IOException {
        getIs().skipNBytes(n);
    }

    public int availableInput() throws IOException {
        return getIs().available();
    }

    public void closeInput() throws IOException {
        getIs().close();
    }
    public void closeOutput() throws IOException {
        getOs().close();
    }
    public void close() throws IOException {
        closeInput();
        closeOutput();
    }

}
