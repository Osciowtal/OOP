package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UtilsTest {

    private Graph createGraph(Class<? extends Graph> type) throws Exception {
        Graph graph = type.getDeclaredConstructor().newInstance();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        return graph;
    }

    @Test
    void differentImplementationsAreEqual() throws Exception {
        Graph list = createGraph(AdjList.class);
        Graph matrix = createGraph(AdjMatrix.class);
        Graph incidence = createGraph(IncidMatrix.class);

        assertEquals(list, matrix);
        assertEquals(list, incidence);
        assertEquals(matrix, incidence);
    }

    @Test
    void differentEdgesMakeGraphsUnequal() throws Exception {
        Graph first = createGraph(AdjList.class);
        Graph second = createGraph(AdjMatrix.class);

        second.rmvEdge("A", "B");

        assertNotEquals(first, second);
    }

    @Test
    void differentVerticesMakeGraphsUnequal() {
        Graph first = new AdjList();
        Graph second = new AdjMatrix();

        first.addVertex("A");
        second.addVertex("B");

        assertNotEquals(first, second);
    }

    @Test
    void graphEqualsItself() {
        Graph graph = new AdjList();

        assertEquals(graph, graph);
    }

    @Test
    void graphIsNotEqualToNullOrAnotherType() {
        Graph graph = new AdjList();

        assertNotEquals(graph, null);
        assertNotEquals(graph, "graph");
    }

    @Test
    void equalGraphsHaveEqualHashCodes() throws Exception {
        Graph first = createGraph(AdjList.class);
        Graph second = createGraph(AdjMatrix.class);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void toStringContainsVerticesAndEdges() {
        Graph graph = new AdjList();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        String result = graph.toString();

        assertTrue(result.contains("A -> B"));
        assertTrue(result.contains("B -> []"));
    }

    @Test
    void emptyGraphHasExpectedString() {
        Graph graph = new AdjList();

        assertEquals("(empty graph)", graph.toString());
    }

    @Test
    void graphUtilsEqualsHandlesSameReference() {
        Graph graph = new AdjList();

        assertTrue(Utils.equals(graph, graph));
    }

    @Test
    void graphUtilsEqualsRejectsNonGraph() {
        Graph graph = new AdjList();

        assertFalse(Utils.equals(graph, "not a graph"));
    }

    @Test
    void graphUtilsToStringHandlesEmptyGraph() {
        Graph graph = new AdjMatrix();

        assertEquals("(empty graph)", Utils.toString(graph));
    }
}