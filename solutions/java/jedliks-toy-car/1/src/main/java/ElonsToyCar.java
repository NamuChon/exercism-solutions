public class ElonsToyCar {
    int drivenMeter = 0, battery = 100;
    public static ElonsToyCar buy() {
        return new ElonsToyCar();
    }

    public String distanceDisplay() {
        return String.format("Driven %d meters", drivenMeter);
    }

    public String batteryDisplay() {
        return "Battery " + ((battery > 0) ? "at " + battery + "%" : "empty");
    }

    public void drive() {
        if (battery != 0) {
            drivenMeter += 20;
            battery--;
        }
    }
}
