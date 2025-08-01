import java.util.Arrays;
class GameOfLife {
    public int[][] tick(int[][] matrix){
        int[][] changedMatrix = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                switch (neighborSum(matrix, i, j)) {
                    case 2:
                        break;
                    case 3:
                        changedMatrix[i][j] = 1;
                        break;
                    default:
                        changedMatrix[i][j] = 0;
                }
            }
        }
        return changedMatrix;
    }

    private int neighborSum(int[][] matrix, int row, int column) {
        int result = 0;
        if (row != 0) {
            result += rowValue(matrix, row, column, row - 1);
        }
        result += rowValue(matrix, row, column, row);
        if (row != matrix.length - 1) {
            result += rowValue(matrix, row, column, row + 1);
        }
        return result;
    }

    private int rowValue(int[][] matrix, int row, int column, int inspectingRow) {
        int result = 0;
        if (column != 0) {
            result += matrix[inspectingRow][column - 1];
        }
        if (row != inspectingRow) {
            result += matrix[inspectingRow][column];
        }
        if (column != matrix[inspectingRow].length - 1) {
            result += matrix[inspectingRow][column + 1];
        }
        return result;
    }
}
