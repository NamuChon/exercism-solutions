from itertools import takewhile

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
    TRANSLATION_PAIRS = {codon: amino_acid for codons, amino_acid in TRANSLATION.items() for codon in codons}

    return [TRANSLATION_PAIRS[codon] for codon in takewhile(
                lambda c: TRANSLATION_PAIRS[c] != "STOP",
                [strand[i:i+3] for i in range(0, len(strand), 3)])
           ]
