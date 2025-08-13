def find_anagrams(word: str, candidates: list[str]) -> list[str]:
    word = word.lower()
    word_sorted = sorted(word)
    return [s for s in candidates if (lower := s.lower()) != word and sorted(lower) == word_sorted]
