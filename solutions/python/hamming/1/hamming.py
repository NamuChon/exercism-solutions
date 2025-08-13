def distance(strand_a: str, strand_b: str) -> int:
    if len(strand_a) != len(strand_b): raise ValueError("Strands must be of equal length.")
    return len([0 for a, b in zip(strand_a, strand_b) if a != b])
