import java.util.stream.IntStream;
class PrimeCalculator {

    int nth(int nth) {
        if (nth < 1) {
            throw new IllegalArgumentException();
        }
        return IntStream.iterate(2, n -> n + 1)
            .filter(current -> IntStream.rangeClosed(2, (int) Math.sqrt(current))
                .allMatch(i -> current % i != 0))
            .limit(nth)
            .max()
            .orElse(2);
    }
}
