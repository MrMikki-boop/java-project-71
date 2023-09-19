package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String makePlain(List<Map<String, Object>> difference) {

        StringBuilder result = new StringBuilder();
        String newLine = System.lineSeparator(); // Получение символа новой строки

        for (Map<String, Object> element : difference) {
            if (element.get("status").equals("deleted")) {
                result.append("Property '")
                        .append(element.get("key"))
                        .append("' was removed")
                        .append(newLine);
            } else if (element.get("status").equals("added")) {
                result.append("Property '")
                        .append(element.get("key"))
                        .append("' was added with value: ")
                        .append(getTextRepresentation(element.get("newValue")))
                        .append(newLine);
            } else if (element.get("status").equals("changed")) {
                result.append("Property '")
                        .append(element.get("key"))
                        .append("' was updated. From ")
                        .append(getTextRepresentation(element.get("oldValue")))
                        .append(" to ")
                        .append(getTextRepresentation(element.get("newValue")))
                        .append(newLine);
            }
        }
        return result.toString().trim();
    }

    public static String getTextRepresentation(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        } else if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
}
