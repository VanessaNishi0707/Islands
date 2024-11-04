
import java.util.*;

enum Color {
    WHITE,
    GREY,
    BLACK
}

class Vertex implements Comparable<Vertex> {
    public String name;
    public int numPeople;
    public List<String> knowledge; // list of knowledge shared
    private int numResource2;
    private int numResource3;
    public int lastVisited;
    public List<Edge> edges; // set of edges pointing out from vertex

    // Used for traversing function
    public Vertex parent;
    public Color color;
    public int distance;

    // Used for intialize graph function
    public double shortestPathEstimate;

    Vertex(String name, int numPeople, int numResource2, int numResource3) {
        this.name = name;
        this.numPeople = numPeople;
        this.knowledge = new ArrayList<String>();
        this.numResource2 = numResource2;
        this.numResource3 = numResource3;
        parent = null;
        color = Color.WHITE;
        distance = 0;
        shortestPathEstimate = -1;
        lastVisited = 0;
    }

    public String getName() {
        return name;
    }

    public double getShortestPathEstimate() {
        return shortestPathEstimate;
    }

    @Override public int compareTo(Vertex a)
    { return 0; }
}

class Edge {
    private Vertex source;
    public Vertex destination;
    public double travelTime;

    Edge(Vertex source, Vertex destination, double travelTime) {
        this.source = source;
        this.destination = destination;
        this.travelTime = travelTime;
    }

    public Vertex getDestination() {
        return destination;
    }
}

class Graph {
    private Map<Vertex, List<Vertex>> adjacencyList; // maps each vertex -> vertices it's connected to
    private List<Vertex> vertices;
    Vertex graphSource; 

    Graph() {
        adjacencyList = new HashMap<Vertex, List<Vertex>>(); 
        vertices = new ArrayList<Vertex>();
        graphSource = null;
    }

    public void addVertex(Vertex v) {
        adjacencyList.putIfAbsent(v, new ArrayList<>()); 
        vertices.add(v);

        if (graphSource == null) { // graph empty
            graphSource = v; 
        }
    }

    public void addEdge(Vertex start, Vertex destination, double travelTime) {
        Edge e = new Edge(start, destination, travelTime);
        if (start.edges == null) {
            start.edges = new ArrayList<>();
        }
        adjacencyList.get(start).add(destination); // add v to start's vertex list
        start.edges.add(e); // add edge to source's edge list
    }

    public void printVertices(Graph g) { // traverse graph using BFS
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

    // Auxillary function for shareKnowledge (modified ver. of Dijkstra)
    public void initializeGraph(Graph graph, Vertex source) {
        for (Map.Entry<Vertex, List<Vertex>> entry : adjacencyList.entrySet()) {
            entry.getKey().shortestPathEstimate = Double.POSITIVE_INFINITY;
            entry.getKey().parent = null;
            //System.out.println(entry.getKey().shortestPathEstimate);
        }
        source.shortestPathEstimate = 0;
    }
    // Auxillary function for shareKnowledge (modified ver. of Dijkstra)
    public boolean relax(Vertex u, Vertex v) {
        double travelTime = Double.POSITIVE_INFINITY;
        // Loop through u's edge list to find the edge that points to v
        for (Edge edge : u.edges) {
            if (edge.destination.name.equals(v.name)) {
                travelTime = edge.travelTime;
                if (v.shortestPathEstimate > (u.shortestPathEstimate + travelTime)) {
                    v.shortestPathEstimate = u.shortestPathEstimate + travelTime;
                    v.parent = u;
                    return true;
                }
            }
        }
        return false;
    }

    public void shareKnowledge(Graph graph, Vertex source, String knowledgeShared) {
        initializeGraph(graph, source);
        ArrayList<Vertex> shortestPath = new ArrayList<Vertex>();
        PriorityQueue<Vertex> prioQueue = new PriorityQueue<Vertex>(graph.adjacencyList.size(), new VertexComparator());

        prioQueue.add(source);
        
        while (!prioQueue.isEmpty()) {
            Vertex u = prioQueue.poll();
            shortestPath.addLast(u);
            u.knowledge.add(knowledgeShared);
            u.lastVisited += 1;
            adjacencyList.get(u).forEach((v) -> {
                if (v.lastVisited < 1 || v.lastVisited > 10 && v.numPeople > 100) {
                    relax(u, v);
                    prioQueue.add(v);
                }
            });
        }

        for (Vertex vertex : vertices) {
            if (vertex.lastVisited < 1) {
                prioQueue.add(vertex);

                while (!prioQueue.isEmpty()) {
                    Vertex u = prioQueue.poll();
                    shortestPath.addLast(u);
                    u.knowledge.add(knowledgeShared);
                    u.lastVisited += 1;
                    adjacencyList.get(u).forEach((v) -> {
                        if ((v.lastVisited < 1) || v.lastVisited > 10 && v.numPeople > 100) {
                            if (relax(u, v)) {
                                prioQueue.add(v);
                            }
                        }
                    });
                }
            }
        }
    }

    class VertexComparator implements Comparator<Vertex> {
        public int compare(Vertex v1, Vertex v2) {
            if (v1.shortestPathEstimate > v2.shortestPathEstimate) {
                return 1;
            }
            else if (v1.shortestPathEstimate < v2.shortestPathEstimate) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        Vertex A = new Vertex("A", 0, 0, 0);
        Vertex B = new Vertex("B", 0, 0, 0);
        Vertex C = new Vertex("C", 0, 0, 0);
        Vertex D = new Vertex("D", 0, 0, 0);
        Vertex E = new Vertex("E", 0, 0, 0);
        Vertex F = new Vertex("F", 0, 0, 0);
        Vertex G = new Vertex("G", 0, 0, 0);


        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);
       
        graph.addEdge(A, B, 1.0);
        graph.addEdge(B, C, 2.0);
        graph.addEdge(B, D, 3.0);
        graph.addEdge(D, E, 4.0);
        graph.addEdge(E, F, 5.0);
        graph.addEdge(E, G, 6.0);

        graph.shareKnowledge(graph, D, "some knowledge");
    }   
}