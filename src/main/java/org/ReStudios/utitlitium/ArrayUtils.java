package org.ReStudios.utitlitium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@SuppressWarnings("unused")
public class ArrayUtils {
    public static List<?> remove(List<?> list, ArrayIndex index){
        return switch (index) {
            case last -> list.subList(0, list.size() - 2);
            case first -> list.subList(1, list.size() - 1);
        };
    }
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
    public static <T> List<T> removeSame(List<T> list, RemoveSameWay way){
        list.removeIf(t -> {
            int count = 0;
            for (T t1 : list) {
                if(way == RemoveSameWay.byEquals){
                    if (t1.equals(t))count++;
                }
                if(way == RemoveSameWay.byClass){
                    if(t1.getClass().isAssignableFrom(t.getClass()))count++;
                }
                if(way == RemoveSameWay.byClassHashCode){
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
    @SafeVarargs
    public static <T> T getLastItem(T... list){
        return getAt(toArrayList(list), ArrayIndex.last);
    }
    public static <T> T getAt(List<T> list, ArrayIndex get){
        if(get == ArrayIndex.last){
            return list.get(list.size()-1);
        }else if(get == ArrayIndex.first){
            return list.get(0);
        }
        return list.get(0);
    }
    enum ArrayIndex {
        last, first
    }
    enum RemoveSameWay {
        byEquals, byClass, byClassHashCode
    }
}