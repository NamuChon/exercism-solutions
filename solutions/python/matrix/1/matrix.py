class Matrix:
    def __init__(self, matrix_string: str):
        self.rows = [list(map(int, r.split(" "))) for r in matrix_string.split("\n")]
        self.columns = list(map(list, zip(*self.rows)))

    def row(self, index: int) -> list[int]:
        return self.rows[index-1]

    def column(self, index: int) -> list[int]:
        return self.columns[index-1]
