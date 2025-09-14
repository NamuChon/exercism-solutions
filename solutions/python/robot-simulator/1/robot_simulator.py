
NORTH = (0, 1)
EAST = (1, 0)
SOUTH = (0, -1)
WEST = (-1, 0)

ORDER = (NORTH, EAST, SOUTH, WEST)
DIRECTION_COUNT = len(ORDER)

class Robot:
    def __init__(self, direction = NORTH, x_pos: int = 0, y_pos: int = 0):
        self.direction = direction
        self.coordinates = (x_pos, y_pos)

    def move(self, command: str) -> None:
        for s in command:
            match s:
                case "R":
                    self.turn(1)
                case "L":
                    self.turn(-1)
                case "A":
                    self.coordinates = (self.coordinates[0] + self.direction[0], self.coordinates[1] + self.direction[1])
                

    def turn(self, amount: int):
        self.direction = ORDER[(ORDER.index(self.direction) + amount) % DIRECTION_COUNT]