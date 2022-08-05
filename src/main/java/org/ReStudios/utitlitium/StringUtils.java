package org.ReStudios.utitlitium;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@SuppressWarnings("unused")
public class StringUtils {
    public static int parseInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException var2) {
            return 0;
        }
    }
    public static double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException var2) {
            return 0.0;
        }
    }

    public static float parseFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException var2) {
            return 0.0F;
        }
    }
    public static String reverse(String s) {
        return (new StringBuilder(s)).reverse().toString();
    }
    public static String characterUp(int index, String s) {
        if (index >= 0 && index < s.length()) {
            StringBuilder sb = new StringBuilder(s);
            sb.setCharAt(index, Character.toUpperCase(s.charAt(index)));
            return sb.toString();
        } else {
            throw new StringIndexOutOfBoundsException("index " + index + ",length " + s.length() + ", string:" + s);
        }
    }
    public static String charsUp(String s, int... ints) {
        int var3 = ints.length;

        for (int anInt : ints) {
            s = characterUp(anInt, s);
        }

        return s;
    }
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
    public static String firstCharUp(String s) {
        return characterUp(0, s);
    }
}