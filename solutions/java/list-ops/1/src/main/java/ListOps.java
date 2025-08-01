import java.util.*;
import java.util.function.*;

class ListOps {

    static <T> List<T> append(List<T> list1, List<T> list2) {
        List<T> result = new LinkedList<>(list1);
        for (T t : list2) result.add(t);
        return result;
    }

    static <T> List<T> concat(List<List<T>> listOfLists) {
        List<T> result = new LinkedList<>();
        for (List<T> list : listOfLists)
            for (T t : list) result.add(t);
        return result;
    }

    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new LinkedList<>();
        for (T t : list)
            if (predicate.test(t)) result.add(t);
        return result;
    }

    static <T> int size(List<T> list) {
        int count = 0;
        for (T t : list) count++;
        return count;
    }

    static <T, U> List<U> map(List<T> list, Function<T, U> transform) {
        List<U> result = new LinkedList<>();
        for (T t : list) result.add(transform.apply(t));
        return result;
    }

    static <T> List<T> reverse(List<T> list) {
        List<T> result = new LinkedList<>();
        for (T t : list) result.addFirst(t);
        return result;
    }

    static <T, U> U foldLeft(List<T> list, U initial, BiFunction<U, T, U> f) {
        U result = initial;
        for (T t : list) result = f.apply(result, t);
        return result;
    }

    static <T, U> U foldRight(List<T> list, U initial, BiFunction<T, U, U> f) {
        U result = initial;
        for (T t : reverse(list)) result = f.apply(t, result);
        return result;
    }

    private ListOps() {
        // No instances.
    }

}
