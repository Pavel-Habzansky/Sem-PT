import java.util.HashMap;

/**
 * Created by PavelHabzansky on 13.11.17.
 */
public class Graph {

    private static Graph INSTANCE;

    private Edge[][] matrix;
    private HashMap<String, Integer> indexMap;

    private Graph(HashMap<String, Integer> indexMap, Edge[][] matrix) {
        this.indexMap = indexMap;
        this.matrix = matrix;
    }

    public static Graph getInstance(HashMap<String, Integer> indexMap, Edge[][] matrix) {
        if (INSTANCE == null)
            INSTANCE = new Graph(indexMap, matrix);
        return INSTANCE;
    }

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

}
