import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by PavelHabzansky on 14.11.17.
 */
public class DataPart implements IPacket {

    private double size;
    private Data parent;
    private Node position;
    private Stack<Node> path;

    public DataPart(double size, Data parent, Node position) {
        this.size = size;
        this.parent = parent;
        this.position = position;
        // TODO implement path
    }

    public Data getParent() {
        return parent;
    }

    @Override
    public Stack<Node> getPath() {
        return path;
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
