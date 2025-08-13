from string import ascii_lowercase, ascii_uppercase

def rotate(text: str, key: int) -> str:
    SIZE = 26
    return "".join([(s if not s.isalpha()
                     else ascii_lowercase[(ascii_lowercase.find(s) + key) % SIZE] if s.islower()
                     else ascii_uppercase[(ascii_uppercase.find(s) + key) % SIZE]) for s in text])
