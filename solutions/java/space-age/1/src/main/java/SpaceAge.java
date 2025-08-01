import java.util.*;
class SpaceAge {
    private final Map<String, Double> ORBITAL_PERIOD = Map.of(
        "Mercury", 0.2408467,
        "Venus", 0.61519726,
        "Earth", 1.0,
        "Mars", 1.8808158,
        "Jupiter", 11.862615,
        "Saturn", 29.447498,
        "Uranus", 84.016846,
        "Neptune", 164.79132);
    private final double seconds, years;
    SpaceAge(double seconds) {
        this.seconds = seconds;
        this.years = seconds / 31_557_600;
    }

    double getSeconds() {
        return seconds;
    }

    private double getYearsOnPlanet(String planet) {
        return years / ORBITAL_PERIOD.get(planet);
    }
    
    double onEarth() {
        return getYearsOnPlanet("Earth");
    }

    double onMercury() {
        return getYearsOnPlanet("Mercury");
    }

    double onVenus() {
        return getYearsOnPlanet("Venus");
    }

    double onMars() {
        return getYearsOnPlanet("Mars");
    }

    double onJupiter() {
        return getYearsOnPlanet("Jupiter");
    }

    double onSaturn() {
        return getYearsOnPlanet("Saturn");
    }

    double onUranus() {
        return getYearsOnPlanet("Uranus");
    }

    double onNeptune() {
        return getYearsOnPlanet("Neptune");
    }

}
