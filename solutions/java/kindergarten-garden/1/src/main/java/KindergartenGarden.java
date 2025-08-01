import java.util.List;
import java.util.ArrayList;
class KindergartenGarden {
    private final List<List<Plant>> plants;
    
    KindergartenGarden(String garden) {
        String[] splitted = garden.split("\n");
        int rowLength = splitted[0].length();
        plants = new ArrayList<>(rowLength / 2);
        for (int i = 0; i < rowLength; i += 2) {
            plants.add(List.of(
                getPlant(splitted[0], i),
                getPlant(splitted[0], i + 1),
                getPlant(splitted[1], i),
                getPlant(splitted[1], i + 1)));
        }
    }

    List<Plant> getPlantsOfStudent(String student) {
        return plants.get(student.charAt(0) - 'A');
    }

    private Plant getPlant(String row, int index) {
        return Plant.getPlant(row.charAt(index));
    }
}
