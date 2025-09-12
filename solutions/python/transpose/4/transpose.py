from itertools import zip_longest

def transpose(text: str) -> str:
    return "\n".join("".join(s).rstrip("\0").replace("\0", " ") for s in zip_longest(*text.splitlines(), fillvalue="\0"))