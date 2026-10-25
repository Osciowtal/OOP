package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void readsValidGraph() throws IOException {
        Path file = tempDir.resolve("graph.txt");

        Files.writeString(file,
                "A B C\n"
                        + "A B\n"
                        + "B C\n");

        Graph graph = Reader.read(file.toString());

        assertEquals(3, graph.getVertices().size());
        assertEquals(java.util.List.of("B"), graph.getNeighbors("A"));
        assertEquals(java.util.List.of("C"), graph.getNeighbors("B"));
    }

    @Test
    void readsGraphWithNoEdges() throws IOException {
        Path file = tempDir.resolve("graph.txt");

        Files.writeString(file, "A B C\n");

        Graph graph = Reader.read(file.toString());

        assertEquals(3, graph.getVertices().size());
        assertTrue(graph.getNeighbors("A").isEmpty());
    }

    @Test
    void readsEmptyVertexLine() throws IOException {
        Path file = tempDir.resolve("graph.txt");

        Files.writeString(file, "\n");

        Graph graph = Reader.read(file.toString());

        assertTrue(graph.getVertices().isEmpty());
    }

    @Test
    void skipsEmptyLinesBetweenEdges() throws IOException {
        Path file = tempDir.resolve("graph.txt");

        Files.writeString(file,
                "A B\n"
                        + "\n"
                        + "A B\n");

        Graph graph = Reader.read(file.toString());

        assertEquals(java.util.List.of("B"), graph.getNeighbors("A"));
    }

    @Test
    void readEmptyFileThrowsException() throws IOException {
        Path file = tempDir.resolve("empty.txt");
        Files.write(file, new byte[0]);

        assertThrows(IOException.class, () -> Reader.read(file.toString()));
    }

    @Test
    void readMalformedEdgeThrowsException() throws IOException {
        Path file = tempDir.resolve("malformed.txt");
        Files.writeString(file, "A B\nA");

        assertThrows(IOException.class, () -> Reader.read(file.toString()));
    }

    @Test
    void readEdgeWithUnknownVertexThrowsException() throws IOException {
        Path file = tempDir.resolve("unknown.txt");
        Files.writeString(file, "A B\nA C");

        assertThrows(IOException.class, () -> Reader.read(file.toString()));
    }

    @Test
    void readNonexistentFileThrowsException() {
        Path file = tempDir.resolve("nonexistent.txt");

        assertThrows(IOException.class, () -> Reader.read(file.toString()));
    }
}