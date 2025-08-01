import java.util.*;
import java.util.stream.*;
class BookStore {
    
    private static final Map<Integer, Double> COSTS = Map.of(
        1, 8.0,
        2, 15.2,
        3, 21.6,
        4, 25.6,
        5, 30.0
    );

    double calculateBasketCost(List<Integer> books) {
        Set<Double> results = new HashSet<>();
        Map<Integer, Integer> frequencyMap = books.stream().collect(Collectors.toMap(i -> i, i -> 1, Integer::sum));
        backtrack(results, new ArrayList<>(frequencyMap.values()), 0.0);
        return Collections.min(results);
    }

    private void backtrack(Set<Double> results, List<Integer> frequencies, double currentCost) {
        if (frequencies.stream().allMatch(i -> i <= 0)) {
            results.add(currentCost);
            return;
        }
        
        List<Integer> updatedFrequencies = new ArrayList<>(frequencies);
        updatedFrequencies.sort(Collections.reverseOrder());
        int end = updatedFrequencies.indexOf(0);
        for (int i = 0; i < (end == -1 ? updatedFrequencies.size() : end); i++) {
            double cost = COSTS.get(i + 1);
            updatedFrequencies.set(i, updatedFrequencies.get(i) - 1);
            backtrack(results, updatedFrequencies, currentCost + cost);
        }
    }
}