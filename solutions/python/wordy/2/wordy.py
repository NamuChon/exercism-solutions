def answer(question: str) -> int:
    question = (question.replace("What is", "")
                        .replace("?", "")
                        .replace("plus", "+")
                        .replace("minus", "-")
                        .replace("multiplied by", "*")
                        .replace("divided by", "//")
                        .strip()
                        .split(" "))
    if any(s.isalpha() for s in question): raise ValueError("unknown operation")
        
    try:
        acc, rest = question[0], question[1:]
        for i in range(0, len(rest), 2):
            if rest[i].isdigit(): raise ValueError()
            acc = str(eval(acc + rest[i] + rest[i+1]))
        return int(acc)
    except:
        raise ValueError("syntax error")
