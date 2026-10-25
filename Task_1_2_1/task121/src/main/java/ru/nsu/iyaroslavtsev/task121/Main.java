package ru.nsu.iyaroslavtsev.task121;

import java.io.IOException;

/**
 * Main class.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Graph graph = Reader.read(args[0]);

        System.out.println("Graph:");
        System.out.println(graph);

        System.out.println("Topological sort:");
        System.out.println(Kahn.sort(graph));

        Graph other = new AdjMatrix();

        for (String v : graph.getVertices()) {
            other.addVertex(v);
        }

        for (String v : graph.getVertices()) {
            for (String neighbor : graph.getNeighbors(v)) {
                other.addEdge(v, neighbor);
            }
        }

        System.out.println("Graphs are equal: " + graph.equals(other));
    }
}