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
                throw new IllegalArgumentException("Неверный формат ввода аргумента. Образец: -key=value.");
            }
            if (words[0].isEmpty() || words[1].isEmpty()) {
                throw new IllegalArgumentException("Введен пустой ключ/значение. Образец: -key=value.");
            }
            try {
                if (Integer.parseInt(words[1]) < 0) {
                    throw new IllegalArgumentException("Введено отрицательное значение для ключа " + words[0]);
                }
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Введена строка, а не число");
            }
            if (!words[0].startsWith("-")) {
                throw new IllegalArgumentException("Ключ " + words[0] + "должен начинаться с \"-\"");
            }
            values.put(words[0].substring(1), Integer.valueOf(words[1]));
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
