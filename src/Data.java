import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jakub Mikeš
 *         <p>
 *         Data class representing data packet
 * @see IPacket
 * @see Node
 * @see Path
 */
public class Data implements IPacket {

    /**
     * In case of changing paths, this data part can fail on edge if it's too loaded
     */
    private boolean canFail;

    /**
     * List of all DataParts derived from this Data packet
     */
    private List<DataPart> dataSegments;

    /**
     * List of visited Nodes during graph traversal
     */
    private List<Node> visited;

    /**
     * Time step at which this Data packet is to be sent
     */
    private int timeStep;

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
     *
     * @param size        Size of this Data packet
     * @param source      Source Node of this Data packet
     * @param destination Destination Node of this Data packet
     * @param timestep    Time step at which this Data packet is supposed to be sent
     */
    public Data(double size, Node source, Node destination, int timestep) {
        this.timeStep = timestep;
        this.size = size;
        this.source = source;
        this.position = source;
        this.destination = destination;
        this.visited = new ArrayList<>();
        visited.add(source);
        this.dataSegments = new ArrayList<>();
    }

    /**
     * Sets new time step for this Data packet
     * @param timestep New time step
     */
    @Override
    public void setTimestep(int timestep) {
        this.timeStep = timestep;
    }

    /**
     * Returns whole Data packet to its source
     */
    @Override
    public void returnHome() {
        for (DataPart segment : dataSegments) {
            segment.returnHome();
        }
        path = null;
        position = source;
    }

    /**
     * Sets value identifying if Data packet can fail during Edge traversal
     * @param canFail Value identifying if Packet can fail during Edge traversal
     */
    @Override
    public void setCanFail(boolean canFail) {
        this.canFail = canFail;
    }

    /**
     * Returns value identifying if Data packet can fail during Edge traversal
     * @return True if Data packet can fail during Edge traversal
     */
    @Override
    public boolean getCanFail() {
        return canFail;
    }

    /**
     * Returns List of all children DataParts
     * @return List of all children DataParts
     */
    @Override
    public List<DataPart> getSegments() {
        return dataSegments;
    }

    /**
     * Adds new DataPart to this Data packet's segments
     * @param part DataPart segment
     */
    @Override
    public void addSegment(DataPart part) {
        dataSegments.add(part);
    }

    /**
     * Sets new List as visited Nodes
     *
     * @param newVisited
     */
    @Override
    public void setVisited(List<Node> newVisited) {
        visited = newVisited;
    }

    /**
     * Resets List of visited Nodes
     */
    @Override
    public void resetVisited() {
        visited = new ArrayList<>();
        visited.add(source);
    }

    /**
     * Returns List of visited Nodes
     *
     * @return List of visited Nodes
     */
    @Override
    public List<Node> getVisited() {
        return this.visited;
    }

    /**
     * Adds new Node to List of visited Nodes
     *
     * @param node New Node to be added to List of visited Nodes
     */
    @Override
    public void addVisit(Node node) {
        visited.add(node);
    }

    /**
     * Sets new source Node for Data packet
     *
     * @param newSource New source Node
     * @see Node
     */
    public void setSource(Node newSource) {
        this.source = newSource;
    }

    /**
     * Sets new destination Node for Data packet
     *
     * @param destination New destination Node
     */
    public void setDestination(Node destination) {
        this.destination = destination;
    }

    /**
     * Returns time step at which this Data packet is to be forwarded
     *
     * @return Time step at which this Data packet is to be forwarded
     */
    @Override
    public int getTimestep() {
        return timeStep;
    }

    /**
     * Returns size of this Data packet
     *
     * @param newSize Size of this data packet
     */
    @Override
    public void setSize(double newSize) {
        this.size = newSize;
    }

    /**
     * Sets new position for this Data packet
     *
     * @param newPosition New postition for this Data packet
     */
    @Override
    public void setPosition(Node newPosition) {
        this.position = newPosition;
    }

    /**
     * Sets new Path for this Data packet
     *
     * @param newPath New Path for data packet
     */
    @Override
    public void setPath(Path newPath) {
        this.path = newPath;
    }

    /**
     * Returns Path of this Data packet
     *
     * @return Path of this Data packet
     */
    @Override
    public Path getPath() {
        return path;
    }

    /**
     * Returns size of this Data packet
     *
     * @return Size of Data packet
     */
    @Override
    public double getSize() {
        return size;
    }

    /**
     * Returns source Node of data packet
     *
     * @return Source Node of data packet
     */
    @Override
    public Node getSource() {
        return source;
    }

    /**
     * Returns destination Node of data packet
     *
     * @return Destination Node of data packet
     */
    @Override
    public Node getDestination() {
        return destination;
    }

    /**
     * Returns current position of data packet
     *
     * @return Current position of data packet
     */
    @Override
    public Node getPosition() {
        return position;
    }

    /**
     * Prints information about visited Nodes
     *
     * @param filename Name of file which is to be printed in
     */
    @Override
    public void printVisitedToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write("Destination reached!\n" + toString() + "\nVisited nodes: " + getVisited() + "\n=================");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Returns String representation of data packet
     *
     * @return String representation of data packet
     */
    @Override
    public String toString() {
        return "Data packet: \nSource: " + source + "\nDestination: " + destination + "\nCurrently on: " + position + "\nSize: " + size+"\n";
    }


}
