import java.util.LinkedList;

/**
 * @author Jakub Mikeš
 *         <p>
 *         Class representing Path from source Node to destination
 */

public class Path {

    /**
     * LinkedList of Node indices for adjacency matrix
     */
    private LinkedList<Integer> path;
    /**
     * Sum of bandwidths between Nodes.
     * Sum is also used for comparing to other Paths
     */
    private Double sum;
    /**
     * Current position of Data packet
     */
    private int currentIndexInPath;

    /**
     * Constructor of Path class, returns instance of this class
     *
     * @param path LinkedList of Integers representing path from source to destination
     * @param sum  Sum of bandwidths between Nodes
     */
    public Path(LinkedList<Integer> path, double sum) {
        this.path = path;
        this.sum = sum;
        this.currentIndexInPath = 0;
    }

    /**
     * Returns index after current index
     *
     * @return Next index int path
     */
    public int getNextIndex() {
        return path.get(currentIndexInPath+1);
    }

    /**
     * Moves current index to next index
     */
    public void moveToNext() {
        currentIndexInPath++;
    }

    /**
     * Returns current Node index
     *
     * @return Current index in path
     */
    public int getCurrentIndexInPath() {
        return path.get(currentIndexInPath);
    }

    /**
     * Prints all indices in this Path and total bandwidth
     */
    public void printPath() {
        System.out.println("Path from " + path.get(0) + " to " + path.get(path.size() - 1));
        for (Integer index : path)
            System.out.print(index + " ");
        System.out.println("\nSum of bandwidths: " + sum);
    }

    /**
     * Returns LinkedList of indices of this Path
     *
     * @return LinkedList of Node indices
     */
    public LinkedList<Integer> getPath() {
        return path;
    }

    /**
     * Sets new LinkedList of indices for this Path
     *
     * @param path
     */
    public void setPath(LinkedList<Integer> path) {
        this.path = path;
    }

    /**
     * Returns sum of bandwidths between Nodes
     *
     * @return Sum of bandwidths
     */
    public Double getSum() {
        return sum;
    }
}
