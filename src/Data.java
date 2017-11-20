
import java.util.Stack;

/**
 * Data class
 * @author Habyansky, Mikes
 *
 */
public class Data implements IPacket {

    private double size;
    private Node position;
    private Node source;
    private Node destination;
    private Path path;

    /**
     * constructor of Data class
     * @param size
     * @param source
     * @param destination
     */
    public Data(double size, Node source, Node destination) {
        this.size = size;
        this.source = source;
        this.position = source;
        this.destination = destination;
    }


/**
 * the method setPath set new path
 * @param newPath
 */
    public void setPath(Path newPath) {
        this.path = newPath;
    }

    /**
     * the method setSize set new size
     * @param newSize
     */
    public void setSize(double newSize) {
        this.size = newSize;
    }

    /**
     * the method setPosition set new position of node
     */
    @Override
    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }

    /**
     * the method getPath return path
     * @return path
     */
    @Override
    public Path getPath() {
        return path;
    }

    /**
     * the method getSize return size
     * @return size
     */
    @Override
    public double getSize() {
        return size;
    }

    /**
     * the method getSource return source
     * @return source
     */
    @Override
    public Node getSource() {
        return source;
    }
    
    /**
     * the method getDestination return destionation of node
     * @return restination
     *     
     */
    @Override
    public Node getDestination() {
        return destination;
    }
    
    /**
     * the method getPosition return position of node
     * @return position
     */
    @Override
    public Node getPosition() {
        return position;
    }



}
