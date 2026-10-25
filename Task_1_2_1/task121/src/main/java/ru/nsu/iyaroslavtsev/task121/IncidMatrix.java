package ru.nsu.iyaroslavtsev.task121;

import java.util.ArrayList;
import java.util.List;

/**
 * Incidence Matrix.
 */
public class IncidMatrix implements Graph {
    private final List<String> vertices = new ArrayList<>();
    private final List<String[]> edges = new ArrayList<>();
    private final List<List<Integer>> matrix = new ArrayList<>();

    @Override
    public void addVertex(String v) {
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
    public void rmvVertex(String v) {
        int vertexIndex = vertices.indexOf(v);

        if (vertexIndex == -1) {
            return;
        }

        for (int i = edges.size() - 1; i >= 0; i--) {
            String[] edge = edges.get(i);

            if (edge[0].equals(v) || edge[1].equals(v)) {
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
    public void addEdge(String src, String dst) {
        if (!vertices.contains(src) || !vertices.contains(dst)) {
            throw new IllegalArgumentException(
                    "Vertex does not exist"
            );
        }

        for (String[] edge : edges) {
            if (edge[0].equals(src) && edge[1].equals(dst)) {
                return;
            }
        }

        edges.add(new String[]{src, dst});

        for (int i = 0; i < vertices.size(); i++) {
            String vertex = vertices.get(i);
            int value = 0;

            if (vertex.equals(src)) {
                value = -1;
            } else if (vertex.equals(dst)) {
                value = 1;
            }

            matrix.get(i).add(value);
        }
    }

    @Override
    public void rmvEdge(String src, String dst) {
        for (int i = 0; i < edges.size(); i++) {
            String[] edge = edges.get(i);

            if (edge[0].equals(src) && edge[1].equals(dst)) {
                edges.remove(i);

                for (List<Integer> row : matrix) {
                    row.remove(i);
                }

                return;
            }
        }
    }

    @Override
    public List<String> getNeighbors(String v) {
        int vertexIndex = vertices.indexOf(v);

        if (vertexIndex == -1) {
            throw new IllegalArgumentException(
                    "Vertex does not exist: " + v
            );
        }

        List<String> neighbors = new ArrayList<>();

        for (int i = 0; i < edges.size(); i++) {
            if (matrix.get(vertexIndex).get(i) == -1) {
                neighbors.add(edges.get(i)[1]);
            }
        }

        return neighbors;
    }

    @Override
    public List<String> getVertices() {
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