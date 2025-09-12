def transpose(text: str) -> str:
    splitted = text.split("\n")
    max_columns = max(map(len, splitted))
    result = [""] * max_columns

    for row in splitted:
        for j in range(max_columns):
            result[j] += row[j] if j < len(row) else "\0"
    
    return "\n".join(s.rstrip("\0").replace("\0", " ") for s in result)