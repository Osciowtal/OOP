package ru.nsu.iyaroslavtsev.task131;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Substring search in a file.
 */
public class Substring {
    /**
     * Public wrap over the find function.
     *
     * @param filepath filepath
     * @param target substring you'd like to find
     * @return list of indexes where substrings were detected
     * @throws IOException IOE
     */
    public static List<Long> find(String filepath, String target) throws IOException {
        List<Long> res = new ArrayList<>();
        innerFind(filepath, target, res);
        return res;
    }

    /**
     * Real find function, uses KMP algorithm.
     *
     * @param fpath filepath
     * @param trgt substring
     * @param res list where to put indexes
     * @throws IOException IOE
     */
    private static void innerFind(String fpath, String trgt, List<Long> res) throws IOException {
        if (fpath == null || trgt == null) {
            throw new IllegalArgumentException("Filepath and target must be not null");
        }

        int[] targetpoints = trgt.codePoints().toArray();
        if (targetpoints.length == 0) {
            throw new IllegalArgumentException("Target must be not empty");
        }

        int[] lps = longestPreffixWhichIsAlsoSuffix(targetpoints);
        int matched = 0;
        long index = 0;

        BufferedReader reader = Files.newBufferedReader(Path.of(fpath), StandardCharsets.UTF_8);
        try (reader) {

            int codepoint;
            while ((codepoint = readCodepoint(reader)) != -1) {

                while (matched > 0 && targetpoints[matched] != codepoint) {
                    matched = lps[matched - 1];
                }

                if (targetpoints[matched] == codepoint) {
                    matched++;
                }

                if (matched == targetpoints.length) {
                    res.add(index - matched + 1);
                    matched = lps[matched - 1];
                }

                index++;
            }
        }
    }

    /**
     * LPS builder.
     *
     * @param codepoints target substring converted to codepoints
     * @return array of lps for every point
     */
    private static int[] longestPreffixWhichIsAlsoSuffix(int[] codepoints) {
        int[] lps = new int[codepoints.length];

        for (int i = 1; i < codepoints.length; i++) {
            int len = lps[i - 1];

            while (len > 0 && codepoints[i] != codepoints[len]) {
                len = lps[len - 1];
            }

            if (codepoints[i] == codepoints[len]) {
                len++;
            }

            lps[i] = len;
        }
        return lps;
    }

    /**
     * Read codepoint.
     *
     * @param reader reader
     * @return codepoint
     * @throws IOException IOE
     */
    private static int readCodepoint(BufferedReader reader) throws IOException {
        int first = reader.read();
        if (first == -1) {
            return -1;
        }

        char high = (char) first;
        if (Character.isHighSurrogate(high)) {
            int second = reader.read();
            char low = (char) second;
            if (second == -1 || !Character.isLowSurrogate(low)) {
                throw new IOException("Invalid surrogate pair");
            }
            return Character.toCodePoint(high, low);
        }

        if (Character.isLowSurrogate(high)) {
            throw new IOException("Unexpected low surrogate");
        }

        return first;
    }
}