package ru.nsu.iyaroslavtsev.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;


class HandTest {

    private Hand playerHand;

    @BeforeEach
    void setUp() {
        playerHand = new Hand("Player");
    }

    @Test
    void testInitialization() {
        assertEquals("Player", playerHand.name);
        assertEquals(0, playerHand.getScore());
        assertEquals(0, playerHand.getAces());
        assertTrue(playerHand.getCards().isEmpty());
    }

    @Test
    void testTakeNormalCard() {
        Card mockCard = mock(Card.class);
        when(mockCard.getVal()).thenReturn(10);

        playerHand.takeCard(mockCard);

        assertEquals(10, playerHand.getScore());
        assertEquals(0, playerHand.getAces());
        assertEquals(1, playerHand.getCards().size());
        assertSame(mockCard, playerHand.getCards().get(0));
    }

    @Test
    void testTakeOneAceNoOverflow() {
        Card mockAce = mock(Card.class);
        when(mockAce.getVal()).thenReturn(11);

        playerHand.takeCard(mockAce);

        assertEquals(11, playerHand.getScore());
        assertEquals(1, playerHand.getAces());
    }

    @Test
    void testAceCollapsesOnOverflow() {
        Card mockAce1 = mock(Card.class);
        Card mockAce2 = mock(Card.class);
        when(mockAce1.getVal()).thenReturn(11);
        when(mockAce2.getVal()).thenReturn(11);

        playerHand.takeCard(mockAce1);
        playerHand.takeCard(mockAce2);

        assertEquals(12, playerHand.getScore());
        assertEquals(1, playerHand.getAces());
    }

    @Test
    void testDisplayCards() {
        Card mockCard1 = mock(Card.class);
        Card mockCard2 = mock(Card.class);

        when(mockCard1.getVal()).thenReturn(10);
        when(mockCard1.pseudoPic()).thenReturn("[10 ♠]");

        when(mockCard2.getVal()).thenReturn(11);
        when(mockCard2.pseudoPic()).thenReturn("[A ♦]");

        playerHand.takeCard(mockCard1);
        playerHand.takeCard(mockCard2);
        String expectedOutput = "\nPlayer's cards: [ 21 ]\n[10 ♠]  [A ♦]  ";
        assertEquals(expectedOutput, playerHand.dispplayCards());
    }

    @Test
    void testDealerCards() {
        Hand dealerHand = new Hand("Dealer");

        Card mockCard1 = mock(Card.class);
        Card mockCard2 = mock(Card.class);

        when(mockCard1.getVal()).thenReturn(10);
        when(mockCard1.pseudoPic()).thenReturn("[K ♣]");

        dealerHand.takeCard(mockCard1);
        dealerHand.takeCard(mockCard2);

        String expectedOutput = "\nDealer's cards: [ 10 + ? ]\n[K ♣]  [hidden]";
        assertEquals(expectedOutput, dealerHand.dealerCards());
    }
}