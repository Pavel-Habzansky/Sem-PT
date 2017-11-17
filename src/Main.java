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

    public static HashMap<String, Integer> generateIndexMap(ArrayList<Edge> edges) {
        HashMap<String, Integer> indexMap = new HashMap<>();
        int mapKeyIndexing = 0;
        for (int i = 0; i < edges.size(); i++) {
            Node node1 = edges.get(i).getNode1();
            if (!indexMap.containsKey(node1.getId())) {
                indexMap.put(node1.getId(), mapKeyIndexing);
                mapKeyIndexing++;
            }
            Node node2 = edges.get(i).getNode2();
            if (!indexMap.containsKey(node2.getId())) {
                indexMap.put(node2.getId(), mapKeyIndexing);
                mapKeyIndexing++;
            }
        }
        return indexMap;
    }

    public static Edge[][] generateMatrix(HashMap<String, Integer> indexMap,
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

            String node1Id = edge.getNode1().getId();
            String node2Id = edge.getNode2().getId();
            int node1Index = indexMap.get(node1Id);
            int node2Index = indexMap.get(node2Id);

            matrix[node1Index][node2Index] = edge;
            matrix[node2Index][node1Index] = symetrixReverseEdge;
        }

        return matrix;
    }

    public static void main(String[] args) {
        ArrayList<Edge> edges = loadEdges("hrany.txt");
        HashMap<String, Integer> indexMap = generateIndexMap(edges);
        Edge[][] edgeAdjacencyMatrix = generateMatrix(indexMap, edges);

        Graph graph = Graph.getInstance(indexMap, edgeAdjacencyMatrix);
        System.out.println(edgeAdjacencyMatrix[0].length);
        graph.printMatrix();

        boolean[] visited = new boolean[edgeAdjacencyMatrix[0].length];
        graph.dfs(2,5,visited);

//        graph.dfs(1,6);

//        System.out.println(graph.edgeExists("id3", "id4"));

//        graph.dfs(edgeAdjacencyMatrix[0][5].getNode1(), edgeAdjacencyMatrix[0][5].getNode2(), new ArrayList<>());

//
//        Stack<Node> stack = new Stack<>();
//        stack.push(new Node("destination"));
//        Data data = new Data(1200, new Node("source"), new Node("destination"), stack);



//        graph.dfs(new Node("id1"), new Node("id3"), new ArrayList<Node>());

//        Edge edge = new Edge(new Node("1"), new Node("2"),100, 0.2);
//        Data data = new Data(20, new Node("1"), new Node("2"), new Stack<>());
//        edge.setLoadForNextStep(data);
//        System.out.println(edge.getLoad());
//        System.out.println(edge.canFail());
    }
}