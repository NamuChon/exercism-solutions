import java.util.*;
class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        if (!stringToVerify.matches("^\\d{1}-*\\d{3}-*\\d{5}-*[\\dX]$")) return false;
        List<Integer> digits = Arrays.stream(stringToVerify.replaceAll("-", "").split("")).mapToInt(s -> {
            if (s.equals("X")) return 10;
            return Integer.parseInt(s);
        }).boxed().toList();
        return formulaResult(digits) % 11 == 0;
    }
    private int formulaResult(List<Integer> digits) {
        if (digits.size() == 1) {
            return digits.getFirst();
        }
        return digits.getFirst() * digits.size() + formulaResult(digits.subList(1, digits.size()));
    }
}
