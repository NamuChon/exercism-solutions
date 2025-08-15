def rows(letter: str) -> list[str]:
    result = []
    distance = ord(letter) - ord("A")

    for i in range(-distance, distance + 1):
        index_abs = abs(i)
        current_letter = chr(ord("A") + distance - index_abs)
        padding = " " * index_abs
        result.append(padding + current_letter + " "*((distance-index_abs)*2-1) + ("" if index_abs == distance else current_letter) + padding)

    return result
