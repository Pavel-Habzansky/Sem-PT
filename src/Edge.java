/**
 * Edge class
 * @author Habzansky, Mikes
 *
 */
public class Edge {

	/**
	 * attributes of Edge class
	 */
    private Node node1;
    private Node node2;
    private double bandwidth;
    private double errorChance;
    private double load;

    /**
     * constructor of Edge class
     * @param node1
     * @param node2
     * @param bandwidth
     * @param errorChance
     */
    public Edge(Node node1, Node node2, double bandwidth, double errorChance) {
        this.node1 = node1;
        this.node2 = node2;
        this.bandwidth = bandwidth;
        this.errorChance = errorChance;
        this.load = 0;
    }

    /**
     * the method canFail() can find out if it fail
     * @return true or flase
     */
    public boolean canFail() {
        return (load >= errorChance);
    }

    /**
     * the metohot resetLoad() set load to 0
     */
    public void resetLoad() {
        this.load = 0;
    }

    /**
     * the method setLoadForNextSetep with parameter nextData load new data to load
     * @param nextData
     */
    public void setLoadForNextStep(IPacket nextData) {
        this.load += nextData.getSize()/(bandwidth/100)/100;
    }

    /**
     * the method getLoad() return load; 
     * @return load
     */
    public double getLoad() {
        return load;
    }

    /**
     * the method getNode1() return node1
     * @return node1
     */
    public Node getNode1() {
        return node1;
    }

    /**
     * the metohod getNode2() return node2
     * @return node2
     */
    public Node getNode2() {
        return node2;
    }

    /**
     * the method getBandwidth() return bandwidth
     * @return bandwidth
     */
    public double getBandwidth() {
        return bandwidth;
    }

    /**
     * the method getErrorChance() return error of chance
     * @return errorChance
     */
    public double getErrorChance() {
        return errorChance;
    }

}
