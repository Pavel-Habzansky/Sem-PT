import java.util.Queue;
import java.util.Stack;

/**
 * Created by PavelHabzansky on 14.11.17.
 */
public interface IPacket {

    double getSize();
    Node getSource();
    Node getDestination();
    Node getPosition();
    void setPosition(Node node);
    Stack<Node> getPath();


}
