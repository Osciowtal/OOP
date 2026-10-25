package ru.nsu.iyaroslavtsev.task121;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;


class MainTest {

    @TempDir
    Path tempDir;

    @Test
    void mainPrintsGraphTopologicalSortAndEquality() throws IOException {
        Path file = tempDir.resolve("graph.txt");

        Files.writeString(file, "A B C\nA B\nB C\n");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));

            Main.main(new String[]{file.toString()});
        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Graph:"));
        assertTrue(result.contains("Topological sort:"));
        assertTrue(result.contains("Graphs are equal: true"));

        assertTrue(result.indexOf("A") < result.indexOf("B"));
        assertTrue(result.indexOf("B") < result.indexOf("C"));
    }
}