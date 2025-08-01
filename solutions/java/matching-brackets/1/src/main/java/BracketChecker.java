import java.util.*;
class BracketChecker {
    private final Map<Character, Character> BRACKETS = Map.of(
        '(', ')',
        '{', '}',
        '[', ']');
    private final boolean result;
    
    BracketChecker(String expression) {
        List<Character> starterStack = new ArrayList<>();
        for (char c : expression.toCharArray()) {
            if (BRACKETS.containsKey(c)) {
                starterStack.add(c);
            } else if (BRACKETS.containsValue(c)) {
                if (starterStack.isEmpty()) {
                    result = false;
                    return;
                }
                if (c == BRACKETS.get(starterStack.getLast())) {
                    starterStack.removeLast();
                } else {
                    result = false;
                    return;
                }
            }
        }
        result = starterStack.isEmpty();
    }

    boolean areBracketsMatchedAndNestedCorrectly() {
        return result;
    }

}