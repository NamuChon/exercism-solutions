def saddle_points(matrix: list[list[int]]) -> list[dict[str, int]]:
    if not matrix: return []
    first_len = len(matrix[0])
    if any(len(row) != first_len for row in matrix): raise ValueError("irregular matrix")
    
    return [{"row": y+1, "column": x+1}
            for y, row_max in enumerate(map(max, matrix))
            for x, col_min in enumerate(map(min, zip(*matrix)))
            if row_max == col_min]
