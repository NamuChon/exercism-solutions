class SpaceAge:
    
    def __init__(self, seconds: int):
        PERIOD_RATIO_IN_EARTH_YEARS = {
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
        
        for planet, period_ratio in PERIOD_RATIO_IN_EARTH_YEARS.items():
            period = round(age_on_earth / period_ratio, 2)
            setattr(self, "on_" + planet.lower(), lambda p=period: p)
    
