
import java.util.Stack;

/**
 * Created by PavelHabzansky on 14.11.17.
 */
public class Data {

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

    public double getSize() {
        return size;
    }

    public void setSize(double newSize) {
        this.size = newSize;
    }

    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public Node getPosition() {
        return position;
    }

    public Stack<Node> getPath() {
        return path;
    }

    public void setPath(Stack<Node> newPath) {
        this.path = newPath;
    }

}
