import java.util.Map;
import java.util.HashMap;
public class DialingCodes {
    private Map<Integer, String> codes = new HashMap<>();
    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setDialingCode(Integer code, String country) {
        codes.put(code, country);
    }

    public String getCountry(Integer code) {
        return codes.get(code);
    }

    public void addNewDialingCode(Integer code, String country) {
        if (!codes.containsKey(code) && !codes.containsValue(country)) {
            setDialingCode(code, country);
        }
    }

    public Integer findDialingCode(String country) {
        return codes.entrySet().stream().filter(e -> e.getValue().equals(country)).map(Map.Entry::getKey).findFirst().orElse(null);
    }

    public void updateCountryDialingCode(Integer code, String country) {
        setDialingCode(code, country);
    }
}
