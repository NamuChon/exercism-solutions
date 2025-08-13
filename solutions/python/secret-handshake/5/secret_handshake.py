def commands(binary_str: str) -> list[str]:
    ACTION_LIST = ("wink", "double blink", "close your eyes", "jump")
    number = int(binary_str, 2)
    actions = [action for i, action in enumerate(ACTION_LIST) if 1 << i & number]
    return list(reversed(actions)) if number & 0b10000 else actions
