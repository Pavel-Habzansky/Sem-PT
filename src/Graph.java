//
//import java.util.ArrayList;
//
///**
// * Created by PavelHabzansky on 26.10.17.
// */
//public class Graph {
//
//    private static Graph INSTANCE;
//
//    private ArrayList<Node> nodes;
//    private ArrayList<Edge> edges;
//
//    public static Graph getInstance() {
//        if (INSTANCE == null)
//            INSTANCE = new Graph();
//        return INSTANCE;
//    }
//
//    public void addNode(Node node) {
//        nodes.add(node);
//    }
//
//    public Node findNode(String nodeId) {
//        for(Node each : nodes) {
//            if (each.getId().compareTo(nodeId) == 0) {
//                return each;
//            }
//        }
//        return null;
//    }
//
//    public void addEdge(String fromId, String toId, double bandwidth, double errorChance) {
//        Edge newEdge = new Edge(fromId, toId, bandwidth, errorChance);
//        edges.add(newEdge);
////        nodes.add(new Node(fromId));
////        nodes.add(new Node(toId));
//    }
//
//    public Edge findEdge(Node from, Node to) {
//        for (Edge each : edges) {
//            if (each.getFrom().getId().equals(from.getId()) && each.getTo().getId().equals(to.getId())) {
//                return each;
//            }
//        }
//        return null;
//    }
//
//    public void printGraph() {
//        for (Edge each : edges) {
//            System.out.println(each);
//        }
//    }
//
//    public void send(Data data) {
//        Node current = data.getSource();
//        for (int i = 1; current != data.getDestination(); i++) {
//            sleepSecond();
//            Node next = data.getCesta()[i];
//            Edge edge = findEdge(current, next);
//            double bandwidth = edge.getBandwidth();
//        }
//    }
//
////    public void send(Data data) {
////        if (data.getSource()
////                .getSuccessors()
////                .contains(data.getDestination())){
////            data.getDestination()
////                    .getData()
////                    .add(data);
////        } else {
////            Node current = data.getSource();
////            for (int i = 1; current != data.getDestination(); i++) {
////                sleepSecond();
////                Node next = data.getCesta()[i];
////                Edge edge = findEdge(current, next);
////                double bandwidth = edge.getBandwidth();
////                if (data.getDataSize() > bandwidth){
////
////                    //TODO stored data vs stacked data
////
//////                    double toBeStacked = data.getDataSize() - bandwidth;
//////                    current.getSmartStack().push(new Data(toBeStacked, current, data.getDestination()));
//////
//////                    while (!current.getSmartStack().isEmpty()) {
//////                        Data rest = current.getSmartStack().pop();
//////                        send(rest);
//////                    }
////
////                }
////
////                current = data.getCesta()[i];
////            }
////        }
////        System.out.println("finish");
////    }
//
//    public void sleepSecond() {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
//
//    private Graph(){
//        nodes = new ArrayList<>();
//        edges = new ArrayList<>();
//    }
//
//}
