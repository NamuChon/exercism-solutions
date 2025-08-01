import java.util.stream.Collectors;
class IsogramChecker {

    boolean isIsogram(String phrase) {
        phrase = phrase.toLowerCase().replaceAll("[-\s]", "");
        return phrase.chars().boxed().collect(Collectors.toSet()).size() == phrase.length();
    }

}
