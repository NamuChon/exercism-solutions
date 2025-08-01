import java.util.*;
import java.util.stream.Collectors;
class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        HashSet<String> myHashSet = new HashSet(myCollection);
        HashSet<String> theirHashSet = new HashSet(theirCollection);
        myHashSet.removeAll(theirCollection);
        theirHashSet.removeAll(myCollection);
        return !myHashSet.isEmpty() && !theirHashSet.isEmpty();
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        return collections.stream().map(HashSet::new).reduce((set1, set2) -> {
            set1.retainAll(set2);
            return set1;
            }).orElse(new HashSet<>());
    }

    static Set<String> allCards(List<Set<String>> collections) {
        return collections.stream().map(HashSet::new).reduce((set1, set2) -> {
            set1.addAll(set2);
            return set1;
            }).orElse(new HashSet<>());
    }
}