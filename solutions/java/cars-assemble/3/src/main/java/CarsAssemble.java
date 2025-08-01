public class CarsAssemble {

    public double productionRatePerHour(int speed) {
        final double[] SUCCESS_RATE = {0.0, 1.0, 1.0, 1.0, 1.0, 0.9, 0.9, 0.9, 0.9, 0.8, 0.77};
        return 221 * speed * SUCCESS_RATE[speed];
    }

    public int workingItemsPerMinute(int speed) {
        return (int) (productionRatePerHour(speed) / 60);
    }
}
