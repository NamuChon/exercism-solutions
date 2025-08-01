import java.util.*;

class PalindromeCalculator {
    
    SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int minFactor, int maxFactor) {
        if (minFactor > maxFactor) throw new IllegalArgumentException("invalid input: min must be <= max");
        SortedMap<Long, List<List<Integer>>> result = new TreeMap<>();
        for (int i = minFactor; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j++) {
                long product = i * j;
                if (isPalendrome(product)) {
                    result.merge(product, List.of(List.of(i, j)), (v1, v2) -> {
                        List<List<Integer>> list = new ArrayList<>(v1);
                        list.addAll(v2);
                        return list;
                    });
                }
            }
        }
        return result;
    }

    private boolean isPalendrome(long number) {
        String numberString = String.valueOf(number);
        return numberString.equals(new StringBuilder(numberString).reverse().toString());
    }
}