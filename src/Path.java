import java.util.LinkedList;

public class Path {

    public LinkedList<Integer> path;
    public Double sum;


    public Path(LinkedList<Integer> path, double sum) {
        this.path = path;
        this.sum = sum;
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
