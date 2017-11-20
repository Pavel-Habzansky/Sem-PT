import java.util.*;

/**
 * Node class
 * @author Habzansky, Mikes
 *
 */
public class Node {
/**
 * attributes of Node class
 */
    private String id;
    private SmartStack smartStack;
    private boolean visited;
    private ArrayList<Node> neighbors;
    private HashMap<Integer, ArrayList<Path>> paths;

    /**
     * constructor of Node class
     * @param id
     */
    public Node(String id) {
        this.id = id;
        this.smartStack = new SmartStack();
        this.visited = false;
        this.neighbors = new ArrayList<>();
        this.paths = new HashMap<>();
    }

    /**
     * the method sortPaths sort paths using the key
     * @param key
     */
    private void sortPaths(int key) {
        Collections.sort(
                paths.get(key),
                Comparator.comparing(Path::getSum)
                        .reversed()
        );

    }

    /**
     * the method printPathToOthers print other paths
     */
    public void printPathsToOthers() {
        System.out.println("All paths from "+id);
        for (Integer key : paths.keySet()) {
            for (int i = 0; i < paths.size(); i++) {
                paths.get(key).get(i).printPath();
            }
        }
        System.out.println("================");
    }

    /**
     * the method addPath add new path
     * @param destination
     * @param path
     */
    public void addPath(int destination, Path path) {
        if (!this.paths.containsKey(destination))
            this.paths.put(destination, new ArrayList<>());
        this.paths.get(destination).add(path);
        sortPaths(destination);
    }

    /**
     * the method getNeighbors return neighbors of node
     * @return neighbors
     */
    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    /**
     * the method setPaths set new path
     * @param paths
     */
    public void setPaths(HashMap<Integer, ArrayList<Path>> paths) {
        this.paths = paths;
    }

    /**
     * the method getPaths return path 
     * @return
     */
    public HashMap<Integer, ArrayList<Path>> getPaths() {
        return paths;
    }

    /**
     * the method getSmartStackLoad return stacked data
     * @return
     */
    public double getSmartStackLoad() {
        return this.smartStack.getStackedData();
    }

    /**
     * the method geVisited find out if node was visited
     * @return visited
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * the method setVisited attribute visited
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * the method getIde return id of node
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * the method equals compare nodes
     * @return true or false
     */
    @Override
    public boolean equals(Object object) {
        Node other = (Node) object;
        return this.id.equals(other.getId());
    }

    /**
     * the method hashCode return hash code if of node
     * @return hash code of id
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
