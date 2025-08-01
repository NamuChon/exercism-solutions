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
    private char shift(char alphabet, int key) {
        char base;
        if (alphabet >= 'a' && alphabet <= 'z') {
            base = 'a';
        }
        else if (alphabet >= 'A' && alphabet <= 'Z') {
            base = 'A';
        }
        else {
            return alphabet;
        }
        key %= 26;
        int numFromEnd = 25 - (alphabet - base);
        if (numFromEnd < key) {
            key -= 26;
        }
        return (char) (alphabet + key);
    }
}
