import java.util.List;
import java.util.stream.*;
class MinesweeperBoard {
    private final List<String> withNumbers;
    MinesweeperBoard(List<String> boardRows) {
        withNumbers = IntStream.range(0, boardRows.size()).mapToObj(rowIndex -> convertRow(rowIndex, boardRows)).toList();
    }
    private String convertRow(int rowIndex, List<String> boardRows) {
        String row = boardRows.get(rowIndex);
        int rowLength = row.length();
        int columnLength = boardRows.size();
        return IntStream.range(0, rowLength).mapToObj(columnIndex -> {
            if (row.charAt(columnIndex) == '*') return "*";
            int count = 0;
            for (int i = rowIndex - 1; i <= rowIndex + 1; i++) {
                if (i < 0 || i >= columnLength) continue;
                for (int j = columnIndex - 1; j <= columnIndex + 1; j++) {
                    if (i == rowIndex && j == columnIndex || j < 0 || j >= rowLength) continue;
                    if (boardRows.get(i).charAt(j) == '*') count++;
                }
            }
            return count == 0 ? " " : String.valueOf(count);
        }).collect(Collectors.joining());
    }
    List<String> withNumbers() {
        return withNumbers;
    }

}