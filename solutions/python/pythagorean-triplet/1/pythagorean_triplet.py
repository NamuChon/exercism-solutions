def triplets_with_sum(number: int) -> list[list[int]]:
    result = []
    for a in range(3, number//3):
        b = (number*number - 2*number*a) // (2*number - 2*a)
        c = number - a - b
        if a < b < c and a**2 + b**2 == c**2:
            result.append([a, b, c])
    return result
