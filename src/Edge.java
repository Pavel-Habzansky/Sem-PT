/**
 * Created by PavelHabzansky on 13.11.17.
 */
public class Edge {

    private Node node1;
    private Node node2;
    private double bandwidth;
    private double errorChance;
    private double load;

    public Edge(Node node1, Node node2, double bandwidth, double errorChance) {
        this.node1 = node1;
        this.node2 = node2;
        this.bandwidth = bandwidth;
        this.errorChance = errorChance;
        this.load = 0;
    }

    public boolean canFail() {
        return (load >= errorChance);
    }

    public void resetLoad() {
        this.load = 0;
    }

    public void setLoadForNextStep(IPacket nextData) {
        this.load += nextData.getSize()/(bandwidth/100)/100;
    }

    public double getLoad() {
        return load;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public double getBandwidth() {
        return bandwidth;
    }

    public double getErrorChance() {
        return errorChance;
    }

}
