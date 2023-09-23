package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Tree {
    public static List<Map<String, Object>> makeDifference(Map<String, Object> mapA, Map<String, Object> mapB) {
        List<Map<String, Object>> list = new ArrayList<>();
        Set<String> keys = new TreeSet<>(mapA.keySet());
        keys.addAll(mapB.keySet());

        for (String key : keys) {
            Map<String, Object> diffMap = new HashMap<>();
            var valueA = mapA.get(key);
            var valueB = mapB.get(key);

            diffMap.put("key", key);

            if (Objects.equals(valueA, valueB)) {
                diffMap.put("status", "unchanged");
                diffMap.put("oldValue", valueA);
            } else if (mapA.containsKey(key)) {
                diffMap.put("status", "deleted");
                diffMap.put("oldValue", valueA);
            } else if (mapB.containsKey(key)) {
                diffMap.put("status", "added");
                diffMap.put("newValue", valueB);
            } else if (mapA.containsKey(key) && mapB.containsKey(key)) {
                diffMap.put("status", "changed");
                diffMap.put("oldValue", valueA);
                diffMap.put("newValue", valueB);
            }

            list.add(diffMap);
        }

        return list;
    }
}
