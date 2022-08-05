package org.ReStudios.utitlitium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@SuppressWarnings("unused")
public class BetterArguments {

    private final HashMap<String, String> arguments;
    private final HashMap<String, String> defaultArguments;
    private BetterArguments(String[] args) {
        arguments = new HashMap<>();
        defaultArguments = new HashMap<>();
        String key = "";
        ArrayList<String> values = new ArrayList<>();
        if(args.length == 0)return;
        for (String arg : args) {
            if (arg.startsWith("-")){
                if (!key.isEmpty()){
                    arguments.put(key.substring(1), String.join(" ", values));
                    values = new ArrayList<>();
                }
                key = arg;
                continue;
            }
            values.add(arg);
        }
        arguments.put(key.substring(1), String.join(" ", values));
    }
    public void putDefault(String key, String value) {
        defaultArguments.put(key, value);
    }

    public String removeDefault(String key) {
        return defaultArguments.remove(key);
    }
    public String getDefault(String key) {
        return defaultArguments.get(key);
    }

    public boolean containsDefault(String key) {
        return defaultArguments.containsKey(key);
    }

    public boolean contains(String key){
        return arguments.containsKey(key);
    }
    public boolean getBoolean(String key){
        if(!contains(key)) {
            if(containsDefault(key))return Boolean.parseBoolean(getDefault(key));
            return false;
        }
        return getString(key).equalsIgnoreCase("true");
    }
    public String getOrShort(String full, String shortKey){
        full = "-"+full;
        if(!contains(full) && !contains(shortKey)){
            if(containsDefault(full)) return getDefault(full);
            if(containsDefault(shortKey)) return getDefault(shortKey);
            return "";
        }
        return contains(full) ? getString(full) : contains(shortKey) ? getString(shortKey) : "";
    }
    public String getString(String key){
        if(!contains(key)) {
            if(containsDefault(key))return getDefault(key);
            else return "";
        }
        return arguments.get(key);
    }
    public int getInteger(String key){
        return getIntegerOr(key, -1);
    }
    public int getIntegerOr(String key, int i){
        return contains(key) ? parseOr(getString(key), 0) : containsDefault(key) ? parseOr(getDefault(key), 0) : i;
    }
    @SuppressWarnings("all")
    private int parseOr(String str, int i){
        try{
            return Integer.parseInt(str);
        }catch (NumberFormatException ignored){
            return i;
        }
    }
    @Override
    public String toString() {
        String output = "{";
        ArrayList<String> values = new ArrayList<>();
        for (Map.Entry<String, String> kv : arguments.entrySet()) {
            if(kv.getValue().isEmpty()){
                values.add(kv.getKey());
            }else{
                values.add(kv.getKey()+"="+"'"+kv.getValue().replace("\"", "\\\"")+"'");
            }
        }
        output += String.join(", ", values);
        return output + "}";
    }
    public static BetterArguments parse(String[] args){
        return new BetterArguments(args);
    }
}