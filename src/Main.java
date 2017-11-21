
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    public static HashMap<Integer, Data> loadData(String source) {
        HashMap<Integer, Data> dataHashMap = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                int timeStep = Integer.parseInt(split[0]);
                Node sourceNode = new Node(split[1]);
                Node destinationNode = new Node(split[2]);
                double dataSize = Double.parseDouble(split[3]);
                Data loadedData = new Data(dataSize, sourceNode, destinationNode);
                dataHashMap.put(timeStep, loadedData);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return dataHashMap;
    }

    public static void main(String[] args) {
        ArrayList<Edge> edges = loadEdges("hrany.txt");
        HashMap<Node, Integer> indexMap = generateIndexMap(edges);
        Edge[][] edgeAdjacencyMatrix = generateMatrix(indexMap, edges);

        HashMap<Integer, Data> dataHashMap = loadData("data.txt");
//        for (Data data : dataHashMap.values())
//            System.out.println(data);

        Graph graph = Graph.getInstance(indexMap, edgeAdjacencyMatrix);
        graph.initPaths();
        graph.setDataRequests(dataHashMap);
//        graph.getNodeFromKey(1).printPathsToOthers();
//        System.out.println(graph.getNodeFromKey(1).getPaths().get(0));
//        graph.getNodeFromKey(1).printPathsToOthers();

        graph.forwardPacket();
//        graph.forwardPacket(new Data(300, new Node("id1"), new Node("id2")));


    }
}