import java.util.stream.*;
class RailFenceCipher {
    private final int rows, period;
    RailFenceCipher(int rows) {
        this.rows = rows;
        this.period = 2*rows - 2;
    }

    String getEncryptedData(String message) {
        return IntStream.range(0, rows).mapToObj(i -> message.replaceAll((i == 0 ? "(.?)()" : ".{1," + i + "}(.?)") + (i == rows - 1 ? "()" : ".{0," + (period - 1 - 2*i) + "}") + (i == 0 ? "" : (i == rows - 1 ? "" : "(.?)") + ".{0," + (i - 1) + "}"), "$1$2")).collect(Collectors.joining());
    }

    String getDecryptedData(String message) {
        int length = message.length();
        boolean[][] marker = new boolean[rows][length];
        int direction = 1, currentRow = 0;
        for (int i = 0; i < length; i++) {
            marker[currentRow][i] = true;
            currentRow += direction;
            if (currentRow <= 0 || currentRow >= rows - 1) direction *= -1;
        }
        char[][] fence = new char[rows][length];
        int messageIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < length; j++) {
                if (marker[i][j]) fence[i][j] = message.charAt(messageIndex++);
            }
        }
        StringBuilder result = new StringBuilder();
        direction = 1;
        currentRow = 0;
        for (int i = 0; i < length; i++) {
            result.append(fence[currentRow][i]);
            currentRow += direction;
            if (currentRow <= 0 || currentRow >= rows - 1) direction *= -1;
        }
        return result.toString();
    }

}