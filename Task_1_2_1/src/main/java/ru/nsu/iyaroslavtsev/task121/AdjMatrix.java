package ru.nsu.iyaroslavtsev.task121;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Adjacency Matrix.
 */
public class AdjMatrix implements Graph {
    private final List<String> vertices = new ArrayList<>();
    private final List<List<Boolean>> matrix = new ArrayList<>();

    @Override
    public void addVertex(String v) {
        if (vertices.contains(v)) {
            return;
        }

        vertices.add(v);

        for (List<Boolean> row : matrix) {
            row.add(false);
        }

        List<Boolean> newRow = new ArrayList<>();

        for (int i = 0; i < vertices.size(); i++) {
            newRow.add(false);
        }

        matrix.add(newRow);
    }

    @Override
    public void rmvVertex(String v) {
        int index = vertices.indexOf(v);

        if (index == -1) {
            return;
        }

        vertices.remove(index);
        matrix.remove(index);

        for (List<Boolean> row : matrix) {
            row.remove(index);
        }
    }

    @Override
    public void addEdge(String src, String dst) {
        int srcIndex = vertices.indexOf(src);
        int dstIndex = vertices.indexOf(dst);

        if (srcIndex == -1 || dstIndex == -1) {
            throw new IllegalArgumentException(
                    "Vertex does not exist"
            );
        }

        matrix.get(srcIndex).set(dstIndex, true);
    }

    @Override
    public void rmvEdge(String src, String dst) {
        int srcIndex = vertices.indexOf(src);
        int dstIndex = vertices.indexOf(dst);

        if (srcIndex == -1 || dstIndex == -1) {
            return;
        }

        matrix.get(srcIndex).set(dstIndex, false);
    }

    @Override
    public List<String> getNeighbors(String v) {
        int index = vertices.indexOf(v);

        if (index == -1) {
            throw new IllegalArgumentException(
                    "Vertex does not exist: " + v
            );
        }

        List<String> neighbors = new ArrayList<>();

        for (int i = 0; i < vertices.size(); i++) {
            if (matrix.get(index).get(i)) {
                neighbors.add(vertices.get(i));
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