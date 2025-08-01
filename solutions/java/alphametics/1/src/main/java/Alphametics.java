import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
class Alphametics {
    private Map<Character, Integer> solution = new HashMap<>();
    private final List<Character> usedLetters;
    private final char[][] addendWords;
    private final char[] sumWord;
    Alphametics(String userInput) {
        Set<Character> usedLettersSet = new HashSet<>();
        addendWords = Pattern.compile("[A-Z]+(?=\\s[+=])")
            .matcher(userInput)
            .results()
            .map(m -> m.group().toCharArray())
            .peek(arr -> addCharArrToSet(arr, usedLettersSet))
            .toArray(char[][]::new);
        sumWord = Pattern.compile("(?<==\\s)[A-Z]+")
            .matcher(userInput)
            .results()
            .findFirst()
            .orElseThrow()
            .group()
            .toCharArray();
        addCharArrToSet(sumWord, usedLettersSet);
        usedLetters = Collections.unmodifiableList(new ArrayList<>(usedLettersSet));
    }

    Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        backtrack(0);
        return solution;
    }
    
    private void addCharArrToSet(char[] arr, Set<Character> set) {
        for (char c : arr) {
            set.add(c);
        }
    }

    private boolean backtrack(int currentUsedLettersIndex) throws UnsolvablePuzzleException {
        if (currentUsedLettersIndex >= usedLetters.size()) {
            return validate();
        }
        Character currentLetter = usedLetters.get(currentUsedLettersIndex);
        for (int i = 0; i < 10; i++) {
            if (solution.containsValue(i)) continue;
            solution.put(currentLetter, i);
            if (backtrack(currentUsedLettersIndex + 1)) {
                return true;
            }
            solution.remove(currentLetter);
        }
        if (currentUsedLettersIndex == 0) throw new UnsolvablePuzzleException();
        return false;
    }

    private boolean validate() {
        if (solution.get(sumWord[0]) == 0 || Arrays.stream(addendWords).anyMatch(word -> solution.get(word[0]) == 0)) return false;
        int expected = getWordValue(solution, sumWord);
        int actual = Arrays.stream(addendWords)
            .mapToInt(word -> getWordValue(solution, word))
            .sum();
        return expected == actual;
    }
    private int getWordValue(Map<Character, Integer> solution, char[] word) {
        final int length = word.length;
        return IntStream.range(0, length)
            .map(i -> solution.get(word[i]) * (int) Math.pow(10, length - 1 - i))
            .sum();
    }
}