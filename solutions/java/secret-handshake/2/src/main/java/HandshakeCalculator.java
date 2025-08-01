import java.util.*;
class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        char[] digits = Integer.toBinaryString(number).toCharArray();
        List<Signal> result = new ArrayList<>();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == '0') {
                continue;
            }
            switch (digits.length - i) {
                case 1 -> result.add(Signal.WINK);
                case 2 -> result.add(Signal.DOUBLE_BLINK);
                case 3 -> result.add(Signal.CLOSE_YOUR_EYES);
                case 4 -> result.add(Signal.JUMP);
                case 5 -> Collections.reverse(result);
            }
        }
        return result;
    }
}
