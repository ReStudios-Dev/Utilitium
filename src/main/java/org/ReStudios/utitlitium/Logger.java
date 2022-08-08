package org.ReStudios.utitlitium;


import java.io.PrintStream;
@SuppressWarnings("unused")
public class Logger extends PrintStream implements Thread.UncaughtExceptionHandler {
    public boolean isErrorStream = false;
    private final PrintStream original;
    int add = 0;

    public Logger(PrintStream stream) {
        super(stream);
        this.original = stream;
    }
    public void print(String s) {
        StackTraceElement el = Utilitium.getCaller(2 + this.add);
        String var10000 = el.getClassName();
        String clazz = var10000 + ":" + el.getLineNumber();
        this.original.printf("[%s %s %s] %s", Thread.currentThread().getName(), this.isErrorStream ? "ERROR" : "INFO", clazz, s);
    }

    public void print(int i) {
        this.add = 1;
        this.print(String.valueOf(i));
        this.add = 0;
    }

    public void print(double d) {
        this.add = 1;
        this.print(String.valueOf(d));
        this.add = 0;
    }

    public void print(char c) {
        this.add = 1;
        this.print(String.valueOf(c));
        this.add = 0;
    }

    public void print(long l) {
        this.add = 1;
        this.print(String.valueOf(l));
        this.add = 0;
    }

    public void print(float f) {
        this.add = 1;
        this.print(String.valueOf(f));
        this.add = 0;
    }

    public void print(boolean b) {
        this.add = 1;
        this.print(String.valueOf(b));
        this.add = 0;
    }

    public void print(Object obj) {
        this.add = 1;
        this.print(String.valueOf(obj));
        this.add = 0;
    }

    public void print(char [] s) {
        this.add = 1;
        this.print(String.valueOf(s));
        this.add = 0;
    }

    public static void setupPrints() {
        System.setOut(new Logger(System.out));
        Logger stream = new Logger(System.err);
        stream.isErrorStream = true;
        System.setErr(stream);
        Thread.currentThread().setUncaughtExceptionHandler(stream);
    }

    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace(this.original);
    }
}
