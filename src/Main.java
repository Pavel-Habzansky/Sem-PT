
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    /**
     * Returns Node from List of Nodes if it contains that Node
     *
     * @param nodes List of Nodes
     * @param node  Node being searched for
     * @return Node from List of Nodes
     * @see Node
     */
    private static Node getNode(List<Node> nodes, Node node) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getId().equals(node.getId())) {
                return nodes.get(i);
            }
        }
        return null;
    }

    /**
     * Reads specific file with Edges and returns List of those Edges
     *
     * @param filename File name of source file
     * @return List of Edges
     * @see Edge
     */
    public static List<Edge> loadEdges(String filename) {
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                Node node1 = new Node(split[0]);
                if (!nodes.contains(node1)) {
                    // uzel v seznamu neni, pridej ho
                    nodes.add(node1);
                } else {
                    // uzel v seznamu je, vyber ho z nej  prirad ho node1
                    node1 = getNode(nodes, node1);
                }
                Node node2 = new Node(split[1]);
                if (!nodes.contains(node2)) {
                    nodes.add(node2);
                } else {
                    node2 = getNode(nodes, node2);
                }
                double bandwidth = Double.parseDouble(split[2]);
                double errorChance = Double.parseDouble(split[3]);
                edges.add(new Edge(node1, node2, bandwidth, errorChance));

            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return edges;
    }

    /**
     * Generated Map of indices for adjacency matrix
     *
     * @param edges List of Edges
     * @return Map with Nodes as keys and indices as values
     * @see Edge
     */
    public static Map<Node, Integer> generateIndexMap(List<Edge> edges) {
        HashMap<Node, Integer> indexMap = new HashMap<>();
        int mapKeyIndexing = 0;
        for (int i = 0; i < edges.size(); i++) {
            Node node1 = edges.get(i).getNode1();
            if (!indexMap.containsKey(node1)) {
                indexMap.put(node1, mapKeyIndexing);
                mapKeyIndexing++;
            }
            Node node2 = edges.get(i).getNode2();
            if (!indexMap.containsKey(node2)) {
                indexMap.put(node2, mapKeyIndexing);
                mapKeyIndexing++;
            }
        }
        return indexMap;
    }

    /**
     * Generater adjacency matrix of edges from Nodes and their indices in index map
     * and List of Edge
     *
     * @param indexMap Map with Nodes as keys and indices as values
     * @param edges    List of edges for matrix
     * @return Adjacency matrix
     * @see Edge
     * @see Node
     */
    public static Edge[][] generateMatrix(Map<Node, Integer> indexMap,
                                          List<Edge> edges) {
        Edge[][] matrix = new Edge[indexMap.size()][indexMap.size()];

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            Edge symetrixReverseEdge = new Edge(
                    edge.getNode2(),
                    edge.getNode1(),
                    edge.getBandwidth(),
                    edge.getErrorChance());

            Node node1 = edge.getNode1();
            Node node2 = edge.getNode2();
            int node1Index = indexMap.get(node1);
            int node2Index = indexMap.get(node2);

            matrix[node1Index][node2Index] = edge;
            matrix[node2Index][node1Index] = symetrixReverseEdge;
        }

        return matrix;
    }

    /**
     * Loads data packets into data requests List
     *
     * @param source Source file with data requests
     * @param graph  Graph which is to be loaded with data
     */
    public static void loadData(String source, Graph graph) {
        ArrayList<IPacket> dataRequests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                int timeStep = Integer.parseInt(split[0]);

                Node sourceNode = graph.getNodeById(split[1]);
                Node destinationNode = graph.getNodeById(split[2]);
                double dataSize = Double.parseDouble(split[3]);
                Data loadedData = new Data(dataSize, sourceNode, destinationNode, timeStep);
                dataRequests.add(loadedData);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        graph.setDataRequests(dataRequests);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Nebyly zadány žádné vstupní soubory!");
            return;
        }
        List<Edge> edges = loadEdges(args[0]);
        Map<Node, Integer> indexMap = generateIndexMap(edges);
        Edge[][] edgeAdjacencyMatrix = generateMatrix(indexMap, edges);

        Graph graph = Graph.getInstance(indexMap, edgeAdjacencyMatrix);
        graph.printMatrix();
        loadData(args[1], graph);

        graph.initPaths();

        graph.sendDataPackets();


    }
}