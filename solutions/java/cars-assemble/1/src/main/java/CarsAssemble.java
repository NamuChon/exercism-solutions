public class CarsAssemble {

    public double productionRatePerHour(int speed) {
        final int[] INTERVAL = {1, 5, 9, 10, 11};
        final double[] SUCCESS_RATE = {1, 0.9, 0.8, 0.77};
        for (int i = 0; i < INTERVAL.length - 1; i++)
            {
                if (speed >= INTERVAL[i] && speed < INTERVAL[i + 1])
                {
                    return 221 * speed * SUCCESS_RATE[i];
                }
            }
        return 0;
    }

    public int workingItemsPerMinute(int speed) {
        return (int) (productionRatePerHour(speed) / 60);
    }
}
