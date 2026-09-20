package ru.nsu.iyaroslavtsev.task112;

import java.util.Scanner;

public class Main {
    private static int round = 0;
    private static int pwins = 0, dwins = 0;
    private static Hand player;
    private static Hand dealer;

    public static void endMessage() {
        System.out.printf("\n       You  | Dealer" +
                          "\nScore: %-4d | %-4d" +
                          "\nWins:  %-4d | %-4d",
                player.getScore(), dealer.getScore(),
                pwins, dwins);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        System.out.println("Welcome to Blackjack");
        System.out.println("[0] = exit\n[1] = take a card\n[2] = stop");

        while (true) { // Round
            System.out.printf("\n----- Round: %d -----", round);
            player = new Hand("Player");
            dealer = new Hand("Dealer");

            player.takeCard(deck.throwCard());
            player.takeCard(deck.throwCard());
            dealer.takeCard(deck.throwCard());
            dealer.takeCard(deck.throwCard());

            player.dispplayCards();
            dealer.dealerCards();
            System.out.printf("\n");

            boolean playerIsAlive = true;
            boolean dealerIsAlive = true;
            boolean blackjacked = false;

            if (dealer.getScore() == 21) {
                dealer.dispplayCards();
                playerIsAlive = false;
                blackjacked = true;
                System.out.printf("\nBlackjack!\nDealer's victory.");
            }
            if (player.getScore() == 21) {
                player.dispplayCards();
                dealerIsAlive = false;
                blackjacked = true;
                System.out.printf("\nBlackjack!\nYour victory.");
            }

            if (!blackjacked) {
                System.out.printf("\n--- Your turn ---");
            }

            while (playerIsAlive && !blackjacked) { // Player's step
                if (player.getScore() > 21) {
                    playerIsAlive = false;
                    System.out.printf("\nOverflow.\nDealer's victory.");
                    break;
                }

                System.out.printf("\n[1] Take. [2] Stop. [0] Exit.\n");
                int action = scanner.nextInt();
                if (action == 0) {
                    scanner.close();
                    System.out.printf("\nExit.");
                    return;
                } else if (action == 1) {
                    player.takeCard(deck.throwCard());
                    System.out.printf("\nYou have got: %s",
                            player.getCards().get(player.getCards().size() - 1).showCard());
                    player.dispplayCards();
                } else if (action == 2) {
                    System.out.printf("\nLets see...");
                    break;
                } else {
                    System.out.printf("\nThere is no such option.");
                }
            }

            if (playerIsAlive && dealerIsAlive) {
                System.out.printf("\n--- Dealer's turn ---");
                System.out.printf("\nDealer's revealing his card:");
                dealer.dispplayCards();

                while (dealer.getScore() < 17) {
                    dealer.takeCard(deck.throwCard());
                    System.out.printf("\nDealer has got: %s",
                            dealer.getCards().get(dealer.getCards().size() - 1).showCard());
                    dealer.dispplayCards();

                    if (dealer.getScore() > 21) {
                        dealerIsAlive = false;
                        System.out.printf("\nOverflow.\nYour victory.");
                        break;
                    }
                }
            }

            if (playerIsAlive && dealerIsAlive) {
                int playerScore = player.getScore();
                int dealerScore = dealer.getScore();
                if (playerScore > dealerScore) {
                    dealerIsAlive = false;
                    System.out.printf("\nYour score is greater that Dealer's one." +
                            "\nYour victory!");
                } else if (playerScore < dealerScore) {
                    playerIsAlive = false;
                    System.out.printf("\nYour score is lower that Dealer's one." +
                            "\nDealer's victory!");
                } else {
                    System.out.printf("\nScores are equal: draw.");
                }
            }

            if (playerIsAlive && !dealerIsAlive) {
                pwins += 1;
            } else if (!playerIsAlive && dealerIsAlive) {
                dwins += 1;
            }

            endMessage();
            System.out.printf("\nAnother round?\n[1] Yes. [0] No.\n");
            int action = scanner.nextInt();
            switch (action) {
                case 0:
                    scanner.close();
                    return;
                case 1:
                    round++;
            }
        }
    }
}