package ru.nsu.iyaroslavtsev.task121;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Incidence Matrix.
 */
public class IncidMatrix<T> implements Graph<T> {
    private final List<T> vertices = new ArrayList<>();
    private final List<Edge<T>> edges = new ArrayList<>();
    private final List<List<Integer>> matrix = new ArrayList<>();

    private static class Edge<T> {
        private final T src;
        private final T dst;

        Edge(T src, T dst) {
            this.src = src;
            this.dst = dst;
        }
    }

    @Override
    public void addVertex(T v) {
        if (vertices.contains(v)) {
            return;
        }

        vertices.add(v);

        List<Integer> row = new ArrayList<>();

        for (int i = 0; i < edges.size(); i++) {
            row.add(0);
        }

        matrix.add(row);
    }

    @Override
    public void rmvVertex(T v) {
        int vertexIndex = vertices.indexOf(v);

        if (vertexIndex == -1) {
            return;
        }

        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge<T> edge = edges.get(i);

            if (Objects.equals(edge.src, v) || Objects.equals(edge.dst, v)) {
                edges.remove(i);

                for (List<Integer> row : matrix) {
                    row.remove(i);
                }
            }
        }

        vertices.remove(vertexIndex);
        matrix.remove(vertexIndex);
    }

    @Override
    public void addEdge(T src, T dst) {
        if (!vertices.contains(src) || !vertices.contains(dst)) {
            throw new IllegalArgumentException(
                    "Vertex does not exist"
            );
        }

        for (Edge<T> edge : edges) {
            if (Objects.equals(edge.src, src) && Objects.equals(edge.dst, dst)) {
                return;
            }
        }

        edges.add(new Edge<>(src, dst));

        for (int i = 0; i < vertices.size(); i++) {
            T vertex = vertices.get(i);
            int value = 0;

            if (Objects.equals(vertex, src)) {
                value = -1;
            } else if (Objects.equals(vertex, dst)) {
                value = 1;
            }

            matrix.get(i).add(value);
        }
    }

    @Override
    public void rmvEdge(T src, T dst) {
        for (int i = 0; i < edges.size(); i++) {
            Edge<T> edge = edges.get(i);

            if (Objects.equals(edge.src, src) && Objects.equals(edge.dst, dst)) {
                edges.remove(i);

                for (List<Integer> row : matrix) {
                    row.remove(i);
                }

                return;
            }
        }
    }

    @Override
    public List<T> getNeighbors(T v) {
        int vertexIndex = vertices.indexOf(v);

        if (vertexIndex == -1) {
            throw new IllegalArgumentException(
                    "Vertex does not exist: " + v
            );
        }

        List<T> neighbors = new ArrayList<>();

        for (int i = 0; i < edges.size(); i++) {
            if (matrix.get(vertexIndex).get(i) == -1) {
                neighbors.add(edges.get(i).dst);
            }
        }

        return neighbors;
    }

    @Override
    public List<T> getVertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    public boolean equals(Object obj) {
        return Utils.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return Utils.hashCode(this);
    }

    @Override
    public String toString() {
        return Utils.toString(this);
    }
}