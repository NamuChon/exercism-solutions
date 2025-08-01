import java.util.*;
class Etl {
    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> result = new HashMap<>();
        old.entrySet().stream().forEach(e -> {
            int points = e.getKey();
            e.getValue().stream().forEach(letter -> result.put(letter.toLowerCase(), points));
        });
        return result;
    }
}
