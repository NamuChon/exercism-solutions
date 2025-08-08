def is_armstrong_number(number):
    digits = str(number)
    length = len(digits)
    return number == sum(int(digit)**length for digit in digits)
