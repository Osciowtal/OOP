package ru.nsu.iyaroslavtsev.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test Card enum.
 */
class CardTest {

    /**
     * Testing value, name and pic of cards.
     */
    @Test
    void cardsTest() {
        Card queenOfSpades = new Card(Suit.SPADES, Rank.QUEEN);
        Card twoOfDiamonds = new Card(Suit.DIAMONDS, Rank.TWO);
        Card aceOfHearts = new Card(Suit.HEARTS, Rank.ACE);

        assertEquals(10, queenOfSpades.getVal());
        assertEquals(2, twoOfDiamonds.getVal());
        assertEquals(11, aceOfHearts.getVal());

        String exp1 = "Queen of Spades [Q ♠]";
        String exp2 = "Two of Diamonds [2 ♦]";
        String exp3 = "Ace of Hearts [A ♥]";

        assertEquals(exp1, queenOfSpades.fullinfoCard());
        assertEquals(exp2, twoOfDiamonds.fullinfoCard());
        assertEquals(exp3, aceOfHearts.fullinfoCard());

        String pic1 = "[Q ♠]";
        String pic2 = "[2 ♦]";
        String pic3 = "[A ♥]";

        assertEquals(pic1, queenOfSpades.pseudoPic());
        assertEquals(pic2, twoOfDiamonds.pseudoPic());
        assertEquals(pic3, aceOfHearts.pseudoPic());

    }
}