

/**
 * IPacket Interface 
 * @author Habzansky, Mikes
 *
 */
public interface IPacket {

	/**
	 * the method getSize()
	 * @return size
	 */
    double getSize();
    
    /**
     * the method getSource
     * @return source
     */
    Node getSource();
    
    /**
     * the method getDestination
     * @return destination
     */
    Node getDestination();
    
    /**
     * the method getPosition
     * @return position
     */
    Node getPosition();
    
    /**
     * the method setPosition set position of node
     * @param node
     */
    void setPosition(Node node);
    
    /**
     * the method getPath()
     * @return path
     */
    Path getPath();


}
