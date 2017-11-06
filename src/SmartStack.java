/**
 * Created by Jakub Mikeš on 29.10.17.
 */
public class SmartStack {

    private final int SIZE = 100;
    private Data[] stackedData = new Data[5];

    private int topElementIndex;

    public SmartStack() {
        this.topElementIndex = 0;
    }

    public double getStackedData() {
        double dataSum = 0;
        for (Data data : stackedData)
            dataSum += data.getDataSize();
        return dataSum;
    }

    public boolean isEmpty() {
        return (topElementIndex == 0);
    }

    public void increaseSize() {
        Data[] increasedStackedData = new Data[stackedData.length*2];
        System.arraycopy(stackedData, 0,
                increasedStackedData, 0, stackedData.length);
        this.stackedData = increasedStackedData;
    }

    public Data pop() {
        Data data = stackedData[topElementIndex];
        topElementIndex--;
        return data;
    }

    public void push(Data data) {
        if (isStackFull()){
            System.out.println("Stack is full. Increasing size... ");
            increaseSize();
            System.out.println("Size increased to: "+stackedData.length);
        }
        stackedData[topElementIndex] = data;
        topElementIndex++;
    }

    public boolean isStackFull() {
        if (stackedData[stackedData.length-1] != null){
//            System.out.println("Stack is full");
            return true;
        }
        return false;
    }

    public int getSize() {
        return this.SIZE;
    }

    public void printStack() {
        for (int i = 0; i < stackedData.length; i++) {
            if (stackedData[i] == null)
                return;
            System.out.println("Data: "+stackedData[i].getDataSize());
        }
    }

}
