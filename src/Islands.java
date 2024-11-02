import java.util.Map;
import java.util.Set;

public class Islands {

    public class Vertex {
        private String name;
        private int numPeople;
        private int numResource1; // replace Resource1 with whatever resource (shells, etc.)
        private int numResource2;
        private int numResource3;
        private Set<Edge> edges; // set of edges going out from vertex

        Vertex(String name, int numPeople, int numResource1, int numResource2, int numResource3) {
            this.name = name;
            this.numPeople = numPeople;
            this.numResource1 = numResource1;
            this.numResource2 = numResource2;
            this.numResource3 = numResource3;
        }
    }

    class Edge {
        private Vertex source;
        private Vertex destination;
        private double travelTime;

        Edge(Vertex source, Vertex destination, double travelTime) {
            this.source = source;
            this.destination = destination;
            this.travelTime = travelTime;
        }
    }

    class Graph {
        private Map<Vertex, Set<Vertex>> adjacencyList;
    }

    // ? 
    public void addVertex(String name, int numPeople, int numResource1, int numResource2, int numResource3) {
        Vertex v = new Vertex(name, numPeople, numResource1, numResource2, numResource3);
        System.out.println(v.name);
    }

    public static void main(String[] args) throws Exception {
        Islands islands = new Islands();
        islands.addVertex("newIsland", 0, 0, 0, 0);
    }   
}
