package ru.nsu.iyaroslavtsev.task121;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Function;

/**
 * Class for reading graphs from a file.
 */
public class Reader {

    /**
     * Reads vertices and edges from a file.
     *
     * @param reader the reader to read from
     * @param graph the graph to fill
     * @param parser the function for parsing vertices
     * @throws IOException if the file format is invalid
     */
    public static <T> void read(
            BufferedReader reader,
            Graph<T> graph,
            Function<String, T> parser) throws IOException {

        String verts = reader.readLine();

        if (verts == null) {
            throw new IOException("Missing vertices.");
        }

        if (!verts.isBlank()) {
            String[] vertices = verts.trim().split("\\s+");

            for (String v : vertices) {
                try {
                    graph.addVertex(parser.apply(v));
                } catch (RuntimeException e) {
                    throw new IOException("Invalid vertex: " + v, e);
                }
            }
        }

        String edgeline;
        while ((edgeline = reader.readLine()) != null) {
            edgeline = edgeline.trim();

            if (edgeline.isEmpty()) {
                continue;
            }

            String[] edge = edgeline.split("\\s+");

            if (edge.length != 2) {
                throw new IOException("Invalid edge format: " + edgeline);
            }

            T src;
            T dst;

            try {
                src = parser.apply(edge[0]);
                dst = parser.apply(edge[1]);
            } catch (RuntimeException e) {
                throw new IOException("Invalid edge: " + edgeline, e);
            }

            try {
                graph.addEdge(src, dst);
            } catch (IllegalArgumentException e) {
                throw new IOException("Edge references an unknown vertex: " + edgeline, e);
            }
        }
    }
}