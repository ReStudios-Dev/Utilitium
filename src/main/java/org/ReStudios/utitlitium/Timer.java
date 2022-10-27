package org.ReStudios.utitlitium;
@SuppressWarnings("unused")
public class Timer {
    long startTime;
    boolean running;
    public Timer() {
        startTime = 0;
    }
    public void run(){
        startTime = System.currentTimeMillis();
    }
    public long getTime(){
        return System.currentTimeMillis() - startTime;
    }
    public long stop(){
        return System.currentTimeMillis() - startTime;
    }
}
