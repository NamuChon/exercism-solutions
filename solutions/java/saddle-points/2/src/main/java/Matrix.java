import java.util.*;
import java.util.stream.IntStream;
class Matrix {
    private final Set<MatrixCoordinate> saddlePoints;
    Matrix(List<List<Integer>> values) {
        saddlePoints = new HashSet<>();
        for (int i = 0; i < values.size(); i++) {
            List<Integer> currentRow = values.get(i);
            for (int j = 0; j < currentRow.size(); j++) {
                int currentNum = currentRow.get(j);
                final int finalJ = j;
                if (currentNum == Collections.max(currentRow) && currentNum == values.stream()
                   .mapToInt(l -> l.get(finalJ))
                   .min().orElse(-1)) {
                    saddlePoints.add(new MatrixCoordinate(i + 1, j + 1));
                }
            }
        }
    }
    Set<MatrixCoordinate> getSaddlePoints() {
            return saddlePoints;
    }
}
