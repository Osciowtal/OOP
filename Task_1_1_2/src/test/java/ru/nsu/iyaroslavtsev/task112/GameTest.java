package ru.nsu.iyaroslavtsev.task112;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        Main.activeRound = true;
        Main.playerstep = true;
        Main.dealerstep = true;
    }

    @Test
    void testDeckInit() {
        Game.deckInit();
        assertNotNull(Game.deck);
        assertFalse(Game.deck.getCards().isEmpty());
    }

    @Test
    void testWelcomeMessage() {
        assertDoesNotThrow(Game::welcomeMessage);
    }

    @Test
    void testStartGame() {
        Game.deckInit();
        Game.startGame();

        assertTrue(Game.playerIsAlive);
        assertTrue(Game.dealerIsAlive);
        assertFalse(Game.blackjacked);
        assertTrue(Main.playerstep);
        assertTrue(Main.dealerstep);

        assertNotNull(Game.player);
        assertNotNull(Game.dealer);
        assertEquals(2, Game.player.getCards().size());
        assertEquals(2, Game.dealer.getCards().size());
    }

    @Test
    void testEndMessage() {
        Game.player = new Hand("Player");
        Game.dealer = new Hand("Dealer");
        assertDoesNotThrow(Game::endMessage);
    }

    @Test
    void testBlackJacktion_PlayerHas21() {
        Game.player = mock(Hand.class);
        Game.dealer = mock(Hand.class);
        when(Game.player.getScore()).thenReturn(21);
        when(Game.dealer.getScore()).thenReturn(10);

        Game.blackJacktion();

        assertTrue(Game.blackjacked);
        assertFalse(Game.dealerIsAlive);
        assertTrue(Game.playerIsAlive);
        assertFalse(Main.playerstep);
        assertFalse(Main.dealerstep);
    }

    @Test
    void testBlackJacktion_DealerHas21() {
        Game.player = mock(Hand.class);
        Game.dealer = mock(Hand.class);
        when(Game.player.getScore()).thenReturn(15);
        when(Game.dealer.getScore()).thenReturn(21);
        when(Game.dealer.dispplayCards()).thenReturn("\nDealer's cards: [ 21 ]\n[A ♠] [10 ♣] ");

        Game.blackJacktion();

        assertTrue(Game.blackjacked);
        assertFalse(Game.playerIsAlive);
        assertTrue(Game.dealerIsAlive);
    }

    @Test
    void testBlackJacktion_DoubleBlackjack() {
        Game.player = mock(Hand.class);
        Game.dealer = mock(Hand.class);
        when(Game.player.getScore()).thenReturn(21);
        when(Game.dealer.getScore()).thenReturn(21);

        Game.blackJacktion();

        assertTrue(Game.blackjacked);
        assertFalse(Game.playerIsAlive);
        assertFalse(Game.dealerIsAlive);
    }

    @Test
    void testPlayerStep_Case0_Exit() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(0);

        Game.playerStep(mockScanner);

        assertFalse(Main.activeRound);
        verify(mockScanner).close();
    }

    @Test
    void testPlayerStep_Case1_TakeCard() {
        Game.player = new Hand("Player");

        Deck mockDeck = mock(Deck.class);
        Card mockCard = mock(Card.class);
        when(mockCard.getVal()).thenReturn(5);
        when(mockCard.fullinfoCard()).thenReturn("Five of Spades [5 ♠]");
        when(mockDeck.throwCard()).thenReturn(mockCard);
        Game.deck = mockDeck;

        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(1);

        Game.playerStep(mockScanner);

        assertEquals(1, Game.player.getCards().size());
        assertEquals(5, Game.player.getScore());
        assertTrue(Game.playerIsAlive);
    }

    @Test
    void testPlayerStep_Case1_Overflow() {
        Game.player = new Hand("Player");

        Deck mockDeck = mock(Deck.class);
        Card mockCard = mock(Card.class);
        when(mockCard.getVal()).thenReturn(25);
        when(mockCard.fullinfoCard()).thenReturn("Ace of Spades [A ♠]");
        when(mockDeck.throwCard()).thenReturn(mockCard);
        Game.deck = mockDeck;

        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(1);

        Game.playerStep(mockScanner);

        assertFalse(Game.playerIsAlive);
        assertFalse(Main.playerstep);
        assertFalse(Main.dealerstep);
    }

    @Test
    void testPlayerStep_Case2_Stop() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(2);

        Game.playerStep(mockScanner);

        assertFalse(Main.playerstep);
    }

    @Test
    void testPlayerStep_DefaultCase() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(999);

        Game.playerStep(mockScanner);

        assertTrue(Main.playerstep);
    }

    @Test
    void testDealerStep_TakesUntil17() {
        Game.dealer = new Hand("Dealer");

        Deck mockDeck = mock(Deck.class);
        Card mockCard = mock(Card.class);
        when(mockCard.getVal()).thenReturn(10);
        when(mockCard.fullinfoCard()).thenReturn("[10 ♣]");
        when(mockDeck.throwCard()).thenReturn(mockCard);
        Game.deck = mockDeck;

        Game.dealerStep();

        assertEquals(2, Game.dealer.getCards().size());
        assertEquals(20, Game.dealer.getScore());
        assertTrue(Game.dealerIsAlive);
    }

    @Test
    void testDealerStep_Overflow() {
        Game.dealer = new Hand("Dealer");

        Deck mockDeck = mock(Deck.class);
        Card mockCard = mock(Card.class);
        when(mockCard.getVal()).thenReturn(25);
        when(mockCard.fullinfoCard()).thenReturn("King of Clubs [K ♣]");
        when(mockDeck.throwCard()).thenReturn(mockCard);
        Game.deck = mockDeck;

        Game.dealerStep();

        assertFalse(Game.dealerIsAlive);
        assertFalse(Main.playerstep);
        assertFalse(Main.dealerstep);
    }

    @Test
    void testDetectWinner_PlayerWinsByPoints() {
        Game.player = mock(Hand.class);
        Game.dealer = mock(Hand.class);
        when(Game.player.getScore()).thenReturn(20);
        when(Game.dealer.getScore()).thenReturn(18);

        Game.detectWinner();

        assertEquals(1, Game.pwins);
        assertEquals(0, Game.dwins);
        assertFalse(Game.dealerIsAlive);
    }

    @Test
    void testDetectWinner_DealerWinsByPoints() {
        Game.player = mock(Hand.class);
        Game.dealer = mock(Hand.class);
        when(Game.player.getScore()).thenReturn(17);
        when(Game.dealer.getScore()).thenReturn(19);

        Game.detectWinner();

        assertEquals(0, Game.pwins);
        assertEquals(1, Game.dwins);
        assertFalse(Game.playerIsAlive);
    }

    @Test
    void testDetectWinner_Draw() {
        Game.player = mock(Hand.class);
        Game.dealer = mock(Hand.class);
        when(Game.player.getScore()).thenReturn(19);
        when(Game.dealer.getScore()).thenReturn(19);

        Game.detectWinner();

        assertEquals(0, Game.pwins);
        assertEquals(0, Game.dwins);
        assertTrue(Game.playerIsAlive);
        assertTrue(Game.dealerIsAlive);
    }

    @Test
    void testRoundAsk_Case0_No() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(0);

        Game.roundAsk(mockScanner);

        assertFalse(Main.activeRound);
        assertEquals(0, Game.round);
    }

    @Test
    void testRoundAsk_Case1_Yes() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(1);

        Game.roundAsk(mockScanner);

        assertTrue(Main.activeRound);
        assertEquals(1, Game.round);
    }

    @Test
    void testRoundAsk_DefaultCase() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(42);

        Game.roundAsk(mockScanner);

        assertTrue(Main.activeRound);
        assertEquals(1, Game.round);
    }
}
