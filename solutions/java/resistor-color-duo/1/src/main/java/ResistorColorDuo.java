import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
class ResistorColorDuo {
    final List<String> colorsInOrder = List.of("black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white");
    int value(String[] colors) {
        return Integer.parseInt(Arrays.stream(colors).limit(2).map(s -> String.valueOf(colorsInOrder.indexOf(s))).collect(Collectors.joining()));
    }
}