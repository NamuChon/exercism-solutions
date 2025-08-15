from string import ascii_uppercase

def rows(letter: str) -> list[str]:
    result = []
    middle_row = ord(letter) - ord("A")

    result.append(" "*middle_row + "A" + " "*middle_row)
    for i in range(1, middle_row + 1):
        padding = " " * (middle_row-i)
        result.append(padding + ascii_uppercase[i] + " "*(i*2-1) + ascii_uppercase[i] + padding)
    return result + result[:-1][::-1]
