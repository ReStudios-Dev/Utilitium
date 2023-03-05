package org.ReStudios.utitlitium;

import java.util.ArrayList;
import java.util.function.Predicate;

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
        if(instances.contains(instance)){
            throw new RuntimeException("Instance already exists");
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
        return (T) instances.stream().filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) {
                return clazz.isAssignableFrom(o.getClass());
            }
        }).findFirst().orElse(null);
    }

    static  {
        instances = new ArrayList<>();
    }
}
