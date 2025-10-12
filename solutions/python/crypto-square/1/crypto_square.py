import math

def cipher_text(plain_text: str) -> str:
    normalized = "".join(s for s in plain_text if s.isalnum()).lower()
    if not normalized: return ""
    length = len(normalized)
    r = round(length**0.5)
    c = math.ceil(length / r)
    normalized += " " * (r*c - length)
    return " ".join(normalized[i::c] for i in range(c))
