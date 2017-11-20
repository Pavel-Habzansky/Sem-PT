/**
 * SmartStack class
 * @author Habzansky, Mikes
 *
 */
public class SmartStack {
/**
 * attributes of SmartStack class
 */
    private final int SIZE = 100;
    private DataPart[] stackedData = new DataPart[5];
    private int topElementIndex;

   
 
    /**
     * constructor of SmartStack class 
     */
    public SmartStack() {
        this.topElementIndex = 0;
    }

    /**
     * the method getStackedData() return data from stack
     * @return dataSum
     */
    public double getStackedData() {
        double dataSum = 0;
        for (DataPart data : stackedData)
            dataSum += data.getSize();
        return dataSum;
    }
/**
 * the method isEmpty find out if the stack is empty
 * @return 0
 */
    public boolean isEmpty() {
        return (topElementIndex == 0);
    }

    
    /**
     * the method increaseSize  increase the length of data
     */
    public void increaseSize() {
        DataPart[] increasedStackedData = new DataPart[stackedData.length * 2];
        System.arraycopy(stackedData, 0,
                increasedStackedData, 0, stackedData.length);
        this.stackedData = increasedStackedData;
    }

    /**
     * the method pop() pop data from top of stack
     * @return data
     */
    public DataPart pop() {
        DataPart data = stackedData[topElementIndex];
        topElementIndex--;
        return data;
    }

    /**
     * the method push with attribute data push data to stack
     * @param data
     */
    public void push(DataPart data) {
        if (isStackFull()) {
            System.out.println("Stack is full. Increasing size... ");
            increaseSize();
            System.out.println("Size increased to: " + stackedData.length);
        }
        stackedData[topElementIndex] = data;
        topElementIndex++;
    }

    /**
     * the method isStackFull find out if the stack is full
     * @return
     */
    public boolean isStackFull() {
        if (stackedData[stackedData.length - 1] != null)
            return true;
        return false;
    }

    /**
     * the method getSize() return size of stack;
     * @return size
     */
    public int getSize() {
        return this.SIZE;
    }

    /**
     * the method printStack() print data from stack
     */
    public void printStack() {
        for (int i = 0; i < stackedData.length; i++) {
            if (stackedData[i] == null)
                return;
            System.out.println("Data: " + stackedData[i].getSize());
        }
    }

}
