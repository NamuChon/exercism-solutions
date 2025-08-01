import java.util.ArrayList;
import java.util.List;

public class KillerSudokuHelper {

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> exclude) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinations(1, cageSum, cageSize, new ArrayList<>(), exclude, result);
        return result;
    }

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinations(1, cageSum, cageSize, new ArrayList<>(), new ArrayList<>(), result);
        return result;
    }
    
    private void generateCombinations(int start, int sum, int cageSize, List<Integer> current, List<Integer> exclude, List<List<Integer>> result) {
        if (current.size() == cageSize) {
            if (sum == 0) result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (exclude.contains(i)) continue;
            if (i > sum) break;
            current.add(i);
            generateCombinations(i + 1, sum - i, cageSize, current, exclude, result);
            current.removeLast();
        }
    }
}
