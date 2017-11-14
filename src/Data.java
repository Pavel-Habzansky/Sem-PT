
import java.util.Stack;

/**
 * Created by Jakub Mikeš on 14.11.17.
 */
public class Data implements IPacket {

    private double size;
    private Node position;
    private Node source;
    private Node destination;
    private Stack<Node> path;

    public Data(double size, Node source, Node destination, Stack<Node> path) {
        this.size = size;
        this.source = source;
        this.position = source;
        this.destination = destination;
        this.path = path;
    }

    public Stack<Node> getPath() {
        return path;
    }

    public void setPath(Stack<Node> newPath) {
        this.path = newPath;
    }



    public void setSize(double newSize) {
        this.size = newSize;
    }

    @Override
    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public Node getSource() {
        return source;
    }
    @Override
    public Node getDestination() {
        return destination;
    }
    @Override
    public Node getPosition() {
        return position;
    }



}
