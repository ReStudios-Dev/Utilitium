package org.ReStudios.utitlitium;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@SuppressWarnings("unused")
public class StringUtils {
    /**
     * Check is string float
     * @param s Potential float string
     * @return true if string is float
     */
    public static boolean isFloat(String s){
        try {
            Float.parseFloat(s);
            return true;
        }catch (NumberFormatException ignored){
            return false;
        }
    }

    /**
     * Check is string int
     * @param s Potential integer string
     * @return true if string is integer
     */
    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch (NumberFormatException ignored){
            return false;
        }
    }

    /**
     * Safe integer parse
     * @param s String that contains integer
     * @return Parsed integer from string, or 0
     */
    public static int parseInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ignored) {
            return 0;
        }
    }

    /**
     * Safe double parse
     * @param s String that contains double value
     * @return Parsed double from string, or 0.0d
     */
    public static double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException ignored) {
            return 0.0;
        }
    }

    /**
     * Safe float parse
     * @param s String that contains float value
     * @return Parsed float from string, or 0
     */
    public static float parseFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException ignored) {
            return 0.0F;
        }
    }

    /**
     * Safe integer parse with default value
     * @param s String that contains integer
     * @param defaultValue Default value if string contains invalid integer
     * @return Parsed integer from string, or defaultValue value
     */
    public static int parseInteger(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }

    /**
     * Safe double parse with default value
     * @param s String that contains double
     * @param defaultValue Default value if string contains invalid double value
     * @return Parsed double from string, or defaultValue value
     */
    public static double parseDouble(String s, double defaultValue) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }

    /**
     * Safe float parse with default value
     * @param s String that contains float value
     * @param defaultValue Default value if string contains invalid float
     * @return Parsed float from string, or defaultValue value
     */
    public static float parseFloat(String s, float defaultValue) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }


    /**
     * Reverses provided string
     * @param s String to be reversed
     * @return reversed string
     */
    public static String reverse(String s) {
        return (new StringBuilder(s)).reverse().toString();
    }

    /**
     * Set's character at index to be upper case
     * @param index char at
     * @param s in
     * @return string with applied changes
     */
    public static String characterUp(int index, String s) {
        if (index >= 0 && index < s.length()) {
            StringBuilder sb = new StringBuilder(s);
            sb.setCharAt(index, Character.toUpperCase(s.charAt(index)));
            return sb.toString();
        } else {
            throw new StringIndexOutOfBoundsException("index " + index + ",length " + s.length() + ", string:" + s);
        }
    }

    /**
     * Set's all chars from ints array to upper case
     * @param s to apply
     * @param ints indexes
     * @return string with applied changes
     */
    public static String charsUp(String s, int... ints) {
        int var3 = ints.length;

        for (int anInt : ints) {
            s = characterUp(anInt, s);
        }

        return s;
    }



    /**
     * Encodes string using MD5 algorithm
     * @param str to encode
     * @return encoded string
     */
    public static String md5(String str){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Set string's first char to upper case
     * @param s String
     * @return String, but first character is upper case
     */
    public static String firstCharUp(String s) {
        return characterUp(0, s);
    }
}