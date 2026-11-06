package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void equalsReturnsTrueForSameObject() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");

        assertTrue(Utils.equals(graph, graph));
    }

    @Test
    void equalsReturnsFalseForNullAndNonGraph() {
        Graph<String> graph = new AdjList<>();
        graph.addVertex("A");

        assertFalse(Utils.equals(graph, null));
        assertFalse(Utils.equals(graph, "not a graph"));
    }

    @Test
    void equalsComparesDifferentRepresentations() {
        Graph<String> list = new AdjList<>();
        Graph<String> matrix = new AdjMatrix<>();

        for (String vertex : new String[]{"A", "B", "C"}) {
            list.addVertex(vertex);
            matrix.addVertex(vertex);
        }

        list.addEdge("A", "B");
        list.addEdge("A", "C");

        matrix.addEdge("A", "C");
        matrix.addEdge("A", "B");

        assertTrue(Utils.equals(list, matrix));
        assertTrue(list.equals(matrix));
    }

    @Test
    void equalsReturnsFalseWhenVertexSetsDiffer() {
        Graph<String> first = new AdjList<>();
        Graph<String> second = new AdjMatrix<>();

        first.addVertex("A");
        second.addVertex("B");

        assertFalse(Utils.equals(first, second));
    }

    @Test
    void equalsReturnsFalseWhenEdgesDiffer() {
        Graph<String> first = new AdjList<>();
        Graph<String> second = new AdjMatrix<>();

        for (String vertex : new String[]{"A", "B", "C"}) {
            first.addVertex(vertex);
            second.addVertex(vertex);
        }

        first.addEdge("A", "B");
        second.addEdge("A", "C");

        assertFalse(Utils.equals(first, second));
    }

    @Test
    void equalsSupportsIntegerGraphs() {
        Graph<Integer> first = new IncidMatrix<>();
        Graph<Integer> second = new AdjList<>();

        for (int vertex : new int[]{1, 2, 3}) {
            first.addVertex(vertex);
            second.addVertex(vertex);
        }

        first.addEdge(1, 2);
        first.addEdge(2, 3);

        second.addEdge(1, 2);
        second.addEdge(2, 3);

        assertTrue(Utils.equals(first, second));
    }

    @Test
    void hashCodeIsEqualForEqualGraphs() {
        Graph<String> first = new AdjList<>();
        Graph<String> second = new AdjMatrix<>();

        for (String vertex : new String[]{"A", "B", "C"}) {
            first.addVertex(vertex);
            second.addVertex(vertex);
        }

        first.addEdge("A", "B");
        first.addEdge("B", "C");

        second.addEdge("B", "C");
        second.addEdge("A", "B");

        assertEquals(Utils.hashCode(first), Utils.hashCode(second));
    }

    @Test
    void toStringReturnsEmptyGraphText() {
        assertEquals("(empty graph)", Utils.toString(new AdjList<>()));
    }

    @Test
    void toStringSortsStringVerticesAndNeighbors() {
        Graph<String> graph = new AdjList<>();

        graph.addVertex("C");
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "C");
        graph.addEdge("A", "B");

        String expected = "A -> B, C" + System.lineSeparator()
                + "B -> []" + System.lineSeparator()
                + "C -> []";

        assertEquals(expected, Utils.toString(graph));
    }

    @Test
    void toStringConvertsIntegerVerticesToStrings() {
        Graph<Integer> graph = new AdjList<>();

        graph.addVertex(2);
        graph.addVertex(10);
        graph.addVertex(1);

        graph.addEdge(2, 10);
        graph.addEdge(2, 1);

        String expected = "1 -> []" + System.lineSeparator()
                + "10 -> []" + System.lineSeparator()
                + "2 -> 1, 10";

        assertEquals(expected, Utils.toString(graph));
    }
}