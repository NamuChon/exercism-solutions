import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Poker {
    private final List<String> bestHands;
    Poker(List<String> hands) {
        List<Hand> evaluated = hands.stream().map(Hand::new).toList();
        Hand bestHand = Collections.max(evaluated);
        bestHands = evaluated.stream().filter(h -> h.compareTo(bestHand) == 0).map(Hand::toString).toList();
    }

    List<String> getBestHands() {
        return bestHands;
    }

}

class Hand implements Comparable<Hand> {
    final String handStr;
    final List<Card> cards;

    public Hand(String handStr) {
        this.handStr = handStr;
        cards = Arrays.stream(handStr.split(" "))
            .map(s -> {
                int splitPoint = s.length() - 1;
                String rankStr = s.substring(0, splitPoint);
                int rank = switch (rankStr) {
                    case "J" -> 11;
                    case "Q" -> 12;
                    case "K" -> 13;
                    case "A" -> 14;
                    default -> Integer.parseInt(rankStr);
                };
                return new Card(rank, s.charAt(splitPoint));
            }).toList();
    }

    @Override
    public int compareTo(Hand otherHand) {
        int thisRanking = Evaluator.getRanking(this.cards);
        int otherRanking = Evaluator.getRanking(otherHand.cards);
        if (thisRanking != otherRanking) return Integer.compare(otherRanking, thisRanking);
        
        List<Integer> thisKickerList = this.getKickerList();
        List<Integer> otherKickerList = otherHand.getKickerList();
        for (int i = 0; i < thisKickerList.size(); i++) {
            int thisRank = thisKickerList.get(i);
            int otherRank = otherKickerList.get(i);
            if (thisRank != otherRank) return Integer.compare(thisRank, otherRank);
        }
        return 0;
    }
    
    private List<Integer> getKickerList() {
        List<Card> sorted = new ArrayList<>(this.cards);
        Collections.sort(sorted);
        boolean isAValue1 = Evaluator.isConsecutive(sorted) && sorted.getFirst().rank == 2;
        
        Map<Integer, Integer> frequency = Evaluator.getRankFrequency(sorted);
        return frequency.entrySet()
            .stream()
            .map(e -> isAValue1 && e.getKey() == 14 ? Map.entry(1, e.getValue()) : e)
            .sorted(Comparator.comparing(e -> ((Map.Entry<Integer, Integer>) e).getValue())
                .thenComparing(e -> ((Map.Entry<Integer, Integer>) e).getKey())
                .reversed())
            .map(Map.Entry::getKey)
            .distinct()
            .toList();
    }

    @Override
    public String toString() {
        return handStr;
    }
}

class Card implements Comparable<Card> {
    final int rank;
    final char suit;
    public Card(int rank, char suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(this.rank, otherCard.rank);
    }
}

class Evaluator {
    private static final List<Predicate<List<Card>>> RANKINGS = List.of(
        cards -> cards.get(0).rank == 10 && isSameSuit(cards) && isConsecutive(cards),
        cards -> isSameSuit(cards) && isConsecutive(cards),
        cards -> isSameRank(cards, 4),
        cards -> isSameRank(cards, 3, 2),
        cards -> isSameSuit(cards),
        cards -> isConsecutive(cards),
        cards -> isSameRank(cards, 3),
        cards -> isSameRank(cards, 2, 2),
        cards -> isSameRank(cards, 2)
    );

    private static boolean isSameSuit(List<Card> cards) {
        return getSuits(cards).distinct().count() == 1;
    }

    private static boolean isSameRank(List<Card> cards, int... counts) {
        IntStream ranks = getRanks(cards);
        if (counts.length == 0) return ranks.distinct().count() == 1;
        Map<Integer, Integer> frequency = getRankFrequency(cards);
        for (int count : counts) {
            Integer matchingRank = frequency.entrySet()
                .stream()
                .filter(e -> e.getValue() >= count)
                .reduce((e1, e2) -> e1.getValue() > e2.getValue() ? e2 : e1)
                .orElse(Map.entry(0, 0))
                .getKey();
            if (frequency.remove(matchingRank) == null) return false;
        }
        return true;
    }

    public static boolean isConsecutive(List<Card> cards) {
        int[] ranks = getRanks(cards).distinct().toArray();
        int length = ranks.length;
        if (length != cards.size()) return false;
        if (ranks[0] == 2 && ranks[length - 2] == 5 && ranks[length - 1] == 14) return true;
        return ranks[length - 1] - ranks[0] == length - 1;
    }

    private static IntStream getRanks(List<Card> cards) {
        return cards.stream().mapToInt(card -> card.rank);
    }
    
    private static IntStream getSuits(List<Card> cards) {
        return cards.stream().mapToInt(card -> card.suit);
    }
    
    public static Map<Integer, Integer> getRankFrequency(List<Card> cards) {
        return getRanks(cards).boxed().collect(Collectors.toMap(k -> k, v -> 1, Integer::sum));
    }
    
    public static int getRanking(List<Card> cards) {
        List<Card> sorted = new ArrayList<>(cards);
        Collections.sort(sorted);
        for (int i = 0; i < RANKINGS.size(); i++) {
            if (RANKINGS.get(i).test(sorted)) return i + 1;
        }
        return 10 + 14 - sorted.getLast().rank;
    }
}