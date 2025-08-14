def square_root(number: int) -> int:
    i = 0
    while i*i < number: i += 1
    return i
