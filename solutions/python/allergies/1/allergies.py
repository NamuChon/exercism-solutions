ALLERGENTS = ("eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats")

class Allergies:

    def __init__(self, score: int):
        self.score = score

    def allergic_to(self, item: str) -> bool:
        return item in self.lst

    @property
    def lst(self) -> list[str]:
        return [allergent for i, allergent in enumerate(ALLERGENTS) if self.score & (1 << i)]
