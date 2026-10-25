package ru.nsu.iyaroslavtsev.task121;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for reading graphs from a file.
 */
public class Reader {

    /**
     * Reads a graph from a file.
     *
     * @param filename the file to read from.
     * @return the constructed graph.
     * @throws IOException if an I/O error occurs or the file format is invalid.
     */
    public static Graph read(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(filename))) {

            String line = reader.readLine();

            if (line == null) {
                throw new IOException("File is empty");
            }

            Graph graph = new AdjList();

            String[] vertices = line.trim().split("\\s+");

            if (!line.isBlank()) {
                for (String vertex : vertices) {
                    graph.addVertex(vertex);
                }
            }

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] edge = line.split("\\s+");

                if (edge.length != 2) {
                    throw new IOException(
                            "Invalid edge format: " + line
                    );
                }

                String src = edge[0];
                String dst = edge[1];

                try {
                    graph.addEdge(src, dst);
                } catch (IllegalArgumentException e) {
                    throw new IOException(
                            "Edge references an unknown vertex: " + line, e
                    );
                }
            }
            return graph;
        }
    }
}