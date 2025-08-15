def proteins(strand: str) -> list[str]:
    TRANSLATION = {
        ("AUG",): "Methionine",
        ("UUU", "UUC"): "Phenylalanine",
        ("UUA", "UUG"): "Leucine",
        ("UCU", "UCC", "UCA", "UCG"): "Serine",
        ("UAU", "UAC"): "Tyrosine",
        ("UGU", "UGC"): "Cysteine",
        ("UGG",): "Tryptophan",
        ("UAA", "UAG", "UGA"): "STOP"
    }
    TRANSLATION_PAIRS = {codon: amino_acid for condons, amino_acid in TRANSLATION.items() for codon in condons}

    result = []
    for i in range(0, len(strand), 3):
        amino_acid = TRANSLATION_PAIRS[strand[i:i+3]]
        if amino_acid == "STOP": return result
        result.append(amino_acid)
    return result
