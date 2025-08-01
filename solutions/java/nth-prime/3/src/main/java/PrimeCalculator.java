import java.util.stream.IntStream;
class PrimeCalculator {

    int nth(int nth) {
        if (nth < 1) {
            throw new IllegalArgumentException();
        }
        
        int result = 2, primeIndex = 0;
        for (int current = 2; primeIndex < nth; current++) {
            final int finalCurrent = current;
            boolean isPrime = IntStream.rangeClosed(2, (int) Math.sqrt(current))
                .allMatch(i -> finalCurrent % i != 0);
            if (isPrime) {
                result = current;
                primeIndex++;
            }
        }
        return result;
    }
}
