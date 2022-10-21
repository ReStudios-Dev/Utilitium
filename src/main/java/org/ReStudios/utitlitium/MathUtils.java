package org.ReStudios.utitlitium;

import org.ReStudios.utitlitium.vectors.Vector2f;

import java.util.Collection;

import static org.ReStudios.utitlitium.ArrayUtils.getSum;

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

    /**
     * This function takes a set of XY coordinates, a distance and a rotation argument.
     * It returns XY coordinates of the point that is the given distance away
     * from the given point, in the given direction.
     * @param point Start position
     * @param distance Distance to output position
     * @param angle direction to output position
     * @return Instance of vector
     */
    public static Vector2f getPositionFromDistanceRotation(Vector2f point, float distance, float angle){
        float a = (float) Math.toRadians(90 - angle);
        float dx = (float) (Math.cos(a) * distance);
        float dy = (float) (Math.sin(a) * distance);
        return new Vector2f(point.x()+dx, point.y()+dy);
    }

    /**
     * Get average value from float list
     * @param floatValues Float list
     * @return Average value
     */
    public static float getAverageValue(Collection<Float> floatValues){
        float sum = getSum(floatValues);
        return sum/floatValues.size();
    }


}