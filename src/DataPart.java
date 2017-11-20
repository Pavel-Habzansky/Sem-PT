
import java.util.Stack;

/**
 * DataPart class
 * @author Habzansky, Mikes
 *
 */
public class DataPart implements IPacket {
/**
 * attributes of DataPart class
 */
    private double size;
    private Data parent;
    private Node position;
    private Path path;

    /**
     * constructor of DataPart class
     * @param size
     * @param parent
     * @param position
     */
    public DataPart(double size, Data parent, Node position) {
        this.size = size;
        this.parent = parent;
        this.position = position;
        // TODO implement path
    }

    /**
     * the method getParent() return parent
     * @return parent
     */
    public Data getParent() {
        return parent;
    }

    
    /**
     * the method getPath() return path
     * @return path
     */
    @Override
    public Path getPath() {
        return path;
    }

    /**
     * the method getSource() return source of parent
     * @return source of parent
     */
    @Override
    public Node getSource() {
        return getParent().getSource();
    }

    /**
     * the method getSize() return size
     * @return size
     */
    @Override
    public double getSize() {
        return size;
    }

    /**
     * them method getDestination return position of parent
     * @return position of parent
     */
    @Override
    public Node getDestination() {
        return getParent().getPosition();
    }

    /**
     * the method getPosition return position of node
     * @return position
     */
    @Override
    public Node getPosition() {
        return position;
    }

    /**
     * the method setPositon set new position of node
     * @return node
     */
    @Override
    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }

}
