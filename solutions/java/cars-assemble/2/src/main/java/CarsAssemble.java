import java.util.HashMap;
import java.util.Map;
public class CarsAssemble {

    public double productionRatePerHour(int speed) {
        final HashMap<Integer, Double> SUCCESS_RATE = new HashMap<>(Map.of(
            1, 1.0,
            2, 1.0,
            3, 1.0,
            4, 1.0,
            5, 0.9,
            6, 0.9,
            7, 0.9,
            8, 0.9,
            9, 0.8,
            10, 0.77
        ));
        SUCCESS_RATE.put(0, 0.0);
        return 221 * speed * SUCCESS_RATE.get(speed);
    }

    public int workingItemsPerMinute(int speed) {
        return (int) (productionRatePerHour(speed) / 60);
    }
}
