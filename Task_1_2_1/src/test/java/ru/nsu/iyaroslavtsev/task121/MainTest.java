package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MainTest {

    @TempDir
    Path tempDir;

    private Path createFile(String content) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.write(file, content.getBytes(StandardCharsets.UTF_8));
        return file;
    }

    private String runAndCaptureOutput(String content) throws IOException {
        Path file = createFile(content);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true));
            Main.main(new String[]{file.toAbsolutePath().toString()});
            return output.toString();
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void runsStringGraph() throws IOException {
        String output = runAndCaptureOutput(
                "String\nAdjList\nA B C\nA B\nB C\n"
        );

        assertTrue(output.contains("Graph:"));
        assertTrue(output.contains("A -> B"));
        assertTrue(output.contains("Topsort:"));
        assertTrue(output.contains("[A, B, C]"));
        assertTrue(output.contains("Graphs are equal: true"));
    }

    @Test
    void runsIntegerGraph() throws IOException {
        String output = runAndCaptureOutput(
                "Integer\nIncidMatrix\n1 2 3\n1 2\n2 3\n"
        );

        assertTrue(output.contains("1 -> 2"));
        assertTrue(output.contains("[1, 2, 3]"));
        assertTrue(output.contains("Graphs are equal: true"));
    }

    @Test
    void throwsWhenNoArgumentsProvided() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Main.main(new String[0])
        );

        assertEquals("Specify filepath", exception.getMessage());
    }

    @Test
    void throwsWhenPathIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> Main.main(new String[]{"   "}));
    }

    @Test
    void throwsWhenFileDoesNotExist() {
        Path missingFile = tempDir.resolve("missing.txt");

        assertThrows(FileNotFoundException.class,
                () -> Main.main(new String[]{missingFile.toString()}));
    }

    @Test
    void throwsWhenFileIsEmpty() throws IOException {
        Path file = createFile("");

        IOException exception = assertThrows(
                IOException.class,
                () -> Main.main(new String[]{file.toString()})
        );

        assertEquals("File is empty.", exception.getMessage());
    }

    @Test
    void throwsForUnknownVertexType() throws IOException {
        Path file = createFile("Float\nAdjList\n1 2\n");

        IOException exception = assertThrows(
                IOException.class,
                () -> Main.main(new String[]{file.toString()})
        );

        assertTrue(exception.getMessage().contains("Unknown vertex type"));
    }

    @Test
    void throwsWhenGraphRepresentationIsMissing() throws IOException {
        Path file = createFile("String\n");

        IOException exception = assertThrows(
                IOException.class,
                () -> Main.main(new String[]{file.toString()})
        );

        assertEquals("Missing graph representation.", exception.getMessage());
    }

    @Test
    void throwsForUnknownGraphRepresentation() throws IOException {
        Path file = createFile("String\nUnknownGraph\nA B\n");

        assertThrows(IllegalArgumentException.class,
                () -> Main.main(new String[]{file.toString()}));
    }

    @Test
    void throwsWhenVertexLineIsMissing() throws IOException {
        Path file = createFile("String\nAdjList\n");

        assertThrows(IOException.class,
                () -> Main.main(new String[]{file.toString()}));
    }

    @Test
    void throwsForInvalidIntegerVertex() throws IOException {
        Path file = createFile("Integer\nAdjList\n1 ABC\n");

        IOException exception = assertThrows(
                IOException.class,
                () -> Main.main(new String[]{file.toString()})
        );

        assertTrue(exception.getMessage().contains("Invalid vertex"));
    }

    @Test
    void throwsWhenGraphContainsCycle() throws IOException {
        Path file = createFile("String\nAdjList\nA B\nA B\nB A\n");

        assertThrows(IllegalArgumentException.class,
                () -> Main.main(new String[]{file.toString()}));
    }
}