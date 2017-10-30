/**
 * Created by PavelHabzansky on 11.10.17.
 */
public class Edge {

    private Node from;
    private Node to;
    private int bandwidth;
    private int errorChance;

    private Graph graph = Graph.getInstance();

    public Edge(String fromId, String toId, int bandwidth, int errorChance) {
        this.from = graph.findNode(fromId);
        if (this.from == null) {
            this.from = new Node(fromId);
            graph.addNode(this.from);
        }
        this.to = graph.findNode(toId);
        if (this.to == null) {
            this.to = new Node(toId);
            graph.addNode(this.to);
        }
        this.bandwidth = bandwidth;
        this.errorChance = errorChance;

        this.from.addSuccessor(this.to);
        this.to.addPredecessor(this.from);
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    public int getErrorChance() {
        return this.errorChance;
    }

    public int getBandwidth() {
        return this.bandwidth;
    }

    @Override
    public String toString() {
        return "Hrana z " + from + " do " + to + " s propustnosti " + bandwidth + " chybovost " + errorChance;
    }

}
