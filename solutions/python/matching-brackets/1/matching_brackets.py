def is_paired(input_string: str) -> bool:
    START, END = ("[", "{", "("), ("]", "}", ")")
    brackets = []
    
    for s in input_string:
        if s in START:
            brackets.append(s)
        elif s in END:
            if not bool(brackets) or brackets[-1] != START[END.index(s)]:
                return False
            brackets.pop(-1)
    return not bool(brackets)
