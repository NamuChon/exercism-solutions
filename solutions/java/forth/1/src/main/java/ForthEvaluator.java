import java.util.*;
import java.util.function.*;
class ForthEvaluator {
    
    List<Integer> evaluateProgram(List<String> input) {
        Map<String, String> definitions = new HashMap<>();
        for (String current : input) {
            if (current.matches(": \\S+ .+? ;")) {
                current = current.toLowerCase();
                String name = current.replaceFirst(": (\\S+) .+? ;", "$1");
                if (name.matches("-?\\d+")) throw new IllegalArgumentException("Cannot redefine numbers");
                String definition = current.replaceFirst(": \\S+ (.+?) ;", "$1");
                for (Map.Entry<String, String> entry : definitions.entrySet()) {
                    definition = definition.replaceAll(entry.getKey(), entry.getValue());
                }
                definitions.put(name, definition);
            }
        }

        List<Integer> result = new LinkedList<>();
        List<String> operations = new LinkedList<>(Arrays.asList(input.getLast().split(" ")));
        while (!operations.isEmpty()) {
            String current = operations.removeFirst().toLowerCase();
            try {
                result.add(Integer.parseInt(current));
            } catch (NumberFormatException e) {
                if (definitions.containsKey(current)) {
                    operations.addAll(0, Arrays.asList(definitions.get(current).split(" ")));
                    continue;
                }
                switch (current) {
                    case "+" -> arithmetic(result, (b, a) -> a + b, "Addition");
                    case "-" -> arithmetic(result, (b, a) -> a - b, "Subtraction");
                    case "*" -> arithmetic(result, (b, a) -> a * b, "Multiplication");
                    case "/" -> arithmetic(result, (b, a) -> {
                        if (b == 0) throw new IllegalArgumentException("Division by 0 is not allowed");
                        return a / b;
                    }, "Division");
                    
                    case "dup" -> {
                        checkSize(result, 1, "Duplicating");
                        result.add(result.getLast());
                    }
                    case "drop" -> {
                        checkSize(result, 1, "Dropping");
                        result.removeLast();
                    }
                    case "swap" -> {
                        checkSize(result, 2, "Swapping");
                        Integer temp = result.removeLast();
                        result.add(result.size() - 1, temp);
                    }
                    case "over" -> {
                        checkSize(result, 2, "Overing");
                        result.add(result.get(result.size() - 2));
                    }
                    default -> throw new IllegalArgumentException("No definition available for operator " + "\"" + current + "\"");
                }
            }
        }
        return result;
    }

    private void arithmetic(List<Integer> result, BiFunction<Integer, Integer, Integer> function, String name) {
        checkSize(result, 2, name);
        result.add(function.apply(result.removeLast(), result.removeLast()));
    }

    private void checkSize(List<Integer> result, int minimumSize, String action) {
        if (result.size() < minimumSize) throw new IllegalArgumentException(action + " requires that the stack contain at least " + minimumSize + " value" + (minimumSize == 1 ? "" : "s"));
    }
}
