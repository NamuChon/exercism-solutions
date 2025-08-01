import java.util.*;
class Anagram {
    private final String word;
    public Anagram(String word) {
        this.word = word;
    }

    public List<String> match(List<String> candidates) {
        return candidates.stream().filter(s -> !s.equalsIgnoreCase(word) && Arrays.equals(word.toLowerCase().chars().sorted().toArray(), s.toLowerCase().chars().sorted().toArray())).toList();
    }
}