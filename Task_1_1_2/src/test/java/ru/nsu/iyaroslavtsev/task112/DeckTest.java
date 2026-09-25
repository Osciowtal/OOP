package ru.nsu.iyaroslavtsev.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * Test Deck.
 */
class DeckTest {

    /**
     * Deck initialization testing.
     */
    @Test
    void testDeckInit() {
        Deck cards = new Deck();
        assertEquals(13 * 4, cards.getCards().size());
        assertNotNull(cards.getCards().get(0));
        assertNotNull(cards.getCards().get(23));
        assertNotNull(cards.getCards().get(51));
    }

    /**
     * Card throwing testing.
     */
    @Test
    void testThrowCard() {
        Deck cards = new Deck();
        Card card = cards.throwCard();
        assertNotNull(card);
        assertEquals(13 * 4 - 1, cards.getCards().size());
    }

    /**
     * Shuffle testing.
     */
    @Test
    void testShuffle() {
        Deck cards = new Deck();
        List<Card> before = cards.getCards();
        cards.shuffle();
        List<Card> after = cards.getCards();

        assertTrue(after.containsAll(before));
    }

    /**
     * Testing case if all cards are taken out - deck auto rebuild.
     */
    @Test
    void testAutoRefill() {
        Deck cards = new Deck();
        for (int i = 0; i < 52; i++) {
            assertNotNull(cards.throwCard());
        }

        assertTrue(cards.getCards().isEmpty());
        Card card = cards.throwCard();
        assertNotNull(card);
        assertEquals(13 * 4 - 1, cards.getCards().size());
    }
}