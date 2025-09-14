def saddle_points(matrix: list[list[int]]) -> list[dict[str, int]]:
    rows = len(matrix)
    if not rows: return []
    first_len = len(matrix[0])
    if any(len(row) != first_len for row in matrix): raise ValueError("irregular matrix")
    
    result = []
    for y, row in enumerate(matrix):
        for x, value in enumerate(row):
            if value == max(row) and all(matrix[i][x] >= value for i in range(rows)):
                result.append({"row": y+1, "column": x+1})
    return result
