class RailFenceCipher {
    private final int rows;
    RailFenceCipher(int rows) {
        this.rows = rows;
    }

    String getEncryptedData(String message) {
        StringBuilder[] rowResults = new StringBuilder[rows];
        for (int i = 0; i < rowResults.length; i++) {
            rowResults[i] = new StringBuilder();
        }
        int direction = 1;
        int currentRow = 0;
        for (char c : message.toCharArray()) {
            rowResults[currentRow].append(c);
            currentRow += direction;
            if (currentRow <= 0 || currentRow >= rows - 1) direction *= -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rowResults) {
            result.append(sb);
        }
        return result.toString();
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