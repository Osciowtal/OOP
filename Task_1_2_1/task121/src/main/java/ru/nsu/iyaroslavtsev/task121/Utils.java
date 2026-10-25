package ru.nsu.iyaroslavtsev.task121;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Helpful class for comparing graphs, getting hashCode and toString.
 */
public final class Utils {
    /**
     * Special thing.
     */
    private Utils() {
    }

    public static boolean equals(Graph graph, Object obj) {
        if (graph == obj) {
            return true;
        }

        if (!(obj instanceof Graph)) {
            return false;
        }

        Graph other = (Graph) obj;

        Set<String> vertices = new HashSet<>(graph.getVertices());
        Set<String> otherVertices = new HashSet<>(other.getVertices());

        if (!vertices.equals(otherVertices)) {
            return false;
        }

        for (String vertex : vertices) {
            Set<String> neighbors =
                    new HashSet<>(graph.getNeighbors(vertex));

            Set<String> otherNeighbors =
                    new HashSet<>(other.getNeighbors(vertex));

            if (!neighbors.equals(otherNeighbors)) {
                return false;
            }
        }

        return true;
    }

    public static int hashCode(Graph graph) {
        return toString(graph).hashCode();
    }

    public static String toString(Graph graph) {
        List<String> vertices = new ArrayList<>(graph.getVertices());
        Collections.sort(vertices);

        if (vertices.isEmpty()) {
            return "(empty graph)";
        }

        StringBuilder result = new StringBuilder();

        for (String vertex : vertices) {
            List<String> neighbors =
                    new ArrayList<>(graph.getNeighbors(vertex));

            Collections.sort(neighbors);

            result.append(vertex)
                    .append(" -> ");

            if (neighbors.isEmpty()) {
                result.append("[]");
            } else {
                result.append(String.join(", ", neighbors));
            }

            result.append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}