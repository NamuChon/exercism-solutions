import re

def translate(text: str) -> str:
    return " ".join(map(translate_word, text.split(" ")))


def translate_word(word: str) -> str:
    return re.sub("^(x(?!r)|y(?!t)|[^aeiouxy])([^aeiouy]*(?:(?<=q)u)?)(.*?)$", r"\3\1\2", word) + "ay"
