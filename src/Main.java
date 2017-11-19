import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static ArrayList<Edge> loadEdges(String filename) {
        ArrayList<Edge> edges = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                Node node1 = new Node(split[0]);
                Node node2 = new Node(split[1]);
                double bandwidth = Double.parseDouble(split[2]);
                double errorChance = Double.parseDouble(split[3]);
                edges.add(new Edge(node1, node2, bandwidth, errorChance));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return edges;
    }

    public static HashMap<Node, Integer> generateIndexMap(ArrayList<Edge> edges) {
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

    public static Edge[][] generateMatrix(HashMap<Node, Integer> indexMap,
                                          ArrayList<Edge> edges) {
        Edge[][] matrix = new Edge[indexMap.size()][indexMap.size()];

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            Edge symetrixReverseEdge = new Edge(
                    edge.getNode2(),
                    edge.getNode1(),
                    edge.getBandwidth(),
                    edge.getErrorChance());

            edge.getNode1().getNeighbors().add(edge.getNode2());
            edge.getNode2().getNeighbors().add(edge.getNode1());

            Node node1 = edge.getNode1();
            Node node2 = edge.getNode2();
            int node1Index = indexMap.get(node1);
            int node2Index = indexMap.get(node2);

            matrix[node1Index][node2Index] = edge;
            matrix[node2Index][node1Index] = symetrixReverseEdge;
        }

        return matrix;
    }

    public static void main(String[] args) {
        ArrayList<Edge> edges = loadEdges("hrany.txt");
        HashMap<Node, Integer> indexMap = generateIndexMap(edges);
        Edge[][] edgeAdjacencyMatrix = generateMatrix(indexMap, edges);

        Graph graph = Graph.getInstance(indexMap, edgeAdjacencyMatrix);
        graph.initPaths();
        graph.printMatrix();

        for (int i = 0; i < graph.getSize(); i++)
            graph.getNodeFromKey(i).printPathsToOthers();



    }
}