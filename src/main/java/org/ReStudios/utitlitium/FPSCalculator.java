package org.ReStudios.utitlitium;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class FPSCalculator {
    private int fps = 0;
    private final ArrayList<Float> history = new ArrayList<>();
    private long lastTime = System.currentTimeMillis();
    private int frames = 0;

    public int currentFPS(){
        return this.fps;
    }
    public int averageFPS(){
        return Math.round(MathUtils.getAverageValue(history));
    }

    public void updateFps() {
        long currentTime = System.currentTimeMillis();
        frames++;
        if (currentTime - lastTime >= 50) {
            float framesPerSecond = (float)(frames / ((currentTime - lastTime) / 1000.0));
            ArrayUtils.addLimited(history, 5, framesPerSecond);
            history.add(framesPerSecond);
            fps = (int) framesPerSecond;
            frames = 0;
            lastTime = currentTime;
        }
    }
}
