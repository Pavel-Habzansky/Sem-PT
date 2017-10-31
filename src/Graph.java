
import java.util.ArrayList;

/**
 * Created by PavelHabzansky on 26.10.17.
 */
public class Graph {

    private static Graph INSTANCE;

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public static Graph getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Graph();
        return INSTANCE;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Node findNode(String nodeId) {
        for(Node each : nodes) {
            if (each.getId().compareTo(nodeId) == 0) {
                return each;
            }
        }
        return null;
    }

    public void addEdge(String fromId, String toId, double bandwidth, double errorChance) {
        Edge newEdge = new Edge(fromId, toId, bandwidth, errorChance);
        edges.add(newEdge);
        nodes.add(new Node(fromId));
        nodes.add(new Node(toId));
    }

    public Edge findEdge(Node from, Node to) {
        for (Edge each : edges) {
            if (each.getFrom().getId().equals(from.getId()) && each.getTo().getId().equals(to.getId())) {
                return each;
            }
        }
        return null;
    }

    public void printGraph() {
        for (Edge each : edges) {
            System.out.println(each);
        }
    }

    private Graph(){
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
