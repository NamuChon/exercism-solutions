def is_armstrong_number(number):
    digits = str(number)
    length = len(digits)
    return number == sum(int(digits[i])**length for i in range(length))
