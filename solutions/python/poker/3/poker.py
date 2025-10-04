from collections import Counter


LETTER_RANKS = {"J": 11, "Q": 12, "K": 13, "A": 14}

RANKINGS = (
    lambda ranks, suits: consecutive(ranks) and same(suits),
    lambda ranks, suits: same(ranks, 1, 4),
    lambda ranks, suits: same(ranks, 2, 3),
    lambda ranks, suits: same(suits),
    lambda ranks, suits: consecutive(ranks),
    lambda ranks, suits: same(ranks, 1, 1, 3),
    lambda ranks, suits: same(ranks, 1, 2, 2),
    lambda ranks, suits: same(ranks, 1, 1, 1, 2)
)

def same(objects, *counts: int) -> bool:
    if not counts: counts = (len(objects),)
    return list(counts) == sorted(Counter(objects).values())

def consecutive(ranks: list[int]) -> bool:
    length = len(ranks)
    if length != len(set(ranks)): return False
    return ranks[-1] - ranks[0] == length - 1

    
class Hand:

    def __init__(self, hand_str: str):
        self.hand_str = hand_str
        splitted = hand_str.split(" ")
        ranks, suits = [s[:-1] for s in splitted], [s[-1] for s in splitted]
        self.ranks = sorted(LETTER_RANKS[s] if s in LETTER_RANKS else int(s) for s in ranks)

        if self.ranks[-1] == 14:
            a_is_1 = [1] + self.ranks[:-1]
            if consecutive(a_is_1):
                self.ranks = a_is_1
        
        self.suits = list(suits)

    @property
    def ranking(self) -> int:
        for i, test in enumerate(RANKINGS):
            if test(self.ranks, self.suits): return i
        return len(RANKINGS)

    @property
    def kicker(self) -> list[int]:
        return [t[0] for t in Counter(reversed(self.ranks)).most_common()]

    def __lt__(self, other) -> bool:
        return self.compare_to(other) < 0

    def compare_to(self, other) -> int:
        if self.ranking != other.ranking:
            return other.ranking - self.ranking
        return (self.kicker > other.kicker) - (self.kicker < other.kicker)


def best_hands(hands: list[str]) -> list[str]:
    processed = [Hand(s) for s in hands]
    best_hand = max(processed)
    return [h.hand_str for h in processed if best_hand.compare_to(h) == 0]