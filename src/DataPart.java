

/**
 * @author PavelHabzansky
 *         <p>
 *         Data segment of bigger Data packet
 * @see Data
 * @see Node
 * @see Path
 * @see IPacket
 */
public class DataPart implements IPacket {

    private int timestep;
    /**
     * Size of this Data segment
     */
    private double size;
    /**
     * Data packet to which this DataPart belong
     */
    private IPacket parent;
    /**
     * Current position of this DataPart represented by Node
     */
    private Node position;
    /**
     * Path of this DataPart
     */
    private Path path;

    /**
     * Property identifying if DataPart is currently stored in SmartStack
     */
    private boolean isInSmartStack;

    /**
     * Constructor of DataPart class, returns instance of this class
     *
     * @param size     Size of this data segment
     * @param parent   Data packet to which this DataPart belong
     * @param position Current position of this Node
     * @param timestep Time step at which this DataPart should be sent
     */
    public DataPart(double size, IPacket parent, Node position, int timestep) {
        this.size = size;
        this.parent = parent;
        this.position = position;
        this.timestep = timestep;
        this.isInSmartStack = true;
    }

    /**
     * Sets new parent for this DataPart
     *
     * @param parent New parental packet
     * @see IPacket
     */
    public void setParent(IPacket parent) {
        this.parent = parent;
    }

    /**
     * Identifies if DataPart is currently in SmartStack
     *
     * @return True if DataPart is in SmartStack
     * @see SmartStack
     */
    public boolean getIsInSmartStack() {
        return isInSmartStack;
    }

    /**
     * Sets positioning in SmartStack as false
     */
    public void leaveSmartStack() {
        isInSmartStack = false;
    }

    /**
     * Sets new time step at which this DataPart is to be forwarded
     *
     * @param timestep New time step
     */
    public void setTimestep(int timestep) {
        this.timestep = timestep;
    }

    @Override
    public int getTimestep() {
        return timestep;
    }

    /**
     * Sets Path for this DataPart
     *
     * @param path New Path for DataPart
     */
    @Override
    public void setPath(Path path) {
        this.path = path;
    }

    /**
     * Returns Data packet to which this data segment belong
     *
     * @return Data packet to which this data segment belong
     */
    public IPacket getParent() {
        return parent;
    }

    /**
     * Returns Path of this DataPart
     *
     * @return Path of this DataPart
     */
    @Override
    public Path getPath() {
        return path;
    }

    /**
     * Returns source Node of this DataPart
     *
     * @return Source Node of this DataPart
     */
    @Override
    public Node getSource() {
        return getParent().getSource();
    }

    /**
     * Returns size of this DataPart
     *
     * @return Size of this DataPart
     */
    @Override
    public double getSize() {
        return size;
    }

    /**
     * Returns destination Node of this DataPart
     *
     * @return Destination Node of this DataPart
     */
    @Override
    public Node getDestination() {
        return getParent().getDestination();
    }

    /**
     * Returns current position of this DataPart
     *
     * @return Current position of this DataPart
     */
    @Override
    public Node getPosition() {
        return position;
    }

    /**
     * Sets new position of this DataPart
     *
     * @param newPosition New position
     */
    @Override
    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }

    /**
     * Sets new size of data for this packet
     *
     * @param newSize New size for data packet
     */
    @Override
    public void setSize(double newSize) {
        this.size = newSize;
    }

    /**
     * String representation of DataPart
     *
     * @return String representation of this class
     */
    @Override
    public String toString() {
        return "DataPart: \nSize: " + size + "\nPosition: " + position + "\nDestination: " + parent.getDestination();
    }

}
