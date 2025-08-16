def factors(value: int) -> list[int]:
    result = []
    i = 2
    
    while i**2 <= value:
        if value % i == 0:
            result.append(i)
            value //= i
        else: i = 3 if i == 2 else i + 2
    
    if value > 1: result.append(value)
    return result
