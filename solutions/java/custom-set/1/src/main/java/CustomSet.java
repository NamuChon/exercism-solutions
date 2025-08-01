import java.util.*;
class CustomSet<T> {

    private Object[] elements;
    
    CustomSet() {
        elements = new Object[0];
    }

    CustomSet(Collection<T> data) {
        elements = new Object[data.size()];
        int i = 0;
        for (T t : data) {
            elements[i] = t;
            i++;
        }
    }

    boolean isEmpty() {
        return elements.length == 0;
    }

    boolean contains(T element) {
        for (Object o : elements) {
            T t = (T) o;
            if (t == null && element == null || t != null && t.equals(element)) return true;
        }
        return false;
    }

    boolean isDisjoint(CustomSet<T> other) {
        return getIntersection(other).isEmpty();
    }

    boolean add(T element) {
        if (contains(element)) return false;
        Object[] newSet = new Object[elements.length + 1];
        for (int i = 0; i < elements.length; i++)
            newSet[i] = elements[i];
        newSet[newSet.length - 1] = element;
        elements = newSet;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomSet<T> other = (CustomSet<T>) obj;
        return isSubset(other) && other.isSubset(this);
    }

    CustomSet<T> getIntersection(CustomSet<T> other) {
        CustomSet<T> result = new CustomSet<>();
        for (Object o : elements) {
            T t = (T) o;
            if (other.contains(t)) result.add(t);
        }
        return result;
    }

    CustomSet<T> getUnion(CustomSet<T> other) {
        CustomSet<T> result = new CustomSet<>();
        for (Object o : elements)
            result.add((T) o);
        for (Object o : other.elements)
            result.add((T) o);
        return result;
    }

    CustomSet<T> getDifference(CustomSet<T> other) {
        CustomSet<T> result = new CustomSet<>();
        for (Object o : elements) {
            T t = (T) o;
            if (!other.contains(t)) result.add(t);
        }
        return result;
    }

    boolean isSubset(CustomSet<T> other) {
        return other.getDifference(this).isEmpty();
    }
}
