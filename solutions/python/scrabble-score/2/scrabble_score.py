def score(word: str) -> int:
    PACKED_SCORES = {
        "AEIOULNRST": 1,
        "DG": 2,
        "BCMP": 3,
        "FHVWY": 4,
        "K": 5,
        "JX": 8,
        "QZ": 10
    }
    SCORES = {letter: value for letters, value in PACKED_SCORES.items() for letter in letters}
    return sum(map(lambda s: SCORES[s], word.upper()))
