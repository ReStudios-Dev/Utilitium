package org.ReStudios.utitlitium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public class ArrayUtils {

    /**
     * An easy way to create even the most complex maps.
     * For example, if you enter data: "int", 4, "str", "good game", "Custom class", new CustomClass()
     * We will get the following map structure: {"int": 4, "str": "good game", "Custom class": CustomClass@0000}
     * @param values Arguments in the form: key, value, key value. Where the value can be any object and the key can be a string.
     * @return Map
     */
    public static HashMap<String, Object> createMap(Object... values){
        HashMap<String, Object> returnValue = new HashMap<>();
        if(values.length > 0 && values.length % 2 == 0)
            for (int i = 0; i < values.length; i += 2) returnValue.put((String) values[i], values[i + 1]);
        return returnValue;
    }

    /**
     * An easy way to create a list from an enum of objects
     * @param values Objects...
     * @return ArrayList
     * @param <T> Object type
     */
    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... values){
        return new ArrayList<>(Arrays.asList(values));
    }

    /**
     * Remove items from list
     * @param list List
     * @param way What to remove
     * @return Cleared list
     * @param <T> List type
     */
    public static <T> List<T> removeSame(final List<T> list, final ComparingMode way){
        list.removeIf(t -> {
            int count = 0;
            for (T t1 : list) {
                if(way == ComparingMode.EQUALITY){
                    if (t1.equals(t))count++;
                }
                if(way == ComparingMode.CLASS){
                    if(t1.getClass().isAssignableFrom(t.getClass()))count++;
                }
                if(way == ComparingMode.HASH_CODE){
                    if(t1.hashCode() == t.hashCode())count++;
                }
            }
            return count > 1;
        });
        return list;
    }

    /**
     * Get random item from list
     * @param list list
     * @return random item from list. null if list is empty
     * @param <T> type
     */
    public static <T> T getRandom(T[] list){
        return getRandom(Arrays.asList(list));
    }

    /**
     * Get random item from list
     * @param list list
     * @return random item from list. null if list is empty
     * @param <T> type
     */
    public static <T> T getRandom(List<T> list){
        if(list.isEmpty())return null;
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }


    /**
     * Reverse list
     * @param arr List
     * @param <T> List type
     */
    public static <T> void reverse(T[] arr) {
        int start = 0;

        for(int end = arr.length - 1; start < end; --end) {
            T temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            ++start;
        }
    }

    /**
     * Get sum of float list
     * @param floatCollection Float list
     * @return Sum
     */
    public static float getSum(Collection<Float> floatCollection){
        float out = 0;
        for (Float aFloat : floatCollection) {
            out += aFloat;
        }
        return out;
    }

    /**
     * Get last item from list
     * @param s List
     * @return Last item from list
     * @param <T> List type
     */
    public static <T> T getLastItem(T[] s) {
        if(s.length == 0)return null;
        return s[s.length - 1];
    }
    public static <T> T getLastItem(List<T> s) {
        if(s.isEmpty())return null;
        return s.get(s.size()-1);
    }

    /**
     * Adds a value to a list while limiting the list's size.
     *
     * @param <T>    the type of elements in the list
     * @param list   the list to which the value will be added
     * @param limit  the maximum size limit of the list
     * @param value  the value to be added to the list
     */
    public static <T> void addLimited(List<T> list, int limit, T value) {
        if(limit < 1){
            return;
        }
        list.add(value);
        while (list.size() > limit){
            list.remove(0);
        }
    }

    public enum ComparingMode {
        EQUALITY, CLASS, HASH_CODE
    }
}