package ru.nsu.iyaroslavtsev.task121;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncidMatrixTest {

    @Test
    void addVertex() {
        Graph graph = new IncidMatrix();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("A");

        assertEquals(2, graph.getVertices().size());
    }

    @Test
    void addAndRemoveEdge() {
        Graph graph = new IncidMatrix();
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        assertEquals(List.of("B"), graph.getNeighbors("A"));

        graph.rmvEdge("A", "B");

        assertTrue(graph.getNeighbors("A").isEmpty());
    }

    @Test
    void duplicateEdgeIsNotAdded() {
        Graph graph = new IncidMatrix();
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");
        graph.addEdge("A", "B");

        assertEquals(List.of("B"), graph.getNeighbors("A"));
    }

    @Test
    void removeVertexRemovesIncidentEdges() {
        Graph graph = new IncidMatrix();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "C");

        graph.rmvVertex("B");

        assertFalse(graph.getVertices().contains("B"));
        assertEquals(List.of("C"), graph.getNeighbors("A"));
        assertTrue(graph.getNeighbors("C").isEmpty());
    }

    @Test
    void removeUnknownVertexDoesNothing() {
        Graph graph = new IncidMatrix();
        graph.addVertex("A");

        assertDoesNotThrow(() -> graph.rmvVertex("X"));
        assertEquals(List.of("A"), graph.getVertices());
    }

    @Test
    void addEdgeWithUnknownVertexThrowsException() {
        Graph graph = new IncidMatrix();
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
    void removeUnknownEdgeDoesNothing() {
        Graph graph = new IncidMatrix();
        graph.addVertex("A");
        graph.addVertex("B");

        assertDoesNotThrow(() -> graph.rmvEdge("A", "B"));
        assertDoesNotThrow(() -> graph.rmvEdge("X", "B"));
    }

    @Test
    void getNeighborsOfUnknownVertexThrowsException() {
        Graph graph = new IncidMatrix();

        assertThrows(
                IllegalArgumentException.class,
                () -> graph.getNeighbors("X")
        );
    }
}