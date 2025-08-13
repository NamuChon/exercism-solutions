from collections import Counter

def find_anagrams(word: str, candidates: list[str]) -> list[str]:
    word = word.lower()
    letter_frequency = Counter(word)
    return [s for s in candidates if (lower := s.lower()) != word and Counter(lower) == letter_frequency]
