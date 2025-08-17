import re

def abbreviate(words: str) -> str:
    return "".join(re.findall(r"(?<![a-zA-Z'])[a-zA-Z]", words)).upper()
