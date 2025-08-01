import java.util.*;
import java.util.stream.*;
class FoodChain {
    private final List<List<String>> allocation = List.of(
        List.of("fly"),
        List.of("spider", "It wriggled and jiggled and tickled inside her."),
        List.of("bird", "How absurd to swallow a bird!"),
        List.of("cat", "Imagine that, to swallow a cat!"),
        List.of("dog", "What a hog, to swallow a dog!"),
        List.of("goat", "Just opened her throat and swallowed a goat!"),
        List.of("cow", "I don't know how she swallowed a cow!"),
        List.of("horse", "She's dead, of course!")
    );
    private final int finalIndex = allocation.size() - 1;
    
    String verse(int verse) {
        verse--;
        return String.join("\n", firstPart(verse), secondPart(verse), thirdParts(verse), fourthPart(verse), fifthPart(verse), finalPart(verse)).trim().replaceAll("\n+", "\n");
    }

    String verses(int startVerse, int endVerse) {
        return IntStream.range(startVerse, endVerse + 1).mapToObj(this::verse).collect(Collectors.joining("\n\n"));
    }

    
    private String firstPart(int index) {
        return String.format("I know an old lady who swallowed a %s.", getName(index));
    }
    private String secondPart(int index) {
        return index > 0 ? allocation.get(index).get(1) : "";
    }
    private String thirdPart(int index) {
        return index != finalIndex ? String.format("She swallowed the %s to catch the %s.", getName(index), getName(index - 1)) : "";
    }
    private String thirdParts(int startIndex) {
        if (startIndex == finalIndex) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i >= 3; i--) {
            sb.append(thirdPart(i));
            sb.append("\n");
        }
        return sb.toString().trim();
    }
    private String fourthPart(int index) {
        return index > 1 && index != finalIndex ? thirdPart(2).replace(".", " that") + secondPart(1).replace("It", "") : "";
    }
    private String fifthPart(int index) {
        return index > 0 && index != finalIndex ? thirdPart(1) : "";
    }
    private String finalPart(int index) {
        return index != finalIndex ? String.format("I don't know why she swallowed the %s. Perhaps she'll die.", getName(0)) : "";
    }
    private String getName(int index) {
        return allocation.get(index).get(0);
    }
}