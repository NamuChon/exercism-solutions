# Score categories.
# Change the values as you see fit.
YACHT = 0
ONES = 1
TWOS = 2
THREES = 3
FOURS = 4
FIVES = 5
SIXES = 6
FULL_HOUSE = 7
FOUR_OF_A_KIND = 8
LITTLE_STRAIGHT = 9
BIG_STRAIGHT = 10
CHOICE = 11

DICE_COUNT = 5


def score(dice: list[int], category: int) -> int:
    if category == YACHT:
        return 50 if same_face(dice) else 0
    if ONES <= category <= SIXES:
        return category * dice.count(category)
    
    dice.sort()
    
    if category == FULL_HOUSE:
        return sum(dice) if (len(set(dice)) == 2
                             and (same_face(dice[:3]) and same_face(dice[3:])
                             or same_face(dice[:2]) and same_face(dice[2:]))) else 0
    if category == FOUR_OF_A_KIND:
        return dice[2]*4 if dice.count(dice[2]) >= 4 else 0
    if category == LITTLE_STRAIGHT:
        return 30 if dice == list(range(1, DICE_COUNT+1)) else 0
    if category == BIG_STRAIGHT:
        return 30 if dice == list(range(2, DICE_COUNT+2)) else 0
    if category == CHOICE:
        return sum(dice)
    return 0

def same_face(dice: list[int]) -> bool:
    return dice.count(dice[0]) == len(dice)
