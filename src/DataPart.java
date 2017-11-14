/**
 * Created by PavelHabzansky on 14.11.17.
 */
public class DataPart {

    private double size;
    private Data parent;
    private Node position;

    public DataPart(double size, Data parent, Node position) {
        this.size = size;
        this.parent = parent;
        this.position = position;
    }

    public double getSize() {
        return size;
    }

    public Data getParent() {
        return parent;
    }

    public Node getPosition() {
        return position;
    }

    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }


}
