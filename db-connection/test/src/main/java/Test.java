import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

/**
 * @author fangzhang
 *
 */
public class Test {
    public static void main(final String[] args) {
        final List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        for (final String s : list) {
            if (s.equals("2")) {
               // list.remove(s);
            }
        }
        System.out.print(list);

        Long value = 101036590;
        System.out.println(value);
    }
}
