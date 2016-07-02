package by.epam.news.util;

/**
 * Created by Olga Shahray on 30.05.2016.
 */
public class ExceptionUtil {


    public static boolean check(Object object) {
        return object != null;
    }
    public static boolean check(String s) {
        return (s != null && !s.isEmpty());
    }


}
