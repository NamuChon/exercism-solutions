import re

OPERATIONS = {"multiplied by": lambda a, b: a * b,
              "divided by": lambda a, b: a // b,
              "plus": lambda a, b: a + b,
              "minus": lambda a, b: a - b}
MATCH_NUMBER, MATCH_OPERATOR = r"-?\d+", "|".join(OPERATIONS.keys())

def answer(question: str) -> int:
    if not all(re.match(rf"(?: (?:is|{MATCH_OPERATOR}))+", operator) for operator in re.findall(r" [a-z](?:[a-z ]*[a-z])?", question)): raise ValueError("unknown operation")
    if not re.match(rf"What is {MATCH_NUMBER}(?: (?:{MATCH_OPERATOR}) {MATCH_NUMBER})*\?", question): raise ValueError("syntax error")
    
    sections = re.search(rf"What is ({MATCH_NUMBER})(.*)\?", question)
    acc, rest = int(sections.group(1)), sections.group(2)
    
    for operation in re.findall(rf"({MATCH_OPERATOR}) ({MATCH_NUMBER})", rest):
        acc = OPERATIONS[operation[0]](acc, int(operation[1]))
    return acc
