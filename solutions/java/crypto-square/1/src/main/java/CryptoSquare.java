import java.util.Arrays;
import java.util.stream.Collectors;
class CryptoSquare {
    private final String cipherText;
    CryptoSquare(String plaintext) {
        String normalizedText = plaintext.replaceAll("[\\s\\p{Punct}]", "").toLowerCase();
        int length = normalizedText.length(), c = 0, r = 0;
        while (true) {
            c++;
            if (r * c >= length) break;
            r++;
            if (r * c >= length) break;
        }
        String[] splittedText = new String[r];
        for (int i = 0; i < r; i++) {
            int start = i * c;
            String chunk = normalizedText.substring(start, Math.min(start + c, length));
            splittedText[i] = i == r - 1 ? String.format("%-" + c + "s", chunk) : chunk;
        }
        
        StringBuilder[] sb = new StringBuilder[c];
        for (int i = 0; i < c; i++) {
            sb[i] = new StringBuilder();
            for (int j = 0; j < r; j++) {
                sb[i].append(splittedText[j].charAt(i));
            }
        }
        cipherText = Arrays.stream(sb).map(StringBuilder::toString).collect(Collectors.joining(" "));
    }

    String getCiphertext() {
        return cipherText;
    }
}
