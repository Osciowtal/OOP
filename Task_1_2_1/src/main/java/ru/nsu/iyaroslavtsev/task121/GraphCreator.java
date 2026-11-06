package ru.nsu.iyaroslavtsev.task121;

/**
 * Creates graph implementations.
 */
public class GraphCreator {

    public static <T> Graph<T> create(String representation) {
        switch (representation.trim()) {
            case "AdjList":
                return new AdjList<T>();

            case "AdjMatrix":
                return new AdjMatrix<T>();

            case "IncidMatrix":
                return new IncidMatrix<T>();

            default:
                throw new IllegalArgumentException(
                        "Unknown graph representation: " + representation
                );
        }
    }
}