
# Project: Island Navigation and Resource Distribution

This project consists of solutions to several algorithms involving island navigation, resource sharing, and tourism, designed using different graph-based algorithms. Each question file (`Question1.java`, `Question2.java`, `Question4.java`) tackles a specific problem related to resource distribution, tourism, or knowledge sharing across islands.

## Table of Contents
- [Question 1: Sharing Knowledge](#question-1-sharing-knowledge)
- [Question 2: Distributing Resources](#question-2-distributing-resources)
- [Question 4: Tourism Route Planning](#question-4-tourism-route-planning)
- [Testing Instructions](#testing-instructions)

---

## Question 1: Sharing Knowledge

**File:** `Question1.java`

### Purpose:
This algorithm ensures that leaders can share their knowledge efficiently across a set of islands. The goal is to visit as many islands as possible using the shortest path while prioritizing islands based on their population and the recency of knowledge shared.

### Algorithm:
The algorithm uses **Dijkstra’s algorithm** to calculate the shortest paths starting from a given source island. If an island has not been visited recently or has a larger population, it becomes a priority for the leader to visit. The algorithm ensures that all islands are visited at least once.

### Key Functions:
- `shareKnowledge(graph, source)`: Implements Dijkstra's algorithm to visit all islands while prioritizing based on recency and population.
  
### Runtime Complexity:
- The runtime is **O(EV^2)** where `E` is the number of edges and `V` is the number of vertices (islands). Each edge is processed in **O(E)** time, and Dijkstra’s shortest path operation is **O(V^2)**.

---

## Question 2: Distributing Resources

**File:** `Question2.java`

### Purpose:
This algorithm distributes resources produced on one island across all other reachable islands. Each island has a canoe with a specific capacity for transporting resources, and the resources should be distributed as evenly as possible.

### Algorithm:
The resource distribution starts at the source island and traverses all neighboring islands using a **BFS-like approach**. It calculates the total capacity of all reachable islands and distributes the resources proportionally to each island based on their capacity.

### Key Functions:
- `distributeResources()`: Distributes resources starting from the source island, ensuring proportional and even distribution based on island capacities.
  
### Runtime Complexity:
- The runtime is **O(V + E)** for BFS traversal, where `V` is the number of islands (vertices) and `E` is the number of connections between them (edges).

---

## Question 4: Tourism Route Planning

**File:** `Question4.java`

### Purpose:
This algorithm plans an efficient route for a tourist to visit islands with unique experiences. The goal is to maximize the number of experiences the tourist can enjoy, while minimizing the travel time between islands.

### Algorithm:
The algorithm uses **Dijkstra's algorithm** to find the shortest path from the starting island to other islands with experiences. It calculates the travel time and adds the time required to enjoy the experiences at each island.

### Key Functions:
- `planTourismRoute(graph, source)`: Implements Dijkstra’s algorithm to calculate the shortest travel time and visit as many islands as possible for tourism.

### Runtime Complexity:
- The runtime is **O(EV log V)** where `V` is the number of islands (vertices) and `E` is the number of connections (edges). Each island's shortest distance is calculated in **O(log V)** time.

---

## Testing Instructions

### How to Test the Functions:

1. **Compile the Java Files:**
   Ensure that you compile the Java files using the following commands:
   ```bash
   javac Question1.java
   javac Question2.java
   javac Question4.java
   ```

2. **Run Each Question:**
   Each question file has a `main()` function that sets up a graph of islands and runs the respective algorithm. You can run each question's main method as follows:

   - **Run Question 1 (Sharing Knowledge):**
     ```bash
     java Question1
     ```

   - **Run Question 2 (Distributing Resources):**
     ```bash
     java Question2
     ```

   - **Run Question 4 (Tourism Route Planning):**
     ```bash
     java Question4
     ```

3. **Expected Output:**
   - For **Question 1**, you will see the order of islands visited and knowledge shared based on recency and population size.
   - For **Question 2**, you will see the resource distribution among the islands with the resources proportional to their capacities.
   - For **Question 4**, you will see the list of experiences the tourist visits in an efficient order.

### Example Output:
For **Question 2**, for example, you might see:
```
Distributing 10.00 resources to island Ni'ihau
Distributing 5.00 resources to island Kaua'i
Distributing 8.00 resources to island O'ahu
...
All resources have been distributed.
```

For **Question 4**, the output might be:
```
Experiences visited: [Surfing, Volcano Tour, Lagoon Tour, Cultural Dance]
```

---

