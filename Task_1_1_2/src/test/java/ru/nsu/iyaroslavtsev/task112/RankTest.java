package ru.nsu.iyaroslavtsev.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTest {

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

    @Test
    void rankAmount() {
        assertEquals(13, Rank.values().length);
    }
}