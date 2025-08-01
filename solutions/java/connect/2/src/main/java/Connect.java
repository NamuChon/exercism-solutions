import java.util.*;
import java.awt.*;
class Connect {

    private static final int[] X_SHIFT = {1, 2, 1, -1, -2, -1}, Y_SHIFT = {1, 0, -1, -1, 0, 1};
    
    private final char[][] board;
    
    public Connect(String[] board) {
        this.board = Arrays.stream(board)
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    public Winner computeWinner() {
        boolean playerOWon = backtrack('O', searchStart('O', true), new HashSet<>());
        boolean playerXWon = backtrack('X', searchStart('X', false), new HashSet<>());
        return playerOWon == playerXWon ? Winner.NONE : playerOWon ? Winner.PLAYER_O : Winner.PLAYER_X;
    }

    private boolean backtrack(char target, Set<Point> current, Set<Point> visited) {
        for (Point point : current) {
            if (visited.contains(point)) continue;
            visited.add(point);
            int x = (int) point.getX(), y = (int) point.getY();
            if (target == 'O' && y == board.length - 1 || target == 'X' && x == board[y].length - 1) return true;
            if (backtrack(target, searchHexagon(target, x, y), visited)) return true;
        }
        return false;
    }

    private Set<Point> searchStart(char target, boolean isRow) {
        Set<Point> result = new HashSet<>();
        if (isRow) {
            char[] row = board[0];
            for (int i = 0; i < row.length; i++) {
                if (row[i] == target) result.add(new Point(i, 0));
            }
        } else {
            for (int i = 0; i < board.length; i++) {
                if (board[i][i] == target) result.add(new Point(i, i));
            }
        }
        return result;
    }

    private Set<Point> searchHexagon(char target, int centerX, int centerY) {
        Set<Point> result = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            int x = centerX + X_SHIFT[i], y = centerY + Y_SHIFT[i];
            if (y >= 0 && y < board.length && x >= 0 && x < board[y].length && board[y][x] == target) {
                result.add(new Point(x, y));
            }
        }
        return result;
    }
}
