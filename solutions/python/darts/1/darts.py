import math

def score(x: int, y: int) -> int:
    distance = math.sqrt(x**2 + y**2)
    if distance > 10.0: return 0
    if distance > 5.0: return 1
    if distance > 1.0: return 5
    return 10
