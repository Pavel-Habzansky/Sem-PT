

import java.util.*;

/**
 * Created by PavelHabzansky on 13.11.17.
 *
 * @author PavelHabzansky
 *         <p>
 *         Graph class created as Singleton.
 *         This class is representation of graph using adjacency matrix of Edges
 * @see Edge
 */

public class Graph {

    /**
     * Instance of this class
     */
    private static Graph INSTANCE;

    /**
     * Matrix representation of Graph
     */
    private Edge[][] matrix;
    /**
     * Map of indices.
     * Each Node (key) has its index in matrix stored in this map as value
     */
    private HashMap<Node, Integer> indexMap;
    /**
     * Map of data requests where Data is stored as value and index of destination as key
     */
    private ArrayList<IPacket> dataRequests;

    /**
     * Private constructor of this Graph, returns instance of this class
     *
     * @param indexMap Map of indices for matrix
     * @param matrix   Adjacency matrix of Edges
     */
    private Graph(HashMap<Node, Integer> indexMap, Edge[][] matrix) {
        this.indexMap = indexMap;
        this.matrix = matrix;
    }

    /**
     * Returns instance of this Graph
     *
     * @param indexMap Map of indices for matrix
     * @param matrix   Adjacency matrix of Edges
     * @return Instance of this Graph
     */
    public static Graph getInstance(HashMap<Node, Integer> indexMap, Edge[][] matrix) {
        if (INSTANCE == null)
            INSTANCE = new Graph(indexMap, matrix);
        return INSTANCE;
    }

    /**
     * Sets data requests for this Graph
     *
     * @param dataRequests Map of data requests where keys are indices of destination Nodes and values are Data packets
     */
    public void setDataRequests(ArrayList<IPacket> dataRequests) {
        this.dataRequests = dataRequests;
    }

    /**
     * Prints all Edges in this matrix
     */
    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != null)
                    System.out.print(matrix[i][j].getBandwidth() + " ");
                else
                    System.out.print("null ");
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
    public void printPath(ArrayList<Node> path) {
        for (int i = 0; i < path.size(); i++)
            System.out.print(path.get(i).getId());
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
                if (i == j)
                    continue;
                dfs(i, j, new ArrayList<>(), i);
            }
        }
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
    public void dfs(int source, int destination, ArrayList<Integer> visited, final int sourceFinal) {
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
                    for (Integer node : visited)
                        indexPath.add(node);
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
     * Method manipulating sending Data requests
     */
    public void sendDataPackets() {

        // pseudokod...
        /**
         * list <ipacket> packety = new list;
         *  //pruchod pres timesteps
         * for (int i = 0; i < dataReq.size; i++) {
         *    // pokud data na pozici j ma timestep == i, pridej ho do packetu
         *      for(in j = 0; j < dataReq.size; j++) {
         *          if(dataReq.get(j).getTimestep == i)
         *              packety.add(dataReq.get(j));
         *      }
         *
         *      // vsechno co je v seznamu packetu forwarduj o krok dal
         *      for(int j = 0; j < packety.size; j++) {
         *          spocitat load na hrane
         *          .
         *          .
         *          .
         *          .
         *          forwardPacket(packety.get(j));
         *      }
         *
         * }
         */


        ArrayList<IPacket> packets = new ArrayList<>();
        System.out.println("start");
//        System.out.println(dataRequests.size());
//        for (int i = 0; i < dataRequests.size(); i++)
        for (int i = 0; !dataRequests.isEmpty(); i++){
//            System.out.println(dataRequests.get(i));

            System.out.println("outer for");
            for (int j = 0; j < dataRequests.size(); j++) {
                if (dataRequests.get(j).getTimestep() == i) {
                    packets.add(dataRequests.get(j));
//                    dataRequests.remove(j);
                    System.out.println("SIZE "+dataRequests.size());
                }
            }

            for (int j = 0; j < packets.size(); j++) {
                // set loads for edges
                // dividing into DataParts
                // add DataParts into packets List

                System.out.println("Packet acquired");
                IPacket packet = packets.get(j);
                System.out.println(packet);
                System.out.println("=======");
                if (packet.getPath() == null) {
                    System.out.println("Path is null");
                    Node positionNode = packet.getPosition();
                    Node destinationNode = packet.getDestination();
                    LinkedList<Integer> destinationPath = positionNode
                            .getPathsTo(indexMap.get(destinationNode))
                            .get(0)
                            .getPath();
                    double sum = positionNode
                            .getPathsTo(indexMap.get(destinationNode))
                            .get(0)
                            .getSum();
                    LinkedList<Integer> linkedPath = new LinkedList<>(destinationPath);
                    Path path = new Path(linkedPath, sum);
                    packet.setPath(path);
                }
//                Node positionNode = packet.getPosition();
//                Node destinationNode = packet.getDestination();
//                LinkedList<Integer> destinationPath = positionNode
//                        .getPathsTo(indexMap.get(destinationNode))
//                        .get(0)
//                        .getPath();
//                double sum = positionNode
//                        .getPathsTo(indexMap.get(destinationNode))
//                        .get(0)
//                        .getSum();
//                LinkedList<Integer> linkedPath = new LinkedList<>(destinationPath);
//                Path path = new Path(linkedPath, sum);
//                packet.setPath(path);

                int currentNode = indexMap.get(packet.getPosition());
//                System.out.println(packet.getPath());
                int nextNode = packet.getPath().getNextIndex();

                System.out.println("CURRENT NODE "+currentNode);
                System.out.println("NEXT NODE "+nextNode);
                Edge nextEdge = matrix[currentNode][nextNode];
                Edge nextEdgeSymetric = matrix[nextNode][currentNode];

                if (!nextEdge.canFail(packet.getSize())) {
                    // hrana v dalsim kroku neselze
                    nextEdge.setLoadForNextStep(packet.getSize());
                    nextEdgeSymetric.setLoadForNextStep(packet.getSize());

                } else if (!nextEdge.canFail(packet.getSize() / 2)) {
                    // hrana neselze po rozdeleni dat na dve casti
                    DataPart dataPart = new DataPart(
                            packet.getSize() / 2,
                            packet,
                            packet.getPosition(),
                            packet.getTimestep() + 1);
                    packet.setSize(
                            packet.getSize() / 2
                    );
//                    System.out.println("DEBUG "+dataPart.getPosition().getPathsTo(indexMap.get(dataPart.getDestination())));
//                    System.out.println("DEBUG position "+dataPart.getPosition());
//                    System.out.println("DEBUG destination "+dataPart.getDestination());
//                    System.out.println(dataPart.getPosition().equals(dataPart.getDestination()));
                    // set path !! copy it !!
                    System.out.println(dataPart.getPath());
//                    LinkedList<Integer> currentPath = dataPart.getPath().getPath();

                    LinkedList<Integer> parentPath = packet.getPath().getPath();
                    LinkedList<Integer> pathForSegment = new LinkedList<>(parentPath);


//                    Collections.copy(pathForSegment, parentPath);
                    dataPart.setPath(new Path(pathForSegment, packet.getPath().getSum()));

//                    dataPart.setPath(dataPart.getPosition()
//                            .getPathsTo(indexMap.get(dataPart.getDestination()))
//                            .get(0));
                    dataRequests.add(dataPart);

                } else {
                    // hrana i tak selze, dej packetu jinou cestu
                    // !!! dej mu kopii, ne primo referenci !!!
                    String destinationId = packet.getDestination().getId();
                    Node destinationNode = getNodeById(destinationId);
                    int destinationIndex = indexMap.get(destinationNode);

                    LinkedList<Integer> currentPath = packet.getPath().getPath();
                    Node position = packet.getPosition();
                    LinkedList<Integer> alternativePath = position.getPathsTo(destinationIndex).get(1).getPath();
                    Collections.copy(currentPath, alternativePath);

//                    Path alternative = packet.getPosition().getPathsTo(destinationIndex).get(1);
//                    packet.setPath(alternative);

                }

            }

            for (int j = 0; j < packets.size(); j++) {
//                System.out.println("Forwarding packet: " + packets.get(j));
                // forwarding
                forwardPacket(packets.get(j));
            }

            // reset loads on edges before next step
            for (int j = 0; j < matrix[0].length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (matrix[j][k] != null) matrix[j][k].resetLoad();
                }
            }

        }

//        int minTimeStep = Collections.min(dataRequests.keySet());
//        int maxKeyStep = Collections.max(dataRequests.keySet());
//        System.out.println("min: "+minTimeStep);
//        System.out.println("max: "+maxKeyStep);

//        Data data = dataRequests.get(minTimeStep);
//        System.out.println(data.getPosition());
//        data.setPath(
//                data.getPosition()
//                        .getPathsTo(indexMap.get(data.getDestination()))
//                        .get(0)
//        );
//        int currentIndex = indexMap.get(data.getPosition());
//        int nextIndex = data.getPath().getNextIndex();
//        Edge edge = matrix[currentIndex][nextIndex];
//        System.out.println(edge);
        // zjist jestli hrana selze
        // pokud ano, spocitej, jestli selze po rozdeleni packetu na dve polovicni casti
        // pokud ano, najdi celkove jinou cestu
        // pokud hrana na zacatku neselze, posli packet
        // pokud hrana neselze po rozdeleni, rozdel, forwarduj jednu polovinu, druhou zarad do requestu a dej do smart stacku na current node
//        for (int i = minTimeStep; i < maxKeyStep; i++) {
//            Data data;
//            if ((data = dataRequests.get(i)) != null) {
//                int currentIndex = indexMap.get(data.getPosition());
//                int nextIndex = data.getPath().getNextIndex();
//                Edge edge = matrix[currentIndex][nextIndex];
//            }
//        }

    }

    /**
     * Method forwarding packet from one Node to another
     */
    public void forwardPacket(IPacket packet) {
        // When this method is called, we already know, this edge will not fail
        int currentIndex = indexMap.get(packet.getPosition());
        int nextIndex = packet.getPath().getNextIndex();
        Edge traversingEdge = matrix[currentIndex][nextIndex];
        packet.setPosition(traversingEdge.getNode2());
        // check if packet is in destination
        System.out.println(packet);
        System.out.println("=====");
//        System.out.println("CHecking in forwarding");
//        System.out.println("Position in forwarding "+packet.getPosition());
//        System.out.println("Destination in forwarding "+packet.getDestination());
        if (packet.getDestination().equals(packet.getPosition())) {
            System.out.println("Packet: " + packet + " is successfully forwarded to its destination!");
            dataRequests.remove(packet);
        }
        packet.getPath().moveToNext();


//        Data data = new Data(300, getNodeFromKey(0), getNodeFromKey(3));
//        System.out.println(data);
//        data.setPath(data.getPosition()
//                .getPathsTo(indexMap.get(data.getDestination()))
//                .get(0)
//        );
//
//        int currentIndex = indexMap.get(data.getPosition());
//        int nextIndex = data.getPath().getNextIndex();
//        Edge edge = matrix[currentIndex][nextIndex];
//        System.out.println(edge);
//        data.setPosition(edge.getNode2());
//        System.out.println(data);

    }

}
