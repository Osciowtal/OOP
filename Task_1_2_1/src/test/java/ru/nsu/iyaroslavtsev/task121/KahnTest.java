package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class KahnTest {

    @Test
    void sortsSimpleGraph() {
        Graph graph = new AdjList();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        List<String> result = Kahn.sort(graph);

        assertEquals(3, result.size());
        assertTrue(result.indexOf("A") < result.indexOf("B"));
        assertTrue(result.indexOf("B") < result.indexOf("C"));
    }

    @Test
    void sortsGraphWithSeveralPossibleOrders() {
        Graph graph = new AdjList();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        List<String> result = Kahn.sort(graph);

        assertEquals(4, result.size());
        assertTrue(result.indexOf("A") < result.indexOf("C"));
        assertTrue(result.indexOf("B") < result.indexOf("C"));
        assertTrue(result.indexOf("C") < result.indexOf("D"));
    }

    @Test
    void sortsEmptyGraph() {
        Graph graph = new AdjList();

        assertTrue(Kahn.sort(graph).isEmpty());
    }

    @Test
    void handlesIsolatedVertices() {
        Graph graph = new AdjList();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        List<String> result = Kahn.sort(graph);

        assertEquals(3, result.size());
        assertTrue(result.containsAll(List.of("A", "B", "C")));
    }

    @Test
    void detectsCycle() {
        Graph graph = new AdjList();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");

        assertThrows(
                IllegalArgumentException.class,
                () -> Kahn.sort(graph)
        );
    }

    @Test
    void detectsSelfLoop() {
        Graph graph = new AdjList();

        graph.addVertex("A");
        graph.addEdge("A", "A");

        assertThrows(
                IllegalArgumentException.class,
                () -> Kahn.sort(graph)
        );
    }
}