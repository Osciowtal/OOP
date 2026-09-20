package ru.nsu.iyaroslavtsev.task112;

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