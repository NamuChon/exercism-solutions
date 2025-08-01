class SpiralMatrixBuilder {
    int[][] buildMatrixOfSize(int size) {
        final int[] X_MOVE = {0, 1, 0, -1}, Y_MOVE = {-1, 0, 1, 0};
        int[][] result = new int[size][size];
        int direction = 1, currentX = 0, currentY = 0;
        for (int i = 1; i <= size*size; i++) {
            result[currentY][currentX] = i;
            currentX += X_MOVE[direction];
            currentY += Y_MOVE[direction];
            int nextX = currentX + X_MOVE[direction];
            int nextY = currentY + Y_MOVE[direction];
            if (isOutOfBound(nextX, size) || isOutOfBound(nextY, size) || result[nextY][nextX] != 0) {
                direction = (direction + 1)%4;
            }
        }
        return result;
    }
    private boolean isOutOfBound(int coordinate, int size) {
        return coordinate < 0 || coordinate >= size;
    }
}
