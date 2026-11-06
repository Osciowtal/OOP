package ru.nsu.iyaroslavtsev.task121;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Kahn class.
 */
public class Kahn {

    /**
     * Performs topological sort using Kahn's algorithm.
     *
     * @param graph the graph to sort
     * @return list of vertices in topological order
     * @throws IllegalArgumentException if the graph contains a cycle
     */
    public static <T> List<T> sort(Graph<T> graph) {
        List<T> vertices = graph.getVertices();

        // 1. Calculate inDegree for each vertex
        Map<T, Integer> inDegree = new HashMap<>();

        for (T v : vertices) {
            inDegree.put(v, 0);
        }

        for (T v : vertices) {
            for (T neighbor : graph.getNeighbors(v)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // 2. Find vertices with inDegree == 0
        Queue<T> queue = new ArrayDeque<>();

        for (T v : vertices) {
            if (inDegree.get(v) == 0) {
                queue.add(v);
            }
        }

        // 3. Topological order
        List<T> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            T v = queue.remove();
            result.add(v);

            for (T neighbor : graph.getNeighbors(v)) {
                int degree = inDegree.get(neighbor) - 1;
                inDegree.put(neighbor, degree);

                if (degree == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Cycle-check
        if (result.size() != vertices.size()) {
            throw new IllegalArgumentException(
                    "Graph contains a cycle. Topsort is impossible."
            );
        }

        return result;
    }
}