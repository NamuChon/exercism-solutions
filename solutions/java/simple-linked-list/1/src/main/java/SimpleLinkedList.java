import java.util.*;
import java.lang.reflect.*;
class SimpleLinkedList<T> {
    private Node<T> head;
    private int size;
    
    SimpleLinkedList() {
        head = null;
        size = 0;
    }

    SimpleLinkedList(T[] values) {
        head = null;
        size = 0;
        for (T t : values) {
            push(t);
        }
    }

    void push(T value) {
        head = new Node<>(value, head);
        size++;
    }

    T pop() {
        if (head == null) throw new NoSuchElementException();
        T value = head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    void reverse() {
        Node<T> previous = null;
        Node<T> current = head;

        while (current != null) {
            Node<T> temp = current.getNext();
            current.setNext(previous);
            previous = current;
            current = temp;
        }
        head = previous;
    }

    T[] asArray(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(clazz, size);
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            result[i] = current.getValue();
            current = current.getNext();
        }
        return result;
    }

    int size() {
        return size;
    }
}

class Node<T> {
    private final T value;
    private Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }
}
