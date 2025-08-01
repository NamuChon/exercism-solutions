import java.util.Arrays;
class Proverb {
    private final String cited;
    Proverb(String[] words) {
        if (words.length == 0) {
            cited = "";
        }
        else if (words.length == 1) {
            cited = "And all for the want of a " + words[0] + ".";
        }
        else {
            cited = "For want of a " + words[0] + Arrays.stream(words)
                .skip(1)
                .limit(words.length - 2)
                .reduce("", (s1, s2) -> s1 + " the " + s2 + " was lost.\nFor want of a " + s2)
                + " the " + words[words.length - 1] + " was lost.\nAnd all for the want of a " + words[0] + ".";
        }
    }

    String recite() {
        return cited;
    }
}
