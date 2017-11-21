

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
    private HashMap<Integer, Data> dataRequests;

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
    public void setDataRequests(HashMap<Integer, Data> dataRequests) {
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
        int minTimeStep = Collections.min(dataRequests.keySet());
        int maxKeyStep = Collections.max(dataRequests.keySet());
//        System.out.println("min: "+minTimeStep);
//        System.out.println("max: "+maxKeyStep);

        Data data = dataRequests.get(minTimeStep);
        System.out.println(data.getPosition());
        data.setPath(
                data.getPosition()
                        .getPathsTo(indexMap.get(data.getDestination()))
                        .get(0)
        );
        int currentIndex = indexMap.get(data.getPosition());
        int nextIndex = data.getPath().getNextIndex();
        Edge edge = matrix[currentIndex][nextIndex];
        System.out.println(edge);
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
    public void forwardPacket() {
        // When this method is called, we already know, this edge will not fail
        Data data = new Data(300, getNodeFromKey(0), getNodeFromKey(3));
        System.out.println(data);
        data.setPath(data.getPosition()
                .getPathsTo(indexMap.get(data.getDestination()))
                .get(0)
        );

        int currentIndex = indexMap.get(data.getPosition());
        int nextIndex = data.getPath().getNextIndex();
        Edge edge = matrix[currentIndex][nextIndex];
        System.out.println(edge);
        data.setPosition(edge.getNode2());
        System.out.println(data);

    }

}
