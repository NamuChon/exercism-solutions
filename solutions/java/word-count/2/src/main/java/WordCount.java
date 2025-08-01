import java.util.*;
import java.util.regex.*;
class WordCount {
    public Map<String, Integer> phrase(String input) {
        Map<String, Integer> result = new HashMap<>();
        Matcher matcher = Pattern.compile("\\w+('*\\w+)*").matcher(input);
        while (matcher.find()) {
            result.merge(matcher.group().toLowerCase(), 1, Integer::sum);
        }
        return result;
    }
}
