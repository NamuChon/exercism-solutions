START = "house that Jack built."
CHARACTERS = (
    ("malt", "lay in"),
    ("rat", "ate"),
    ("cat", "killed"),
    ("dog", "worried"),
    ("cow with the crumpled horn", "tossed"),
    ("maiden all forlorn", "milked"),
    ("man all tattered and torn", "kissed"),
    ("priest all shaven and shorn", "married"),
    ("rooster that crowed in the morn", "woke"),
    ("farmer sowing his corn", "kept"),
    ("horse and the hound and the horn", "belonged to")
)


def recite(start_verse: int, end_verse: int) -> list[str]:
    return ["This is the " + get_verse(i) for i in range(start_verse - 1, end_verse)]


def get_verse(start: int) -> str:
    if start == 0: return START
    character, action = CHARACTERS[start - 1]
    return f"{character} that {action} the {get_verse(start - 1)}"
    