package Graphs;

import java.util.*;

import BST.LinkedQueue;
//Importing the QueueInterface from the provided package
import BST.QueueInterface;

//Implementing the GraphInterface<T>
public class WeightedGraph<T> implements GraphInterface<T> {
    private Map<T, List<Edge<T>>> adjacencyMap;
    private Map<T, Boolean> markMap; // Add markMap to store vertex markings

    // Constructor
    public WeightedGraph() {
        adjacencyMap = new HashMap<>();
        markMap = new HashMap<>(); // Initialize markMap
    }

    // Inner class to represent an edge with weight
    private class Edge<T> {
        T destination;
        int weight;

        public Edge(T destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    // Function to add an edge into the graph
    public void addEdge(T fromVertex, T toVertex, int weight) {
        adjacencyMap.computeIfAbsent(fromVertex, k -> new ArrayList<>()).add(new Edge<>(toVertex, weight));
        adjacencyMap.computeIfAbsent(toVertex, k -> new ArrayList<>()).add(new Edge<>(fromVertex, weight)); // Assuming undirected graph
    }
    
    // Other methods from the GraphInterface<T> that need implementation
    @Override
    public boolean isEmpty() {
        return adjacencyMap.isEmpty();
    }

    @Override
    public boolean isFull() {
        // The graph can theoretically have unlimited vertices
        return false;
    }
    
    public void addVertex(T vertex) {
        adjacencyMap.putIfAbsent(vertex, new ArrayList<>());
    }
    
    @Override
    public boolean hasVertex(T vertex) {
        return adjacencyMap.containsKey(vertex);
    }
    
    @Override
    public int weightIs(T fromVertex, T toVertex) {
        if (!adjacencyMap.containsKey(fromVertex)) {
            return 0; // Vertex not found, return 0 or throw an exception
        }
        
        List<Edge<T>> edges = adjacencyMap.get(fromVertex);
        for (Edge<T> edge : edges) {
            if (edge.destination.equals(toVertex)) {
                return edge.weight; // Return the weight of the edge
            }
        }
        
        return 0; // Edge not found, return 0 or throw an exception
    }
    
    @Override
    public QueueInterface<T> getToVertices(T vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            return null; // Vertex not found, return null or throw an exception
        }
        
        List<Edge<T>> edges = adjacencyMap.get(vertex);
        QueueInterface<T> queue = new LinkedQueue<>(); // Assuming LinkedQueue is your implementation of QueueInterface
        
        for (Edge<T> edge : edges) {
            queue.enqueue(edge.destination); // Add the destination vertex to the queue
        }
        
        return queue;
    }
    
    @Override
    public void clearMarks() {
        markMap.clear(); // Clear all vertex markings
    }

    @Override
    public void markVertex(T vertex, boolean marked) {
        markMap.put(vertex, marked); // Mark or unmark the vertex
    }

    @Override
    public boolean isMarked(T vertex) {
        return markMap.containsKey(vertex) && markMap.get(vertex); // Check if the vertex is marked
    }

    @Override
    public T getUnmarked() {
        for (T vertex : adjacencyMap.keySet()) {
            if (!isMarked(vertex)) {
                return vertex; // Return the first unmarked vertex found
            }
        }
        return null; // Return null if no unmarked vertex is found
    }

    // Function to perform Breadth First Search and find shortest paths from start vertex
    public Map<T, Integer> BFS(T start, T end) {
        Map<T, Integer> edgeWeights = new HashMap<>(); // Store weights instead of distances
        Map<T, T> path = new HashMap<>(); // Track the path from start to end
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();

            if (current.equals(end)) {
                // If end vertex is reached, backtrack to reconstruct the path
                return reconstructPath(start, end, path, edgeWeights);
            }

            for (Edge<T> neighbor : adjacencyMap.get(current)) {
                if (!visited.contains(neighbor.destination)) {
                    queue.add(neighbor.destination);
                    visited.add(neighbor.destination);
                    path.put(neighbor.destination, current); // Store the previous vertex in the path
                    edgeWeights.put(neighbor.destination, neighbor.weight); // Store edge weight
                }
            }
        }

        return edgeWeights;
    }

    // Helper method to reconstruct the path from start to end vertex
    private Map<T, Integer> reconstructPath(T start, T end, Map<T, T> path, Map<T, Integer> edgeWeights) {
        Map<T, Integer> shortestPath = new HashMap<>();
        T current = end;
        while (!current.equals(start)) {
            shortestPath.put(current, edgeWeights.get(current));
            current = path.get(current); // Move to the previous vertex in the path
        }
        shortestPath.put(start, 0); // Include the start vertex with zero weight
        return shortestPath;
    }

    // Method to print adjacency list for visualization
    public void printAdjacencyList() {
        for (Map.Entry<T, List<Edge<T>>> entry : adjacencyMap.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            List<Edge<T>> edges = entry.getValue();
            for (int i = 0; i < edges.size(); i++) {
                Edge<T> edge = edges.get(i);
                System.out.print(edge.destination + " (" + edge.weight + " minute(s))");
                if (i < edges.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }}
