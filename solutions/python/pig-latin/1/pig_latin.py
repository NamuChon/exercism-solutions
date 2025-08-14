import re

def translate(text: str) -> str:
    return " ".join([translate_word(s) for s in text.split(" ")])


def translate_word(word: str) -> str:
    if not re.match(r"^(?:[aeiou]|xr|yt)", word):
        start = re.search(r"(?:[aeiou]|(?<=.)y)", word).start()
        if word[start-1:start+1] == "qu":
            start += 1
        word = word[start:] + word[:start]
    return word + "ay"
