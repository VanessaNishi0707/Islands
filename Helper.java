import java.util.*;

// General vertex class for islands, tracking population, resources, and experiences
class Vertex implements Comparable<Vertex> {
    public String name;
    public int numPeople;
    public List<String> knowledge;
    public List<String> experiences;
    public int experienceTime; // Total time needed to enjoy the island's experiences
    public Map<String, Integer> resources; // Different resources on this island
    public int lastVisited;
    public List<Edge> edges; // Outgoing edges from this island

    // For traversals
    public Vertex parent;
    public double shortestPathEstimate;
    public int distance;

    Vertex(String name, int numPeople, Map<String, Integer> resources, List<String> experiences, int experienceTime) {
        this.name = name;
        this.numPeople = numPeople;
        this.knowledge = new ArrayList<>();
        this.resources = resources;
        this.experiences = experiences;
        this.experienceTime = experienceTime;
        this.edges = new ArrayList<>();
        this.lastVisited = 0;
        this.shortestPathEstimate = Double.POSITIVE_INFINITY;
    }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(this.shortestPathEstimate, other.shortestPathEstimate);
    }
}

// Edge class representing travel between two islands
class Edge {
    public Vertex destination;
    public int travelTime;

    Edge(Vertex destination, int travelTime) {
        this.destination = destination;
        this.travelTime = travelTime;
    }
}

// Dijkstra's algorithm for shortest paths
class GraphTraversal {
    public static void dijkstra(Map<String, Vertex> graph, Vertex start) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        start.shortestPathEstimate = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            for (Edge edge : current.edges) {
                Vertex neighbor = edge.destination;
                double newEstimate = current.shortestPathEstimate + edge.travelTime;
                if (newEstimate < neighbor.shortestPathEstimate) {
                    neighbor.shortestPathEstimate = newEstimate;
                    neighbor.parent = current;
                    queue.add(neighbor);
                }
            }
        }
    }
}
