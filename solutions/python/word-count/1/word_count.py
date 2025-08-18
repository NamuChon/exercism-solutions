import re
from collections import Counter

def count_words(sentence: str) -> dict[str, int]:
    return dict(Counter(re.findall(r"[a-zA-Z0-9]+(?:'[a-zA-Z0-9]+)?", sentence.lower())))
