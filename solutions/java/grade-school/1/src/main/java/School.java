import java.util.*;
class School {
    private Map<Integer, List<String>> roster = new HashMap<>();
    boolean add(String student, int grade) {
        if (roster().contains(student)) return false;
        grade(grade).add(student);
        return true;
    }

    List<String> roster() {
        return roster.values().stream().flatMap(l -> l.stream().sorted()).toList();
    }

    List<String> grade(int grade) {
        roster.putIfAbsent(grade, new ArrayList<>());
        final List<String> gradeList = roster.get(grade);
        gradeList.sort(null);
        return gradeList;
    }

}
