import java.util.*;
import java.util.stream.Collectors;
class BowlingGame {
    private int score = 0, frameNum = 1, remainingPins = 10;
    private List<Integer> remainingBonusCount = new ArrayList<>();
    private boolean isFirstRoll = true;
    void roll(int pins) {
        if (pins < 0) throw new IllegalStateException("Negative roll is invalid");
        if (pins > 10) throw new IllegalStateException("Pin count exceeds pins on the lane");
        boolean isBonusFrame = frameNum == 11;
        remainingPins -= pins;
        if (remainingPins < 0) throw new IllegalStateException("Pin count exceeds pins on the lane");
        score += pins;
        if (remainingBonusCount.isEmpty()) {
            if (isBonusFrame) throw new IllegalStateException("Cannot roll after game is over");
        } else {
            remainingBonusCount = remainingBonusCount.stream().map(i -> {
                score += pins;
                return i - 1;
            }).filter(i -> i != 0).collect(Collectors.toList());
            if (isBonusFrame) {
                score -= pins;
                if (remainingPins == 0) remainingPins = 10;
                return;
            }
        }
        if (isFirstRoll) {
            if (remainingPins == 0) {
                remainingBonusCount.add(2);
                newFrame();
            } else isFirstRoll = false;
        } else {
            if (remainingPins == 0) remainingBonusCount.add(1);
            newFrame();
        }
    }

    int score() {
        if (frameNum < 10 || !remainingBonusCount.isEmpty()) throw new IllegalStateException("Score cannot be taken until the end of the game");
        return score;
    }

    private void newFrame() {
        isFirstRoll = true;
        frameNum++;
        remainingPins = 10;
    }
}