package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class KahnTest {

    @Test
    void sortsSimpleChain() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        assertEquals(List.of("A", "B", "C"), Kahn.sort(graph));
    }

    @Test
    void returnsValidOrderForBranchedGraph() {
        Graph<String> graph = new AdjList<>();

        for (String vertex : List.of("A", "B", "C", "D")) {
            graph.addVertex(vertex);
        }

        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        List<String> order = Kahn.sort(graph);
        Map<String, Integer> position = new HashMap<>();

        for (int i = 0; i < order.size(); i++) {
            position.put(order.get(i), i);
        }

        assertEquals(4, order.size());
        assertTrue(position.get("A") < position.get("C"));
        assertTrue(position.get("B") < position.get("C"));
        assertTrue(position.get("C") < position.get("D"));
    }

    @Test
    void sortsIntegerGraph() {
        Graph<Integer> graph = new AdjMatrix<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertEquals(List.of(1, 2, 3), Kahn.sort(graph));
    }

    @Test
    void returnsEmptyListForEmptyGraph() {
        Graph<String> graph = new IncidMatrix<>();

        assertTrue(Kahn.sort(graph).isEmpty());
    }

    @Test
    void throwsForCycle() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.addEdge("B", "A");

        assertThrows(IllegalArgumentException.class,
                () -> Kahn.sort(graph));
    }

    @Test
    void throwsForSelfLoop() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");
        graph.addEdge("A", "A");

        assertThrows(IllegalArgumentException.class,
                () -> Kahn.sort(graph));
    }
}