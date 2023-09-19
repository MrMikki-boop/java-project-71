package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate1(String filepath1, String filepath2, String format) throws Exception {
        Path absPath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path absPath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content1 = Files.readString(absPath1);
        String content2 = Files.readString(absPath2);

        Map<String, Object> parsedData1 = Parser.makeParsing(filepath1, content1);
        Map<String, Object> parsedData2 = Parser.makeParsing(filepath2, content2);

        List<Map<String, Object>> difference = Tree.makeDifference(parsedData1, parsedData2);
        return Formatter.chooseFormat(difference, format);
    }

    public static String generate1(String filepath1, String filepath2) throws Exception {
        String format = "stylish";
        return generate1(filepath1, filepath2, format);
    }
}
