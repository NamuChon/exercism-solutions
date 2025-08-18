import random
from string import ascii_uppercase, digits

class Robot:
    names = set()
    
    def __init__(self):
        self.name = None
        self.reset()

    def reset(self):
        unique = False
        while not unique:
            new_name = "".join(random.choices(ascii_uppercase, k=2) + random.choices(digits, k=3))
            if new_name not in Robot.names: unique = True
        Robot.names.discard(self.name)
        Robot.names.add(new_name)
        self.name = new_name
