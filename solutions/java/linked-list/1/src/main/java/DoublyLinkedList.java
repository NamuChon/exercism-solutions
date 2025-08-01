class DoublyLinkedList<T> {
    private Element<T> head = null;

    void push(T value) {
        Element<T> newHead = new Element<>(value, head, null);
        if (head != null) {
            head.next = newHead;
        }
        head = newHead;
    }

    T pop() {
        T value = head.value;
        head = head.prev;
        return value;
    }

    void unshift(T value) {
        Element<T> secondElement = getSecondElement();
        if (secondElement == null) {
            if (head == null) {
                push(value);
            } else {
                head.prev = new Element<>(value, null, head);
            }
        } else if (secondElement.prev == null) {
            push(value);
        } else {
            secondElement.prev.prev = new Element<>(value, null, secondElement.prev);
        }
    }

    T shift() {
        Element<T> secondElement = getSecondElement();
        T result;
        if (secondElement == null) {
            result = head.value;
            head = null;
        } else {
            result = secondElement.prev.value;
            secondElement.prev = null;
        }
        return result;
    }

    private Element<T> getSecondElement() {
        if (head == null) return null;
        Element<T> firstElement = head;
        while (firstElement.prev != null) {
            firstElement = firstElement.prev;
        }
        return firstElement.next;
    }
    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;

        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
