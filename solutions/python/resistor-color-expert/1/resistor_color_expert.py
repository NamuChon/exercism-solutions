from math import log

def resistor_label(colors: list[str]) -> str:
    length = len(colors)
    if length == 1:
        return f"{get_value(colors[0])} ohms"
    
    UNITS = ["", "kilo", "mega", "giga"]
    TOLERANCE = {"grey": 0.05, "violet": 0.1, "blue": 0.25, "green": 0.5, "brown": 1, "red": 2, "gold": 5, "silver": 10}
    result = sum(get_value(color) * 10**(length - i - 3) for i, color in enumerate(colors[:-2])) * 10**get_value(colors[-2])
    unit = 0 if result == 0 else min(3, int(log(result, 1000)))
    result /= 1000**unit
    return f"{result:g} {UNITS[unit]}ohms ±{TOLERANCE[colors[-1]]}%"


def get_value(color: str) -> int:
    return ["black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"].index(color)