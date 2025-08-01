class CollatzCalculator {

    int computeStepCount(int start) {
        if (start < 1) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        int value = start;
        int count = 0;
        while (value != 1) {
            value = value % 2 == 0 ? value / 2 : value * 3 + 1;
            count++;
        }
        return count;
    }
}
