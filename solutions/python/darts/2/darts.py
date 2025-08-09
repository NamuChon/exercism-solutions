def score(x: int, y: int) -> int:
    distance = (x**2 + y**2)**0.5
    return (distance<=1)*5 + (distance<=5)*4 + (distance<=10)
