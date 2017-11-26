import java.util.*;

/**
 * Node class
 *
 * @author Habzansky, Mikes
 */
public class Node {

    /**
     * Id of Node
     */
    private String id;
    /**
     * SmartStack of Node
     */
    private SmartStack smartStack;
    /**
     * Map of paths to target where Integer key is index of Node in matrix
     *
     * @see Graph
     */
    private Map<Integer, List<Path>> paths;

    /**
     * Constructor of Node class returning instance of this object
     *
     * @param id Id of Node
     */
    public Node(String id) {
        this.id = id;
        this.smartStack = new SmartStack();
        this.paths = new HashMap<>();
    }

    /**
     * Sets new id for Node
     *
     * @param id New id for Node
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets SmartStack for Node
     *
     * @param smartStack New SmartStack
     */
    public void setSmartStack(SmartStack smartStack) {
        this.smartStack = smartStack;
    }

    /**
     * Returns SmartStack on this Node
     * @see SmartStack
     * @return SmartStack on this Node
     */
    public SmartStack getSmartStack() {
        return smartStack;
    }


    /**
     * Method sorts paths of this Node descending using Comparator.comparing() method
     *
     * @param key
     * @see Comparator
     */
    private void sortPaths(int key) {
        Collections.sort(
                paths.get(key),
                Comparator.comparing(Path::getSum)
                        .reversed()
        );

    }

    /**
     * Returns List of paths leading to Node represented by index param in matrix
     *
     * @param index index of Node in matrix
     * @return List of paths to Node
     * @see Graph
     */
    public List<Path> getPathsTo(int index) {
        return paths.get(index);
    }

    /**
     * Method prints all paths to all other Nodes
     */
    public void printPathsToOthers() {
        System.out.println("All paths from " + id);
        for (Integer key : paths.keySet()) {
            for (int i = 0; i < paths.size(); i++) {
                paths.get(key).get(i).printPath();
            }
        }
        System.out.println("================");
    }

    /**
     * Method adds new Path to paths param
     *
     * @param destination Index of destination Node used as a key
     * @param path        Path to be added
     */
    public void addPath(int destination, Path path) {
        if (!this.paths.containsKey(destination)) {
            this.paths.put(destination, new ArrayList<>());
        }
        this.paths.get(destination).add(path);
        sortPaths(destination);
    }

    /**
     * Method sets paths for this Node
     *
     * @param paths Paths to be set
     */
    public void setPaths(Map<Integer, List<Path>> paths) {
        this.paths = paths;
    }

    /**
     * Method returning all Paths as a Map
     *
     * @return All Paths as a Map
     */
    public Map<Integer, List<Path>> getPaths() {
        return paths;
    }

    /**
     * Returns ammount of stored data in this Node's SmartStack
     *
     * @return Ammount of stored data in SmartStack
     * @see SmartStack
     */
    public double getSmartStackLoad() {
        return this.smartStack.getStackedData();
    }

    /**
     * Returns id of this Node
     *
     * @return Node id
     */
    public String getId() {
        return id;
    }

    /**
     * Compared this Node to another object based on its id
     *
     * @param object Object to be compared
     * @return True if thid Node's and object's id match
     */
    @Override
    public boolean equals(Object object) {
        Node other = (Node) object;
        return this.id.equals(other.getId());
    }

    /**
     * Calculated this Node's hash code based on its id
     *
     * @return Hash code of this Node's id
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Returns String representation of this Node
     *
     * @return String representation of this Node
     */
    @Override
    public String toString() {
        return "Node " + id;
    }

}
