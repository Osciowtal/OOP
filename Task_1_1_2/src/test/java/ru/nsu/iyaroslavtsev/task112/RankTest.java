package ru.nsu.iyaroslavtsev.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Test Rank enum.
 */
class RankTest {

    /**
     * Rank attributes (value, name, symbol).
     */
    @Test
    void properAttributes() {
        for (Rank r : Rank.values()) {

            int expectVal;
            if (r == Rank.JACK || r == Rank.QUEEN || r == Rank.KING) {
                expectVal = 10;
            } else if (r == Rank.ACE) {
                expectVal = 11;
            } else {
                expectVal = r.ordinal() + 2;
            }

            String expectName = r.getName().charAt(0) + r.getName().substring(1).toLowerCase();

            String expectSymb = switch (r) {
                case JACK -> "J";
                case QUEEN -> "Q";
                case KING -> "K";
                case ACE -> "A";
                default -> String.valueOf(expectVal);
            };

            assertEquals(expectVal, r.getVal());
            assertEquals(expectName, r.getName());
            assertEquals(expectSymb, r.getSymb());
        }
    }

    /**
     * There should be 13 ranks: from 2 up to 10 (9) + 3 pics (J, Q, K) + 1 ace.
     */
    @Test
    void rankAmount() {
        assertEquals(13, Rank.values().length);
    }
}