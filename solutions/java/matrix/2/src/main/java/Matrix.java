import java.util.*;
class Matrix {
    final int[][] rows;
    final int[][] columns;
    Matrix(String matrixAsString) {
        rows = Arrays.stream(matrixAsString.split("\n"))
            .map(row -> Arrays.stream(row.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray())
            .toArray(int[][]::new);
        
        int height = rows.length;
        int length = rows[0].length;
        columns = new int[length][height];
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                columns[j][i] = rows[i][j];
            }
        }
    }

    int[] getRow(int rowNumber) {
        return rows[rowNumber - 1];
    }

    int[] getColumn(int columnNumber) {
        return columns[columnNumber - 1];
    }
}
