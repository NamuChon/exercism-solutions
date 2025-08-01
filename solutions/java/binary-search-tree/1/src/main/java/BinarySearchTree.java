import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;
class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root = null;
    void insert(T value) {
        if (root == null) {
            root = new Node<>(value, null, null);
            return;
        }
        Node<T> result = root;
        boolean found = false;
        while (!found) {
            int compared = value.compareTo(result.getData());
            if (compared > 0) {
                Node<T> right = result.getRight();
                if (right == null) {
                    result.setRight(new Node<>(value, null, null));
                    found = true;
                } else {
                    result = right;
                }
            } else {
                Node<T> left = result.getLeft();
                if (left == null) {
                    result.setLeft(new Node<>(value, null, null));
                    found = true;
                } else {
                    result = left;
                }
            }
        }
    }

    List<T> getAsSortedList() {
        return nodeStreamToValueList(flattenByOrder(root));
    }

    List<T> getAsLevelOrderList() {
        return nodeStreamToValueList(flattenByDepth(() -> Stream.of(root)));
    }

    Node<T> getRoot() {
        return root;
    }

    private Stream<Node<T>> flattenByOrder(Node<T> toFlatten) {
        return Stream.of(toFlatten).flatMap(node -> {
            Node<T> left = node.getLeft();
            Node<T> right = node.getRight();
            Stream<Node<T>> currentStream = Stream.of(node);
            Stream<Node<T>> leftStream = left == null ? Stream.empty() : flattenByOrder(left);
            Stream<Node<T>> rightStream = right == null ? Stream.empty() : flattenByOrder(right);
            return Stream.concat(Stream.concat(leftStream, currentStream), rightStream);
        });
    }

    private Stream<Node<T>> flattenByDepth(Supplier<Stream<Node<T>>> toFlattenSupplier) {
        if (toFlattenSupplier.get().findAny().isEmpty()) return Stream.empty();
        return Stream.concat(toFlattenSupplier.get(), flattenByDepth(() -> toFlattenSupplier.get().flatMap(node -> Stream.of(node.getLeft(), node.getRight())).filter(Objects::nonNull)));
    }

    private List<T> nodeStreamToValueList(Stream<Node<T>> stream) {
        return stream.map(Node<T>::getData).toList();
    }

    static class Node<T> {
        private T data;
        private Node<T> left, right;
        
        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        
        void setLeft(Node<T> left) {
            this.left = left;
        }

        void setRight(Node<T> right) {
            this.right = right;
        }
        
        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }
        
        T getData() {
            return data;
        }
    }
}
