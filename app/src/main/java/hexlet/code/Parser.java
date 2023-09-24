package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> makeParsing(String content) throws Exception {
        String format = getDataFormat(content);
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper;
        if (format.equals("json")) {
            mapper = new ObjectMapper();
            map = mapper.readValue(content, Map.class);
        } else if (format.equals("yaml") || format.equals("yml")) {
            mapper = new ObjectMapper(new YAMLFactory());
            map = mapper.readValue(content, Map.class);
        }
        return map;
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
