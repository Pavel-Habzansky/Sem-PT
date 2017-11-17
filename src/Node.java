import java.util.ArrayList;

/**
 * Created by PavelHabzansky on 13.11.17.
 */
public class Node {

    private String id;
    private SmartStack smartStack;
    private boolean visited;
    private ArrayList<Node> neighbors;

    public Node(String id) {
        this.id = id;
        this.smartStack = new SmartStack();
        this.visited = false;
        this.neighbors = new ArrayList<>();
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
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

}
