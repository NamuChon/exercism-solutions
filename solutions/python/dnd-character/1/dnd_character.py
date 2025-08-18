import random

ABILITIES = ("strength", "dexterity", "constitution", "intelligence", "wisdom", "charisma")

class Character:
    def __init__(self):
        for ability in ABILITIES:
            score = sum(sorted(random.randint(1, 6) for _ in range(4))[1:])
            setattr(self, ability, score)
        self.hitpoints = 10 + modifier(self.constitution)

    def ability(self):
        return getattr(self, ABILITIES[random.randrange(len(ABILITIES))])

def modifier(value: int) -> int:
    return (value - 10) // 2
