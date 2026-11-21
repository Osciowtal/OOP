package ru.nsu.iyaroslavtsev.task131;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


class SubstringTest {

    @TempDir
    Path tempDir;
    private Path createFile(String filename, String content)
            throws IOException {
        Path path = tempDir.resolve(filename);
        Files.writeString(path, content, StandardCharsets.UTF_8);
        return path;
    }

    @Test
    void findsOccurrences() throws IOException {
        Path file = createFile("input.txt", "абракадабра");
        assertEquals(
                List.of(1L, 8L),
                Substring.find(file.toString(), "бра")
        );
    }

    @Test
    void returnsEmptyListWhenNoOccurrences() throws IOException {
        Path file = createFile("input.txt", "абракадабра");
        assertEquals(
                List.of(),
                Substring.find(file.toString(), "xyz")
        );
    }

    @Test
    void findsEntireFile() throws IOException {
        Path file = createFile("input.txt", "абра");
        assertEquals(
                List.of(0L),
                Substring.find(file.toString(), "абра")
        );
    }

    @Test
    void findsOverlappingOccurrences() throws IOException {
        Path file = createFile("input.txt", "ааааа");
        assertEquals(
                List.of(0L, 1L, 2L, 3L),
                Substring.find(file.toString(), "аа")
        );
    }

    @Test
    void findsSingleCharacterOccurrences() throws IOException {
        Path file = createFile("input.txt", "ааааа");
        assertEquals(
                List.of(0L, 1L, 2L, 3L, 4L),
                Substring.find(file.toString(), "а")
        );
    }

    @Test
    void handlesUnicodeCodePoints() throws IOException {
        Path file = createFile("input.txt", "A😊B😊C");
        assertEquals(
                List.of(1L, 3L),
                Substring.find(file.toString(), "😊")
        );
    }

    @Test
    void handlesDifferentAlphabets() throws IOException {
        Path file = createFile("input.txt", "AБ中ش");
        assertEquals(
                List.of(1L),
                Substring.find(file.toString(), "Б中")
        );
    }

    @Test
    void handlesEmptyFile() throws IOException {
        Path file = createFile("input.txt", "");
        assertEquals(
                List.of(),
                Substring.find(file.toString(), "abc")
        );
    }

    @Test
    void handlesLongerTarget() throws IOException {
        Path file = createFile("input.txt", "аб");
        assertEquals(
                List.of(),
                Substring.find(file.toString(), "абра")
        );
    }

    @Test
    void rejectsEmptyTarget() throws IOException {
        Path file = createFile("input.txt", "абра");
        assertThrows(
                IllegalArgumentException.class,
                () -> Substring.find(file.toString(), "")
        );
    }

    @Test
    void rejectsNullArguments() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Substring.find(null, "abc")
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> Substring.find("input.txt", null)
        );
    }

    @Test
    void throwsExceptionWhenFileDoesNotExist() {
        Path file = tempDir.resolve("missing.txt");
        assertThrows(
                IOException.class,
                () -> Substring.find(file.toString(), "abc")
        );
    }

    @Test
    void handlesBufferBoundary() throws IOException {
        String prefix = "a".repeat(8191);
        Path file = createFile("input.txt", prefix + "😊X");
        assertEquals(
                List.of(8191L),
                Substring.find(file.toString(), "😊X")
        );
    }

    @Test
    void handlesLargeFile() throws IOException {
        Path file = tempDir.resolve("large.txt");
        char[] block = new char[8192];
        Arrays.fill(block, 'a');
        int blocks = 10000;
        long expectedIndex = (long) block.length * blocks;

        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardCharsets.UTF_8
        )) {
            for (int i = 0; i < blocks; i++) {
                writer.write(block);
            }

            writer.write("XYZ");
        }
        assertEquals(
                List.of(expectedIndex),
                Substring.find(file.toString(), "XYZ")
        );
    }
}