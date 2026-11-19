package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AdjListTest {

    @Test
    void addVertexAndGetVertices() {
        Graph<String> graph = new AdjList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("A");

        assertEquals(Set.of("A", "B"), new HashSet<>(graph.getVertices()));
    }

    @Test
    void addEdgeAndGetNeighbors() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "B"); // Duplicate edge must not be added.

        assertEquals(List.of("B", "C"), graph.getNeighbors("A"));
        assertEquals(List.of(), graph.getNeighbors("B"));
    }

    @Test
    void addEdgeThrowsWhenVertexDoesNotExist() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");

        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdge("A", "B"));
        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdge("B", "A"));
    }

    @Test
    void removeEdge() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        graph.rmvEdge("A", "B");

        assertTrue(graph.getNeighbors("A").isEmpty());
    }

    @Test
    void removeVertexAlsoRemovesIncidentEdges() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        graph.rmvVertex("B");

        assertEquals(Set.of("A", "C"), new HashSet<>(graph.getVertices()));
        assertTrue(graph.getNeighbors("A").isEmpty());
        assertTrue(graph.getNeighbors("C").isEmpty());
        assertThrows(IllegalArgumentException.class,
                () -> graph.getNeighbors("B"));
    }

    @Test
    void getNeighborsReturnsCopy() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        List<String> neighbors = graph.getNeighbors("A");
        neighbors.clear();

        assertEquals(List.of("B"), graph.getNeighbors("A"));
    }

    @Test
    void supportsIntegerVertices() {
        Graph<Integer> graph = new AdjList<>();
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addEdge(10, 20);

        assertEquals(List.of(20), graph.getNeighbors(10));
    }
}