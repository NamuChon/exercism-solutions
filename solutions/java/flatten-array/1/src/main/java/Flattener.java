import java.util.*;
class Flattener {

    List<Object> flatten(List<?> list) {
        List<Object> result = new ArrayList<>();
        for (Object i : list) {
            if (i instanceof List) {
                result.addAll(flatten((List)i));
            } else if (i != null) {
                result.add(i);
            }
        }
        return result;
    }

}