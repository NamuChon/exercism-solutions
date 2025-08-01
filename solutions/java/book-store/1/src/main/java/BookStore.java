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

        Collections.sort(frequencies, Collections.reverseOrder());
        for (Set<Integer> choice : getPossibleChoices(frequencies)) {
            if (choice.isEmpty()) continue;
            double cost = COSTS.get(choice.size());
            List<Integer> updatedFrequencies = new ArrayList<>(frequencies);
            for (int i : choice) {
                updatedFrequencies.set(i, updatedFrequencies.get(i) - 1);
            }
            backtrack(results, updatedFrequencies, currentCost + cost);
        }
    }

    private Set<Set<Integer>> getPossibleChoices(List<Integer> frequencies) {
        Set<Set<Integer>> result = new HashSet<>();
        for (int i = 0; i < frequencies.size(); i++) {
            if (frequencies.get(i) <= 0) break;
            Set<Integer> currentChoice = IntStream.rangeClosed(0, i).boxed().collect(Collectors.toSet());
            result.add(currentChoice);
        }
        return result;
    }
}