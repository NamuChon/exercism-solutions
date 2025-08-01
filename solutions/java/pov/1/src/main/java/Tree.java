import java.util.*;
class Tree {
    private final String label;
    private final List<Tree> children;

    public Tree(String label) {
        this(label, new ArrayList<>());
    }

    public Tree(String label, List<Tree> children) {
        this.label = label;
        this.children = children;
    }

    public static Tree of(String label) {
        return new Tree(label);
    }

    public static Tree of(String label, List<Tree> children) {
        return new Tree(label, children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return label.equals(tree.label)
                && children.size() == tree.children.size()
                && children.containsAll(tree.children)
                && tree.children.containsAll(children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, children);
    }

    @Override
    public String toString() {
        return "Tree{" + label +
                ", " + children +
                "}";
    }

    public Tree fromPov(String fromNode) {
        Tree result = fromPov(this, null, fromNode);
        if (result == null) throw new UnsupportedOperationException("Tree could not be reoriented");
        return result;
    }

    private Tree fromPov(Tree start, Tree parent, String target) {
        String label = start.label;
        List<Tree> children = start.children;
        if (label.equals(target)) {
            List<Tree> childrenCopy = new ArrayList<>(children);
            if (parent != null) childrenCopy.add(parent);
            return new Tree(label, childrenCopy);
        }
        
        
        for (int i = 0; i < children.size(); i++) {
            List<Tree> childrenCopy = new ArrayList<>(children);
            Tree child = childrenCopy.remove(i);
            if (parent != null) childrenCopy.add(parent);

            Tree result = fromPov(child, new Tree(label, childrenCopy), target);
            if (result != null) return result;
        }
        return null;
    }
    
    public List<String> pathTo(String fromNode, String toNode) {
        try {
            List<String> result = fromPov(fromNode).getPath(toNode);
            if (result == null) throw new UnsupportedOperationException();
            return result;
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("No path found");
        }
    }

    private List<String> getPath(String target) {
        if (label.equals(target)) return List.of(label);
        
        for (Tree child : children) {
            List<String> path = child.getPath(target);
            if (path != null) {
                List<String> result = new LinkedList<>();
                result.add(label);
                result.addAll(path);
                return result;
            }
        }
        return null;
    }
}
