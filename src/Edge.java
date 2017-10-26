/**
 * Created by PavelHabzansky on 11.10.17.
 */
public class Edge {

    private Node from;
    private Node to;
    private int bandwidth;

    private Graph graph = Graph.getInstance();

    public Edge(String fromId, String toId, int bandwidth) {
        this.from = graph.findNode(fromId);
        if (this.from == null) {
            this.from = new Node(fromId);
            // add 'from' to graph
        }
        this.to = graph.findNode(toId);
        if (this.to == null) {
            this.to = new Node(toId);
        }
        this.bandwidth = bandwidth;

        this.from.addSuccessor(this.to);
        this.to.addPredecessor(this.from);
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    public int getBandwidth() {
        return this.bandwidth;
    }

    public void updateBandwidth(int newVal) {
        this.bandwidth = newVal;
    }

    @Override
    public String toString() {
        return "Hrana z " + from + " do " + to + " s propustnosti " + bandwidth;
    }

}
