def is_pangram(sentence):
    return set("abcdefghijklmnopqrstuvwxyz").issubset(set(sentence.lower()))
