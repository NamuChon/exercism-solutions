import java.util.*;
import java.util.concurrent.*;
class ParallelLetterFrequency {
    private final Map<Character, Integer> letterCounts;
    ParallelLetterFrequency(String[] texts) {
        letterCounts = new ConcurrentHashMap<>();
        Arrays.stream(texts).parallel().forEach(text -> text.chars().parallel().filter(Character::isLetter).forEach(c -> letterCounts.merge((char) Character.toLowerCase(c), 1, Integer::sum)));
    }
    
    Map<Character, Integer> countLetters() {
        return letterCounts;
    }

}
