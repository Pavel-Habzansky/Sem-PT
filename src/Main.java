import java.io.*;

/**
 * Created by PavelHabzansky on 11.10.17.
 */
public class Main {

    public static void loadGraph(String filename) {
        File file = new File(filename);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                String fromId = split[0];
                String toId = split[1];
                int bandwidth = Integer.parseInt(split[2]);
                Graph.getInstance()
                        .addEdge(
                                fromId,
                                toId,
                                bandwidth);
            }
            Graph.getInstance()
                    .printGraph();
        }catch (IOException ex) {
            System.err.println("Soubor nenalezen");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        loadGraph("uzly.txt");
    }

}
