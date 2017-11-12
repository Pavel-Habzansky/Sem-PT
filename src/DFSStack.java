/**
 * Created by PavelHabzansky on 12.11.17.
 */
public class DFSStack {

    private final int INIT_SIZE = 20;
    private int[] stack;
    private int top;

    public DFSStack() {
        stack = new int[INIT_SIZE];
        this.top = 0;
    }

    public void push(int j) {
        stack[top++] = j;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("DFSStack empty");
            return 0;
        }
        return stack[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("DFSStack empty");
            return 0;
        }
        return stack[top];
    }

    public boolean isEmpty() {
        return (top == 0);
    }

}
