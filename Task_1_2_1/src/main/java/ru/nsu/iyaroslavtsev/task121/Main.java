package ru.nsu.iyaroslavtsev.task121;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main class.
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args command line arguments; the first is the filename - use an ABSOLUTE PATH
     * @throws IOException if the file cannot be read
     */
    public static void main(String[] args) throws IOException {

        if (args.length == 0 || args[0].isBlank()) {
            throw new IllegalArgumentException("Specify filepath");
        }

        String filePath = args[0];
        FileReader fileReadr = new FileReader(filePath);
        BufferedReader buffReadr = new BufferedReader(fileReadr);

        try (buffReadr) {
            String strVrtxType = buffReadr.readLine();
            if (strVrtxType == null) {
                throw new IOException("File is empty.");
            }

            VertexType vrtxType;
            try {
                vrtxType = VertexType.valueOf(
                        strVrtxType.trim().toUpperCase()
                );
            } catch (IllegalArgumentException e) {
                throw new IOException("Unknown vertex type: " + strVrtxType, e);
            }

            String graphRepresentation = buffReadr.readLine();
            if (graphRepresentation == null || graphRepresentation.isBlank()) {
                throw new IOException("Missing graph representation.");
            }
            graphRepresentation = graphRepresentation.trim();

            switch (vrtxType) {
                case STRING: {
                    Graph<String> graph = GraphCreator.create(graphRepresentation);
                    Reader.read(buffReadr, graph, VertexParse::parseString);
                    run(graph);
                    break;
                }
                case INTEGER: {
                    Graph<Integer> graph = GraphCreator.create(graphRepresentation);
                    Reader.read(buffReadr, graph, VertexParse::parseInteger);
                    run(graph);
                    break;
                }
                default:
                    throw new IOException("Vertex type error.");
            }
        }
    }

    private static <T> void run(Graph<T> graph) {
        System.out.println("Graph:");
        System.out.println(graph);

        System.out.println("Topsort:");
        System.out.println(Kahn.sort(graph));

        Graph<T> graphTwin = new AdjMatrix<>();
        for (T v : graph.getVertices()) {
            graphTwin.addVertex(v);
        }
        for (T vsrc : graph.getVertices()) {
            for (T vdst : graph.getNeighbors(vsrc)) {
                graphTwin.addEdge(vsrc, vdst);
            }
        }

        System.out.println("Graphs are equal: " + graph.equals(graphTwin));
    }
}