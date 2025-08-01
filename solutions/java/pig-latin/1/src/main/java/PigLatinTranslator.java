import java.util.*;
import java.util.stream.Collectors;
class PigLatinTranslator {
    final private List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u');
    public String translate(String word) {
        return Arrays.stream(word.split(" ")).map(this::translateWord).collect(Collectors.joining(" "));
    }
    private String translateWord(String word) {
        String firstTwo = word.substring(0, 2);
        if (VOWELS.contains(word.charAt(0)) || firstTwo.equals("xr") || firstTwo.equals("yt")) {
            return word + "ay";
        }
        int i;
        for (i = 1; i < word.length(); i++) {
            if (VOWELS.contains(word.charAt(i))) {
                if (word.substring(i - 1, i + 1).equals("qu")) {
                    i++;
                }
                break;
            }
            if (word.charAt(i) == 'y') {
                break;
            }
        }
        return word.substring(i, word.length()) + word.substring(0, i) + "ay";
    }
}