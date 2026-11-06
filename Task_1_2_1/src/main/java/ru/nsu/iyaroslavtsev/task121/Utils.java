package ru.nsu.iyaroslavtsev.task121;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    /**
     * Compares two graphs for equality.
     *
     * @param graph the first graph
     * @param obj the second object to compare
     * @return true if the graphs are equal, false otherwise
     */
    public static <T> boolean equals(Graph<T> graph, Object obj) {
        if (graph == obj) {
            return true;
        }

        if (!(obj instanceof Graph<?>)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Graph<T> other = (Graph<T>) obj;

        Set<T> vertices = new HashSet<>(graph.getVertices());
        Set<T> otherVertices = new HashSet<>(other.getVertices());

        if (!vertices.equals(otherVertices)) {
            return false;
        }

        for (T vertex : vertices) {
            Set<T> neighbors =
                    new HashSet<>(graph.getNeighbors(vertex));

            Set<T> otherNeighbors =
                    new HashSet<>(other.getNeighbors(vertex));

            if (!neighbors.equals(otherNeighbors)) {
                return false;
            }
        }

        return true;
    }

    public static int hashCode(Graph<?> graph) {
        return toString(graph).hashCode();
    }

    /**
     * Returns a string representation of the graph.
     *
     * @param graph the graph to convert
     * @return string representation of the graph
     */
    public static <T> String toString(Graph<T> graph) {
        List<T> vertices = new ArrayList<>(graph.getVertices());

        vertices.sort(Comparator.comparing(String::valueOf));

        if (vertices.isEmpty()) {
            return "(empty graph)";
        }

        StringBuilder result = new StringBuilder();

        for (T vertex : vertices) {
            List<String> neighbors = new ArrayList<>();

            for (T neighbor : graph.getNeighbors(vertex)) {
                neighbors.add(String.valueOf(neighbor));
            }

            Collections.sort(neighbors);

            result.append(vertex).append(" -> ");

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