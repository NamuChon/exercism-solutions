import java.util.*;
class OpticalCharacterReader {

    private static final List<List<String>> FONTS = List.of(
        List.of(" _ ",
                "| |",
                "|_|"),
        
        List.of("   ",
                "  |",
                "  |"),
        
        List.of(" _ ",
                " _|",
                "|_ "),

        List.of(" _ ",
                " _|",
                " _|"),

        List.of("   ",
                "|_|",
                "  |"),

        List.of(" _ ",
                "|_ ",
                " _|"),

        List.of(" _ ",
                "|_ ",
                "|_|"),

        List.of(" _ ",
                "  |",
                "  |"),

        List.of(" _ ",
                "|_|",
                "|_|"),

        List.of(" _ ",
                "|_|",
                " _|")
    );

    String parse(List<String> input) {
        if (input.size() % 4 != 0) throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
        if (input.stream().anyMatch(s -> s.length() % 3 != 0)) throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.size() / 4; i++) {
            List<String> row = input.subList(i * 4, i * 4 + 3);
            for (int j = 0; j < input.get(i * 4).length() / 3; j++) {
                final int finalJ = j;
                List<String> chunk = row.stream()
                    .map(s -> s.substring(finalJ * 3, finalJ * 3 + 3))
                    .toList();
                int index = FONTS.indexOf(chunk);
                result.append(index == -1 ? '?' : (char) ('0' + index));
            }
            result.append(',');
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

}