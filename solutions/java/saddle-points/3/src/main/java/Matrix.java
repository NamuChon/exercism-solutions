import java.util.*;
import java.util.stream.IntStream;
class Matrix {
    private final Set<MatrixCoordinate> saddlePoints;
    Matrix(List<List<Integer>> values) {
        saddlePoints = new HashSet<>();
        if (values.isEmpty()) return;
        int[] columnMins = IntStream.range(0, values.get(0).size())
            .map(i -> values.stream()
                .mapToInt(row -> row.get(i))
                .min().orElse(-1))
            .toArray();
        
        IntStream.range(0, values.size())
            .forEach(i -> {
                List<Integer> currentRow = values.get(i);
                int rowMax = Collections.max(currentRow);
                IntStream.range(0, currentRow.size())
                    .forEach(j -> {
                        int currentNum = currentRow.get(j);
                        if (currentNum == rowMax && currentNum == columnMins[j]) {
                            saddlePoints.add(new MatrixCoordinate(i + 1, j + 1));
                        }
                    });
            });
    }
    Set<MatrixCoordinate> getSaddlePoints() {
            return saddlePoints;
    }
}
