from string import ascii_lowercase as LETTERS
import secrets

ORD_A, ALPHABET_LENGTH = ord("a"), len(LETTERS)

class Cipher:
    def __init__(self, key: str = None):
        self.key = key or "".join(secrets.choice(LETTERS) for _ in range(secrets.randbelow(101) + 100))
        self.key_length = len(self.key)
        self.key_values = [ord(s) - ORD_A for s in self.key]

    def encode(self, text: str, reverse: bool = False) -> str:
        return "".join(chr((ord(s) - ORD_A
                            + (-1 if reverse else 1) * self.key_values[i%self.key_length])
                            % ALPHABET_LENGTH
                            + ORD_A)
                       for i, s in enumerate(text))

    def decode(self, text: str) -> str:
        return self.encode(text, True)
