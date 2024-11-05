package Question4;
import java.util.*;

class Vertex implements Comparable<Vertex> {
    public String name;
    public int numPeople;
    public List<String> experiences;
    public int experienceTime;
    public List<Edge> edges;
    public double shortestPathEstimate;

    public Vertex(String name, int numPeople, List<String> experiences, int experienceTime) {
        this.name = name;
        this.numPeople = numPeople;
        this.experiences = experiences;
        this.experienceTime = experienceTime;
        this.edges = new ArrayList<>();
        this.shortestPathEstimate = Double.POSITIVE_INFINITY; // Initially set to infinity
    }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(this.shortestPathEstimate, other.shortestPathEstimate);
    }
}

class Edge {
    public Vertex destination;
    public int travelTime;

    public Edge(Vertex destination, int travelTime) {
        this.destination = destination;
        this.travelTime = travelTime;
    }
}

public class Question4 {

    // Plan a tourist route that maximizes experiences in the shortest possible time
    public static List<String> planTourismRoute(Map<String, Vertex> graph, String startIsland) {
        Vertex start = graph.get(startIsland);
        dijkstra(graph, start);  // Run Dijkstra's algorithm to find shortest paths from start island

        // List of experiences enjoyed
        List<String> experiencesVisited = new ArrayList<>();

        // Traverse through the graph using the shortest paths found by Dijkstra
        for (Vertex island : graph.values()) {
            if (!island.experiences.isEmpty() && island != start) {
                experiencesVisited.addAll(island.experiences);
            }
        }

        return experiencesVisited;
    }

    // Dijkstra's algorithm to calculate the shortest path from the start island
    private static void dijkstra(Map<String, Vertex> graph, Vertex start) {
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
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example setup: Create islands and edges
        Map<String, Vertex> graph = new HashMap<>();

        Vertex hawaii = new Vertex("Hawaii", 1000, Arrays.asList("Surfing", "Volcano Tour"), 3);
        Vertex tahiti = new Vertex("Tahiti", 500, Arrays.asList("Lagoon Tour", "Cultural Dance"), 2);
        Vertex boraBora = new Vertex("Bora Bora", 300, Arrays.asList("Snorkeling", "Resort Stay"), 4);

        graph.put(hawaii.name, hawaii);
        graph.put(tahiti.name, tahiti);
        graph.put(boraBora.name, boraBora);

        // Connect the islands with edges (travel time)
        hawaii.edges.add(new Edge(tahiti, 5));
        tahiti.edges.add(new Edge(boraBora, 3));
        boraBora.edges.add(new Edge(hawaii, 7));

        // Plan the tourism route starting from Hawaii
        List<String> experiences = planTourismRoute(graph, "Hawaii");

        // Output the experiences visited
        System.out.println("Experiences visited: " + experiences);
    }
}
