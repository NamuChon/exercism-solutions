import java.util.*;
public class Cipher {

    private static final Random random = new Random();
    private static final String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";

    private final String key;
    private final int[] keyArray;
    
    public Cipher() {
        StringBuilder key = new StringBuilder();
        keyArray = new int[random.nextInt(100, 201)];
        for (int i = 0; i < keyArray.length; i++) {
            int index = random.nextInt(ALPHABETS.length());
            keyArray[i] = index;
            key.append(ALPHABETS.charAt(index));
        }
        this.key = key.toString();
    }

    public Cipher(String key) {
        this.key = key;
        keyArray = key.chars()
            .map(ALPHABETS::indexOf)
            .toArray();
    }

    public String getKey() {
        return key;
    }

    public String encode(String plainText) {
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < plainText.length(); i++) {
            result.append((char) ('a' + (plainText.charAt(i) - 'a' + keyArray[keyIndex]) % ALPHABETS.length()));
            keyIndex = (keyIndex + 1) % keyArray.length;
        }
        return result.toString();
    }

    public String decode(String cipherText) {
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < cipherText.length(); i++) {
            result.append((char) ('a' + (cipherText.charAt(i) - 'a' - keyArray[keyIndex] + ALPHABETS.length()) % ALPHABETS.length()));
            keyIndex = (keyIndex + 1) % keyArray.length;
        }
        return result.toString();
    }
}
