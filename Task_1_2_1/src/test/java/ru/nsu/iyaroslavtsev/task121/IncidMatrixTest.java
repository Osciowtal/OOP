package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class IncidMatrixTest {

    @Test
    void addVertexAndGetVertices() {
        Graph<String> graph = new IncidMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("A");

        assertEquals(Set.of("A", "B"), new HashSet<>(graph.getVertices()));
    }

    @Test
    void addEdgeAndGetNeighbors() {
        Graph<String> graph = new IncidMatrix<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "B");

        assertEquals(List.of("B", "C"), graph.getNeighbors("A"));
        assertTrue(graph.getNeighbors("B").isEmpty());
    }

    @Test
    void addEdgeThrowsWhenVertexDoesNotExist() {
        Graph<String> graph = new IncidMatrix<>();
        graph.addVertex("A");

        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdge("A", "B"));
        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdge("B", "A"));
    }

    @Test
    void removeEdge() {
        Graph<String> graph = new IncidMatrix<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        graph.rmvEdge("A", "B");

        assertTrue(graph.getNeighbors("A").isEmpty());
    }

    @Test
    void removeVertexRemovesAllIncidentEdgesAndKeepsMatrixConsistent() {
        Graph<String> graph = new IncidMatrix<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("A", "D");

        graph.rmvVertex("B");

        assertEquals(Set.of("A", "C", "D"), new HashSet<>(graph.getVertices()));
        assertEquals(List.of("D"), graph.getNeighbors("A"));
        assertEquals(List.of("D"), graph.getNeighbors("C"));
        assertTrue(graph.getNeighbors("D").isEmpty());
        assertThrows(IllegalArgumentException.class,
                () -> graph.getNeighbors("B"));
    }

    @Test
    void addingVertexAfterEdgesKeepsMatrixConsistent() {
        Graph<String> graph = new IncidMatrix<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.addVertex("C");
        graph.addEdge("C", "A");

        assertEquals(List.of("B"), graph.getNeighbors("A"));
        assertEquals(List.of("A"), graph.getNeighbors("C"));
    }

    @Test
    void supportsIntegerVertices() {
        Graph<Integer> graph = new IncidMatrix<>();
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addEdge(10, 20);

        assertEquals(List.of(20), graph.getNeighbors(10));
    }
}