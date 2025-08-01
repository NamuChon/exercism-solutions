import java.util.Set;
import java.util.stream.Collectors;
public class PangramChecker {
    public boolean isPangram(String input) {
        return input.toLowerCase().chars().filter(Character::isLetter).mapToObj(c -> (char) c).collect(Collectors.toSet()).size() == 26;
    }

}
