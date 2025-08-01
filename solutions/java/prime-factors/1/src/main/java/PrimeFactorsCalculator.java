import java.util.*;

class PrimeFactorsCalculator {

    List<Long> calculatePrimeFactorsOf(long number) {
        List<Long> result = new ArrayList<>();
        mainLoop:
        while (number != 1) {
            for (long i = 2; i <= (int) Math.sqrt(number); i++) {
                if (number % i == 0) {
                    result.add(i);
                    number /= i;
                    continue mainLoop;
                }
            }
            result.add(number);
            number = 1;
        }
        return result;
    }

}