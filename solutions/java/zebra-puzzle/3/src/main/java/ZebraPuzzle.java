import java.util.*;
import java.util.function.*;
class ZebraPuzzle {

    private final int HOUSE_COUNT = 5;
    
    private final Map<String, Set<String>> PROPERTIES = Map.of(
        "house", Set.of("red", "green", "ivory", "yellow", "blue"),
        "nationality", Set.of("Englishman", "Spaniard", "Ukrainian", "Norwegian", "Japanese"),
        "pet", Set.of("zebra", "dog", "snail", "fox", "horse"),
        "beverage", Set.of("water", "coffee", "tea", "milk", "orange juice"),
        "hobby", Set.of("dancing", "painting", "reading", "football", "chess")
    );
    private final String[] ORDER = {"house", "nationality", "hobby", "pet", "beverage"};

    private final Set<Supplier<Boolean>> RULES = Set.of(
        () -> isSameHouse("nationality", "Englishman", "house", "red"),
        () -> isSameHouse("nationality", "Spaniard", "pet", "dog"),
        () -> isSameHouse("house", "green", "beverage", "coffee"),
        () -> isSameHouse("nationality", "Ukrainian", "beverage", "tea"),
        () -> assertDistance("house", "green", "house", "ivory", 1),
        () -> isSameHouse("pet", "snail", "hobby", "dancing"),
        () -> isSameHouse("house", "yellow", "hobby", "painting"),
        () -> hasProperty(2, "beverage", "milk"),
        () -> hasProperty(0, "nationality", "Norwegian"),
        () -> isNeighbor("hobby", "reading", "pet", "fox"),
        () -> isNeighbor("hobby", "painting", "pet", "horse"),
        () -> isSameHouse("hobby", "football", "beverage", "orange juice"),
        () -> isSameHouse("nationality", "Japanese", "hobby", "chess"),
        () -> isNeighbor("nationality", "Norwegian", "house", "blue")
    );

    private boolean isSameHouse(String key1, String value1, String key2, String value2) {
        return assertDistance(key1, value1, key2, value2, 0);
    }

    private boolean isNeighbor(String key1, String value1, String key2, String value2) {
        return assertDistance(key1, value1, key2, value2, 1) || assertDistance(key1, value1, key2, value2, -1);
    }

    private boolean hasProperty(int houseIndex, String key, String value) {
        Map<String, String> house = findHouse(key, value);
        if (house == null) return true;
        return houseIndex == houses.indexOf(house);
    }

    private boolean assertDistance(String key1, String value1, String key2, String value2, int expected) {
        Map<String, String> house1 = findHouse(key1, value1), house2 = findHouse(key2, value2);
        if (house1 == null || house2 == null) return true;
        return houses.indexOf(house1) - houses.indexOf(house2) == expected;
    }


    
    private final List<Map<String, String>> houses = new ArrayList<>(HOUSE_COUNT);
    private final Map<String, Set<List<String>>> permutations = new HashMap<>();

    public ZebraPuzzle() {
        for (int i = 0; i < HOUSE_COUNT; i++) {
            houses.add(new HashMap<>());
        }
        backtrack(0);
    }

    private boolean backtrack(int houseIndex) {
        if (houseIndex > 0 && !matchesRules()) return false;
        if (houseIndex == HOUSE_COUNT) return true;

        String key = ORDER[houseIndex];
        for (List<String> permutation : getPermutations(key)) {
            for (int i = 0; i < HOUSE_COUNT; i++) {
                houses.get(i).put(key, permutation.get(i));
            }
            if (backtrack(houseIndex + 1)) return true;
            for (Map<String, String> house : houses) {
                house.remove(key);
            }
        }
        return false;
    }

    private Set<List<String>> getPermutations(String key) {
        return permutations.computeIfAbsent(key, k -> {
            Set<List<String>> result = new HashSet<>();
            Set<String> set = PROPERTIES.get(key);
            computePermutations(result, new ArrayList<>(set), set.size(), 0);
            return result;
        });
    }

    private void computePermutations(Set<List<String>> result, List<String> list, int size, int start) {
        if (start == size) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < size; i++) {
            Collections.swap(list, start, i);
            computePermutations(result, list, size, start + 1);
            Collections.swap(list, start, i);
        }
    }

    private boolean matchesRules() {
        for (Supplier<Boolean> rule : RULES) {
            if (!rule.get()) return false;
        }
        return true;
    }

    

    private Map<String, String> findHouse(String key, String value) {
        return houses.stream()
            .filter(map -> value.equals(map.get(key)))
            .findFirst()
            .orElse(null);
    }
    
    String getWaterDrinker() {
        return findHouse("beverage", "water").get("nationality");
    }

    String getZebraOwner() {
        return findHouse("pet", "zebra").get("nationality");
    }
}