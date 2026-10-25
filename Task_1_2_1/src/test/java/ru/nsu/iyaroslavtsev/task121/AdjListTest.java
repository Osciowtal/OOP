package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class AdjListTest {

    @Test
    void addVertex() {
        Graph graph = new AdjList();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("A");

        assertEquals(2, graph.getVertices().size());
        assertTrue(graph.getVertices().contains("A"));
    }

    @Test
    void addAndRemoveEdge() {
        Graph graph = new AdjList();
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");
        graph.addEdge("A", "B");

        assertEquals(List.of("B"), graph.getNeighbors("A"));

        graph.rmvEdge("A", "B");

        assertTrue(graph.getNeighbors("A").isEmpty());
    }

    @Test
    void removeVertex() {
        Graph graph = new AdjList();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("C", "B");

        graph.rmvVertex("B");

        assertFalse(graph.getVertices().contains("B"));
        assertTrue(graph.getNeighbors("A").isEmpty());
        assertTrue(graph.getNeighbors("C").isEmpty());
    }

    @Test
    void removeUnknownVertexDoesNothing() {
        Graph graph = new AdjList();
        graph.addVertex("A");

        assertDoesNotThrow(() -> graph.rmvVertex("X"));
        assertEquals(List.of("A"), graph.getVertices());
    }

    @Test
    void addEdgeWithUnknownVertexThrowsException() {
        Graph graph = new AdjList();
        graph.addVertex("A");

        assertThrows(
                IllegalArgumentException.class,
                () -> graph.addEdge("A", "B")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> graph.addEdge("B", "A")
        );
    }

    @Test
    void getNeighborsOfUnknownVertexThrowsException() {
        Graph graph = new AdjList();

        assertThrows(
                IllegalArgumentException.class,
                () -> graph.getNeighbors("X")
        );
    }

    @Test
    void removeUnknownEdgeDoesNothing() {
        Graph graph = new AdjList();
        graph.addVertex("A");
        graph.addVertex("B");

        assertDoesNotThrow(() -> graph.rmvEdge("X", "B"));
        assertDoesNotThrow(() -> graph.rmvEdge("A", "X"));
    }
}