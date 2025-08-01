import java.util.*;
class BinarySearch {
    private final List<Integer> items;
    BinarySearch(List<Integer> items) {
        this.items = items;
    }

    int indexOf(int item) throws ValueNotFoundException {
        List<Integer> itemsCopy = new ArrayList<>(items);
        int listIndex = 0;
        while (!itemsCopy.isEmpty()) {
            int middleIndex = itemsCopy.size() / 2;
            int middleItem = itemsCopy.get(middleIndex);
            if (item == middleItem) {
                return listIndex + middleIndex;
            } else if (item < middleItem) {
                itemsCopy = itemsCopy.subList(0, middleIndex);
            } else if (item > middleItem) {
                itemsCopy = itemsCopy.subList(middleIndex + 1, itemsCopy.size());
                listIndex += middleIndex + 1;
            }
        }
        throw new ValueNotFoundException("Value not in array");
    }
}
