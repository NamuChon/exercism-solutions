from string import ascii_lowercase

def encode(plain_text: str) -> str:
    result_list = [ascii_lowercase[-ascii_lowercase.find(s)-1] if s.isalpha() else s
                   for s in plain_text.lower()
                   if s.isalpha() or s.isdigit()]
    length = len(result_list)
    return " ".join("".join(result_list[i:min(length, i+5)]) for i in range(0, length, 5))


def decode(ciphered_text):
    return encode(ciphered_text).replace(" ", "")
