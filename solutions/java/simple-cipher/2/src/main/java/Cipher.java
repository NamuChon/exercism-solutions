import java.util.*;
public class Cipher {

    private static final Random random = new Random();
    private static final String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";

    private final String key;
    private final int[] keyArray, decodeKeyArray;
    
    public Cipher() {
        StringBuilder key = new StringBuilder();
        keyArray = new int[random.nextInt(100, 201)];
        decodeKeyArray = new int[keyArray.length];
        for (int i = 0; i < keyArray.length; i++) {
            int index = random.nextInt(ALPHABETS.length());
            keyArray[i] = index;
            decodeKeyArray[i] = -index;
            key.append(ALPHABETS.charAt(index));
        }
        this.key = key.toString();
    }

    public Cipher(String key) {
        this.key = key;
        keyArray = key.chars()
            .map(ALPHABETS::indexOf)
            .toArray();
        decodeKeyArray = Arrays.stream(keyArray)
            .map(i -> -i)
            .toArray();
    }

    public String getKey() {
        return key;
    }

    public String encode(String plainText) {
        return encode(plainText, keyArray);
    }

    private String encode(String text, int[] keyArray) {
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            result.append((char) ('a' + (text.charAt(i) - 'a' + keyArray[keyIndex] + ALPHABETS.length()) % ALPHABETS.length()));
            keyIndex = (keyIndex + 1) % keyArray.length;
        }
        return result.toString();
    }

    public String decode(String cipherText) {
        return encode(cipherText, decodeKeyArray);
    }
}
