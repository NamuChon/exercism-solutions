import java.util.*;
class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        char[] digits = Integer.toBinaryString(number).toCharArray();
        List<Signal> result = new ArrayList<>();
        for (int i = 5; i > 5 - digits.length; i--) {
            int digitIndex = digits.length + i - 6;
            if (digits[digitIndex] == '0') {
                continue;
            }
            switch (i) {
                case 5:
                    result.add(Signal.WINK);
                    break;
                case 4:
                    result.add(Signal.DOUBLE_BLINK);
                    break;
                case 3:
                    result.add(Signal.CLOSE_YOUR_EYES);
                    break;
                case 2:
                    result.add(Signal.JUMP);
                    break;
                case 1:
                    Collections.reverse(result);
            }
        }
        return result;
    }
}
