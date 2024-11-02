import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

enum Color {
    WHITE,
    GREY,
    BLACK
}

class Graph {
    class Vertex {
        private String name;
        private int numPeople;
        private int numResource1; // replace Resource1 with whatever resource (shells, etc.)
        private int numResource2;
        private int numResource3;
        private List<Edge> edges; // set of edges pointing out from vertex

        // Used for traversing function
        private Vertex parent;
        private Color color;
        private int distance;
    
        Vertex(String name, int numPeople, int numResource1, int numResource2, int numResource3) {
            this.name = name;
            this.numPeople = numPeople;
            this.numResource1 = numResource1;
            this.numResource2 = numResource2;
            this.numResource3 = numResource3;
            parent = null;
            color = Color.WHITE;
            distance = 0;
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

    private Map<Vertex, List<Vertex>> adjacencyList; // maps vertex -> vertices it's connected to
    Vertex graphSource; 

    Graph() {
        adjacencyList = new HashMap<Vertex, List<Vertex>>();
        graphSource = null;
    }

    public void addVertex(Vertex start, Vertex v) {
        adjacencyList.putIfAbsent(v, new ArrayList<>()); 

        if (start == null) { // graph empty
            graphSource = v; 
        }
        if (start != null) {
            adjacencyList.get(start).add(v); // add v to start's vertex list
        }
    }

    public void addEdge(Vertex start, Vertex destination, double weight) {
        Edge e = new Edge(start, destination, weight);
        if (start.edges == null) {
            start.edges = new ArrayList<>();
        }
        start.edges.add(e); // add edge to source's edge list
    }

    public void buildGraph() {
        Vertex A = new Vertex("A", 0, 0, 0, 0);
        Vertex B = new Vertex("B", 0, 0, 0, 0);
        Vertex C = new Vertex("C", 0, 0, 0, 0);
        Vertex D = new Vertex("D", 0, 0, 0, 0);

        addVertex(null, A);
        addVertex(A, B);
        addVertex(B, C);
        addVertex(B, D);
       
        addEdge(A, B, 0);
        addEdge(B, C, 0);
        addEdge(B, D, 0);
    }

    public void printVertices(Graph g) { //traverse graph using BFS
        graphSource.color = Color.GREY;
        
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(graphSource);
        System.out.println(graphSource.name + " " + graphSource.distance);
        while (!queue.isEmpty()) {
            Vertex u = queue.remove();
            
            adjacencyList.get(u).forEach((v) -> {
                if (v.color == Color.WHITE) {
                    v.color = Color.GREY;
                    v.distance = u.distance + 1;
                    v.parent = u;
                    System.out.println(v.name + " " + v.distance + " parent:" + v.parent.name);
                    queue.add(v);
                }
            });
            u.color = Color.BLACK;
        }
    }

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();

        graph.buildGraph();
        graph.printVertices(graph);
    }   
}