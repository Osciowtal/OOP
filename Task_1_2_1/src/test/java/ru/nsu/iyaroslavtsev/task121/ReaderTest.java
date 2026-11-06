package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.junit.jupiter.api.Test;

class ReaderTest {

    private BufferedReader readerFor(String content) {
        return new BufferedReader(new StringReader(content));
    }

    @Test
    void readsStringVerticesAndEdges() throws IOException {
        Graph<String> graph = new AdjList<>();

        BufferedReader reader = readerFor(
                "A B C\nA B\nB C\n"
        );

        Reader.read(reader, graph, vertexParse::parseString);

        assertEquals(List.of("B"), graph.getNeighbors("A"));
        assertEquals(List.of("C"), graph.getNeighbors("B"));
        assertTrue(graph.getNeighbors("C").isEmpty());
    }

    @Test
    void readsIntegerVerticesAndEdges() throws IOException {
        Graph<Integer> graph = new AdjMatrix<>();

        BufferedReader reader = readerFor(
                "10 20 30\n10 20\n20 30\n"
        );

        Reader.read(reader, graph, vertexParse::parseInteger);

        assertEquals(List.of(10, 20, 30), graph.getVertices());
        assertEquals(List.of(20), graph.getNeighbors(10));
        assertEquals(List.of(30), graph.getNeighbors(20));
    }

    @Test
    void skipsEmptyEdgeLines() throws IOException {
        Graph<String> graph = new AdjList<>();

        BufferedReader reader = readerFor(
                "A B\n\nA B\n   \n"
        );

        Reader.read(reader, graph, vertexParse::parseString);

        assertEquals(List.of("B"), graph.getNeighbors("A"));
    }

    @Test
    void acceptsEmptyVertexLine() throws IOException {
        Graph<String> graph = new AdjList<>();

        BufferedReader reader = readerFor("   \n");

        Reader.read(reader, graph, vertexParse::parseString);

        assertTrue(graph.getVertices().isEmpty());
    }

    @Test
    void throwsWhenVertexLineIsMissing() {
        Graph<String> graph = new AdjList<>();

        BufferedReader reader = readerFor("");

        assertThrows(IOException.class,
                () -> Reader.read(reader, graph, vertexParse::parseString));
    }

    @Test
    void throwsForInvalidVertex() {
        Graph<Integer> graph = new AdjList<>();

        BufferedReader reader = readerFor("1 ABC\n");

        IOException exception = assertThrows(
                IOException.class,
                () -> Reader.read(reader, graph, vertexParse::parseInteger)
        );

        assertTrue(exception.getMessage().contains("Invalid vertex: ABC"));
        assertInstanceOf(IllegalArgumentException.class, exception.getCause());
    }

    @Test
    void throwsForMalformedEdge() {
        Graph<String> graph = new AdjList<>();

        BufferedReader reader = readerFor("A B C\nA B C\n");

        IOException exception = assertThrows(
                IOException.class,
                () -> Reader.read(reader, graph, vertexParse::parseString)
        );

        assertTrue(exception.getMessage().contains("Invalid edge format"));
    }

    @Test
    void throwsForInvalidIntegerInEdge() {
        Graph<Integer> graph = new AdjList<>();

        BufferedReader reader = readerFor("1 2\n1 ABC\n");

        IOException exception = assertThrows(
                IOException.class,
                () -> Reader.read(reader, graph, vertexParse::parseInteger)
        );

        assertTrue(exception.getMessage().contains("Invalid edge"));
    }

    @Test
    void throwsWhenEdgeReferencesUnknownVertex() {
        Graph<String> graph = new AdjList<>();

        BufferedReader reader = readerFor("A B\nA C\n");

        IOException exception = assertThrows(
                IOException.class,
                () -> Reader.read(reader, graph, vertexParse::parseString)
        );

        assertTrue(exception.getMessage().contains("unknown vertex"));
        assertInstanceOf(IllegalArgumentException.class, exception.getCause());
    }
}