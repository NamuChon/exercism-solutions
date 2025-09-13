def egg_count(display_value: int) -> int:
    count = 0
    while display_value > 0:
        display_value &= display_value - 1
        count += 1
    return count
