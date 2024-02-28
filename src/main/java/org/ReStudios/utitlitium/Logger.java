package org.ReStudios.utitlitium;


import java.io.PrintStream;
@SuppressWarnings("unused")
public class Logger extends PrintStream implements Thread.UncaughtExceptionHandler {
    public boolean isErrorStream = false;
    private final PrintStream originalPrintStream;
    int reflectionMethodCallDepthOffset = 0;
    Colorizium.AColorProvider colorProvider;
    String colorPrefix, colorSuffix;
    boolean colors;

    public Logger(PrintStream stream) {
        super(stream);
        this.originalPrintStream = stream;
    }

    public Logger(PrintStream stream, Colorizium.AColorProvider colorProvider, String colorPrefix, String colorSuffix) {
        super(stream);
        this.originalPrintStream = stream;
        this.colorProvider = colorProvider;
        this.colorPrefix = colorPrefix;
        this.colorSuffix = colorSuffix;
        colors = true;
    }

    public void print(String stringToPrint) {
        StackTraceElement traceElement = Utilitium.getCaller(2 + this.reflectionMethodCallDepthOffset);
        String className = traceElement.getClassName();
        String clazz = className + ":" + traceElement.getLineNumber();
        if(colors){
            stringToPrint = Colorizium.apply(stringToPrint, colorPrefix, colorProvider, colorSuffix);
        }
        this.originalPrintStream.printf("[%s %s %s] %s", Thread.currentThread().getName(), this.isErrorStream ? "ERROR" : "INFO", clazz, stringToPrint);
    }

    public void print(int i) {
        this.reflectionMethodCallDepthOffset = 1;
        this.print(String.valueOf(i));
        this.reflectionMethodCallDepthOffset = 0;
    }

    public void print(double d) {
        this.reflectionMethodCallDepthOffset = 1;
        this.print(String.valueOf(d));
        this.reflectionMethodCallDepthOffset = 0;
    }

    public void print(char c) {
        this.reflectionMethodCallDepthOffset = 1;
        this.print(String.valueOf(c));
        this.reflectionMethodCallDepthOffset = 0;
    }

    public void print(long l) {
        this.reflectionMethodCallDepthOffset = 1;
        this.print(String.valueOf(l));
        this.reflectionMethodCallDepthOffset = 0;
    }

    public void print(float f) {
        this.reflectionMethodCallDepthOffset = 1;
        this.print(String.valueOf(f));
        this.reflectionMethodCallDepthOffset = 0;
    }

    public void print(boolean b) {
        this.reflectionMethodCallDepthOffset = 1;
        this.print(String.valueOf(b));
        this.reflectionMethodCallDepthOffset = 0;
    }

    public void print(Object obj) {
        this.reflectionMethodCallDepthOffset = 1;
        this.print(String.valueOf(obj));
        this.reflectionMethodCallDepthOffset = 0;
    }

    public void print(char [] s) {
        this.reflectionMethodCallDepthOffset = 1;
        this.print(String.valueOf(s));
        this.reflectionMethodCallDepthOffset = 0;
    }

    /**
     * Setup colored prints
     * @see Colorizium
     * @param colorPrefix Color prefix
     * @param provider Color provider (can be null to default)
     * @param colorSuffix Color suffix
     */
    public static void setupPrints(String colorPrefix, Colorizium.AColorProvider provider, String colorSuffix) {
        System.setOut(new Logger(System.out, provider, colorPrefix, colorSuffix));
        Logger customErrorLoggerStream = new Logger(System.err, provider, colorPrefix, colorSuffix);
        customErrorLoggerStream.isErrorStream = true;
        System.setErr(customErrorLoggerStream);
        Thread.currentThread().setUncaughtExceptionHandler(customErrorLoggerStream);
    }

    /**
     * Setup plain prints
     */
    public static void setupPrints(){
        System.setOut(new Logger(System.out));
        Logger customErrorLoggerStream = new Logger(System.err);
        customErrorLoggerStream.isErrorStream = true;
        System.setErr(customErrorLoggerStream);
        Thread.currentThread().setUncaughtExceptionHandler(customErrorLoggerStream);
    }

    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace(this.originalPrintStream);
    }
}
