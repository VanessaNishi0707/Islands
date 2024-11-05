package Question2;

import java.util.*;

public class Question2 {

    // Class representing an Island
    static class Island {
        String name;
        double capacity; // Maximum capacity of the canoe
        Map<Island, Double> routes; // Neighboring islands with the distance/cost

        public Island(String name, double capacity) {
            this.name = name;
            this.capacity = capacity;
            this.routes = new HashMap<>();
        }

        public void addRoute(Island island, double cost) {
            routes.put(island, cost);
        }
    }

    // Class to manage resource distribution
    static class ResourceDistribution {
        private final Island source; // The island producing the resource
        private final double totalResources; // Total resources available to distribute

        public ResourceDistribution(Island source, double totalResources) {
            this.source = source;
            this.totalResources = totalResources;
        }

        public void distributeResources() {
            // A queue to manage distribution
            Queue<Island> queue = new LinkedList<>();
            Set<Island> visited = new HashSet<>();
            queue.add(source);
            visited.add(source);

            // First, find all reachable islands
            List<Island> reachableIslands = new ArrayList<>();
            while (!queue.isEmpty()) {
                Island current = queue.poll();
                reachableIslands.add(current);

                // Add neighboring islands to the queue
                for (Island neighbor : current.routes.keySet()) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            // Calculate equal distribution
            double numIslands = reachableIslands.size();
            double resourcesPerIsland = totalResources / numIslands;

            // Distribute resources equally
            for (Island island : reachableIslands) {
                double resourcesToDistribute = Math.min(island.capacity, resourcesPerIsland);
                System.out.printf("Distributing %.2f resources to island %s\n", resourcesToDistribute, island.name);
            }

            System.out.println("All resources have been distributed.");
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Creating islands
        Island niihau = new Island("Niihau", 10.0); // Resource producer
        Island kauai = new Island("Kauai", 6.0);
        Island oahu = new Island("Oahu", 8.0);
        Island maui = new Island("Maui", 7.0);
        Island lanai = new Island("Lānai", 6.0);

        // Adding routes (direct connections)
        niihau.addRoute(kauai, 1.0);
        niihau.addRoute(oahu, 2.0);
        kauai.addRoute(maui, 1.5);
        oahu.addRoute(maui, 1.0);
        maui.addRoute(lanai, 0.5);

        // Assuming a total of 30 resources available from Niʻihau
        ResourceDistribution distribution = new ResourceDistribution(niihau, 30);
        distribution.distributeResources();
    }
}
