import java.util.*;
class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        List<Signal> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if ((1 << i & number) == 0) continue;
            if (i == 4) {
                Collections.reverse(result);
            }
            else {
                result.add(Signal.values()[i]);
            }
        }
        return result;
    }
}
