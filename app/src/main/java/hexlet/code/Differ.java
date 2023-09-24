package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> data1 = read(filepath1);
        Map<String, Object> data2 = read(filepath2);
        List<Map<String, Object>> comparisonResult = Tree.makeDifference(data1, data2);
        return Formatter.formatAndOutput(comparisonResult, format);
    }

    public static Map<String, Object> read(String filePath) throws Exception {
        var type = getDataFormat(filePath);
        var content = Files.readString(Path.of(filePath).toAbsolutePath().normalize());
        return Parser.makeParsing(content, type);
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
