def rectangles(strings: list[str]) -> int:
    result = 0
    for i, row in enumerate(strings):
        for j, cur in enumerate(row):
            if cur == "+":
                for k in range(j+1, len(row)):
                    if row[k] == "+":
                        for l in range(i+1, len(strings)):
                            bottom = strings[l]
                            if bottom[k] == "+":
                                if (bottom[j] == "+"
                                    and all(s == "-" or s == "+" for s in bottom[j+1:k])
                                    and all(s == "|" or s == "+" for m in range(i+1, l) for s in strings[m][j])):
                                    result += 1
                            elif bottom[k] != "|": break
                    elif row[k] != "-": break
    return result
