import java.util.HashMap;
import java.util.Map;

public class Parser {

    private final Map<String, Integer> values = new HashMap<>();

    public Parser(String[] args) {
        int defaultLinesNumber = 10000;
        int defaultLineLength = 100;
        for (String arg : args) {
            String[] words = arg.split("=");
            if (words.length != 2) {
                throw new IllegalArgumentException("Invalid argument input format. Sample: -key=value.");
            }
            if (words[0].isEmpty() || words[1].isEmpty()) {
                throw new IllegalArgumentException("An empty key/value is entered. Sample: -key=value.");
            }
            if (!words[0].startsWith("-")) {
                throw new IllegalArgumentException("Key " + words[0] + "must start with \"-\"");
            }
            if ((words[0].equals("-l") || words[0].equals("-n"))) {
                int value;
                try {
                    value = Integer.parseInt(words[1]);
                    if (value < 0) {
                        throw new IllegalArgumentException("A negative value has been entered for the key " + words[0]);
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("A string is entered, not a number");
                }
                values.put(words[0].substring(1), value);
            }
        }
        if (!values.containsKey("l")) {
            values.put("l", defaultLineLength);
        }
        if (!values.containsKey("n")) {
            values.put("n", defaultLinesNumber);
        }
    }

    public Integer get(String key) {
        return values.get(key);
    }
}
