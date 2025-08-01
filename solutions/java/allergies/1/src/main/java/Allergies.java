import java.util.*;
import java.util.stream.IntStream;
class Allergies {
    private final List<Allergen> allergens;
    Allergies(int score) {
        allergens = new ArrayList<>();
        setAllergens(allergens, score);
        Collections.sort(allergens);
    }
    
    boolean isAllergicTo(Allergen allergen) {
        return allergens.contains(allergen);
    }

    List<Allergen> getList() {
        return allergens;
    }

    private void setAllergens(List<Allergen> allergens, int score) {
        if (score != 0) {
            int target = IntStream.iterate(1, i -> i * 2).takeWhile(i -> i <= score).boxed().toList().getLast();
            Arrays.stream(Allergen.values()).filter(e -> e.getScore() == target).findFirst().ifPresent(allergens::add);
            setAllergens(allergens, score - target);
        }
    }
}
