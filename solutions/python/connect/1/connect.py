
class ConnectGame:
    def __init__(self, board: str):
        self.board = board.replace(" ", "").split("\n")

    def get_winner(self) -> str:
        o_won = self.check_win("O")
        x_won = self.check_win("X")
        if o_won and x_won or not o_won and not x_won: return ""
        if o_won: return "O"
        return "X"

    def check_win(self, player: str) -> bool:
        PATHS = ((-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0))
        max_row, max_col = len(self.board) - 1, len(self.board[0]) - 1
        
        stack = ([(0, j) for j, s in enumerate(self.board[0]) if s == player] if player == "O"
                 else [(i, 0) for i, s in enumerate(self.board) if s[0] == player])
        visited = list(stack)
        
        def valid(point: tuple[int]) -> bool:
            row, col = point
            return (point not in visited
                    and 0 <= row <= max_row
                    and 0 <= col <= max_col
                    and self.board[row][col] == player)
        
        while stack:
            cur_row, cur_col = stack.pop()
            if player == "O" and cur_row == max_row: return True
            if player == "X" and cur_col == max_col: return True
                
            for move_row, move_col in PATHS:
                new_point = (cur_row + move_row, cur_col + move_col)
                if valid(new_point):
                    stack.append(new_point)
                    visited.append(new_point)
        
        return False
