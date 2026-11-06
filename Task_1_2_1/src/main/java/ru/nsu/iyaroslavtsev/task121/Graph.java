package ru.nsu.iyaroslavtsev.task121;

import java.util.List;

/**
 * Graph interface.
 */
public interface Graph<T> {
    void addVertex(T v);

    void rmvVertex(T v);

    void addEdge(T src, T dst);

    void rmvEdge(T src, T dst);

    List<T> getNeighbors(T v);

    List<T> getVertices();

    boolean equals(Object obj);

    int hashCode();

    String toString();
}