import java.util.*;

public class Prism {

    private static final double EPSILON = 5e-3;

    public record LaserInfo(double x, double y, double angle) {}

    public record PrismInfo(int id, double x, double y, double angle) {}

    public static List<Integer> findSequence(LaserInfo laser, List<PrismInfo> prisms) {
        List<Integer> result = new LinkedList<>();
        while (true) {
            double dx = Math.cos(Math.toRadians(laser.angle()));
            double dy = Math.sin(Math.toRadians(laser.angle()));
            
            PrismInfo hitPrism = null;
            double shortestDistance = Double.MAX_VALUE;
            for (PrismInfo prism : prisms) {
                if (!result.isEmpty() && result.getLast() == prism.id()) continue;
                if (intersects(laser, dx, dy, prism)) {
                    double distance = getDistance(laser, prism);
                    if (distance < shortestDistance) {
                        hitPrism = prism;
                        shortestDistance = distance;
                    }
                }
            }
            if (hitPrism == null) return result;
            if (result.contains(hitPrism.id())) {
                result.add(hitPrism.id());
                return result;
            }
            result.add(hitPrism.id());
            laser = new LaserInfo(hitPrism.x(), hitPrism.y(), laser.angle() + hitPrism.angle());
        }
    }

    private static boolean intersects(LaserInfo laser, double dx, double dy, PrismInfo prism) {
        double vx = prism.x() - laser.x(), vy = prism.y() - laser.y();
        return Math.abs(dx*vy - dy*vx) < EPSILON && dx*vx + dy*vy >= -EPSILON;
    }

    private static double getDistance(LaserInfo laser, PrismInfo prism) {
        return Math.sqrt(Math.pow(prism.x() - laser.x(), 2) + Math.pow(prism.y() - laser.y(), 2));
    }
}