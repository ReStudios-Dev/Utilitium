package org.ReStudios.utitlitium;

import org.jetbrains.annotations.Nullable;
@SuppressWarnings("unused")
public class Utilitium {
    public static <T> @Nullable T castOrNull(Class<T> clazz, Object object) {
        return clazz.isAssignableFrom(object.getClass()) ? clazz.cast(object) : null;
    }

    public static StackTraceElement getCaller(int depth) {
        Throwable t = new Throwable();
        int actualDepth = Math.min(depth + 1, t.getStackTrace().length);
        return t.getStackTrace()[actualDepth];
    }

    public static boolean safeEquals(Object a, Object b) {
        if (a == null && b == null) {
            return true;
        } else {
            return a != null && a.equals(b);
        }
    }
}