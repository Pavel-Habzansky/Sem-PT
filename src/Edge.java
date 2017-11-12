import javafx.util.Pair;

/**
 * Created by PavelHabzansky on 11.10.17.
 */

public class Edge {
    private Node node1;
    private Node node2;
    private double bandwidth;
    private double errorChance;

//    private Graph graph = Graph.getInstance();

    public Edge(Node node1, Node node2, double bandwidth, double errorChance) {
        this.node1 = (node1.getId().compareTo(node2.getId()) <= 0) ? node1 : node2;
        this.node2 = (this.node1==node1) ? node2 : node1;

        this.bandwidth = bandwidth;
        this.errorChance = errorChance;
    }

    public Node getNode1() {
        return this.node1;
    }

    public Node getNode2() {
        return this.node2;
    }

    public double getErrorChance() {
        return this.errorChance;
    }

    public double getBandwidth() {
        return this.bandwidth;
    }

    @Override
    public int hashCode() {
        return (node1.getId() + node2.getId()).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Edge))
            return false;
        Edge edge = (Edge) object;
        return edge.getNode1().equals(this.node1) &&
                edge.getNode2().equals(this.node2);
    }

    @Override
    public String toString() {
        return "Hrana z " + node1 + " do " + node2 + " s propustnosti " + bandwidth + " chybovost " + errorChance;
    }

}
