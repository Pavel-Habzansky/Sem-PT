import java.util.*;

/**
 * Created by PavelHabzansky on 13.11.17.
 */
public class Node {

    private String id;
    private SmartStack smartStack;
    private boolean visited;
    private ArrayList<Node> neighbors;
    private HashMap<Integer, ArrayList<Path>> paths;

    public Node(String id) {
        this.id = id;
        this.smartStack = new SmartStack();
        this.visited = false;
        this.neighbors = new ArrayList<>();
        this.paths = new HashMap<>();
    }

    private void sortPaths(int key) {
        Collections.sort(
                paths.get(key),
                Comparator.comparing(Path::getSum)
                        .reversed()
        );

    }

    public void printPathsToOthers() {
        System.out.println("All paths from "+id);
        for (Integer key : paths.keySet()) {
            for (int i = 0; i < paths.size(); i++) {
                paths.get(key).get(i).printPath();
            }
        }
        System.out.println("================");
    }

    public void addPath(int destination, Path path) {
        if (!this.paths.containsKey(destination))
            this.paths.put(destination, new ArrayList<>());
        this.paths.get(destination).add(path);
        sortPaths(destination);
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public void setPaths(HashMap<Integer, ArrayList<Path>> paths) {
        this.paths = paths;
    }

    public HashMap<Integer, ArrayList<Path>> getPaths() {
        return paths;
    }

    public double getSmartStackLoad() {
        return this.smartStack.getStackedData();
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        Node other = (Node) object;
        return this.id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
