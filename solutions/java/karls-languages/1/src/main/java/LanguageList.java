import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class LanguageList {
    private final List<String> languages = new ArrayList<>();
    private final List<String> EXCITING_LANGUAGES = new ArrayList<>(Arrays.asList("Java", "Kotlin"));
    public boolean isEmpty() {
        return languages.isEmpty();
    }

    public void addLanguage(String language) {
        languages.add(language);
    }

    public void removeLanguage(String language) {
        languages.remove(language);
    }

    public String firstLanguage() {
        return languages.get(0);
    }

    public int count() {
        return languages.size();
    }

    public boolean containsLanguage(String language) {
        return languages.contains(language);
    }

    public boolean isExciting() {
        return languages.stream().anyMatch(EXCITING_LANGUAGES::contains);
    }
}
