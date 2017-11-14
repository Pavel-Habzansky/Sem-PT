/**
 * Created by PavelHabzansky on 14.11.17.
 */
public class DataPart implements IPacket {

    private double size;
    private Data parent;
    private Node position;

    public DataPart(double size, Data parent, Node position) {
        this.size = size;
        this.parent = parent;
        this.position = position;
    }

    public Data getParent() {
        return parent;
    }

    @Override
    public Node getSource() {
        return getParent().getSource();
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public Node getDestination() {
        return getParent().getPosition();
    }

    @Override
    public Node getPosition() {
        return position;
    }

    @Override
    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }

}
