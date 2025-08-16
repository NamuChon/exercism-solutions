def factors(value: int) -> int:
    result = []
    i = 2
    while value > 1:
        if value % i == 0:
            result.append(i)
            value //= i
            i = 2
        else: i += 1
    return result
