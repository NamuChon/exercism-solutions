ALLERGENTS = ("eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats")

class Allergies:

    def __init__(self, score: int):
        self.score = score

    def allergic_to(self, item: str) -> bool:
        return bool(1 << ALLERGENTS.index(item) & self.score)

    @property
    def lst(self) -> list[str]:
        return list(filter(self.allergic_to, ALLERGENTS))
