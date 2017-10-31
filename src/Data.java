/**
 * Created by Jakub Mikeš on 29.10.17.
 */
public class Data {

    private double dataSize;
    private Node source;
    private Node destination;

    public Data(int dataSize, Node source, Node destination) {
        this.dataSize = dataSize;
        this.source = source;
        this.destination = destination;
    }

    public double getDataSize() {
        return this.dataSize;
    }

    public Node getDestination() {
        return this.destination;
    }

    public Node getSource() {
        return this.source;
    }
}
