def prime(number: int) -> int:
    if number == 0: raise ValueError("there is no zeroth prime")
    if number == 1: return 2

    nth, current = 1, 1
    while nth < number:
        current += 2
        if is_prime(current): nth += 1
    return current


def is_prime(number: int) -> bool:
    return all(number % i != 0 for i in range(2, int(number**0.5) + 1))