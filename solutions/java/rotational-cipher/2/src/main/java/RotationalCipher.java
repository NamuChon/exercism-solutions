import java.util.stream.Collectors;
class RotationalCipher {
    private final int shiftKey;
    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        return data.chars()
            .mapToObj(c -> String.valueOf(shift((char) c, shiftKey)))
            .collect(Collectors.joining());
    }
    private char shift(char c, int key) {
        if (!Character.isLetter(c)) {
            return c;
        }
        char base = Character.isUpperCase(c) ? 'A' : 'a';
        return (char) (base + (c - base + key) % 26);
    }
}
