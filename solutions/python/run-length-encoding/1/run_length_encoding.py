import re

def decode(string: str) -> str:
    return re.sub(r"(\d*)([a-zA-Z\s])", lambda m: m.group(2) * int(m.group(1) or 1), string)


def encode(string: str) -> str:
    return re.sub(r"([a-zA-Z\s])\1*", lambda m: ("" if (n := len(m.group())) == 1 else str(n)) + m.group(1), string)
