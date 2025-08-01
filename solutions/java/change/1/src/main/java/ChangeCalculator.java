import java.util.*;
class ChangeCalculator {

    private record Branch(int index, List<Integer> change, int total) {}
    private final List<Integer> currencyCoins;
    
    ChangeCalculator(List<Integer> currencyCoins) {
        this.currencyCoins = new ArrayList<>(currencyCoins);
        Collections.reverse(this.currencyCoins);
    }

    List<Integer> computeMostEfficientChange(int grandTotal) {
        if (grandTotal == 0) return List.of();
        if (grandTotal < 0) throw new IllegalArgumentException("Negative totals are not allowed.");

        Queue<Branch> queue = new ArrayDeque<>();
        for (int i = 0; i < currencyCoins.size(); i++) {
            int current = currencyCoins.get(i);
            if (current > grandTotal) continue;
            queue.add(new Branch(i, List.of(current), grandTotal - current));
        }

        while (!queue.isEmpty()) {
            Branch branch = queue.poll();
            int total = branch.total();
            if (total == 0) return branch.change();
            for (int i = branch.index(); i < currencyCoins.size(); i++) {
                int current = currencyCoins.get(i);
                if (current > total) continue;
                List<Integer> change = new ArrayList<>(branch.change());
                change.addFirst(current);
                queue.add(new Branch(i, change, total - current));
            }
        }
        
        throw new IllegalArgumentException("The total " + grandTotal + " cannot be represented in the given currency.");
    }
}
