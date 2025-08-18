class Luhn:
    def __init__(self, card_num: str):
        self.card_num = card_num.replace(" ", "")

    def valid(self) -> bool:
        return (self.card_num.isdecimal()
                and self.card_num != "0"
                and sum((int(s)*(i%2+1)-1) % 9 + 1
                        for i, s in enumerate(self.card_num[::-1]) if s != "0")
                % 10 == 0)

