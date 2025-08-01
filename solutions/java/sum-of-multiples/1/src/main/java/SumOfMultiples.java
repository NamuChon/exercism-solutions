import java.util.Arrays;
import java.util.stream.IntStream;
class SumOfMultiples {
    private final int sum;
    SumOfMultiples(int number, int[] set) {
        sum = set.length == 0 ? 0 : Arrays.stream(set)
            .mapToObj(i -> IntStream.range(1, i == 0 ? 1 : (int) Math.ceil((double) number / i)).map(j -> j * i))
            .reduce(IntStream::concat).orElse(IntStream.of(0))
            .distinct()
            .sum();
    }

    int getSum() {
        return sum;
    }

}
