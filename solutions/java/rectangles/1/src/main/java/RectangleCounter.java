import java.util.*;
import java.util.List;
import java.awt.*;
class RectangleCounter {

    int countRectangles(String[] grid) {
        char[][] matrix = Arrays.stream(grid)
            .map(String::toCharArray)
            .toArray(char[][]::new);
        int count = 0;
        for (Point start : getStartingPoints(matrix)) {
            for (int horizontal : getHorizontalConnections(matrix, start)) {
                for (int vertical : getVerticalConnections(matrix, start)) {
                    if (matrix[vertical][horizontal] != '+') continue;
                    Point diagonal = new Point(horizontal, vertical);
                    if (isConnected(matrix, new Point(horizontal, start.y), diagonal) && isConnected(matrix, new Point(start.x, vertical), diagonal)) count++;
                }
            }
        }
        return count;
    }

    private List<Point> getStartingPoints(char[][] matrix) {
        List<Point> result = new ArrayList<>();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == '+') result.add(new Point(x, y));
            }
        }
        return result;
    }

    private List<Integer> getHorizontalConnections(char[][] matrix, Point start) {
        List<Integer> result = new ArrayList<>();
        char[] row = matrix[start.y];
        for (int x = start.x + 1; x < row.length; x++) {
            char current = row[x];
            if (current == '+') result.add(x);
            else if (current != '-') return result;
        }
        return result;
    }

    private List<Integer> getVerticalConnections(char[][] matrix, Point start) {
        List<Integer> result = new ArrayList<>();
        for (int y = start.y + 1; y < matrix.length; y++) {
            char current = matrix[y][start.x];
            if (current == '+') result.add(y);
            else if (current != '|') return result;
        }
        return result;
    }

    private boolean isConnected(char[][] matrix, Point point1, Point point2) {
        if (point1.y == point2.y) {
            char[] row = matrix[point1.y];
            for (int x = point1.x + 1; x < point2.x; x++) {
                char current = row[x];
                if (current != '+' && current != '-') return false;
            }
            return true;
        } else if (point1.x == point2.x) {
            for (int y = point1.y + 1; y < point2.y; y++) {
                char current = matrix[y][point1.x];
                if (current != '+' && current != '|') return false;
            }
            return true;
        } else return false;
    }
}