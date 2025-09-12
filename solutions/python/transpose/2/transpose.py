def transpose(text: str) -> str:
    splitted = text.split("\n")
    rows = len(splitted)
    max_columns = max(map(len, splitted))
    result = [""] * max_columns

    for row in splitted:
        for j in range(max_columns):
            c = row[j].replace(" ", "\0") if j < len(row) else " "
            result[j] += c
    
    return "\n".join(s.rstrip().replace("\0", " ") for s in result)