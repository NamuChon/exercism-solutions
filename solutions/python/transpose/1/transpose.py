def transpose(text: str) -> str:
    splitted = text.split("\n")
    rows = len(splitted)
    max_columns = max(map(len, splitted))
    result = [""] * max_columns

    for row in splitted:
        for j in range(max_columns):
            c = row[j].replace(" ", "\0") if j < len(row) else " "
            result[j] += c

    for i in range(max_columns):
        result[i] = result[i].rstrip().replace("\0", " ")
    
    return "\n".join(result)