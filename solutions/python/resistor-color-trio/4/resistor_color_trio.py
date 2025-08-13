from math import log

def label(colors: list[str]) -> str:
    COLORS = ("black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white")
    UNITS = ("", "kilo", "mega", "giga")
    result = (COLORS.index(colors[0])*10 + COLORS.index(colors[1])) * 10**COLORS.index(colors[2])
    unit = 0 if result == 0 else min(3, int(log(result, 1000)))
    result /= 1000**unit
    return f"{result:g} {UNITS[unit]}ohms"
