import java.util.List;
class ResistorColorTrio {
    final List<String> colorsInOrder = List.of("black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white");
    String label(String[] colors) {
        long result = (10 * colorsInOrder.indexOf(colors[0]) + colorsInOrder.indexOf(colors[1])) * (long) Math.pow(10, colorsInOrder.indexOf(colors[2]));
        return result < 1000 ? result + " ohms" : result < 1000000 ? result / 1000 + " kiloohms" : result < 1000000000 ? result / 1000000 + " megaohms" : result / 1000000000 + " gigaohms";
    }
}