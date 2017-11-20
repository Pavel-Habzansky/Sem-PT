import java.util.LinkedList;

/**
 * @author Jakub Mikeš
 */

public class Path {

    private LinkedList<Integer> path;
    private Double sum;
    private int currentIndexInPath;

    public Path(LinkedList<Integer> path, double sum) {
        this.path = path;
        this.sum = sum;
        this.currentIndexInPath = path.get(0);
    }

    public int getNextIndex() {
        return currentIndexInPath+1;
    }

    public void moveToNext() {
        currentIndexInPath++;
    }

    public int getCurrentIndexInPath() {
        return currentIndexInPath;
    }

    public void printPath() {
        System.out.println("Path from "+path.get(0)+" to "+path.get(path.size()-1));
        for (Integer index : path)
            System.out.print(index + " ");
        System.out.println("\nSum of bandwidths: "+sum);
    }

    public LinkedList<Integer> getPath() {
        return path;
    }

    public void setPath(LinkedList<Integer> path) {
        this.path = path;
    }

    public Double getSum() {
        return sum;
    }
}
