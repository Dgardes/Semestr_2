package Part_1;

import java.util.Arrays;
import java.util.List;

public class Main {

    @SuppressWarnings("unchecked")
    public static <T> T getValue(Object obj, Class<T> clazz) {
        return clazz.cast(obj);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(Object obj) {
        return (T) obj;
    }

    public static void main(String[] args) {
        List<Object> list = Arrays.asList("Author", "Book");
        for (Object element : list) {
            String data = Main.getValue(element, String.class);
            System.out.println(data);
            System.out.println(Main.<String>getValue(element));
        }
    }
}