

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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

    public String getNodeIdFromKey(int key) {
        for (String o : indexMap.keySet()) {
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

    // TODO modify to traverse based on bandwidth - higher = better
    // works, not as wished
    public void dfs(int source, int destination, boolean[] visited) {
        if (source == destination) {
            System.out.println(destination);
            return;
        }
        if (!visited[source]) {
            visited[source] = true;
            System.out.print(source + " ");

            for (int i = 0; i < matrix[source].length; i++) {
                if (matrix[source][i] != null && !visited[i]) {
                    dfs(i, destination, visited);
                    return;
                }
            }
        }
    }

    // does something reasonable
//    public void dfs(int source, int destination) {
//        Stack<Integer> stack = new Stack<>();
//        int v, i;
//        int[] isVisited = new int[matrix[0].length];
//
//        stack.push(source);
//        while (!stack.isEmpty()) {
//            v = stack.pop();
//            if (isVisited[v] == 0) {
//                System.out.print("\n" + (getNodeIdFromKey(v)));
//                isVisited[v] = 1;
//            }
//            for (i = 0; i < destination; i++) {
//                if (matrix[v][i] != null && isVisited[i] == 0) {
//                    stack.push(v);
//                    isVisited[i] = 1;
//                    System.out.println(" " + (getNodeIdFromKey(i)));
//                    v = i;
//                }
//            }
//        }
//    }

    public boolean edgeExists(String nodeId1, String nodeId2) {
        if (matrix[indexMap.get(nodeId1)][indexMap.get(nodeId2)] != null)
            return true;
        return false;
    }

//    public void dfs(Node start, Node end, ArrayList<Node> visited) {
//        Node current;
//        visited.add(start);
//        for (int i = 0; i < matrix[0].length; i++) {
//            if (matrix[indexMap.get(start.getId())][i] != null) {
//                current = new Node((String) indexMap.keySet().toArray()[i]);
//                if (indexMap.get(start.getId()).equals(indexMap.get(end.getId()))) {
//                    printPath(visited);
//                    if (matrix[indexMap.get(visited.get(0).getId())]
//                            [indexMap.get(visited.get(1).getId())] != null) {
//                        double totalBandwidth = matrix[indexMap.get(visited.get(0).getId())]
//                                [indexMap.get(visited.get(1).getId())].getBandwidth();
//                        for (int j = 0; j < visited.size() - 1; j++) {
//                            totalBandwidth += matrix[indexMap.get(visited.get(j).getId())]
//                                    [indexMap.get(visited.get(j + 1).getId())].getBandwidth();
//                        }
//                        System.out.println("Total bandwidth of path: " + totalBandwidth);
//                        visited.remove(start);
//                        return;
//                    }
//                }
//                if (!(visited.contains(current))) {
//                    dfs(current, end, visited);
//                }
//            }
//        }
//        visited.remove(start);
//    }

    // TODO just a test
    public void sendDataPackets(IPacket data) {
        Node nextJump = data.getPath().pop();
        int fromIndex = indexMap.get(data.getPosition().getId());
        int toIndex = indexMap.get(nextJump.getId());
        Edge line = matrix[fromIndex][toIndex];
        line.setLoadForNextStep(data);
        if (!line.canFail()) {
            System.out.println("Line is not too loaded, packet can get through");
            data.setPosition(nextJump);
            if (data.getPosition().equals(data.getDestination()))
                System.out.println("Data is in its destination!");
        } else {
            System.out.println("Line is too loaded. Aborting... ");
        }
    }

}
