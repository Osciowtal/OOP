package ru.nsu.iyaroslavtsev.task121;

/**
 * Converts vertex values read from a file.
 */
public class VertexParse {
    /**
     * String is the vertex itself if the vertex type is String.
     *
     * @param value vertex String
     * @return result
     */
    public static String parseString(String value) {
        return value;
    }

    /**
     * The same thing but now we want to get an Integer as vertex representation.
     *
     * @param value vertex String
     * @return result
     */
    public static Integer parseInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid integer vertex: " + value, e
            );
        }
    }
}