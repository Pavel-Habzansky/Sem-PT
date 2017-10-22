
import java.util.ArrayList;

/**
 * Created by PavelHabzansky on 11.10.17.
 */
public class Node {

    // TODO implement VISITED / UNVISITED state Enum type for Dijkstra

    private String id;
    private ArrayList<Node> predecessors;
    private ArrayList<Node> successors;

    public Node(String id) {
        this.id = id;
        this.predecessors = new ArrayList<>();
        this.successors = new ArrayList<>();
        // TODO set state to unvisited
    }

    public String getId() {
        return this.id;
    }

    public void addPredecessor(Node node) {
        this.predecessors.add(node);
    }

    public void addSuccessor(Node node) {
        this.successors.add(node);
    }

    public ArrayList<Node> getPredecessors() {
        return this.predecessors;
    }

    public ArrayList<Node> getSuccessors() {
        return this.successors;
    }

    @Override
    public String toString() {
        return "Uzel "+id;
    }

}
