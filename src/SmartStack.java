/**
 * @author Habzansky, Mikes
 *         <p>
 *         SmartStack class used to store Data on Node
 */
public class SmartStack {

    /**
     * Total size that can be stored on this SmartStack
     */
    private final double SIZE = 100;
    /**
     * Array of DataParts stored in this SmartStack
     *
     * @see DataPart
     */
    private DataPart[] stackedData = new DataPart[5];
    /**
     * Index pointing to last free index
     */
    private int topElementIndex;


    /**
     * Constructor that returns instance of this SmartStack
     */
    public SmartStack() {
        this.topElementIndex = 0;
    }

    /**
     * Returns sum of stacked data
     *
     * @return Total data stored in this SmartStack
     */
    public double getStackedData() {
        double dataSum = 0;
        for (DataPart data : stackedData) {
            if (data == null) {
                continue;
            }
            dataSum += data.getSize();
        }
        return dataSum;
    }

    /**
     * Indentifies if this SmartStack is empty
     *
     * @return Empty state
     */
    public boolean isEmpty() {
        return (topElementIndex == -1);
    }


    /**
     * Dynamically increases size of this SmartStack if its stackedData is full
     */
    private void increaseSize() {
        DataPart[] increasedStackedData = new DataPart[stackedData.length * 2];
        System.arraycopy(stackedData, 0,
                increasedStackedData, 0, stackedData.length);
        this.stackedData = increasedStackedData;
    }

    /**
     * Returns top element stored in this SmartStack and deletes it
     *
     * @return Top element stored in SmartStack
     */
    public DataPart pop() {
        DataPart data = stackedData[topElementIndex--];
//        topElementIndex--;
        return data;
    }

    /**
     * Resets the whole SmartStack and sends all data back due to overflow
     */
    public void fail() {
        System.out.println("SmartStack has failed due to stack overflow!");
        for (DataPart data : stackedData) {
            if (data == null) {
                continue;
            }
            data.getParent().returnHome();
            data = null;
        }
        topElementIndex = 0;
    }

    /**
     * Pushes DataPart on top of this SmartStack
     *
     * @param data DataPart to be stored
     */
    public void push(DataPart data) {
        if (isStackFull()) {
            System.err.println(data);
            System.out.println("Stack is full. Increasing size... ");
            increaseSize();
            System.out.println("Size increased to: " + stackedData.length);
        }
        stackedData[++topElementIndex] = data;
    }

    /**
     * Checks if SmartStack is full
     *
     * @return True if SmartStack is full
     */
    private boolean isStackFull() {
        return (stackedData[stackedData.length - 1] != null);
    }

    /**
     * Returns total SIZE of this SmartStack
     *
     * @return Size of SmartStack
     */
    public double getSize() {
        return this.SIZE;
    }

    /**
     * Prints sizes of all stored DataParts
     */
    public void printStack() {
        for (int i = 0; i < stackedData.length; i++) {
            if (stackedData[i] == null) {
                return;
            }
            System.out.println("Data: " + stackedData[i].getSize());
        }
    }

}
