import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by PavelHabzansky on 12.11.17.
 */
public class UndirectedGraph {

    private static UndirectedGraph INSTANCE;

    private HashMap<String, Node> nodes;
    private HashMap<Integer, Edge> edges;

    private UndirectedGraph() {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
    }

    public static UndirectedGraph getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UndirectedGraph();
        return INSTANCE;
    }

    public boolean addEdge(Node node1, Node node2, double bandwidth, double errorChance) {
        if (node1.equals(node2))
            return false;

        Edge edge = new Edge(node1, node2, bandwidth, errorChance);
        if (edges.containsKey(edge.hashCode()))
            return false;
        else if (node1.containsNeighbor(edge) || node2.containsNeighbor(edge))
            return false;

        edges.put(edge.hashCode(), edge);
        node1.addNeighbour(edge);
        node2.addNeighbour(edge);
        return true;
    }

    public boolean containsEdge(Edge edge) {
        if (edge.getNode1() == null || edge.getNode2() == null)
            return false;
        return this.edges.containsKey(edge.hashCode());
    }

    public boolean containsNode(Node node) {
        return this.nodes.get(node.getId()) != null;
    }

    public Node getNode(String id) {
        return this.nodes.get(id);
    }

    public boolean addNode(Node node) {
        Node current = this.nodes.get(node.getId());
        if (current != null)
            return false;
        nodes.put(node.getId(), node);
        return false;
    }

    public Set<String> nodeKeys() {
        return this.nodes.keySet();
    }

    public Set<Edge> getEdges() {
        return new HashSet<>(this.edges.values());
    }

    public void printGraph() {
        for (Edge edge : edges.values()) {
            System.out.println(edge);
        }
    }

}
