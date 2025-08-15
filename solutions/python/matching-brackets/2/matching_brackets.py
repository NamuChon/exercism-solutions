import re

def is_paired(input_string: str) -> bool:
    input_string, replaced = re.sub(r"[^[\]{}()]", "", input_string), 1
    while replaced:
        input_string, replaced = re.subn("\[]|{}|\(\)", "", input_string)
    return not bool(input_string)
