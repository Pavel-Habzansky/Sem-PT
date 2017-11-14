/**
 * Created by PavelHabzansky on 13.11.17.
 */
public class Node {

    private String id;

    public Node(String id) {
        this.id = id;
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
