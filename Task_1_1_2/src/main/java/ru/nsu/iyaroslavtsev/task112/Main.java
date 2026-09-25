package ru.nsu.iyaroslavtsev.task112;

import java.util.Scanner;

/**
 * Main class that implements Blackjack itself.
 */
public class Main {
    public static boolean activeRound = true;
    public static boolean playerstep = true;
    public static boolean dealerstep = true;

    /**
     * main.
     *
     * @param args args.
     */
    public static void main(String[] args) {

        Game.deckInit();
        Game.welcomeMessage();
        Scanner scanner = new Scanner(System.in);

        while (activeRound) {

            Game.startGame();
            Game.blackJacktion();

            if (!Game.blackjacked) {

                System.out.printf("\n--- Your turn ---");
                while (playerstep) {
                    Game.playerStep(scanner);
                }

                if (dealerstep) {
                    System.out.printf("\n--- Dealer's turn ---");
                    Game.dealerStep();
                }
            }

            Game.detectWinner();
            Game.endMessage();
            Game.roundAsk(scanner);
        }

        scanner.close();
        System.out.printf("\nExit.");
        return;
    }
}