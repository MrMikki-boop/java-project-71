package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String makeStylish(List<Map<String, Object>> difference) {

        StringBuilder result = new StringBuilder();
        result.append("{").append(System.lineSeparator());
        boolean isFirst = true;

        for (Map<String, Object> element : difference) {
            if (!isFirst) {
                result.append(System.lineSeparator());
            }
            if (element.get("status").equals("deleted")) {
                result.append("  - ")
                        .append(element.get("key"))
                        .append(": ")
                        .append(element.get("oldValue"));
            } else if (element.get("status").equals("added")) {
                result.append("  + ")
                        .append(element.get("key"))
                        .append(": ")
                        .append(element.get("newValue"));
            } else if (element.get("status").equals("unchanged")) {
                result.append("    ")
                        .append(element.get("key"))
                        .append(": ")
                        .append(element.get("oldValue"));
            } else if (element.get("status").equals("changed")) {
                result.append("  - ")
                        .append(element.get("key"))
                        .append(": ")
                        .append(element.get("oldValue"));
                result.append(System.lineSeparator());
                result.append("  + ")
                        .append(element.get("key"))
                        .append(": ")
                        .append(element.get("newValue"));
            }
            isFirst = false;
        }
        result.append(System.lineSeparator()).append("}");
        return result.toString();
    }
}
