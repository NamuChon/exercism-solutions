def score(word: str) -> int:
    PACKED_SCORES = {
        ("A", "E", "I", "O", "U", "L", "N", "R", "S", "T"): 1,
        ("D", "G"): 2,
        ("B", "C", "M", "P"): 3,
        ("F", "H", "V", "W", "Y"): 4,
        ("K",): 5,
        ("J", "X"): 8,
        ("Q", "Z"): 10
    }
    SCORES = {letter: value for letters, value in PACKED_SCORES.items() for letter in letters}
    return sum(map(lambda s: SCORES[s], word.upper()))
