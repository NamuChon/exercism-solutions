class SpaceAge:
    
    def __init__(self, seconds: int):
        PERIOD_IN_EARTH_YEARS = {
            "Mercury": 0.2408467,
            "Venus": 0.61519726,
            "Earth": 1.0,
            "Mars": 1.8808158,
            "Jupiter": 11.862615,
            "Saturn": 29.447498,
            "Uranus": 84.016846,
            "Neptune": 164.79132
        }
        age_on_earth = seconds / 31557600
        self.periods = {planet: round(age_on_earth/period, 2) for planet, period in PERIOD_IN_EARTH_YEARS.items()}

    
    def on_mercury(self) -> float:
        return self.periods["Mercury"]

    def on_venus(self) -> float:
        return self.periods["Venus"]

    def on_earth(self) -> float:
        return self.periods["Earth"]

    def on_mars(self) -> float:
        return self.periods["Mars"]

    def on_jupiter(self) -> float:
        return self.periods["Jupiter"]

    def on_saturn(self) -> float:
        return self.periods["Saturn"]

    def on_uranus(self) -> float:
        return self.periods["Uranus"]

    def on_neptune(self) -> float:
        return self.periods["Neptune"]
    
