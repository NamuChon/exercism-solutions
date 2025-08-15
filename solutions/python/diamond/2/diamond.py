def rows(letter: str) -> list[str]:
    result = []
    middle_row = ord(letter) - ord("A")

    result.append(" "*middle_row + "A" + " "*middle_row)
    for i in range(1, middle_row + 1):
        padding, letter = " " * (middle_row-i), chr(ord("A") + i)
        result.append(padding + letter + " "*(i*2-1) + letter + padding)
    return result + result[:-1][::-1]
