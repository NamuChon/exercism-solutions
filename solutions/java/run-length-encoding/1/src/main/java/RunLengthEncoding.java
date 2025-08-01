import java.util.regex.*;
class RunLengthEncoding {

    String encode(String data) {
        if (data.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        char previous = data.charAt(0);
        int count = 0;
        for (char c : data.toCharArray()) {
            if (c == previous) {
                count++;
            } else {
                appendCode(sb, count, previous);
                count = 1;
                previous = c;
            }
        }
        appendCode(sb, count, previous);
        return sb.toString();
    }
    private void appendCode(StringBuilder sb, int count, char letter) {
        if (count != 1) {
            sb.append(count);
        }
        sb.append(letter);
    }
    String decode(String data) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("([a-zA-Z\\s])");
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String letter = matcher.group();
            int letterIndex = matcher.start();
            int digitStart = letterIndex - 1;
            while (digitStart >= 0 && Character.isDigit(data.charAt(digitStart))) {
                digitStart--;
            }
            digitStart++;
            if (digitStart < letterIndex) {
                sb.append(letter.repeat(Integer.parseInt(data.substring(digitStart, letterIndex))));
            } else {
                sb.append(letter);
            }
        }
        return sb.toString();
    }

}