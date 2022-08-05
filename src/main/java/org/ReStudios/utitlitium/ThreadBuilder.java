package org.ReStudios.utitlitium;
@SuppressWarnings("unused")
public class ThreadBuilder {
    private final Thread thread;

    public ThreadBuilder(Runnable task) {
        this.thread = new Thread(task);
    }

    public ThreadBuilder setPriority(int priority) {
        this.thread.setPriority(priority);
        return this;
    }

    public ThreadBuilder setName(String name) {
        this.thread.setName(name);
        return this;
    }

    public Thread build() {
        return this.thread;
    }
    public ThreadBuilder setHandler(Thread.UncaughtExceptionHandler handler) {
        this.thread.setUncaughtExceptionHandler(handler);
        return this;
    }
}