def response(hey_bob: str) -> str:
    hey_bob = hey_bob.strip()
    is_question = hey_bob.endswith("?")
    letters = [c for c in hey_bob if c.isalpha()]
    is_yell = letters and all(c.isupper() for c in letters)
    
    if is_question and is_yell: return "Calm down, I know what I'm doing!"
    if is_question: return "Sure."
    if is_yell: return "Whoa, chill out!"
    if hey_bob == "": return "Fine. Be that way!"
    return "Whatever."
