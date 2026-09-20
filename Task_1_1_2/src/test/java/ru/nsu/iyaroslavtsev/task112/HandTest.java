package ru.nsu.iyaroslavtsev.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void testSimpleTake() {
        Hand user = new Hand("user");
        user.takeCard(new Card(Suit.SPADES, Rank.TEN));
        user.takeCard(new Card(Suit.SPADES, Rank.SEVEN));

        assertEquals(17, user.getScore());
        assertEquals(2, user.getCards().size());
        assertEquals(0, user.getAces());
    }

    @Test
    void testAceCollapse() {
        Hand user = new Hand("user");
        user.takeCard(new Card(Suit.SPADES, Rank.ACE));
        user.takeCard(new Card(Suit.SPADES, Rank.FIVE));
        assertEquals(16, user.getScore());
        assertEquals(1, user.getAces());

        user.takeCard(new Card(Suit.SPADES, Rank.KING));

        assertEquals(16, user.getScore());
        assertEquals(0, user.getAces());

        user.takeCard(new Card(Suit.HEARTS, Rank.ACE));
        user.takeCard(new Card(Suit.DIAMONDS, Rank.ACE));
        user.takeCard(new Card(Suit.CLUBS, Rank.ACE));

        assertEquals(19, user.getScore());
        assertEquals(0, user.getAces());
    }
}