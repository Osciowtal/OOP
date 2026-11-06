package ru.nsu.iyaroslavtsev.task121;

/**
 * Converts vertex values read from a file.
 */
public class vertexParse {

    public static String parseString(String value) {
        return value;
    }

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