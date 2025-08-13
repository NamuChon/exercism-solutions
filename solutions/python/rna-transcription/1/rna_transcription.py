def to_rna(dna_strand: str) -> str:
    return dna_strand.translate(str.maketrans({"G": "C", "C": "G", "T": "A", "A": "U"}))
