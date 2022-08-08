package org.ReStudios.utitlitium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@SuppressWarnings("unused")
public class ArrayUtils {

    public static HashMap<String, String> createMap(String... values){
        HashMap<String, String> returnValue = new HashMap<>();
        if(values.length > 0 && values.length % 2 == 0)
            for (int i = 0; i < values.length; i += 2) returnValue.put(values[i], values[i + 1]);
        return returnValue;
    }
    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... values){
        return new ArrayList<>(Arrays.asList(values));
    }
    public static <T> List<T> removeSame(List<T> list, ComparingMode way){
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
    public static <T> void reverse(T[] arr) {
        int start = 0;

        for(int end = arr.length - 1; start < end; --end) {
            T temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            ++start;
        }
    }

    public static <T> T getLastItem(T[] s) {
        ArrayList<T> list = toArrayList(s);
        return list.get(list.size() - 1);
    }

    enum ComparingMode {
        EQUALITY, CLASS, HASH_CODE
    }
}