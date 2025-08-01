import java.util.*;
import java.util.stream.IntStream;
class Matrix {
    private final List<List<Integer>> rows;
    private final List<List<Integer>> columns;
    Matrix(List<List<Integer>> values) {
        rows = values;
        columns = values.isEmpty() ? values : IntStream.range(0, values.get(0).size())
            .mapToObj(i -> values.stream()
                .map(row -> row.get(i))
                .toList())
            .toList();
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        Set<MatrixCoordinate> result = new HashSet<>();
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < columns.size(); j++) {
                int currentNum = rows.get(i).get(j);
                if (currentNum == Collections.max(rows.get(i)) && currentNum == Collections.min(columns.get(j))) {
                    result.add(new MatrixCoordinate(i + 1, j + 1));
                }
            }
        }
        return result;
    }
}
