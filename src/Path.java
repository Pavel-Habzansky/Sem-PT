import java.util.LinkedList;

public class Path {

public LinkedList<Edge> path;
public double sum;


public Path(LinkedList<Edge> path){
	this.path = path;
		makeSum();
}

public void makeSum(){
	double sum=0;
	
	for(int i=0; i<path.size();i++){
		sum +=(double) path.get(i).getBandwidth(); 
	}
	this.sum=sum;
}

public LinkedList<Edge> getPath(){
	return path;
}

public void setPath(LinkedList<Edge> path){
	this.path= path;
	makeSum();
}

public double getSum(){
	return sum;
	}
}
