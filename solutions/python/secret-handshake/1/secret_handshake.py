from math import log

def commands(binary_str: str) -> list[str]:
    ACTION_LIST = ["wink", "double blink", "close your eyes", "jump"]
    number = int(binary_str, 2)
    actions = []
    i = 1
    while i <= 0b1000:
        if number & i: actions.append(ACTION_LIST[int(log(i, 2))])
        i <<= 1
    return list(reversed(actions)) if number & 0b10000 else actions
