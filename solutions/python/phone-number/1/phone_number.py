import re

class PhoneNumber:
    def __init__(self, number: str):
        if re.search(r"[A-Za-z]", number): raise ValueError("letters not permitted")
        number = re.sub(r"[\+\-\.\s\(\)]", "", number)
        if re.search(r"\D", number): raise ValueError("punctuations not permitted")
        
        length = len(number)
        if length < 10: raise ValueError("must not be fewer than 10 digits")
        if length > 11: raise ValueError("must not be greater than 11 digits")
        
        if length == 11:
            if not number.startswith("1"): raise ValueError("11 digits must start with 1")
            number = number[1:]

        if number[0] == "0": raise ValueError("area code cannot start with zero")
        if number[0] == "1": raise ValueError("area code cannot start with one")
        if number[3] == "0": raise ValueError("exchange code cannot start with zero")
        if number[3] == "1": raise ValueError("exchange code cannot start with one")
        
        self.number, self.area_code = number, number[:3]

    def pretty(self) -> str:
        return f"({self.area_code})-{self.number[3:6]}-{self.number[6:]}"
