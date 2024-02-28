package org.ReStudios.utitlitium;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class InstanceManager {

    /**
     * Instance list
     */
    private static final ArrayList<Object> instances;

    /**
     * Register a new instance
     * @param instance Instance to register
     */
    public static void register(Object instance){
        if (hasInstance(instance.getClass())) {
            instances.removeIf(o -> instance.getClass().isAssignableFrom(o.getClass()));
        }
        instances.add(instance);
    }

    /**
     * Get instance
     * @param clazz Instance's super
     * @return Instance of class
     * @param <T> class template
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(final Class<T> clazz){
        return (T) instances.stream().filter(o -> clazz.isAssignableFrom(o.getClass())).findFirst().orElse(null);
    }

    public static boolean hasInstance(final Class<?> clazz){
        return instances.stream().anyMatch(o -> clazz.isAssignableFrom(o.getClass()));
    }

    static  {
        instances = new ArrayList<>();
    }
}
