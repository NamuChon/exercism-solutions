def convert(number: int) -> str:
    words = []
    if number % 3 == 0: words.append("Pling")
    if number % 5 == 0: words.append("Plang")
    if number % 7 == 0: words.append("Plong")
    return "".join(words) if words else str(number)
