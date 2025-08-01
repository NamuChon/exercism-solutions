import java.util.*;
import java.util.stream.*;
class RelativeDistance {
    private final Map<String, List<String>> familyTree;

    RelativeDistance(Map<String, List<String>> familyTree) {
        this.familyTree = familyTree;
    }

    int degreeOfSeparation(String personA, String personB) {
        Queue<Person> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> distance = new HashMap<>();

        queue.add(new Person(familyTree, personA));
        visited.add(personA);
        distance.put(personA, 0);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            int currentDistance = distance.get(current.getName());

            for (String relative : current.getRelatives()) {
                if (visited.contains(relative)) continue;
                if (relative.equals(personB)) return currentDistance + 1;
                visited.add(relative);
                distance.put(relative, currentDistance + 1);
                queue.add(new Person(familyTree, relative));
            }
        }
        return -1;
    }
}

class Person {
    private final String name;
    private final List<String> relatives;

    public Person(Map<String, List<String>> familyTree, String person) {
        name = person;
        List<String> parents = familyTree.getOrDefault(person, List.of());
        List<String> children = familyTree.entrySet()
            .stream()
            .filter(e -> e.getValue().contains(person))
            .map(Map.Entry::getKey)
            .toList();
        List<String> spouses = familyTree.values()
            .stream()
            .filter(l -> l.contains(person))
            .flatMap(List::stream)
            .distinct()
            .filter(s -> !s.equals(person))
            .toList();
        
        relatives = Stream.of(parents, children, spouses)
            .flatMap(List::stream)
            .toList();
    }

    public String getName() {
        return name;
    }

    public List<String> getRelatives() {
        return relatives;
    }
}