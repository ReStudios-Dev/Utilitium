package org.ReStudios.utitlitium;

import org.ReStudios.utitlitium.vectors.Vector2d;
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
     * Cast number in range to some range (from <a href="https://www.arduino.cc/reference/en/language/functions/math/map">arduino</a>)
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
    public static int map(int x, int in_min, int in_max, int out_min, int out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public static float map(float x, float in_min, float in_max, float out_min, float out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public static double map(double x, double in_min, double in_max, double out_min, double out_max){
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

    /**
     * Get the factorial of a number
     * @param x Input
     * @return Factorial
     */
    public static int factorial(int x) {
        int result = 1;
        for (int i = 2; i <= x; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Function for calculating the binomial coefficient C(n, r)
     * @param n r
     * @param r r
     * @return Binomial coefficient C(n, r)
     */
    public static int C(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    /**
     * Function to calculate a point on a Bezier curve
     * <a href="https://en.wikipedia.org/wiki/B%C3%A9zier_curve">Bézier curve</a>
     * @param points Points
     * @param t Current time (0.0 - 1.0)
     * @return Current point
     */
    public static Vector2d getBezierPoint(Vector2d[] points, double t) {
        Vector2d retPos = new Vector2d(0, 0);
        int n = points.length - 1;
        for (int i = 0; i <= n; i++) {
            retPos.add(Math.pow(1 - t, i) * Math.pow(t, n - i) * points[i].x() * C(n, i),
                    Math.pow(1 - t, i) * Math.pow(t, n - i) * points[i].y() * C(n, i));
        }
        return retPos;
    }
}