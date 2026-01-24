def line_up(name, number):
    last_digit = number % 10
    ending = "th" if number % 100 // 10 == 1 else {1: "st", 2: "nd", 3: "rd"}.get(last_digit, "th")
    return f"{name}, you are the {number}{ending} customer we serve today. Thank you!"
