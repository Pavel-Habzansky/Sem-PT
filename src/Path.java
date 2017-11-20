import java.util.LinkedList;
/**
 * Path class
 * @author Habzansky, Mikes
 *
 */
public class Path {
/**
 * attributes of Path class
 */
    public LinkedList<Integer> path;
    public Double sum;

    /**
     * constructor of Path class  
     * 
     * @param path
     * @param sum
 */
    public Path(LinkedList<Integer> path, double sum) {
        this.path = path;
        this.sum = sum;
    }

    
    /**
     * the method printPath() print path and sum  
     * 
     */
    public void printPath() {
        System.out.println("Path from "+path.get(0)+" to "+path.get(path.size()-1));
        for (Integer index : path)
            System.out.print(index + " ");
        System.out.println("\nSum of bandwidths: "+sum);
    }

  /**
   * the method getPath() return path 
   * @return path
   */
    public LinkedList<Integer> getPath() {
        return path;
    }

    
 /** 
 * the method setPath set path  
 * @param path
 */
    public void setPath(LinkedList<Integer> path) {
        this.path = path;
    }

/**
 * the method getSum return sum 
 * @return sum
 */
    public Double getSum() {
        return sum;
    }
}
