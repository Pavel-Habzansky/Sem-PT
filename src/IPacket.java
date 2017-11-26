import java.util.List;

/**
 * IPacket Interface
 *
 * @author Habzansky, Mikes
 *         <p>
 *         Interface representing data packet properties
 * @see Data
 * @see DataPart
 */
public interface IPacket {

    /**
     * Method returning size of data packet
     *
     * @return size of data packet
     */
    double getSize();

    /**
     * Method returning source of data packet
     *
     * @return source of data packet as Node
     */
    Node getSource();

    /**
     * Method returning destination of data packet
     *
     * @return destination of data packet as Node
     */
    Node getDestination();

    /**
     * Method returning position of data packet
     *
     * @return position of data packet as Node
     */
    Node getPosition();

    /**
     * Method sets position of data packet
     *
     * @param node current position
     */
    void setPosition(Node node);

    /**
     * Method returns Path of data packet
     *
     * @return path of data packet
     */
    Path getPath();

    /**
     * Method sets Path od data packet
     *
     * @param path new Path of data packet
     */
    void setPath(Path path);

    /**
     * Returns timestep at which packet is supposed to be forwarded
     *
     * @return Timestep value
     */
    int getTimestep();

    void setTimestep(int timestep);

    /**
     * Sets new size for packet
     *
     * @param newSize New size for data packet
     */
    void setSize(double newSize);

    /**
     * Prints information about graph traversal
     * @param filename Name of file which is to be printed in
     */
    void printVisitedToFile(String filename);

    /**
     * Adds Node to List of visited Nodes
     * @param node New visited Node
     */
    void addVisit(Node node);

    /**
     * Resets List of visited Nodes
     */
    void resetVisited();

    /**
     * Sets new List of visited Nodes
     * @param nodes New List of visited Nodes
     */
    void setVisited(List<Node> nodes);

    /**
     * Returns List of visited Nodes
     * @return List of visited Nodes
     */
    List<Node> getVisited();

    /**
     * Returns List of all children DataPart packets
     * @return List of all children DataPart packets
     */
    List<DataPart> getSegments();

    /**
     * Adds DataPart segment to children segments
     * @param part DataPart segment
     */
    void addSegment(DataPart part);

    /**
     * Returns value identifying if this Packet can fail during Edge traversal
     * @return True of Packet can fail during Edge traversal
     */
    boolean getCanFail();

    /**
     * Sets value identifying if Packet can fail during Edge traversal
     * @param canFail Value identifying if Packet can fail during Edge traversal
     */
    void setCanFail(boolean canFail);

    /**
     * Returns whole data Packet to its source
     */
    void returnHome();

    /**
     * Returns parental IPacket of current IPacket
     * @return Parental IPacket
     */
    IPacket getParent();


}
