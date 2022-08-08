package org.ReStudios.utitlitium;
@SuppressWarnings("unused")
public class MathUtils {

    /**
     * Clamp
     * @param min min integer
     * @param max max integer
     * @param val current integer
     * @return value between min & max
     */
    public static int clamp(int min, int max, int val){
        return Math.max(min, Math.min(val, max));
    }

    /**
     * Clamp
     * @param min min integer
     * @param max max integer
     * @param val current integer
     * @return value between min & max
     */
    public static float clamp(float min, float max, float val){
        return Math.max(min, Math.min(val, max));
    }

    /**
     * Clamp
     * @param min min integer
     * @param max max integer
     * @param val current integer
     * @return value between min & max
     */
    public static double clamp(double min, double max, double val){
        return Math.max(min, Math.min(val, max));
    }
    
   

    /**
     * Cast int in range to some range (from <a href="https://www.arduino.cc/reference/en/language/functions/math/map">arduino</a>)
     * @param x current value
     * @param in_min input min
     * @param in_max input max
     * @param out_min output min
     * @param out_max output max
     * @return The mapped value.
     */
    public static long map(long x, long in_min, long in_max, long out_min, long out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}