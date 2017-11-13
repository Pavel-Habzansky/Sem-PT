import java.util.HashMap;

/**
 * Created by PavelHabzansky on 13.11.17.
 */
public class Graph {

    private Edge[][] matrix;
    private HashMap<String, Integer> indexMap;

    public Graph(Edge[][] matrix, HashMap<String, Integer> indexMap) {
        this.matrix = matrix;
        this.indexMap = indexMap;
    }

    public void addEdge(Edge edge) {
        Node node1 = edge.getNode1();
        Node node2 = edge.getNode2();
        Edge symetricEdge = new Edge(node2, node1, edge.getBandwidth(), edge.getErrorChance());
        int indexNode1 = indexMap.get(node1.getId());
        int indexNode2 = indexMap.get(node2.getId());
        matrix[indexNode1][indexNode2] = edge;
        matrix[indexNode2][indexNode1] = symetricEdge;
    }

}
