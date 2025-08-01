import java.util.*;
public class Say {
    private final String[] ONE_DIGITS = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"},
    TEN_TO_NINETEEN = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"},
    TWO_DIGITS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"},
    CHUNK_DIGITS = {"", "thousand", "million", "billion"};
    
    public String say(long number) {
        if (number > 999_999_999_999L || number < 0) {
            throw new IllegalArgumentException();
        }
        if (number == 0) {
            return "zero";
        }

        final String STR = String.valueOf(number);
        List<Integer> chunks = new ArrayList<>();
        for (int i = STR.length(); i > 0; i -= 3) {
            chunks.add(Integer.parseInt(STR.substring(Math.max(0, i - 3), i)));
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chunks.size(); i++) {
            final String CHUNK = getChunk(chunks.get(i));
            result.insert(0, " " + CHUNK + (CHUNK.isEmpty() ? "" : " " + CHUNK_DIGITS[i]));
        }
        return result.toString().trim();
    }
    
    private String getChunk(int num) {
        StringBuilder sb = new StringBuilder();
        final int HUNDREDTH = num / 100;

        if (HUNDREDTH != 0) {
            sb.append(ONE_DIGITS[HUNDREDTH] + " " + "hundred" + " ");
            num %= 100;
        }

        final int TENTH = num / 10;
        final int LAST = num % 10;
        
        if (num < 10) {
            sb.append(ONE_DIGITS[num]);
        }
        else if (num < 20) {
            sb.append(TEN_TO_NINETEEN[LAST]);
        }
        else {
            sb.append(TWO_DIGITS[TENTH] + (LAST == 0 ? "" : "-" + ONE_DIGITS[LAST]));
        }
        
        return sb.toString().trim();
    }
}
