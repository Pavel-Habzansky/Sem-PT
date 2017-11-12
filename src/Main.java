

import java.io.*;

/**
 * Created by PavelHabzansky on 11.10.17.
 */

// Thread.sleep(1000) can be used for simulation
public class Main {

    public static void loadGraph(UndirectedGraph graph, String filename) {
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                Node node1 = new Node(split[0]);
                Node node2 = new Node(split[1]);
                double bandwidth = Double.parseDouble(split[2]);
                double errorChance = Double.parseDouble(split[3]);
                graph.addEdge(node1,
                        node2,
                        bandwidth,
                        errorChance);
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        /**
         * Test of loadGraph
         */
        UndirectedGraph graph = UndirectedGraph.getInstance();
        loadGraph(graph, "hrany.txt");
        graph.printGraph();

    }

}
