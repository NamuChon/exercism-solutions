import java.util.Arrays;
class PascalsTriangleGenerator {

    int[][] generateTriangle(int rows) {
        int[][] result = new int[rows][];
        int[] currentRow = {1};
        for (int i = 0; i < rows; i++) {
            result[i] = Arrays.copyOf(currentRow, currentRow.length);
            int[] nextRow = new int[currentRow.length + 1];
            nextRow[0] = 1;
            nextRow[nextRow.length - 1] = 1;
            for (int j = 1; j < nextRow.length - 1; j++) {
                nextRow[j] = currentRow[j - 1] + currentRow[j];
            }
            currentRow = Arrays.copyOf(nextRow, nextRow.length);
        }
        return result;
    }
}