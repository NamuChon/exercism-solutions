import java.util.*;
import java.util.stream.Collectors;
class Anagram {
    private final Map<Character, Integer> wordLetterCounts;
    private final String word;
    public Anagram(String word) {
        this.word = word;
        this.wordLetterCounts = getLetterCounts(word);
    }

    public List<String> match(List<String> candidates) {
        return candidates.stream().filter(s -> !s.equalsIgnoreCase(word) && getLetterCounts(s).equals(wordLetterCounts)).toList();
    }

    private Map<Character, Integer> getLetterCounts(String word) {
        return word.toLowerCase().chars().mapToObj(c -> (char) c).collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
    }
}