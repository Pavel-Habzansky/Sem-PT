

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
     * @return Timestep value
     */
    int getTimestep();

    /**
     * Sets new size for packet
     * @param newSize New size for data packet
     */
    void setSize(double newSize);


}
