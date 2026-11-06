package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GraphCreatorTest {

    @Test
    void createsAdjList() {
        Graph<String> graph = GraphCreator.create("AdjList");

        assertInstanceOf(AdjList.class, graph);
    }

    @Test
    void createsAdjMatrix() {
        Graph<Integer> graph = GraphCreator.create("AdjMatrix");

        assertInstanceOf(AdjMatrix.class, graph);
    }

    @Test
    void createsIncidMatrix() {
        Graph<String> graph = GraphCreator.create("IncidMatrix");

        assertInstanceOf(IncidMatrix.class, graph);
    }

    @Test
    void trimsRepresentation() {
        Graph<String> graph = GraphCreator.create("  AdjList  ");

        assertInstanceOf(AdjList.class, graph);
    }

    @Test
    void throwsForUnknownRepresentation() {
        assertThrows(IllegalArgumentException.class,
                () -> GraphCreator.create("UnknownGraph"));
    }
}