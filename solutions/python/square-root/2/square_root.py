def square_root(number: int) -> int:
    i = 0
    while (i+1) * (i+1) <= number: i += 1
    return i
