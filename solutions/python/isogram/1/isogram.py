def is_isogram(string: str) -> bool:
    letters = [s for s in string.lower() if s.isalpha()]
    return len(letters) == len(set(letters))
