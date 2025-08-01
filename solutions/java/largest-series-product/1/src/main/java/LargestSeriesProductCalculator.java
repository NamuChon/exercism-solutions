import java.util.*;
class LargestSeriesProductCalculator {
    private final String inputNumber;
    LargestSeriesProductCalculator(String inputNumber) {
        if (!inputNumber.matches("\\d*")) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }
        this.inputNumber = inputNumber;
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }
        if (numberOfDigits > inputNumber.length()) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }
        List<String> series = new ArrayList<>();
        for (int i = 0; i < inputNumber.length() - numberOfDigits + 1; i++) {
            series.add(inputNumber.substring(i, i + numberOfDigits));
        }
        return series.stream().mapToLong(s -> s.chars().mapToLong(c -> Character.getNumericValue(c)).reduce(1, Math::multiplyExact)).max().orElse(0);
    }
}
