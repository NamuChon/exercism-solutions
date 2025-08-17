def say(number: int) -> str:
    LARGE_SCALES = ("", "thousand", "million", "billion")
    
    if not 0 <= number <= 999_999_999_999: raise ValueError("input out of range")
    if number == 0: return "zero"

    result = []
    i = 0
    while number:
        hundreds = say_hundreds(number % 1000)
        if hundreds: result.insert(0, hundreds + (" " + LARGE_SCALES[i] if i else ""))
        number //= 1000
        i += 1
    return " ".join(result)


def say_hundreds(number: int) -> str:
    ONES = ("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    TENS = ("twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
    
    result = ""
    
    hundreds = number // 100
    if hundreds:
        result += ONES[hundreds] + " hundred "
        number %= 100
    
    if number < 20:
        result += ONES[number]
    else:
        result += TENS[number // 10 - 2] + ("-" + ONES[ones] if (ones := number % 10) else "")

    return result.strip()
