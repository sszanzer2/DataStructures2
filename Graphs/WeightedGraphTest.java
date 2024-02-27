package Graphs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class WeightedGraphTest {

    @Test
    public void testAddVertex() {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");
        assertTrue(graph.hasVertex("A"));
    }

    @Test
    public void testAddEdge() {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);
        assertTrue(graph.weightIs("A", "B") == 5);
    }
    
    @Test
    public void testBFS_SingleVertex() {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");

        // Perform BFS from vertex "A" to "A" (single vertex)
        Map<String, Integer> path = graph.BFS("A", "A");

        // Verify the result
        assertNotNull(path); // Check that a path is found
        assertTrue(path.containsKey("A")); // Check that the path contains the start/end vertex
        assertEquals(0, (int) path.get("A")); // Check that the distance to itself is 0
    }


    @Test
    public void testBFS_NoEdges() {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        // Perform BFS from vertex "A" to "C" (no edges in the graph)
        Map<String, Integer> path = graph.BFS("A", "C");

        // Verify the result
        assertTrue(path.isEmpty()); // Check that no path is found
    }


    @Test
    public void testWeightIs_NoEdge() {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        assertTrue(graph.weightIs("A", "B") == 0);
    }

    @Test
    public void testIsFull() {
        WeightedGraph<String> graph = new WeightedGraph<>();
        assertFalse(graph.isFull());
    }

    @Test
    public void testIsEmpty() {
        WeightedGraph<String> graph = new WeightedGraph<>();
        assertTrue(graph.isEmpty());
    }

    @Test
    public void testClearMarks() {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");
        graph.markVertex("A", true);
        graph.clearMarks();
        assertFalse(graph.isMarked("A"));
    }

    @Test
    public void testBFS_SimpleGraph() {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B", 1);

        // Perform BFS from vertex "A" to "B" in a simple graph
        Map<String, Integer> path = graph.BFS("A", "B");

        // Verify the result
        assertNotNull(path); // Check that a path is found
        assertFalse(path.isEmpty()); // Check that the path is not empty
        assertEquals(1, (int) path.get("B")); // Check the shortest distance from A to B
    }

}

