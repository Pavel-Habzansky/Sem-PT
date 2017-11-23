

import java.util.*;

/**
 * Created by PavelHabzansky on 13.11.17.
 *
 * @author PavelHabzansky
 *         <p>
 *         Graph class created as Singleton.
 *         This class is representation of graph using adjacency matrix of Edges
 * @see Edge
 * @see Node
 */

public class Graph {

    /**
     * Instance of this class
     */
    private static Graph instance;

    /**
     * Matrix representation of Graph
     * Indices mapping Nodes to columns/rows to this matrix are stored in indexMap
     */
    private Edge[][] matrix;
    /**
     * Map of indices.
     * Each Node (key) has its index in matrix stored in this map as value
     */
    private Map<Node, Integer> indexMap;
    /**
     * Map of data requests where Data is stored as value and index of destination as key
     */
    private List<IPacket> dataRequests;

    /**
     * Private constructor of this Graph, returns instance of this class
     *
     * @param indexMap Map of indices for matrix
     * @param matrix   Adjacency matrix of Edges
     */
    private Graph(Map<Node, Integer> indexMap, Edge[][] matrix) {
        setIndexMap(indexMap);
        setMatrix(matrix);
    }

    /**
     * Sets matrix for this Graph
     *
     * @param matrix Adjacency matrix graph representation
     */
    public void setMatrix(Edge[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Sets index map for this Graph
     *
     * @param indexMap Index map with Nodes as keys and their indices for adjacency matrix
     */
    public void setIndexMap(Map<Node, Integer> indexMap) {
        this.indexMap = indexMap;
    }

    /**
     * Returns instance of this Graph
     *
     * @param indexMap Map of indices for matrix
     * @param matrix   Adjacency matrix of Edges
     * @return Instance of this Graph
     */
    public static Graph getInstance(Map<Node, Integer> indexMap, Edge[][] matrix) {
        if (instance == null) {
            instance = new Graph(indexMap, matrix);
        }
        return instance;
    }

    /**
     * Sets data requests for this Graph
     *
     * @param dataRequests Map of data requests where keys are indices of destination Nodes and values are Data packets
     */
    public void setDataRequests(List<IPacket> dataRequests) {
        this.dataRequests = dataRequests;
    }

    /**
     * Prints all Edges in this matrix
     */
    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != null) {
                    System.out.print(matrix[i][j].getBandwidth() + " ");
                } else {
                    System.out.print("null ");
                }
            }
            System.out.println("]");
        }
    }

    /**
     * Returns Node from index map base on key input
     *
     * @param key Value of Node's index
     * @return Node mapped to index in adjacency matrix
     */
    public Node getNodeFromKey(int key) {
        for (Node o : indexMap.keySet()) {
            if (indexMap.get(o).equals(key)) {
                return o;
            }
        }
        return null;
    }

    /**
     * Prints ids of path's Nodes
     *
     * @param path Path to be printed
     */
    @Deprecated
    public void printPath(List<Node> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getId());
        }
        System.out.println();
    }

    /**
     * Creates Paths for all Nodes
     *
     * @see Path
     */
    public void initPaths() {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == j) {
                    continue;
                }
                dfs(i, j, new ArrayList<>(), i);
            }
        }
        System.out.println();
    }

    /**
     * Returns size of this Graph's adjacency matrix
     *
     * @return Size of adjacency matrix
     */
    public int getSize() {
        return matrix[0].length;
    }

    /**
     * Depth-First-Search algorithm recursively finds all possible Paths from source to destination
     *
     * @param source      Index of source Node
     * @param destination Index of destination Node
     * @param visited     List of visited Nodes
     * @param sourceFinal Immutable index of source Node
     */
    public void dfs(int source, int destination, List<Integer> visited, final int sourceFinal) {
        int current;
        visited.add(source);
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[source][i] != null) {
                current = i;
                if (source == destination) {

                    double sumBandwidth = matrix[visited.get(0)][visited.get(1)].getBandwidth();
                    for (int j = 1; j < visited.size() - 1; j++) {
                        sumBandwidth += matrix[visited.get(j)][visited.get(j + 1)].getBandwidth();
                    }

                    LinkedList<Integer> indexPath = new LinkedList<>();
                    for (Integer nodeIndex : visited) {
                        indexPath.add(nodeIndex);
                    }
                    Path path = new Path(indexPath, sumBandwidth);
                    Node sourceNode = getNodeFromKey(sourceFinal);
                    sourceNode.addPath(destination, path);

                    visited.remove(new Integer(source));
                    return;
                }
                if (!(visited.contains(current))) {
                    dfs(current, destination, visited, sourceFinal);
                }
            }
        }
        visited.remove(new Integer(source));
    }

    /**
     * Returns Node from index map based on its id
     *
     * @param id Id of searched Node
     * @return Node from index map keys
     */
    public Node getNodeById(String id) {
        return getNodeFromKey(indexMap.get(new Node(id)));
    }

    /**
     * Identifies if Edge between two Nodes exists
     *
     * @param nodeIndex1 Id of first Node
     * @param nodeIndex2 Id of second Node
     * @return True of edge between 2 Nodes exists
     */
    public boolean edgeExists(int nodeIndex1, int nodeIndex2) {
        return (matrix[nodeIndex1][nodeIndex2] != null);
    }

    /**
     * Resets loads on all Edges
     */
    public void resetLoads() {
        for (int j = 0; j < matrix[0].length; j++) {
            for (int k = 0; k < matrix[0].length; k++) {
                if (matrix[j][k] != null) {
                    matrix[j][k].resetLoad();
                }
            }
        }
    }

    /**
     * Method manipulating sending Data requests
     */
    public void sendDataPackets() {

        ArrayList<IPacket> packets = new ArrayList<>();

        for (int i = 0; !dataRequests.isEmpty(); i++) {

            for (int j = 0; j < dataRequests.size(); j++) {
                if (dataRequests.get(j).getTimestep() == i) {

                    IPacket forwarding = dataRequests.get(j);
                    packets.add(forwarding);
//                    dataRequests.remove(forwarding);
                }

            }

            for (int j = 0; j < packets.size(); j++) {
                // set loads for edges
                // dividing into DataParts
                // add DataParts into packets List

                IPacket packet = packets.get(j);

                if (packet.getPath() == null) {
                    setPathForPacket(packet);
                }

                int currentNode = packet.getPath().getCurrentNodeIndexInPath();
                int nextNode = packet.getPath().getNextNodeIndex();


                Edge nextEdge = matrix[currentNode][nextNode];
                Edge nextEdgeSymetric = matrix[nextNode][currentNode];

                if (!nextEdge.canFail(packet.getSize())) {
                    // hrana v dalsim kroku neselze
                    nextEdge.setLoadForNextStep(packet.getSize());
                    nextEdgeSymetric.setLoadForNextStep(packet.getSize());

                } else if (!nextEdge.canFail(packet.getSize() / 2)) {

                    segmentData(packet, i);

                } else {
                    // hrana i tak selze, dej packetu jinou cestu
                    // !!! dej mu kopii, ne primo referenci !!!

                    changePath(packet);
                }

            }

            for (int j = 0; j < packets.size(); j++) {
                // forwarding

                IPacket forwardingPacket = packets.get(j);
                forwardPacket(forwardingPacket, packets);

                if (forwardingPacket.getDestination().equals(forwardingPacket.getPosition())) {
                    dataRequests.remove(forwardingPacket);
//                    packets.remove(forwardingPacket);
                } else {
                    forwardingPacket.getPath().moveToNext();
                }

            }

            resetLoads();

        }

    }

    /**
     * Sets new path for packet before forwarding
     *
     * @param packet Packet currently being sent
     * @see IPacket
     */
    public void setPathForPacket(IPacket packet) {
        Node positionNode = packet.getPosition();
        Node destinationNode = packet.getDestination();
        List<Integer> destinationPath = positionNode
                .getPathsTo(indexMap.get(destinationNode))
                .get(0)
                .getPath();
        double sum = positionNode
                .getPathsTo(indexMap.get(destinationNode))
                .get(0)
                .getSum();
        List<Integer> linkedPath = new LinkedList<>(destinationPath);
        Path path = new Path(linkedPath, sum);
        packet.setPath(path);
    }

    /**
     * Divides data packet in half creating DataPart and adding it to data requests for next timestep
     *
     * @param packet   Data packet to be segmented
     * @param lastStep Current timestep, data segment is given current timestep + 1
     * @see IPacket
     */
    public void segmentData(IPacket packet, int lastStep) {
        DataPart dataPart = new DataPart(
                packet.getSize() / 2,
                packet,
                packet.getPosition(),
                lastStep + 1);
        packet.setSize(
                packet.getSize() / 2
        );

        System.out.println(dataPart);
        int destinationIndex = indexMap.get(dataPart.getDestination());

        Node position = packet.getPosition();
        if (position.getPathsTo(destinationIndex) == null) {
            System.out.println();
        }
        List<Integer> alternativePath = position.getPathsTo(destinationIndex).get(1).getPath();
        List<Integer> currentPath = new LinkedList<>(alternativePath);

        dataPart.setPath(new Path(currentPath, packet.getPath().getSum()));
    }

    /**
     * Changes Path for currently forwarded packet
     *
     * @param packet Packet whose path is to be changed
     * @see Path
     * @see IPacket
     */
    public void changePath(IPacket packet) {
        String destinationId = packet.getDestination().getId();
        Node destinationNode = getNodeById(destinationId);
        int destinationIndex = indexMap.get(destinationNode);

        List<Integer> currentPath = packet.getPath().getPath();
        Node position = packet.getPosition();
        List<Integer> alternativePath = position.getPathsTo(destinationIndex).get(1).getPath();
        Collections.copy(currentPath, alternativePath);
    }

    /**
     * Method forwarding packet from one Node to another
     *
     * @param packet  Packet to be forwarded in current step
     * @param packets List of packets containing currently forwarded packet
     */
    public void forwardPacket(IPacket packet, List<IPacket> packets) {
        // When this method is called, we already know, this edge will not fail

        int currentIndex = packet.getPath().getCurrentNodeIndexInPath();
        int nextIndex = packet.getPath().getNextNodeIndex();
        Edge traversingEdge = matrix[currentIndex][nextIndex];
        packet.setPosition(traversingEdge.getNode2());
        if (packet.getPosition().getPaths().isEmpty()) {
            System.out.println();
        }
        // check if packet is in destination
        if (packet.getDestination().equals(packet.getPosition())) {
            System.out.println("Packet: " + packet + " is successfully forwarded to its destination!");
//            dataRequests.remove(packet);
            packets.remove(packet);
        }


    }

}
