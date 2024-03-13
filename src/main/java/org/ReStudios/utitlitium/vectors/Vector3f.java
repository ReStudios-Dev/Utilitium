package org.ReStudios.utitlitium.vectors;

import org.ReStudios.utitlitium.ArrayUtils;
import org.ReStudios.utitlitium.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@SuppressWarnings("unused")
public class Vector3f {
    /**
     * X, Y & Z values
     */
    public float x, y, z;

    /**
     * Constructor default values
     */
    public Vector3f(){
        x=0;
        y=0;
        z=0;
    }

    /**
     * Constructor with custom values
     * @param x X value
     * @param y Y value
     * @param z Z value
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
    public Vector3f setX(float x) {
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
    public Vector3f setY(float y) {
        this.y = y;
        return this;
    }

    /**
     * Get Z value
     * @return Z value
     */
    public float z() {
        return z;
    }

    /**
     * Set custom Z value
     * @param z new Z value
     * @return Instance of vector
     */
    public Vector3f setZ(float z) {
        this.z = z;
        return this;
    }

    /**
     * Add values from other vector
     * @param v other Vector
     * @return Instance of vector
     */
    public Vector3f add(Vector3f v){
        this.x += v.x();
        this.y += v.y();
        this.z += v.z();
        return this;
    }

    /**
     * Add values from direct values
     * @param x add X value
     * @param y add Y value
     * @param z add Z value
     * @return Instance of vector
     */
    public Vector3f add(float x, float y, float z){
        return add(new Vector3f(x,y,z));
    }

    /**
     * Add values from 1 value.
     * Add a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 5, 8 and 10
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 7, 10 and 12
     * @param add value to add
     * @return Instance of vector
     */
    public Vector3f add(float add){
        return add(add, add, add);
    }

    /**
     * Subtract values from other vector
     * @param v other Vector
     * @return Instance of vector
     */
    public Vector3f sub(Vector3f v){
        this.x -= v.x();
        this.y -= v.y();
        this.z -= v.z();
        return this;
    }

    /**
     * Subtract values from direct values
     * @param x subtract X value
     * @param y subtract Y value
     * @param z subtract Z value
     * @return Instance of vector
     */
    public Vector3f sub(float x, float y, float z){
        return sub(new Vector3f(x,y,z));
    }

    /**
     * Subtract values from 1 value.
     * Subtract a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 5, 8 and 10
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 3, 6 and 8
     * @param sub value to subtract
     * @return Instance of vector
     */
    public Vector3f sub(float sub){
        return sub(sub, sub, sub);
    }

    /**
     * Multiply values from other vector
     * @param v other Vector
     * @return Instance of vector
     */
    public Vector3f mul(Vector3f v){
        this.x *= v.x();
        this.y *= v.y();
        this.z *= v.z();
        return this;
    }

    /**
     * Multiply values from direct values
     * @param x multiply X value
     * @param y multiply Y value
     * @param z multiply Z value
     * @return Instance of vector
     */
    public Vector3f mul(float x, float y, float z){
        return mul(new Vector3f(x,y,z));
    }

    /**
     * Multiply values from 1 value.
     * Multiply a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 5, 8 and 10
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 10, 16 and 20
     * @param mul value to multiply
     * @return Instance of vector
     */
    public Vector3f mul(float mul){
        return mul(mul, mul, mul);
    }

    /**
     * Divide values from other vector
     * @param v other Vector
     * @return Instance of vector
     */
    public Vector3f div(Vector3f v){
        if(v.x() != 0)this.x /= v.x();
        if(v.y() != 0)this.y /= v.y();
        if(v.z() != 0)this.z /= v.z();
        return this;
    }

    /**
     * Divide values from direct values
     * @param x divide X value
     * @param y divide Y value
     * @param z divide Z value
     * @return Instance of vector
     */
    public Vector3f div(float x, float y, float z){
        return div(new Vector3f(x,y,z));
    }

    /**
     * Divide values from 1 value.
     * Divide a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 4, 8 and 10
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 2, 4 and 5
     * @param div value to divide
     * @return Instance of vector
     */
    public Vector3f div(float div){
        return div(div, div, div);
    }

    /**
     * Raise the power of the current vector to the values of another vector
     * (The X of the current vector is raised to the power of the X value of another vector,
     * and so on with the rest of the values)
     * @param v other vector
     * @return Instance of vector
     */
    public Vector3f pow(Vector3f v){
        this.x = (float) Math.pow(this.x, v.x());
        this.y = (float) Math.pow(this.y, v.y());
        this.z = (float) Math.pow(this.z, v.z());
        return this;
    }

    /**
     * Raise the power from direct values
     * @param x pow X value
     * @param y pow Y value
     * @param z pow Z value
     * @return Instance of vector
     */
    public Vector3f pow(float x, float y, float z){
        return pow(new Vector3f(x,y,z));
    }

    /**
     * Raise the power values from 1 value.
     * Raise the power a value to all values of the vector.
     * For example: Let's imagine that we have a vector with values 4, 8 and 10
     * if we call this method with a value of 2,
     * then at the output we will get a vector with values 16, 64 and 100
     * *In other words: x = x^pow*
     * @param pow value to divide
     * @return Instance of vector
     */
    public Vector3f pow(float pow){
        return pow(pow, pow, pow);
    }

    /**
     * Clone current vector
     * Will create exactly the same vector, but as a separate object.
     * This is useful if you need to save values if you change them later (for example).
     * @return Instance of copied vector
     */
    @SuppressWarnings("all")
    public Vector3f clone() {
        return new Vector3f(x, y, z);
    }

    /**
     * Any vector, when normalized, only changes its magnitude, not its direction.
     * Also, every vector pointing in the same direction is normalized to the same vector
     * (because magnitude and direction uniquely define a vector).
     * In other words, divides the vector to the minimum values which brings the vector to the "direction"
     * @return Instance of vector
     */
    public Vector3f normalize(){

        // TODO ?!
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
        return "{"+x+";"+y+";"+z+"}";
    }

    /**
     * Convert vector to serialized string
     * For example, for storage outside the application
     * @return Serialized vector
     */
    public String serializeToString(){
        return x+"!"+y+"!"+z;
    }

    /**
     * Convert vector to map
     * For example, for storage outside the application
     * @return Serialized vector
     */
    public Map<String, Object> serializeToMap(){
        return ArrayUtils.createMap(
                "x", x,
                "y", y,
                "z", z
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
    public static Vector3f deserializeFromBytes(byte[] bytes) throws DeserializeException {
        return deserializeFromString(new String(bytes));
    }

    /**
     * Deserialize map list to Vector class
     * @return Instance of vector
     * @throws DeserializeException Invalid or incorrect map or its values
     */
    public static Vector3f deserializeFromMap(Map<String, Object> map) throws DeserializeException {
        if(!map.containsKey("x") || !map.containsKey("y") || !map.containsKey("z")){
            throw new DeserializeException("Map doesn't contains x, y or z keys");
        }
        if(!(map.get("x") instanceof Float) || !(map.get("y") instanceof Float) || !(map.get("z") instanceof Float)){
            throw new DeserializeException("Map values doesn't instance of float value");
        }
        float x = (float) map.get("x");
        float y = (float) map.get("y");
        float z = (float) map.get("z");
        return new Vector3f(x,y,z);
    }

    /**
     * Deserialize string to Vector class
     * @return Instance of vector
     * @throws DeserializeException Invalid or incorrect string value
     */
    @SuppressWarnings("all")
    public static Vector3f deserializeFromString(String str) throws DeserializeException {
        if(str.length() < 5){throw new DeserializeException("Minimum length - 5 characters (num, \"!\", num, \"!\", num)");}
        int dl = String.valueOf(Float.MAX_VALUE).length();
        if(str.length() > dl+1+dl+1+dl){throw new DeserializeException("Maximum length - "+(dl+1+dl+1+dl)+" characters (max number, \"!\", max number, \"!\", max number)");}
        if(!str.contains("!")){
            throw new DeserializeException("Incorrect format (num!num!num)");
        }
        if(!str.matches("^\\d+\\!\\d+$")){
            throw new DeserializeException("Incorrect format (num!num!num)");
        }
        String x = str.split("\\!")[0];
        String y = str.split("\\!")[1];
        String z = str.split("\\!")[2];
        return new Vector3f(StringUtils.parseFloat(x), StringUtils.parseFloat(y), StringUtils.parseFloat(z));
    }

    public Vector3 asVector3(){
        return new Vector3(Math.round(x), Math.round(y), Math.round(z));
    }

    /**
     * Get distance between 2 vectors
     * @param vector Another vector
     * @return Distance
     */
    public double distance(Vector3f vector){
        if(vector == null) return -1;
        float v1x = this.x;
        float v1y = this.y;
        float v1z = this.z;
        float v2x = vector.x;
        float v2y = vector.y;
        float v2z = vector.z;
        return Math.sqrt(Math.pow(v2x - v1x, 2) + Math.pow(v2y - v1y, 2) + Math.pow(v2z - v1z, 2));
    }

    /**
     * Get vector length (Distance from origin)
     * @return Length
     */
    public double length(){
        return distance(new Vector3f(0.0f, 0.0f, 0.0f));
    }

}
