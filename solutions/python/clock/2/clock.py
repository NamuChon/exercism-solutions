class Clock:
    def __init__(self, hour: int, minute: int):
        minutes = (hour*60 + minute) % 1440
        self.hour = minutes // 60
        self.minute = minutes % 60

    def __repr__(self):
        return f"Clock({self.hour}, {self.minute})"

    def __str__(self):
        return f"{self.hour:02d}:{self.minute:02d}"

    def __eq__(self, other):
        return self.hour == other.hour and self.minute == other.minute

    def __add__(self, minutes: int):
        return Clock(self.hour, self.minute + minutes)

    def __sub__(self, minutes: int):
        return Clock(self.hour, self.minute - minutes)
