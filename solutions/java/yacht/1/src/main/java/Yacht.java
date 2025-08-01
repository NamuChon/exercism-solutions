import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
class Yacht {
    private final int score;
    Yacht(int[] dice, YachtCategory yachtCategory) {
        int diceTotal = Arrays.stream(dice).sum();
        switch (yachtCategory) {
            case ONES:
            case TWOS:
            case THREES:
            case FOURS:
            case FIVES:
            case SIXES:
                int categoryNum = yachtCategory.ordinal();
                score = (int) Arrays.stream(dice).filter(i -> i == categoryNum).count() * categoryNum;
                break;
            case FULL_HOUSE:
                score = matchesFrequency(dice, 3, true) && matchesFrequency(dice, 2, true) ? diceTotal : 0;
                break;
            case FOUR_OF_A_KIND:
                score = matchesFrequency(dice, 4, false) ? 4 * numRepeatedFourTimes(dice) : 0;
                break;
            case LITTLE_STRAIGHT:
                score = straightResult(dice, 1);
                break;
            case BIG_STRAIGHT:
                score = straightResult(dice, 2);
                break;
            case CHOICE:
                score = diceTotal;
                break;
            default:
                score = matchesFrequency(dice, 5, true) ? 50 : 0;
        }
    }

    int score() {
        return score;
    }

    private boolean matchesFrequency(int[] dice, int count, boolean countExact) {
        return Arrays.stream(dice).anyMatch(i -> {
            int currentCount = (int) Arrays.stream(dice).filter(j -> j == i).count();
            return countExact ? currentCount == count : currentCount >= count;
        });
    }

    private int numRepeatedFourTimes(int[] dice) {
        return dice[0] == dice[1] ? dice[0] : dice[2];
    }

    private int straightResult(int[] dice, int start) {
        return IntStream.range(start, start + 5).allMatch(i -> Arrays.stream(dice).anyMatch(j -> j == i)) ? 30 : 0;
    }
}
