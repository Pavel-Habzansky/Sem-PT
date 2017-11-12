
import java.util.ArrayList;

/**
 * Created by PavelHabzansky on 11.10.17.
 */
public class Node {

    // TODO implement VISITED / UNVISITED state Enum type for Dijkstra

    private String id;
    private SmartStack smartStack;
    //    private ArrayList<Node> predecessors;
//    private ArrayList<Node> successors;
    private ArrayList<Edge> neighborhood;
    private ArrayList<Data> data;

    public Node(String id) {
        this.id = id;
        this.neighborhood = new ArrayList<>();
//        this.predecessors = new ArrayList<>();
//        this.successors = new ArrayList<>();
        this.smartStack = new SmartStack();
    }

    public SmartStack getSmartStack() {
        return smartStack;
    }

    public String getId() {
        return this.id;
    }

    public void addNeighbour(Edge edge) {
        if (containsNeighbor(edge))
            return;
        this.neighborhood.add(edge);
    }

    public ArrayList<Edge> getNeighborhood() {
        return this.neighborhood;
    }

    public boolean containsNeighbor(Edge edge) {
        return this.neighborhood.contains(edge);
    }

    public Edge getNeighbor(int index) {
        return this.neighborhood.get(index);
    }

    public int neighborCount() {
        return this.neighborhood.size();
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Node))
            return false;

        Node v = (Node) other;
        return this.id.equals(v.id);
    }

    public ArrayList<Data> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Uzel " + id;
    }

    //    public ArrayList<Node> getPredecessors() {
//        return this.predecessors;
//    }
//
//    public ArrayList<Node> getSuccessors() {
//        return this.successors;
//    }

    //    public void addPredecessor(Node node) {
//        this.predecessors.add(node);
//    }
//
//    public void addSuccessor(Node node) {
//        this.successors.add(node);
//    }

}
