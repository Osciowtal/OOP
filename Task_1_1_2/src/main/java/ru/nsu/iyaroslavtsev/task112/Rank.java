package ru.nsu.iyaroslavtsev.task112;

/**
 * enum that contains card Ranks' data.
 * int value: how many points a card of this rank costs.
 * String name: card name to display in console.
 * String symb: short part to display as a card sign (like "Q" for Queen and "2" for Two).
 */
public enum Rank {
    TWO(2, "Two", "2"),
    THREE(3, "Three", "3"),
    FOUR(4, "Four", "4"),
    FIVE(5, "Five", "5"),
    SIX(6, "Six", "6"),
    SEVEN(7, "Seven", "7"),
    EIGHT(8, "Eight", "8"),
    NINE(9, "Nine", "9"),
    TEN(10, "Ten", "10"),
    JACK(10, "Jack", "J"),
    QUEEN(10, "Queen", "Q"),
    KING(10, "King", "K"),
    ACE(11, "Ace", "A");

    private final int value;
    private final String name;
    private final String symb;

    private Rank(int value, String name, String symb) {
        this.value = value;
        this.name = name;
        this.symb = symb;
    }

    public int getVal() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getSymb() {
        return symb;
    }

}