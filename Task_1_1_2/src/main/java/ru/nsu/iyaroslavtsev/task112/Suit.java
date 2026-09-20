package ru.nsu.iyaroslavtsev.task112;

/**
 * enum that contains Suit info for cards.
 * Name and little symbols for card representation (♠ ♥ ♦ ♣).
 */
public enum Suit {
    SPADES("Spades", "♠"),
    HEARTS("Hearts", "♥"),
    DIAMONDS("Diamonds",  "♦"),
    CLUBS("Clubs", "♣");

    private final String name;
    private final String symb;

    Suit(String name, String symb) {
        this.name = name;
        this.symb = symb;
    }

    public String getName() {
        return name;
    }

    public String getSymb() {
        return symb;
    }
}