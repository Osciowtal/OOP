package ru.nsu.iyaroslavtsev.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void testPlayerStepTakeCard() {
        try (MockedConstruction<Hand> mockedHand = mockConstruction(Hand.class)) {

            Deck mockDeck = mock(Deck.class);
            Card mockCard = mock(Card.class);

            when(mockDeck.throwCard()).thenReturn(mockCard);

            Game.deck = mockDeck;

            Scanner mockScanner = mock(Scanner.class);
            when(mockScanner.nextInt()).thenReturn(1);

            Game.player = new Hand("Player");

            Hand mockPlayerHand = mockedConstructionTarget(mockedHand, 0);
            when(mockPlayerHand.getScore()).thenReturn(15);

            Game.playerStep(mockScanner);

            verify(mockPlayerHand).takeCard(mockCard);
            assertTrue(Game.playerIsAlive);
        }
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