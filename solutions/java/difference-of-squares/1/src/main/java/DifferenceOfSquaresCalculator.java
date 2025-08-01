class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int sum = 0;
        for (int i = 1; i < input + 1; i++) {
            sum += i;
        }
        return sum * sum;
    }

    int computeSumOfSquaresTo(int input) {
        int result = 0;
        for (int i = 1; i < input + 1; i++) {
            result += i * i;
        }
        return result;
    }

    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
    }
    
}