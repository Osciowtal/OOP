package ru.nsu.iyaroslavtsev.task112;

import java.util.Objects;
import java.util.Scanner;

/**
 * Game class that implements all game's techniques.
 */
public class Game {

    public static Deck deck;

    public static boolean playerIsAlive = true;
    public static boolean dealerIsAlive = true;
    public static boolean blackjacked = false;

    public static int pwins = 0;
    public static int dwins = 0;
    public static int round = 0;

    public static Hand player;
    public static Hand dealer;

    /**
     * Initializes the deck and shuffles it.
     */
    public static void deckInit() {
        deck = new Deck();
        deck.shuffle();
    }

    /**
     * Displays welcome message.
     */
    public static void welcomeMessage() {
        System.out.println("Welcome to Blackjack");
        System.out.println("[0] = exit\n[1] = take a card\n[2] = stop");
    }

    /**
     * Sets all essential bool flags to the starting state.
     * Initializes player and dealer.
     * Deals two cards to both player and dealer.
     */
    public static void startGame() {
        playerIsAlive = true;
        dealerIsAlive = true;
        blackjacked = false;
        Main.playerstep = true;
        Main.dealerstep = true;

        player = new Hand("Player");
        dealer = new Hand("Dealer");

        System.out.printf("\n---- Round: %d -----", round);

        player.takeCard(deck.throwCard());
        player.takeCard(deck.throwCard());
        dealer.takeCard(deck.throwCard());
        dealer.takeCard(deck.throwCard());

        System.out.printf(player.dispplayCards());
        System.out.printf(dealer.dealerCards());

        System.out.printf("\n");
    }

    /**
     * End stats: shows round info (score, wins).
     */
    public static void endMessage() {
        System.out.printf("\n       You  | Dealer"
                        + "\nScore: %-4d | %-4d"
                        + "\nWins:  %-4d | %-4d",
                player.getScore(), dealer.getScore(),
                pwins, dwins);
    }

    /**
     * Checks if starting points of any person are equal to 21.
     * If so, sets blackjack flag as true.
     */
    public static void blackJacktion() {
        if (player.getScore() == 21) {
            dealerIsAlive = false;
            blackjacked = true;
        }
        if (dealer.getScore() == 21) {
            playerIsAlive = false;
            blackjacked = true;
        }

        if (blackjacked) {
            Main.playerstep = false;
            Main.dealerstep = false;

            if (playerIsAlive && !dealerIsAlive) {
                System.out.printf("\nYou've got a BLACKJACK!");
            } else if (!playerIsAlive && dealerIsAlive) {
                System.out.printf("\nDealer's got a BLACKJACK!");
                System.out.printf(dealer.dispplayCards());
            } else {
                System.out.printf("\nINSANE.\n* * * DOUBLE BLACKJACK! * * *");
            }
        }
    }

    /**
     * Acts according to user's choose.
     *
     * @param scanner - console value getter.
     */
    public static void playerStep(Scanner scanner) {
        System.out.printf("\n[1] Take. [2] Stop. [0] Exit.\n");
        int action = scanner.nextInt();
        switch (action) {
            case 0:
                scanner.close();
                System.out.printf("\nExit.");
                Main.activeRound = false;
                break;
            case 1:
                Card card = deck.throwCard();
                player.takeCard(card);
                System.out.printf("\nYou have got: %s", card.fullinfoCard());
                System.out.printf(player.dispplayCards());
                overflowCheck(player);
                break;
            case 2:
                System.out.printf("\nLets see...");
                Main.playerstep = false;
                break;
            default:
                System.out.printf("\nThere's no such option.");
                break;
        }
    }

    /**
     * Dealer mechanique - take cards until score is under 17.
     */
    public static void dealerStep() {
        System.out.printf("\nDealer's revealing his card:");
        System.out.printf(dealer.dispplayCards());

        while (dealer.getScore() < 17) {
            Card card = deck.throwCard();
            dealer.takeCard(card);
            System.out.printf("\nDealer has got: %s", card.fullinfoCard());
            System.out.printf(dealer.dispplayCards());
            overflowCheck(dealer);
        }
    }

    /**
     * Checks score overflow.
     *
     * @param who - who.
     */
    public static void overflowCheck(Hand who) {
        if (who.getScore() > 21) {
            System.out.printf("\nOverflow: ");
            if (Objects.equals(who.name, "Player")) {
                playerIsAlive = false;
                Main.dealerstep = false;
                Main.playerstep = false;
                System.out.printf("\nYou are defeated.");
            } else if (Objects.equals(who.name, "Dealer")) {
                dealerIsAlive = false;
                Main.playerstep = false;
                Main.dealerstep = false;
                System.out.printf("\nDealer is defeated.");
            }
        }
    }

    /**
     * Detects winner.
     */
    public static void detectWinner() {
        if (playerIsAlive && dealerIsAlive) {
            int pscore = player.getScore();
            int dscore = dealer.getScore();
            String relation;
            if (pscore > dscore) {
                dealerIsAlive = false;
                relation = ">";
            } else if (pscore < dscore) {
                playerIsAlive = false;
                relation = "<";
            } else {
                relation = "=";
            }
            System.out.printf("\nYour score %s Dealer's score", relation);
        }

        if (playerIsAlive && !dealerIsAlive) {
            pwins += 1;
        } else if (!playerIsAlive && dealerIsAlive) {
            dwins += 1;
        }
    }

    /**
     * Asks about another round.
     *
     * @param scanner - console value getter.
     */
    public static void roundAsk(Scanner scanner) {
        System.out.printf("\nAnother round?\n[1] Yes. [0] No.\n");
        int action = scanner.nextInt();
        switch (action) {
            case 0:
                Main.activeRound = false;
                break;
            case 1:
                round++;
                break;
            default:
                System.out.printf("\nThere is no such option.\nStarting next game by default.");
                round++;
                break;
        }
    }
}