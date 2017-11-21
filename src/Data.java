


/**
 * @author Jakub Mikeš
 *
 * Data class representing data packet
 * @see IPacket
 * @see Node
 * @see Path
 */
public class Data implements IPacket {

    /**
     * Size of data packet
     */
    private double size;
    /**
     * Current position of data packet
     */
    private Node position;
    /**
     * Source Node of data packet
     */
    private Node source;
    /**
     * Destination Node of data packet
     */
    private Node destination;
    /**
     * Path of this data packet
     */
    private Path path;

    /**
     * Constructor of Data class, returns instance of thiss class
     * @param size Size of this Data packet
     * @param source Source Node of this Data packet
     * @param destination Destination Node of this Data packet
     */
    public Data(double size, Node source, Node destination) {
        this.size = size;
        this.source = source;
        this.position = source;
        this.destination = destination;
    }

    /**
     * Returns size of this Data packet
     * @param newSize Size of this data packet
     */
    public void setSize(double newSize) {
        this.size = newSize;
    }

    /**
     * Sets new position for this Data packet
     * @param newPosition New postition for this Data packet
     */
    @Override
    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }

    /**
     * Sets new Path for this Data packet
     * @param newPath New Path for data packet
     */
    @Override
    public void setPath(Path newPath) {
        this.path = newPath;
    }

    /**
     * Returns Path of this Data packet
     * @return Path of this Data packet
     */
    @Override
    public Path getPath() {
        return path;
    }

    /**
     * Returns size of this Data packet
     * @return Size of Data packet
     */
    @Override
    public double getSize() {
        return size;
    }

    /**
     * Returns source Node of data packet
     * @return Source Node of data packet
     */
    @Override
    public Node getSource() {
        return source;
    }

    /**
     * Returns destination Node of data packet
     * @return Destination Node of data packet
     */
    @Override
    public Node getDestination() {
        return destination;
    }

    /**
     * Returns current position of data packet
     * @return Current position of data packet
     */
    @Override
    public Node getPosition() {
        return position;
    }

    /**
     * Returns String representation of data packet
     * @return String representation of data packet
     */
    @Override
    public String toString() {
        return "Data packet: \nSource: "+source+"\nDestination: "+destination;
    }



}
