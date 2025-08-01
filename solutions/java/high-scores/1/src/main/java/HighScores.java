import java.util.List;
import java.util.Comparator;
class HighScores {
    private final List<Integer> highScores;
    public HighScores(List<Integer> highScores) {
        this.highScores = highScores;
    }

    List<Integer> scores() {
        return highScores;
    }

    Integer latest() {
        return highScores.getLast();
    }

    Integer personalBest() {
        return highScores.stream().max(Integer::compareTo).get();
    }

    List<Integer> personalTopThree() {
        return highScores.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
    }

}
