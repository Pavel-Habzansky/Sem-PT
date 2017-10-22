/**
 * Created by PavelHabzansky on 11.10.17.
 */
public class Edge {

    private Node from;
    private Node to;
    private int bandwidth;

    public Edge(Node from, Node to, int bandwidth) {
        this.from = Graph.findNode(from);
        if (this.from == null) {
            this.from = new Node(from.getId());
            // add 'from' to graph
        }
        this.to = Graph.findNode(to);
        if (this.to == null) {
            this.to = new Node(to.getId());
            // add 'to' to graph
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
