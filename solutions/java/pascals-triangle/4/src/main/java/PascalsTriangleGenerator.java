import java.util.stream.IntStream;
class PascalsTriangleGenerator {

    int[][] generateTriangle(int rows) {
        int[][] result = new int[rows][];
        IntStream.range(0, rows).forEach(i -> {
            result[i] = new int[i + 1];
            result[i][0] = 1;
            result[i][i] = 1;
            IntStream.range(1, i).forEach(j -> result[i][j] = result[i - 1][j - 1] + result[i - 1][j]);
        });
        return result;
    }
}