import re

NUMBERS = ["No", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"]

VERSE = """{n1} green bottles hanging on the wall,
{n1} green bottles hanging on the wall,
And if one green bottle should accidentally fall,
There'll be {n2} green bottles hanging on the wall.
"""

def recite(start: int, take: int = 1) -> list[str]:
    result = []
    for i in range(start, start - take, -1):
        result.extend(re.sub(r"(?<=[Oo]ne green bottle)s", "", VERSE.format(n1=NUMBERS[i], n2=NUMBERS[i-1].lower())).split("\n"))
    return result[:-1]
