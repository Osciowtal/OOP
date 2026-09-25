package ru.nsu.iyaroslavtsev.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.Scanner;


class GameTest {
    @BeforeEach
    void setUp() {
        Game.pwins = 0;
        Game.dwins = 0;
        Game.round = 0;
        Game.deck = null;
        Game.player = null;
        Game.dealer = null;
        Game.playerIsAlive = true;
        Game.dealerIsAlive = true;
        Game.blackjacked = false;
        Game.deckInit();
    }


    @Test
    void testDeckInit() {
        try (MockedConstruction<Deck> mockedDeck = mockConstruction(Deck.class)) {

            Game.deckInit();
            assertEquals(1, mockedDeck.constructed().size());

            Deck mockDeckInstance = mockedDeck.constructed().get(0);
            verify(mockDeckInstance).shuffle();

            assertNotNull(Game.deck);
        }
    }

    @Test
    void testPlayerStep_ActionTakeCard() {
        Game.player = new Hand("Player");

        Deck mockDeck = mock(Deck.class);
        Card mockCard = mock(Card.class);

        when(mockCard.getVal()).thenReturn(10);
        when(mockCard.pseudoPic()).thenReturn("[10 ♠]");

        when(mockDeck.throwCard()).thenReturn(mockCard);
        Game.deck = mockDeck;

        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(1);

        Game.playerStep(mockScanner);

        assertEquals(1, Game.player.getCards().size());
        assertSame(mockCard, Game.player.getCards().get(0));
        assertEquals(10, Game.player.getScore());
        assertTrue(Game.playerIsAlive);
    }

    @Test
    void testDetectWinner() {
        Hand mockPlayer = mock(Hand.class);
        Hand mockDealer = mock(Hand.class);

        when(mockPlayer.getScore()).thenReturn(20);
        when(mockDealer.getScore()).thenReturn(18);

        Game.player = mockPlayer;
        Game.dealer = mockDealer;
        Game.playerIsAlive = true;
        Game.dealerIsAlive = true;

        Game.detectWinner();

        assertEquals(1, Game.pwins);
        assertEquals(0, Game.dwins);
        assertFalse(Game.dealerIsAlive);
    }

    private <T> T mockedConstructionTarget(MockedConstruction<T> mocked, int index) {
        return mocked.constructed().get(index);
    }
}