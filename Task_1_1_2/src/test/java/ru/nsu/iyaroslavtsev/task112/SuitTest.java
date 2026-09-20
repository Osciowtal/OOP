package ru.nsu.iyaroslavtsev.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuitTest {

    @Test
    void properAttributes() {
        String expectName;
        String expectSymb;
        for (Suit s : Suit.values()) {
            switch (s) {
                case SPADES:
                    expectName = "Spades";
                    expectSymb = "♠";
                    break;
                case HEARTS:
                    expectName = "Hearts";
                    expectSymb = "♥";
                    break;
                case DIAMONDS:
                    expectName = "Diamonds";
                    expectSymb = "♦";
                    break;
                case CLUBS:
                    expectName = "Clubs";
                    expectSymb = "♣";
                    break;
                default:
                    expectName = "ERR";
                    expectSymb = "ERR";
                    break;
            }

            assertEquals(expectName, s.getName());
            assertEquals(expectSymb, s.getSymb());
        }
    }

    @Test
    void suitAmount() {
        assertEquals(4, Suit.values().length);
    }
}