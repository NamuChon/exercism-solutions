class CircularBuffer<T> {

    Node<T> tail, head;

    CircularBuffer(final int size) {
        Node<T> current = new Node<>(null, null);
        head = current;
        for (int i = 1; i < size; i++) {
            current = new Node<>(null, current);
        }
        tail = current;
        head.next = tail;
        head = head.next;
    }

    T read() throws BufferIOException {
        T value = tail.value;
        if (value == null) throw new BufferIOException("Tried to read from empty buffer");
        tail.value = null;
        tail = tail.next;
        return value;
    }

    void write(T data) throws BufferIOException {
        if (head.value != null) throw new BufferIOException("Tried to write to full buffer");
        head.value = data;
        head = head.next;
    }

    void overwrite(T data) {
        if (head.value == null) {
            try {
                write(data);
            } catch (BufferIOException e) {
                throw new AssertionError("Unreachable");
            }
        } else {
            head.value = data;
            head = head.next;
            tail = tail.next;
        }
    }

    void clear() {
        while (tail.value != null) {
            tail.value = null;
            tail = tail.next;
        }
    }

}

class Node<T> {
    T value;
    Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}