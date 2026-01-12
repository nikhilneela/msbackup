import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        /*String input = "This            is a           really           long    text   with    lots of               sp" +
                "aces. we will use   this as an   example to   demonstrate the power of streams in java    ";

        //Get a list of string with no spaces

        List<String> tokens = Arrays.stream(input.trim().split(" "))
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        for (String token:
             tokens) {
            System.out.print(token + " ");
        }
        */

        String[] fruits = new String[] {"apple", "banana", "watermelon"};

        if (Arrays.stream(fruits).anyMatch(f -> f.equals("banana"))) {
            System.out.println("Contains Banana");
        }
    }
}
