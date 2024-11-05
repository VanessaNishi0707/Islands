import java.util.*;

class Question4 {
    public static List<String> planTourismRoute(Map<String, Vertex> graph, String startIsland) {
        Vertex start = graph.get(startIsland);
        GraphTraversal.dijkstra(graph, start);

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

    public static void main(String[] args) {
        // Example setup: Create islands and edges
        Map<String, Vertex> graph = new HashMap<>();

        Vertex hawaii = new Vertex("Hawaii", 1000, new HashMap<>(), Arrays.asList("Surfing", "Volcano Tour"), 3);
        Vertex tahiti = new Vertex("Tahiti", 500, new HashMap<>(), Arrays.asList("Lagoon Tour", "Cultural Dance"), 2);
        Vertex boraBora = new Vertex("Bora Bora", 300, new HashMap<>(), Arrays.asList("Snorkeling", "Resort Stay"), 4);

        graph.put(hawaii.name, hawaii);
        graph.put(tahiti.name, tahiti);
        graph.put(boraBora.name, boraBora);

        // Connect the islands with edges (travel time)
        hawaii.edges.add(new Edge(tahiti, 5));
        tahiti.edges.add(new Edge(boraBora, 3));
        boraBora.edges.add(new Edge(hawaii, 7));

        // Plan the tourism route starting from Hawaii
        List<String> experiences = planTourismRoute(graph, "Hawaii");

        // Output the experiences
        System.out.println("Experiences visited: " + experiences);
    }
}
