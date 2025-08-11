def label(colors: list[str]) -> str:
    COLORS = ["black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"]
    UNITS = ["", "kilo", "mega", "giga"]
    result = (COLORS.index(colors[0])*10 + COLORS.index(colors[1])) * 10**COLORS.index(colors[2])
    unit = 0
    while result != 0 and result % 1000 == 0 and unit < len(UNITS) - 1:
        result //= 1000
        unit += 1
    return str(result) + " " + UNITS[unit] + "ohms"
