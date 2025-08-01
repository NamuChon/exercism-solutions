import java.util.*;
class Etl {
    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> result = new HashMap<>();
        old.forEach((point, letters) -> letters.forEach(letter -> result.put(letter.toLowerCase(), point)));
        return result;
    }
}
