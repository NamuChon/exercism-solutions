import random
from string import ascii_uppercase as LETTERS

class Robot:
    names = set()
    
    def __init__(self):
        self.name = None
        self.reset()

    def reset(self):
        unique = False
        while not unique:
            new_name = "".join(LETTERS[random.randrange(len(LETTERS))] for _ in range(2)) + "".join(str(random.randrange(10)) for _ in range(3))
            if new_name not in Robot.names: unique = True
        Robot.names.discard(self.name)
        Robot.names.add(new_name)
        self.name = new_name
