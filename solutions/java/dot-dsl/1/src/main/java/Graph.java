import java.util.*;
public class Graph {
    private final Map<String, String> attributes;
    private Map<String, Node> nodes = new HashMap<>();
    private List<Edge> edges = new ArrayList<>();
    public Graph() {
        attributes = new HashMap<>();
    }

    public Graph(Map<String, String> attributes) {
        this.attributes = new HashMap<>(attributes);
    }

    public Collection<Node> getNodes() {
        return nodes.values();
    }

    public Collection<Edge> getEdges() {
        return edges;
    }

    public Graph node(String name) {
        nodes.put(name, new Node(name));
        return this;
    }

    public Graph node(String name, Map<String, String> attributes) {
        nodes.put(name, new Node(name, attributes));
        return this;
    }

    public Graph edge(String start, String end) {
        edges.add(new Edge(start, end));
        return this;
    }

    public Graph edge(String start, String end, Map<String, String> attributes) {
        edges.add(new Edge(start, end, attributes));
        return this;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
