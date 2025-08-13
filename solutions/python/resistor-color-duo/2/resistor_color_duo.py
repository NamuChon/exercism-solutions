def value(colors: list[str]) -> int:
    COLORS = ("black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white")
    return COLORS.index(colors[0])*10 + COLORS.index(colors[1])
