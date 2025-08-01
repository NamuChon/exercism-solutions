import java.util.*;
import java.util.stream.IntStream;
class ProteinTranslator {
    List<String> translate(String rnaSequence) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < rnaSequence.length(); i += 3) {
            if (i + 3 > rnaSequence.length()) {
                throw new IllegalArgumentException("Invalid codon");
            }
            String translated = codonToAminoAcid(rnaSequence.substring(i, i + 3));
            if (translated.equals("STOP")) {
                break;
            }
            result.add(translated);
        }
        return result;
    }
    
    private String codonToAminoAcid(String codon) {
        return switch (codon) {
            case "AUG" -> "Methionine";
            case "UUU", "UUC" -> "Phenylalanine";
            case "UUA", "UUG" -> "Leucine";
            case "UCU", "UCC", "UCA", "UCG" -> "Serine";
            case "UAU", "UAC" -> "Tyrosine";
            case "UGU", "UGC" -> "Cysteine";
            case "UGG" -> "Tryptophan";
            case "UAA", "UAG", "UGA" -> "STOP";
            default -> throw new IllegalArgumentException("Invalid codon");
        };
    }
}
