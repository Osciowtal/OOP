package ru.nsu.iyaroslavtsev.task121;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for Adjacency List.
 */
public class AdjList<T> implements Graph<T> {
    /**
     * adjList.
     */
    private final Map<T, List<T>> adjList = new HashMap<>();

    @Override
    public void addVertex(T v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    @Override
    public void rmvVertex(T v) {
        adjList.remove(v);

        for (List<T> neighbors : adjList.values()) {
            neighbors.remove(v);
        }
    }

    @Override
    public void addEdge(T src, T dst) {
        if (!adjList.containsKey(src) || !adjList.containsKey(dst)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        if (!adjList.get(src).contains(dst)) {
            adjList.get(src).add(dst);
        }
    }

    @Override
    public void rmvEdge(T src, T dst) {
        if (adjList.containsKey(src)) {
            adjList.get(src).remove(dst);
        }
    }

    @Override
    public List<T> getNeighbors(T v) {
        if (!adjList.containsKey(v)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        return new ArrayList<>(adjList.get(v));
    }

    @Override
    public List<T> getVertices() {
        return new ArrayList<>(adjList.keySet());
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