package bookstore.utils;

public class StringUtils {
    public static boolean isNullOrEmptyOrBlank(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }
}
