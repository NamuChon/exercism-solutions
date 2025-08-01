import java.util.*;
public class Satellite {
    public Tree treeFromTraversals(List<Character> preorderInput, List<Character> inorderInput) {
        if (preorderInput.size() != inorderInput.size()) throw new IllegalArgumentException("traversals must have the same length");
        if (new HashSet<>(preorderInput).size() < preorderInput.size()) throw new IllegalArgumentException("traversals must contain unique items");
        if (!preorderInput.stream().allMatch(inorderInput::contains)) throw new IllegalArgumentException("traversals must have the same elements");
        if (preorderInput.isEmpty()) return new Tree(null);
        preorderInput = new ArrayList<>(preorderInput);
        Node root = new Node(preorderInput.removeFirst());
        return backtrack(root, root, preorderInput, inorderInput);
    }

    private Tree backtrack(Node root, Node current, List<Character> preorderInput, List<Character> inorderInput) {
        if (preorderInput.isEmpty()) {
            Tree tree = new Tree(root);
            if (tree.inorder().equals(inorderInput)) return tree;
            return null;
        }

        Node next = new Node(preorderInput.removeFirst());
        Tree result;
        if (current.left == null) {
            current.left = next;
            result = backtrack(root, current, preorderInput, inorderInput);
            if (result != null) return result;
            result = backtrack(root, next, preorderInput, inorderInput);
            if (result != null) return result;
            current.left = null;
        } else {
            current.right = next;
            result = backtrack(root, current.left, preorderInput, inorderInput);
            if (result != null) return result;
            result = backtrack(root, next, preorderInput, inorderInput);
            if (result != null) return result;
            current.right = null;
        }
        preorderInput.addFirst(next.value);
        return null;
    }
}
