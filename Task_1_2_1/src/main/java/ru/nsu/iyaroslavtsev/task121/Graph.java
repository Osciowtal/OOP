package ru.nsu.iyaroslavtsev.task121;

import java.util.List;

/**
 * Graph interface.
 */
public interface Graph {
    void addVertex(String v);
    void rmvVertex(String v);

    void addEdge(String src, String dst);
    void rmvEdge(String src, String dst);

    List<String> getNeighbors(String v);
    List<String> getVertices();

    boolean equals(Object obj);
    int hashCode();
    String toString();
}