package org.ReStudios.utitlitium;
@SuppressWarnings("unused")
public class ThreadBuilder {
    private final Thread thread;

    public ThreadBuilder(Runnable task) {
        this.thread = new Thread(task);
    }

    /**
     * Sets thread's priority
     * @param priority to be set
     * @return this
     */
    public ThreadBuilder setPriority(int priority) {
        this.thread.setPriority(priority);
        return this;
    }

    /**
     * Sets thread's name
     * @param name to be set
     * @return this
     */
    public ThreadBuilder setName(String name) {
        this.thread.setName(name);
        return this;
    }

    /**
     * Sets thread's exception handler
     * @param handler to be set
     * @return this
     */
    public ThreadBuilder setHandler(Thread.UncaughtExceptionHandler handler) {
        this.thread.setUncaughtExceptionHandler(handler);
        return this;
    }

    /**
     * Builds this thread
     * @return thread with applied parameters
     */
    public Thread build() {
        return this.thread;
    }

    /**
     * Builds this thread and runs it
     * @return thread with applied parameters
     */
    public Thread buildAndRun(){
        this.thread.start();
        return this.thread;
    }
}