package org.ReStudios.utitlitium;


@SuppressWarnings("unused")
public class Utilitium {

    /**
     * Casts object to clazz
     * @param clazz to cast
     * @param object to be cast
     * @return cast object, or null
     * @param <T> type parameter
     */
    public static <T> T castOrNull(Class<T> clazz, Object object) {
        return clazz.isAssignableFrom(object.getClass()) ? clazz.cast(object) : null;
    }

    /**
     * Returns method's caller
     * @param depth to look for
     * @return method's caller
     */
    public static StackTraceElement getCaller(int depth) {
        Throwable t = new Throwable();
        int actualDepth = Math.min(depth + 1, t.getStackTrace().length);
        return t.getStackTrace()[actualDepth];
    }

    /**
     * Performs safe equals check
     * @param a object
     * @param b object
     * @return is objects equals
     */
    public static boolean safeEquals(Object a, Object b) {
        if (a == null && b == null) {
            return true;
        } else {
            return a != null && a.equals(b);
        }
    }
}