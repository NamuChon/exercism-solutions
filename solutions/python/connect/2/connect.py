
class ConnectGame:
    def __init__(self, board: str):
        self.board = board.replace(" ", "").split("\n")
        self.rows, self.cols = len(self.board) - 1, len(self.board[0]) - 1

    def get_winner(self) -> str:
        o_won = self.check_win("O")
        x_won = self.check_win("X")
        if o_won and not x_won: return "O"
        if x_won and not o_won: return "X"
        return ""

    def check_win(self, player: str) -> bool:
        PATHS = ((-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0))
        
        stack = ([(0, j) for j, s in enumerate(self.board[0]) if s == player] if player == "O"
                 else [(i, 0) for i, s in enumerate(self.board) if s[0] == player])
        visited = set(stack)
        
        def valid(point: tuple[int, int]) -> bool:
            row, col = point
            return (point not in visited
                    and 0 <= row <= self.rows
                    and 0 <= col <= self.cols
                    and self.board[row][col] == player)
        
        while stack:
            cur_row, cur_col = stack.pop()
            if player == "O" and cur_row == self.rows or player == "X" and cur_col == self.cols: return True
                
            for move_row, move_col in PATHS:
                new_point = (cur_row + move_row, cur_col + move_col)
                if valid(new_point):
                    stack.append(new_point)
                    visited.add(new_point)
        
        return False
