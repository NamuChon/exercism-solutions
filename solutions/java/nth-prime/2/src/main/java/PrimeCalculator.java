class PrimeCalculator {

    int nth(int nth) {
        if (nth < 1) {
            throw new IllegalArgumentException();
        }
        
        int result = 2, primeIndex = 0;
        for (int current = 2; primeIndex < nth; current++) {
            boolean isPrime = true;
            for(int i = 2; i <= Math.sqrt(current); i++) {
                if (current % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                result = current;
                primeIndex++;
            }
        }
        return result;
    }
}
