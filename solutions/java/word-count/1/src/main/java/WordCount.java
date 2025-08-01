import java.util.*;
import java.util.regex.*;
class WordCount {
    public Map<String, Integer> phrase(String input) {
        Map<String, Integer> result = new HashMap<>();
        Matcher matcher = Pattern.compile("\\w+('*\\w+)*").matcher(input);
        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            if (result.containsKey(word)) {
                result.computeIfPresent(word, (k, v) -> v + 1);
            } else {
                result.put(word, 1);
            }
        }
        return result;
    }
}
