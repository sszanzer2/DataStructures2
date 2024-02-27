package Graphs;

import java.util.*;

public class Main {
    // Main method for testing
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        // Adding locations
        graph.addVertex("Home");
        graph.addVertex("Work");
        graph.addVertex("Park");
        graph.addVertex("School");
        graph.addVertex("Gym");
        graph.addVertex("Grocery Store");

        // Adding commuting routes (edges)
        graph.addEdge("Home", "Work", 5); // Route from home to work
        graph.addEdge("Home", "Park", 3); // Route from home to park
        graph.addEdge("Work", "School", 4); // Route from work to school
        graph.addEdge("Work", "Gym", 2); // Route from work to gym
        graph.addEdge("Park", "Grocery Store", 1); // Route from park to grocery store
        graph.addEdge("School", "Gym", 3); // Route from school to gym
        graph.addEdge("Gym", "Grocery Store", 2); // Route from gym to grocery store

        // Printing adjacency list
        System.out.println("Adjacency List:");
        graph.printAdjacencyList();

        // Demonstrate three distinct paths through the graph
        System.out.println("\nPaths through the graph:");
        demonstratePaths(graph);
    }

    // Method to demonstrate three distinct paths through the graph
    private static void demonstratePaths(WeightedGraph<String> graph) {
        // Path from Home to Gym
        System.out.println("a) Path from Home to Gym:");
        Map<String, Integer> pathHomeToGym = graph.BFS("Home", "Gym");
        printPath(pathHomeToGym);

        // Path from Park to Grocery Store
        System.out.println("b) Path from Park to Grocery Store:");
        Map<String, Integer> pathParkToStore = graph.BFS("Park", "Grocery Store");
        printPath(pathParkToStore);

        // Path from Work to School
        System.out.println("c) Path from Work to School:");
        Map<String, Integer> pathWorkToSchool = graph.BFS("Work", "School");
        printPath(pathWorkToSchool);
    }

    // Method to print the path
    private static void printPath(Map<String, Integer> path) {
        for (Map.Entry<String, Integer> entry : path.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " minutes");
        }
        System.out.println();
    }
}
