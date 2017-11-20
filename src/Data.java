
import java.util.Stack;

/**
 * Created by Jakub Mikeš on 14.11.17.
 */
public class Data implements IPacket {

    private double size;
    private Node position;
    private Node source;
    private Node destination;
    private Path path;

    public Data(double size, Node source, Node destination) {
        this.size = size;
        this.source = source;
        this.position = source;
        this.destination = destination;
    }



    public void setSize(double newSize) {
        this.size = newSize;
    }

    @Override
    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }

    @Override
    public void setPath(Path newPath) {
        this.path = newPath;
    }

    @Override
    public Path getPath() {
        return path;
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

    @Override
    public String toString() {
        return "Data packet: \nSource: "+source+"\nDestination: "+destination;
    }



}
