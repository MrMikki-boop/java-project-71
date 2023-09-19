package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> makeParsing(String format, String content) throws Exception {
        Map map = new HashMap<>();
        ObjectMapper mapper;
        if (format.contains(".json")) {
            mapper = new ObjectMapper();
            map = mapper.readValue(content, Map.class);
        } else if (format.contains(".yaml") || format.contains(".yml")) {
            mapper = new ObjectMapper(new YAMLFactory());
            map = mapper.readValue(content, Map.class);
        }
        return map;
    }
}
