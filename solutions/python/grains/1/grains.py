def square(number):
    if not 1 <= number <= 64: raise ValueError("square must be between 1 and 64")
    return pow(2, number - 1)

def total():
    return sum(square(i + 1) for i in range(64))
