package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stylish {
    public static String makeStylish(List<Map<String, Object>> difference) {
        return "{" + System.lineSeparator() +
                difference.stream()
                        .map(Stylish::formatElement)
                        .collect(Collectors.joining(System.lineSeparator())) +
                System.lineSeparator() + "}";
    }

    private static String formatElement(Map<String, Object> element) {
        String key = (String) element.get("key");
        String status = (String) element.get("status");
        String oldValue = formatValue(element.get("oldValue"));
        String newValue = formatValue(element.get("newValue"));

        return switch (status) {
            case "deleted" -> "  - " + key + ": " + oldValue;
            case "added" -> "  + " + key + ": " + newValue;
            case "unchanged" -> "    " + key + ": " + oldValue;
            case "changed" -> "  - " + key + ": " + oldValue + System.lineSeparator() +
                    "  + " + key + ": " + newValue;
            default -> throw new IllegalArgumentException("Unknown status: " + status);
        };
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else {
            return value.toString();
        }
    }
}
