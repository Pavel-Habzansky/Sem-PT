/**
 * Created by Jakub Mikeš on 29.10.17.
 */
public class Data {

    private double dataSize;
    private Node source;
    private Node destination;
    private Node[] cesta;

    public Data(double dataSize, Node source, Node destination) {
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

    public Node[] getCesta() {
        return this.cesta;
    }

    public void send() {
        if (this.source.getSuccessors().contains(this.destination)) {
            this.destination.getData().add(this);
        } else {
            Node current = this.source;
            for (int i = 1; current != destination; i++) {
                current = cesta[i];
            }
        }
        System.out.println("finish");
    }
}
