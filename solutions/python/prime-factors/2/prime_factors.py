def factors(value: int) -> int:
    result = []
    i = 2
    
    while i**2 <= value:
        if value % i == 0:
            result.append(i)
            value //= i
        else: i += 1
    
    if value > 1: result.append(value)
    return result
