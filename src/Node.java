/**
 * Created by PavelHabzansky on 13.11.17.
 */
public class Node {

    private String id;
    private SmartStack smartStack;

    public Node(String id) {
        this.id = id;
        this.smartStack = new SmartStack();
    }

    public double getSmartStackLoad() {
        return this.smartStack.getStackedData();
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
