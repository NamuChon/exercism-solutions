import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;
class RnaTranscription {
    final Map<String, String> transcription = Map.of(
        "G", "C",
        "C", "G",
        "T", "A",
        "A", "U");
    String transcribe(String dnaStrand) {
        return !dnaStrand.isEmpty() ? Arrays.stream(dnaStrand.split("")).map(s -> transcription.get(s)).collect(Collectors.joining()) : "";
    }
    
}
