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
            PriorityQueue<Island> queue = new PriorityQueue<>(Comparator.comparingDouble(island -> island.routes.size()));
            Set<Island> visited = new HashSet<>();
            queue.add(source);

            double remainingResources = totalResources;

            while (!queue.isEmpty() && remainingResources > 0) {
                Island current = queue.poll();

                if (visited.contains(current)) {
                    continue;
                }
                visited.add(current);

                // Distribute resources to the current island
                double resourcesToDistribute = Math.min(current.capacity, remainingResources);
                System.out.printf("Distributing %.2f resources to island %s\n", resourcesToDistribute, current.name);
                remainingResources -= resourcesToDistribute;

                // Add neighboring islands to the queue
                for (Island neighbor : current.routes.keySet()) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }

            if (remainingResources > 0) {
                System.out.println("Not all resources could be distributed.");
            } else {
                System.out.println("All resources have been distributed.");
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Creating islands
        Island niihau = new Island("Niʻihau", 10.0); // Resource producer
        Island kauai = new Island("Kauaʻi", 5.0);
        Island oahu = new Island("Oʻahu", 8.0);
        Island maui = new Island("Maui", 7.0);
        Island lanai = new Island("Lānaʻi", 6.0);

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
