from string import ascii_lowercase as LETTERS
import secrets

ALPHABET_LENGTH = len(LETTERS)

class Cipher:
    def __init__(self, key: str = None):
        self.key = key or "".join(secrets.choice(LETTERS) for _ in range(secrets.randbelow(101) + 100))
        self.key_length = len(self.key)
        self.key_values = list(map(LETTERS.index, self.key))

    def encode(self, text: str, reverse: bool = False) -> str:
        return "".join(LETTERS[(LETTERS.index(s)
                                + (-1 if reverse else 1) * self.key_values[i%self.key_length])
                                % ALPHABET_LENGTH]
                       for i, s in enumerate(text))

    def decode(self, text: str) -> str:
        return self.encode(text, True)
