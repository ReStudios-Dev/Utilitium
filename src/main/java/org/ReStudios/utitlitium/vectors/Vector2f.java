package org.ReStudios.utitlitium.vectors;

import org.ReStudios.utitlitium.ArrayUtils;
import org.ReStudios.utitlitium.StringUtils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@SuppressWarnings("unused")
public class Vector2f {
    /**
     * X & Y values
     */
    public float x, y;

    /**
     * Constructor default values
     */
    public Vector2f(){
        x=0;
        y=0;
    }

    /**
     * Constructor with custom values
     * @param x X value
     * @param y Y value
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get X value
     * @return X value
     */
    public float x() {
        return x;
    }

    /**
     * Set custom X value
     * @param x new X value
     * @return Instance of vector
     */
    public Vector2f setX(float x) {
        this.x = x;
        return this;
    }

    /**
     * Get Y value
     * @return Y value
     */
    public float y() {
        return y;
    }

    /**
     * Set custom Y value
     * @param y new Y value
     * @return Instance of vector
     */
    public Vector2f setY(float y) {
        this.y = y;
        return this;
    }

    /**
     * Add values from other vector
     * @param v other Vector
     * @return Instance of vector
     */
    public Vector2f add(Vector2f v){
        this.x += v.x();
        this.y += v.y();
        return this;
    }

    /**
     * Add values from direct values
     * @param x add X value
     * @param y add Y value
     * @return Instance of vector
     */
    public Vector2f add(float x, float y){
        return add(new Vector2f(x,y));
    }

    /**
     * Add values from 1 value.
     * Add a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 5 and 8,
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 7 and 10
     * @param add value to add
     * @return Instance of vector
     */
    public Vector2f add(float add){
        return add(add, add);
    }

    /**
     * Subtract values from other vector
     * @param v other Vector
     * @return Instance of vector
     */
    public Vector2f sub(Vector2f v){
        this.x -= v.x();
        this.y -= v.y();
        return this;
    }

    /**
     * Subtract values from direct values
     * @param x subtract X value
     * @param y subtract Y value
     * @return Instance of vector
     */
    public Vector2f sub(float x, float y){
        return sub(new Vector2f(x,y));
    }

    /**
     * Subtract values from 1 value.
     * Subtract a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 5 and 8,
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 3 and 6
     * @param sub value to subtract
     * @return Instance of vector
     */
    public Vector2f sub(float sub){
        return sub(sub, sub);
    }

    /**
     * Multiply values from other vector
     * @param v other Vector
     * @return Instance of vector
     */
    public Vector2f mul(Vector2f v){
        this.x *= v.x();
        this.y *= v.y();
        return this;
    }

    /**
     * Multiply values from direct values
     * @param x multiply X value
     * @param y multiply Y value
     * @return Instance of vector
     */
    public Vector2f mul(float x, float y){
        return mul(new Vector2f(x,y));
    }

    /**
     * Multiply values from 1 value.
     * Multiply a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 5 and 8,
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 10 and 16
     * @param mul value to multiply
     * @return Instance of vector
     */
    public Vector2f mul(float mul){
        return mul(mul, mul);
    }

    /**
     * Divide values from other vector
     * @param v other Vector
     * @return Instance of vector
     */
    public Vector2f div(Vector2f v){
        if(v.x() != 0)this.x /= v.x();
        if(v.y() != 0)this.y /= v.y();
        return this;
    }

    /**
     * Divide values from direct values
     * @param x divide X value
     * @param y divide Y value
     * @return Instance of vector
     */
    public Vector2f div(float x, float y){
        return div(new Vector2f(x,y));
    }

    /**
     * Divide values from 1 value.
     * Divide a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 4 and 8,
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 2 and 4
     * @param div value to divide
     * @return Instance of vector
     */
    public Vector2f div(float div){
        return div(div, div);
    }

    /**
     * Raise the power of the current vector to the values of another vector
     * (The X of the current vector is raised to the power of the X value of another vector,
     * and so on with the rest of the values)
     * @param v other vector
     * @return Instance of vector
     */
    public Vector2f pow(Vector2f v){
        this.x = (float) Math.pow(this.x, v.x());
        this.y = (float) Math.pow(this.y, v.y());
        return this;
    }

    /**
     * Raise the power from direct values
     * @param x pow X value
     * @param y pow Y value
     * @return Instance of vector
     */
    public Vector2f pow(float x, float y){
        return pow(new Vector2f(x,y));
    }

    /**
     * Raise the power values from 1 value.
     * Raise the power a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 4 and 8,
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 16 and 64
     * *In other words: x = x^pow*
     * @param pow value to divide
     * @return Instance of vector
     */
    public Vector2f pow(float pow){
        return pow(pow, pow);
    }

    /**
     * Clone current vector
     * Will create exactly the same vector, but as a separate object.
     * This is useful if you need to save values if you change them later (for example).
     * @return Instance of copied vector
     */
    @SuppressWarnings("all")
    public Vector2f clone() {
        return new Vector2f(x, y);
    }

    /**
     * Any vector, when normalized, only changes its magnitude, not its direction.
     * Also, every vector pointing in the same direction is normalized to the same vector
     * (because magnitude and direction uniquely define a vector).
     * In other words, divides the vector to the minimum values which brings the vector to the "direction"
     * @return Instance of vector
     */
    public Vector2f normalize(){
        float i = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return clone().div(i);
    }

    /**
     * Convert vector to string
     * FE. Useful for debug
     * @return Stringed vector
     */
    @Override
    public String toString() {
        return "{"+x+";"+y+"}";
    }

    /**
     * Convert vector to serialized string
     * For example, for storage outside the application
     * @return Serialized vector
     */
    public String serializeToString(){
        return x+"!"+y;
    }

    /**
     * Convert vector to map
     * For example, for storage outside the application
     * @return Serialized vector
     */
    public Map<String, Object> serializeToMap(){
        return ArrayUtils.createMap(
                "x", x,
                "y", y
        );
    }

    /**
     * Convert vector to byte list
     * For example, for storage outside the application
     * @param charset Custom charset
     * @return Serialized vector
     */
    public byte[] serializeToBytes(Charset charset){
        return serializeToString().getBytes(charset);
    }

    /**
     * Convert vector to byte list with default charset (UTF-8)
     * For example, for storage outside the application
     * @return Serialized vector
     */
    public byte[] serializeToBytes(){
        return serializeToString().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Deserialize byte list to Vector class
     * @param bytes byte list
     * @return Instance of vector
     * @throws DeserializeException Invalid or incorrect byte list value
     */
    public static Vector2f deserializeFromBytes(byte[] bytes) throws DeserializeException {
        return deserializeFromString(new String(bytes));
    }

    /**
     * Deserialize map list to Vector class
     * @return Instance of vector
     * @throws DeserializeException Invalid or incorrect map or its values
     */
    public static Vector2f deserializeFromMap(Map<String, Object> map) throws DeserializeException {
        if(!map.containsKey("x") || !map.containsKey("y")){
            throw new DeserializeException("Map doesn't contains x or y keys");
        }
        if(!(map.get("x") instanceof Float) || !(map.get("y") instanceof Float)){
            throw new DeserializeException("Map values doesn't instance of float value");
        }
        float x = (float) map.get("x");
        float y = (float) map.get("y");
        return new Vector2f(x,y);
    }

    /**
     * Deserialize string to Vector class
     * @return Instance of vector
     * @throws DeserializeException Invalid or incorrect string value
     */
    @SuppressWarnings("all")
    public static Vector2f deserializeFromString(String str) throws DeserializeException {
        if(str.length() < 3){throw new DeserializeException("Minimum length - 3 characters (num, \"!\", num)");}
        float dl = String.valueOf(Float.MAX_VALUE).length();
        if(str.length() > dl+1+dl){throw new DeserializeException("Maximum length - "+(dl+1+dl)+" characters (max number, \"!\", max number)");}
        if(!str.contains("!")){
            throw new DeserializeException("Incorrect format (num!num)");
        }
        if(!str.matches("^\\d+\\!\\d+$")){
            throw new DeserializeException("Incorrect format (num!num)");
        }
        String x = str.split("\\!")[0];
        String y = str.split("\\!")[1];
        return new Vector2f(StringUtils.parseFloat(x), StringUtils.parseFloat(y));
    }


    /**
     * Convert vector to java Point2D
     * @see java.awt.geom.Point2D
     * @return Java Point
     */
    public Point2D asJavaPoint(){
        return new Point((int) x, (int) y);
    }

    /**
     * Fetches angle relative to this vector
     * where 3 O'Clock is 0 and 12 O'Clock is 270 degrees
     *
     * @param to Vector to rotation
     * @return angle in degrees from 0-360.
     */
    public double getAngle(Vector2f to) {
        double dx = to.x - x;
        // Minus to correct for cord re-mapping
        double dy = -(to.y - y);

        double inRads = Math.atan2(dy, dx);

        // We need to map to cord system when 0 degree is at 3 O'clock, 270 at 12 O'clock
        if (inRads < 0)
            inRads = Math.abs(inRads);
        else
            inRads = 2 * Math.PI - inRads;

        return Math.toDegrees(inRads);
    }

    public Vector2 asVector2(){
        return new Vector2(Math.round(x), Math.round(y));
    }
}
