package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatAndOutput(List<Map<String, Object>> difference, String format)
            throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.makeStylish(difference);
            case "plain" -> Plain.makePlain(difference);
            case "json" -> Json.makeJson(difference);
            default -> throw new Exception("Wrong format: " + format);
        };
    }
}
