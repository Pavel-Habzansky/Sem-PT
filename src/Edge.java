/**
 * @author PavelHabzansky
 *         <p>
 *         Class representing Edge in Graph
 * @see Graph
 * @see Node
 */
public class Edge {

    /**
     * First Node on this Edge
     */
    private Node node1;
    /**
     * Second Node on this Edge
     */
    private Node node2;
    /**
     * Bandwidth of this Edge
     */
    private double bandwidth;
    /**
     * Percentual load at which this Edge can fail
     */
    private double errorChance;
    /**
     * Current percentual load on this Edge
     */
    private double load;

    /**
     * Constructor of Edge class, returns instance of this class
     *
     * @param node1       First Node on this Edge
     * @param node2       Second Node on this Edge
     * @param bandwidth   Bandwidth of this Edge
     * @param errorChance Load at which this Edge can fail
     */
    public Edge(Node node1, Node node2, double bandwidth, double errorChance) {
        this.node1 = node1;
        this.node2 = node2;
        this.bandwidth = bandwidth;
        this.errorChance = errorChance;
        this.load = 0;
    }

    /**
     * Sets Node for node1
     *
     * @param node New Node for node1
     */
    public void setNode1(Node node) {
        this.node1 = node;
    }

    /**
     * Sets Node for node2
     *
     * @param node New Node for node2
     */
    public void setNode2(Node node) {
        this.node2 = node;
    }

    /**
     * Sets new bandwidth
     *
     * @param bandwidth New bandwidth
     */
    public void setBandwidth(double bandwidth) {
        this.bandwidth = bandwidth;
    }

    /**
     * Sets new error chance
     *
     * @param errorChance New error chance
     */
    public void setErrorChance(double errorChance) {
        this.errorChance = errorChance;
    }

    /**
     * Identifies if this Edge can fail under load of input data size
     *
     * @param dataSize Load of data
     * @return True if Edge can fail
     */
    public boolean canFail(double dataSize) {
        return (getLoadForNextStep(dataSize) >= errorChance);
    }

    /**
     * Returns percentual load under input data size
     * This method helps to determine of Edge can fail
     *
     * @param dataSize Load of data
     * @return Percentual load in next step
     */
    public double getLoadForNextStep(double dataSize) {
        return dataSize / (bandwidth / 100) / 100;
    }

    /**
     * Resets current load of this Edge to 0
     */
    public void resetLoad() {
        this.load = 0;
    }

    /**
     * Calculates load for next step
     *
     * @param dataSize Load of data to be added
     */
    public void setLoadForNextStep(double dataSize) {
        this.load += dataSize / (bandwidth / 100) / 100;
    }

    /**
     * Returns current load of data
     *
     * @return Current load of data
     */
    public double getLoad() {
        return load;
    }

    /**
     * Returns first Node on this Edge
     *
     * @return First Node on this Edge
     */
    public Node getNode1() {
        return node1;
    }

    /**
     * Returns second Node on this Edge
     *
     * @return Second Node on this Edge
     */
    public Node getNode2() {
        return node2;
    }

    /**
     * Returns bandwidth of this Edge
     *
     * @return Bandwidth of this Edge
     */
    public double getBandwidth() {
        return bandwidth;
    }

    /**
     * Returns percentual load at which Edge can fail
     *
     * @return Percentual load at which Edge can fail
     */
    public double getErrorChance() {
        return errorChance;
    }

    /**
     * Returns String representation of this Edge
     *
     * @return String representation of this Edge
     */
    @Override
    public String toString() {
        return "From: " + node1 + " to " + node2 + " bandwidth " + bandwidth + " error chance " + errorChance;
    }

}
