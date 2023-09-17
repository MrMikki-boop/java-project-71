package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;

public class Tree {
    public static List<Map<String, Object>> makeDifference(Map<String, Object> mapA, Map<String, Object> mapB) {
        List<Map<String, Object>> list = new ArrayList<>();
        Set<String> allKeys = new HashSet<>(mapA.keySet());
        allKeys.addAll(mapB.keySet());

        for (String key : allKeys) {
            list.add(getDifferenceMap(key, mapA.get(key), mapB.get(key)));
        }

        return list;
    }

    private static Map<String, Object> getDifferenceMap(String key, Object valueA, Object valueB) {
        Map<String, Object> diffMap = new TreeMap<>();
        diffMap.put("key", key);

        if (Objects.equals(valueA, valueB)) {
            diffMap.put("status", "unchanged");
            diffMap.put("oldValue", valueA);
        } else {
            if (valueA != null) {
                diffMap.put("status", "deleted");
                diffMap.put("oldValue", valueA);
            }
            if (valueB != null) {
                diffMap.put("status", "added");
                diffMap.put("newValue", valueB);
            }
            if (valueA != null && valueB != null) {
                diffMap.put("status", "changed");
                diffMap.put("oldValue", valueA);
                diffMap.put("newValue", valueB);
            }
        }
        return diffMap;
    }
}
