

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by PavelHabzansky on 13.11.17.
 */
public class Graph {

    private static Graph INSTANCE;

    private Edge[][] matrix;
    private HashMap<Node, Integer> indexMap;

    private Graph(HashMap<Node, Integer> indexMap, Edge[][] matrix) {
        this.indexMap = indexMap;
        this.matrix = matrix;
    }

    public static Graph getInstance(HashMap<Node, Integer> indexMap, Edge[][] matrix) {
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

    public Node getNodeFromKey(int key) {
        for (Node o : indexMap.keySet()) {
            if (indexMap.get(o).equals(key)) {
                return o;
            }
        }
        return null;
    }

    public void printPath(ArrayList<Node> path) {
        for (int i = 0; i < path.size(); i++)
            System.out.print(path.get(i).getId());
        System.out.println();
    }

    public void initPaths() {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == j)
                    continue;
                dfs(i, j, new ArrayList<>(),i);
            }
        }
    }

    public int getSize() {
        return matrix[0].length;
    }


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

    public boolean edgeExists(String nodeId1, String nodeId2) {
        if (matrix[indexMap.get(new Node(nodeId1))][indexMap.get(new Node(nodeId2))] != null)
            return true;
        return false;
    }

    // TODO refactor for path and test functionality
    public void sendDataPackets(IPacket data) {
//        Node nextJump = data.getPath().pop();
//        int fromIndex = indexMap.get(data.getPosition().getId());
//        int toIndex = indexMap.get(nextJump.getId());
//        Edge line = matrix[fromIndex][toIndex];
//        line.setLoadForNextStep(data);
//        if (!line.canFail()) {
//            System.out.println("Line is not too loaded, packet can get through");
//            data.setPosition(nextJump);
//            if (data.getPosition().equals(data.getDestination()))
//                System.out.println("Data is in its destination!");
//        } else {
//            System.out.println("Line is too loaded. Aborting... ");
//        }
    }

}
