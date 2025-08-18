def recite(start_verse: int, end_verse: int) -> list[str]:
    return list(map(get_verse, range(start_verse, end_verse + 1)))


def get_verse(number: int) -> str:
    GIFTS = ("a Partridge in a Pear Tree", "two Turtle Doves", "three French Hens",
             "four Calling Birds", "five Gold Rings", "six Geese-a-Laying",
             "seven Swans-a-Swimming", "eight Maids-a-Milking", "nine Ladies Dancing",
             "ten Lords-a-Leaping", "eleven Pipers Piping", "twelve Drummers Drumming")
    
    return "On the {} day of Christmas my true love gave to me: {}.".format(
        ("first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth")[number - 1],
        GIFTS[0] if number == 1 else ", ".join(GIFTS[number-1:0:-1]) + ", and " + GIFTS[0]
    )
