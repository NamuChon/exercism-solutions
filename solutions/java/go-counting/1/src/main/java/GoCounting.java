import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;
class GoCounting {
    private static final int[] DIRECTION_X = {-1, 1, 0, 0}, DIRECTION_Y = {0, 0, -1, 1};
    private final int limitX, limitY;
    private final Map<Set<Point>, Player> territories;
    GoCounting(String board) {
        char[][] boardChar = Arrays.stream(board.split("\n")).map(String::toCharArray).toArray(char[][]::new);
        limitX = boardChar[0].length - 1;
        limitY = boardChar.length - 1;
        boolean[][] visited = new boolean[boardChar[0].length][boardChar.length];
        territories = new HashMap<>();
        for (int y = 0; y < boardChar.length; y++) {
            for (int x = 0; x < boardChar[y].length; x++) {
                if (visited[x][y] || boardChar[y][x] != ' ') continue;
                Point currentPoint = new Point(x, y);
                putTerritory(boardChar, visited, territories, currentPoint);
            }
        }
    }

    Player getTerritoryOwner(int x, int y) {
        throwIfOutOfBounds(x, y);
        return findTerritory(x, y).getValue();
    }

    Set<Point> getTerritory(int x, int y) {
        throwIfOutOfBounds(x, y);
        return findTerritory(x, y).getKey();
    }

    Map<Player, Set<Point>> getTerritories() {
        Map<Player, Set<Point>> result = territories.entrySet()
            .stream()
            .collect(Collectors.toMap(e -> e.getValue(), e -> e.getKey(), (set1, set2) -> {
                set1.addAll(set2);
                return set1;
            }, HashMap::new));
        for (Player p : Player.values()) {
            result.putIfAbsent(p, Set.of());
        }
        return result;
    }

    private Map.Entry<Set<Point>, Player> findTerritory(int x, int y) {
        Point targetPoint = new Point(x, y);
        return territories.entrySet()
            .stream()
            .filter(e -> e.getKey().contains(targetPoint))
            .findFirst()
            .orElse(Map.entry(Set.of(), Player.NONE));
    }

    private void throwIfOutOfBounds(int x, int y) {
        if (!isInBoundary(x, y)) throw new IllegalArgumentException("Invalid coordinate");
    }

    private void putTerritory(char[][] boardChar, boolean[][] visited, Map<Set<Point>, Player> territories, Point point) {
        
        Queue<Point> queue = new ArrayDeque<>();
        Set<Point> territory = new HashSet<>();
        Player owner = null;
        
        queue.add(point);
        territory.add(point);
        while (!queue.isEmpty()) {
            Point currentPoint = queue.poll();
            int x = (int) currentPoint.getX(), y = (int) currentPoint.getY();

            for (int i = 0; i < 4; i++) {
                int neighborX = x + DIRECTION_X[i], neighborY = y + DIRECTION_Y[i];
                Point neighborPoint = new Point(neighborX, neighborY);
                if (!isInBoundary(neighborX, neighborY) || territory.contains(neighborPoint)) continue;
                char boardPoint = boardChar[neighborY][neighborX];
                if (boardPoint == ' ') {
                    territory.add(neighborPoint);
                    queue.add(neighborPoint);
                } else {
                    if (owner == Player.NONE) continue;
                    Player newOwner = getPlayer(boardPoint);
                    if (owner == null) {
                        owner = newOwner;
                    } else if (owner != newOwner) {
                        owner = Player.NONE;
                    }
                }
            }
        }
        if (owner == null) owner = Player.NONE;
        territories.put(territory, owner);
    }

    private boolean isInBoundary(int x, int y) {
        return x >= 0 && y >= 0 && x <= limitX && y <= limitY;
    }

    private Player getPlayer(char boardPoint) {
        return switch (boardPoint) {
            case 'W' -> Player.WHITE;
            case 'B' -> Player.BLACK;
            default -> Player.NONE;
        };
    }
}